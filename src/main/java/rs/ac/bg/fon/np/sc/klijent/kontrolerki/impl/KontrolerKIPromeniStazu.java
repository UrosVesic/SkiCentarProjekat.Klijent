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
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonlib.domen.Staza;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.staza.PromeniStazuForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIPromeniStazu extends OpstiKontrolerKI {

    Staza staza;
    PromeniStazuForma psf;

    public KontrolerKIPromeniStazu(OpstaEkranskaForma oef, Staza staza) {
        this.staza = staza;
        this.oef = oef;
        psf = (PromeniStazuForma) oef;
    }

    public Staza getStaza() {
        return staza;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.addProperty("idStaze", psf.getTxtSifraStaze().getText());
        obj.addProperty("brojStaze", psf.getTxtBrojStaze().getText());
        obj.addProperty("nazivStaze", psf.getTxtNazivStaze().getText());
        obj.addProperty("tipStaze", String.valueOf(psf.getCmbTipStaze().getSelectedItem()));
        JsonObject obj1 = (JsonObject) gson.toJsonTree(psf.getCmbSkiCentar().getSelectedItem());
        obj.add("skiCentar", obj1);
        objekat = gson.toJson(obj);
        staza = gson.fromJson(objekat, Staza.class);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(objekat, JsonObject.class);
        psf.getTxtSifraStaze().setText(obj.get("idStaze").getAsLong() + "");
    }

    public void pripremiKomboBox() {
        Gson gson = new Gson();
        psf.getCmbSkiCentar().removeAllItems();
        try {
            soUcitajListuSkiCentara();
            niz = gson.fromJson(objekat, SkiCentar[].class);
            psf.getCmbSkiCentar().setModel(new DefaultComboBoxModel(niz));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
        psf.getCmbSkiCentar().setSelectedItem(staza.getSkiCentar());
    }

    public void pripremiFormu() {
        psf.getTxtSifraStaze().setText(staza.getIdStaze() + "");
        psf.getTxtBrojStaze().setText(staza.getBrojStaze());
        psf.getTxtNazivStaze().setText(staza.getNazivStaze());
        psf.getCmbTipStaze().setSelectedItem(staza.getTipStaze());
    }

}
