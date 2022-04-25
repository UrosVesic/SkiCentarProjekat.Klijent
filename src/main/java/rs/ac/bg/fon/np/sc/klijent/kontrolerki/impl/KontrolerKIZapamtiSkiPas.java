/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.commonlib.domen.StavkaSkiPasa;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleStavkeSkiPasa;
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.ZapamtiSkiPasForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIZapamtiSkiPas extends OpstiKontrolerKI {

    ZapamtiSkiPasForma zspf;

    public KontrolerKIZapamtiSkiPas(OpstaEkranskaForma oef) {
        this.oef = oef;
        zspf = (ZapamtiSkiPasForma) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.addProperty("ukupnaCena", zspf.getTxtUkupnaCena().getText());
        obj.addProperty("imePrezimeKupca", zspf.getTxtImePrezimeKupca().getText());
        obj.addProperty("datumIzdavanja", zspf.getTxtDatumIzdavanja().getText());
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) zspf.getTblStavkeSkiPasa().getModel();
        JsonArray arr = new JsonArray();
        for (StavkaSkiPasa stavkaSkiPasa : model.getSkiPas().getStavkeSkiPasa()) {
            JsonObject stavkaObj = new JsonObject();
            JsonObject skiKartaObj = (JsonObject) gson.toJsonTree(stavkaSkiPasa.getSkiKarta());
            JsonObject skiPasObj = new JsonObject();
            skiPasObj.addProperty("sifraSkiPasa", stavkaSkiPasa.getSkiPas().getSifraSkiPasa());
            stavkaObj.add("skiKarta", skiKartaObj);
            stavkaObj.add("skiPas", skiPasObj);
            stavkaObj.addProperty("vrednostStavke", stavkaSkiPasa.getVrednostStavke());
            stavkaObj.addProperty("pocetakVazenja", sdf.format(stavkaSkiPasa.getPocetakVazenja()));
            stavkaObj.addProperty("zavrsetakVazenja", sdf.format(stavkaSkiPasa.getZavrsetakVazenja()));
            arr.add(stavkaObj);
        }
        obj.add("stavkeSkiPasa", arr);
        objekat = gson.toJson(obj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(objekat, JsonObject.class);
        zspf.getTxtSifraSkiPasa().setText(obj.get("sifraSkiPasa").getAsString());
    }

    public void pripremiTabelu() {
        zspf.getTblStavkeSkiPasa().setModel(new ModelTabeleStavkeSkiPasa(new SkiPas(), this));
        Gson gson = new Gson();
        try {
            soUcitajListuSkiKarata();
            niz = gson.fromJson(objekat, SkiKarta[].class);
            pripremiKomboboks();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Neuspesno ucitavanje liste ski centara", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox cmbSkiCentri = new JComboBox(niz);

        TableColumn tcSkiCentar = zspf.getTblStavkeSkiPasa().getColumnModel().getColumn(3);
        tcSkiCentar.setCellEditor(new DefaultCellEditor(cmbSkiCentri));

    }

    private void pripremiKomboboks() {
        zspf.getCmbSkiKarte().setModel(new DefaultComboBoxModel(niz));
    }

    public void dodajStavkuUTabelu() {
        //SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) zspf.getTblStavkeSkiPasa().getModel();
        SkiPas skiPas = model.getSkiPas();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            skiPas.setDatumIzdavanja(sdf.parse(zspf.getTxtDatumIzdavanja().getText()));
            StavkaSkiPasa stavka = new StavkaSkiPasa();
            stavka.setPocetakVazenja(sdf.parse(zspf.getTxtPocetakVazenja().getText()));
            stavka.setSkiKarta((SkiKarta) zspf.getCmbSkiKarte().getSelectedItem());
            stavka.setZavrsetakVazenja(stavka.generisiDatumZavrsetka());
            zspf.getTxtZavrsetakVazenja().setText(sdf.format(stavka.getZavrsetakVazenja()));
            stavka.setSkiPas(skiPas);
            if (!zspf.getTxtVrednostStavke().getText().equals("")) {
                stavka.setVrednostStavke(new BigDecimal(zspf.getTxtVrednostStavke().getText()));
            } else {
                stavka.setVrednostStavke(BigDecimal.ZERO);
            }
            model.dodaj(stavka);
            zspf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(zspf, "Datum mora biti unesen u formatu dd.MM.gggg");
            Logger.getLogger(ZapamtiSkiPasForma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(zspf, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String postaviCenu(List<StavkaSkiPasa> stavkeSkiPasa) {
        BigDecimal cena = new BigDecimal(0);
        for (StavkaSkiPasa stavkaSkiPasa : stavkeSkiPasa) {
            //cena += stavkaSkiPasa.getVrednostStavke().intValue();
            cena = cena.add(stavkaSkiPasa.getVrednostStavke());
        }
        return cena + "";
    }

    public void ObrisiStavku() {
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) zspf.getTblStavkeSkiPasa().getModel();
        if (zspf.getTblStavkeSkiPasa().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(zspf, "Izaberite red u tabeli");
        } else {
            model.obrisi(zspf.getTblStavkeSkiPasa().getSelectedRow());
        }
    }

    @Override
    public void promeniCenu() {
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) zspf.getTblStavkeSkiPasa().getModel();
        zspf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
    }

}
