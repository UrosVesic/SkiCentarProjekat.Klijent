/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import rs.ac.bg.fon.np.sc.commonLib.domen.Kupac;
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
        obj.addProperty("ukupnaCena", ispf.getTxtUkupnaCena().getText());
        JsonElement jsonKupac = gson.toJsonTree((Kupac) ispf.getCmbKupci().getSelectedItem());
        obj.add("kupac", jsonKupac);
        obj.addProperty("datumIzdavanja", sdf.format(ispf.getJdcDatumIzdavanje().getDate()));
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
        ispf.getCmbKupci().setSelectedItem(skiPas.getKupac());
        ispf.getTxtUkupnaCena().setText(skiPas.getUkupnaCena() + "");
        ispf.getJdcDatumIzdavanje().setDate(skiPas.getDatumIzdavanja());
        ispf.getTxtSezona().setText(skiPas.getSezona());
    }

    public void pripremiFormu() {
        AutoCompleteDecorator.decorate(ispf.getCmbKupci());
        AutoCompleteDecorator.decorate(ispf.getCmbSkiKarte());
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
            skiPas.setDatumIzdavanja(ispf.getJdcDatumIzdavanje().getDate());
            skiPas.setSezona(ispf.getTxtSezona().getText());
            StavkaSkiPasa stavka = new StavkaSkiPasa();
            stavka.setPocetakVazenja(ispf.getJdcPocetakVazenja().getDate());
            stavka.setSkiKarta((SkiKarta) ispf.getCmbSkiKarte().getSelectedItem());
            stavka.setZavrsetakVazenja(stavka.generisiDatumZavrsetka());
            ispf.getJdcZavrsetakVazenja().setDate(stavka.getZavrsetakVazenja());
            stavka.setSkiPas(skiPas);
            if (!ispf.getTxtVrednostStavke().getText().equals("")) {
                stavka.setVrednostStavke(new BigDecimal(ispf.getTxtVrednostStavke().getText()));
            } else {
                stavka.setVrednostStavke(BigDecimal.ZERO);
            }
            model.dodaj(stavka);
            ispf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(ispf, "Datum mora biti unesen u formatu dd.MM.gggg");
            Logger.getLogger(ZapamtiSkiPasForma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ispf, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String postaviCenu(List<StavkaSkiPasa> stavkeSkiPasa) {
        BigDecimal cena = new BigDecimal(0);
        for (StavkaSkiPasa stavkaSkiPasa : stavkeSkiPasa) {
            cena = cena.add(stavkaSkiPasa.getVrednostStavke());
        }
        return cena + "";
    }

    @Override
    public void promeniCenu() {
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        ispf.getTxtUkupnaCena().setText(postaviCenu(model.getSkiPas().getStavkeSkiPasa()));
    }

    public void ObrisiStavku() {
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) ispf.getTblStavkeSkiPasa().getModel();
        if (ispf.getTblStavkeSkiPasa().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(ispf, "Izaberite red u tabeli");
        } else {
            model.obrisi(ispf.getTblStavkeSkiPasa().getSelectedRow());
        }
    }

    public void izracunajSezonu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date izdavanje = ispf.getJdcDatumIzdavanje().getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(izdavanje);
        int mesec = calendar.get(Calendar.MONTH);
        int godina = calendar.get(Calendar.YEAR);
        if (mesec > 6) {
            ispf.getTxtSezona().setText(godina + "/" + (godina + 1));
        } else {
            ispf.getTxtSezona().setText((godina - 1) + "/" + godina);
        }
    }

    public void postaviDatumIzdavanja() {
        ispf.getJdcDatumIzdavanje().setDate(ispf.getJdcPocetakVazenja().getDate());
    }

    public void ograniciDatumStavki() {
        ispf.getJdcPocetakVazenja().setMinSelectableDate(ispf.getJdcDatumIzdavanje().getDate());
    }

}
