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
import com.google.gson.JsonParser;
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
import rs.ac.bg.fon.np.sc.commonlib.domen.Kupac;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.commonlib.domen.StavkaSkiPasa;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Operacije;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.editori.DateCellEditor;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleStavkeSkiPasa;
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.ZapamtiSkiPasForma;
import rs.ac.bg.fon.np.sc.klijent.komunikacija.Komunikacija;
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
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("ukupnaCena", (zspf.getTxtUkupnaCena().getText().isEmpty() ? null : zspf.getTxtUkupnaCena().getText()));
        Kupac kupac = (Kupac) zspf.getCmbKupci().getSelectedItem();
        JsonObject jsonKupac = (JsonObject) gson.toJsonTree(kupac);
        obj1.add("kupac", jsonKupac);
        obj1.addProperty("datumIzdavanja", (zspf.getJdcDatumIzdavanja().getDate() == null ? null : sdf.format(zspf.getJdcDatumIzdavanja().getDate())));
        obj1.addProperty("sezona", (zspf.getTxtSezona().getText().isEmpty() ? null : zspf.getTxtSezona().getText()));
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) zspf.getTblStavkeSkiPasa().getModel();
        JsonArray arr = (JsonArray) gson.toJsonTree(model.getSkiPas().getStavkeSkiPasa());
        obj1.add("stavkeSkiPasa", arr);
        obj.add("parametar", obj1);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(jsonString, JsonObject.class);
        zspf.getTxtSifraSkiPasa().setText(obj.get("sifraSkiPasa").getAsString());
    }

    public void pripremiFormu() {
        AutoCompleteDecorator.decorate(zspf.getCmbKupci());
        AutoCompleteDecorator.decorate(zspf.getCmbSkiKarte());
        zspf.getTblStavkeSkiPasa().setModel(new ModelTabeleStavkeSkiPasa(new SkiPas(), this));
        Gson gson = new Gson();
        try {
            soUcitajListuSkiKarata();
            niz = gson.fromJson(jsonString, SkiKarta[].class);
            pripremiKomboboksSkiKarte();
            JComboBox cmbSkiCentri = new JComboBox(niz);

            TableColumn tcSkiCentar = zspf.getTblStavkeSkiPasa().getColumnModel().getColumn(3);
            tcSkiCentar.setCellEditor(new DefaultCellEditor(cmbSkiCentri));
            soUcitajListuKupaca();
            niz = gson.fromJson(jsonString, Kupac[].class);
            pripremiKomboboksKupci();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        zspf.getTxtVrednostStavke().setText(((SkiKarta) zspf.getCmbSkiKarte().getSelectedItem()).getCenaSkiKarte() + "");

        TableColumn tcPocetakVazenja = zspf.getTblStavkeSkiPasa().getColumnModel().getColumn(1);
        DateCellEditor dateCellEditor = new DateCellEditor();
        dateCellEditor.setMinSelectableDate(zspf.getJdcDatumIzdavanja().getDate());
        tcPocetakVazenja.setCellEditor(dateCellEditor);
    }

    private void pripremiKomboboksSkiKarte() {
        zspf.getCmbSkiKarte().setModel(new DefaultComboBoxModel(niz));
    }

    private void pripremiKomboboksKupci() {
        zspf.getCmbKupci().setModel(new DefaultComboBoxModel(niz));
    }

    public void dodajStavkuUTabelu() {
        //SkiPas skiPas = (SkiPas) odo;
        ModelTabeleStavkeSkiPasa model = (ModelTabeleStavkeSkiPasa) zspf.getTblStavkeSkiPasa().getModel();
        SkiPas skiPas = model.getSkiPas();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            skiPas.setDatumIzdavanja(zspf.getJdcDatumIzdavanja().getDate());
            skiPas.setSezona(zspf.getTxtSezona().getText());
            StavkaSkiPasa stavka = new StavkaSkiPasa();
            stavka.setPocetakVazenja(zspf.getJdcPocetakVazenja().getDate());
            stavka.setSkiKarta((SkiKarta) zspf.getCmbSkiKarte().getSelectedItem());
            stavka.setZavrsetakVazenja(stavka.generisiDatumZavrsetka());
            zspf.getJdcZavrsetakVazenja().setDate(stavka.getZavrsetakVazenja());
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

    public void izracunajSezonu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date izdavanje = zspf.getJdcDatumIzdavanja().getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(izdavanje);
        int mesec = calendar.get(Calendar.MONTH);
        int godina = calendar.get(Calendar.YEAR);
        if (mesec > 6) {
            zspf.getTxtSezona().setText(godina + "/" + (godina + 1));
        } else {
            zspf.getTxtSezona().setText((godina - 1) + "/" + godina);
        }
    }

    public void postaviDatumIzdavanja() {
        zspf.getJdcDatumIzdavanja().setDate(zspf.getJdcPocetakVazenja().getDate());
    }

    public void ograniciDatumStavki() {
        zspf.getJdcPocetakVazenja().setMinSelectableDate(zspf.getJdcDatumIzdavanja().getDate());
        DateCellEditor dateCellEditor = (DateCellEditor) zspf.getTblStavkeSkiPasa().getColumnModel().getColumn(1).getCellEditor();
        dateCellEditor.setMinSelectableDate(zspf.getJdcDatumIzdavanja().getDate());
    }

    public void dodajKupca() throws Exception {
        String kupac = zspf.getTxtDodajKupca().getText();
        String[] kupacNiz = kupac.split("\n");
        if (kupacNiz.length < 3) {
            throw new Exception("Niste uneli sve podatke");
        }
        Kupac kupacObj = new Kupac(0, kupacNiz[0], kupacNiz[1], kupacNiz[2]);
        try {
            soZapamtiKupca(kupacObj);
            zspf.getCmbKupci().addItem(kupacObj);
            zspf.getCmbKupci().setSelectedItem(kupacObj);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(zspf, "Sistem ne moze da sacuva kupca: " + ex.getMessage(), "greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void soZapamtiKupca(Kupac kupac) throws Exception {
        obj = new JsonObject();
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("brojLK", kupac.getBrojLK());
        obj1.addProperty("ime", kupac.getIme());
        obj1.addProperty("prezime", kupac.getPrezime());
        obj1.add("kupac", gson.toJsonTree(obj1));
        KonvertujOperacijuUJson(Operacije.ZAPAMTI_KUPCA);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                 jsonString = gson.toJson(element.getAsJsonObject().get("Popunime"));
            } else {
                throw gson.fromJson(element.getAsJsonObject().get("exception"), Exception.class);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    protected void KonvertujOperacijuUJson(int op) {
        super.KonvertujOperacijuUJson(op); //To change body of generated methods, choose Tools | Templates.
    }
    
          

}
