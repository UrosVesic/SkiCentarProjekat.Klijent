/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skikarta.ZapamtiSkiKartuForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIZapamtiSkiKartu extends OpstiKontrolerKI {

    public KontrolerKIZapamtiSkiKartu(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        ZapamtiSkiKartuForma zskf = (ZapamtiSkiKartuForma) oef;
        obj = new JsonObject();
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("vrstaSkiKarte", String.valueOf(zskf.getCmbVrstaSkiKarte().getSelectedItem()));
        if (!zskf.getTxtCenaSkiKarte().getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(oef, "Cena mora biti uneta kao broj");
            obj1.addProperty("cenaSkiKarte", (String) null);
        } else {
            obj1.addProperty("cenaSkiKarte", zskf.getTxtCenaSkiKarte().getText().equals("") ? null : zskf.getTxtCenaSkiKarte().getText());
        }

        obj1.add("skiCentar", gson.toJsonTree(zskf.getCmbSkiCentar().getSelectedItem()));
        obj.add("parametar", obj1);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        SkiKarta skiKarta = gson.fromJson(jsonString, SkiKarta.class);
        ZapamtiSkiKartuForma zskf = (ZapamtiSkiKartuForma) oef;
        zskf.getTxtSifraSkiKarte().setText(skiKarta.getSifraSkiKarte() + "");
    }

    public void pripremiKombobox() {
        ZapamtiSkiKartuForma zskf = (ZapamtiSkiKartuForma) oef;
        Gson gson = new Gson();
        zskf.getCmbSkiCentar().removeAllItems();
        try {
            soUcitajListuSkiCentara();
            niz = gson.fromJson(jsonString, SkiCentar[].class);
            zskf.getCmbSkiCentar().setModel(new DefaultComboBoxModel(niz));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

}
