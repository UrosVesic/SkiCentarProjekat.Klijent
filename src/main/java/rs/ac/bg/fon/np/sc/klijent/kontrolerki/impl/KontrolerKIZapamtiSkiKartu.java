/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np.sc.commonlib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Odgovor;
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
        Gson gson = new Gson();
        ZapamtiSkiKartuForma zskf = (ZapamtiSkiKartuForma) oef;
        JsonObject obj = new JsonObject();
        JsonObject obj1 = new JsonObject();
        obj.addProperty("vrstaSkiKarte", String.valueOf(zskf.getCmbVrstaSkiKarte().getSelectedItem()));
        obj.addProperty("cenaSkiKarte", zskf.getTxtCenaSkiKarte().getText());
        obj1 = (JsonObject) gson.toJsonTree(zskf.getCmbSkiCentar().getSelectedItem());
        obj.add("skiCentar", obj1);
        objekat = new Gson().toJson(obj);
        System.out.println(objekat);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        SkiKarta skiKarta = gson.fromJson(objekat, SkiKarta.class);
        ZapamtiSkiKartuForma zskf = (ZapamtiSkiKartuForma) oef;
        zskf.getTxtSifraSkiKarte().setText(skiKarta.getSifraSkiKarte() + "");
    }

    public void pripremiKombobox() {
        ZapamtiSkiKartuForma zskf = (ZapamtiSkiKartuForma) oef;
        Gson gson = new Gson();
        zskf.getCmbSkiCentar().removeAllItems();
        try {
            soUcitajListuSkiCentara();
            niz = gson.fromJson(objekat, SkiCentar[].class);
            zskf.getCmbSkiCentar().setModel(new DefaultComboBoxModel(niz));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

}
