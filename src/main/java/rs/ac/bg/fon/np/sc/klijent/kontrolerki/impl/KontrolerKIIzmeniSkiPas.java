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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.IzmeniSkiPasForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.ZapamtiSkiPasForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIIzmeniSkiPas extends OpstiKontrolerKI {

    IzmeniSkiPasForma ispf;

    public KontrolerKIIzmeniSkiPas(OpstaEkranskaForma oef) {
        this.oef = oef;
        ispf = (IzmeniSkiPasForma) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
        JsonObject obj = new JsonObject();
        obj.addProperty("sifraSkiPasa", ispf.getTxtSifraSkiPasa().getText());
        obj.addProperty("ukupnaCena", ispf.getTxtUkupnaCena().getText().equals("") ? null : ispf.getTxtUkupnaCena().getText());
        obj.addProperty("imePrezimeKupca", ispf.getTxtImePrezimeKupca().getText());
        obj.addProperty("datumIzdavanja", ispf.getTxtDatumIzdavanja().getText().equals("") ? null : ispf.getTxtDatumIzdavanja().getText());
        obj.addProperty("sezona", ispf.getTxtSezona().getText());
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        JsonArray arr = (JsonArray) gson.toJsonTree(model.getSkiPas().getStavkeSkiPasa());
        obj.add("stavkeSkiPasa", arr);
        jsonString = gson.toJson(obj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MMM dd, yyyy").create();
        SkiPas skiPas = gson.fromJson(jsonString, SkiPas.class);
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        model.setSkiPas(skiPas);
        ispf.getTxtSifraSkiPasa().setText(skiPas.getSifraSkiPasa() + "");
        ispf.getTxtImePrezimeKupca().setText(skiPas.getImePrezimeKupca());
        ispf.getTxtUkupnaCena().setText(skiPas.getUkupnaCena() + "");
        ispf.getTxtDatumIzdavanja().setText(sdf.format(skiPas.getDatumIzdavanja()));
        ispf.getTxtSezona().setText(skiPas.getSezona());
    }

    public void pripremiFormu() {
        ispf.getTblStavkeSkiPasa().setModel(new ModelTabeleStavkeSkiPasa(new SkiPas(), this));
        Gson gson = new Gson();
        try {
            soUcitajListuSkiKarata();
            niz = gson.fromJson(jsonString, SkiKarta[].class);
            pripremiKomboBoks();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Neuspesno ucitavanje liste ski centara", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox cmbSkiCentri = new JComboBox(niz);

        TableColumn tcSkiCentar = ispf.getTblStavkeSkiPasa().getColumnModel().getColumn(3);
        tcSkiCentar.setCellEditor(new DefaultCellEditor(cmbSkiCentri));
    }

    public void pripremiKomboBoks() {
        ispf.getCmbSkiKarte().setModel(new DefaultComboBoxModel(niz));
    }

    public void dodajStavkuUTabelu() {
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        SkiPas skiPas = model.getSkiPas();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            skiPas.setDatumIzdavanja(sdf.parse(ispf.getTxtDatumIzdavanja().getText()));
            skiPas.setSezona(ispf.getTxtSezona().getText());
            StavkaSkiPasa stavka = new StavkaSkiPasa();
            stavka.setPocetakVazenja(sdf.parse(ispf.getTxtPocetakVazenja().getText()));
            stavka.setSkiKarta((SkiKarta) ispf.getCmbSkiKarte().getSelectedItem());
            stavka.setZavrsetakVazenja(stavka.generisiDatumZavrsetka());
            ispf.getTxtZavrsetakVazenja().setText(sdf.format(stavka.getZavrsetakVazenja()));
            stavka.setSkiPas(skiPas);
            if (!ispf.getTxtVrednostStavke().getText().equals("")) {
                stavka.setVrednostStavke(new BigDecimal(ispf.getTxtVrednostStavke().getText()));
            } else {
                stavka.setVrednostStavke(BigDecimal.ZERO);
            }
            model.dodaj(stavka);
            ispf.getTxtUkupnaCena().setText(promeniCenu(model.getSkiPas().getStavkeSkiPasa()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(ispf, "Datum mora biti unesen u formatu dd.MM.gggg");
            Logger.getLogger(ZapamtiSkiPasForma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ispf, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public String promeniCenu(List<StavkaSkiPasa> stavkeSkiPasa) {
        BigDecimal cena = new BigDecimal(0);
        for (StavkaSkiPasa stavkaSkiPasa : stavkeSkiPasa) {
            cena = cena.add(stavkaSkiPasa.getVrednostStavke());
        }
        return cena + "";
    }

    @Override
    public void promeniCenu() {
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        ispf.getTxtUkupnaCena().setText(promeniCenu(model.getSkiPas().getStavkeSkiPasa()));
    }
    
    
    
    public void ObrisiStavku() {
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        if (ispf.getTblStavkeSkiPasa().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(ispf, "Izaberite red u tabeli");
        } else {
            model.obrisi(ispf.getTblStavkeSkiPasa().getSelectedRow());
        }
    }

}
