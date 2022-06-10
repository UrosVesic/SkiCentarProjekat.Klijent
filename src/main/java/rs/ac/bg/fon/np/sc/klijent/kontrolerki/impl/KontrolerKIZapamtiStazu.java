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
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.staza.ZapamtiStazuForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIZapamtiStazu extends OpstiKontrolerKI {

    ZapamtiStazuForma zsf;

    public KontrolerKIZapamtiStazu(OpstaEkranskaForma oef) {
        this.oef = oef;
        zsf = (ZapamtiStazuForma) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("brojStaze", zsf.getTxtBrojStaze().getText());
        obj1.addProperty("nazivStaze", zsf.getTxtNazivStaze().getText());
        obj1.addProperty("tipStaze", String.valueOf(zsf.getCmbTipStaze().getSelectedItem()));
        obj1.add("skiCentar", gson.toJsonTree(zsf.getCmbSkiCentar().getSelectedItem()));
        obj = new JsonObject();
        obj.add("parametar", obj1);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(jsonString, JsonObject.class);
        zsf.getTxtSifraStaze().setText(obj.get("idStaze").getAsLong() + "");
    }

    public void pripremiKomboBox() {
        Gson gson = new Gson();
        zsf.getCmbSkiCentar().removeAllItems();
        try {
            soUcitajListuSkiCentara();
            niz = gson.fromJson(jsonString, SkiCentar[].class);
            zsf.getCmbSkiCentar().setModel(new DefaultComboBoxModel(niz));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

}
