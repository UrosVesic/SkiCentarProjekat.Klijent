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
import rs.ac.bg.fon.np.sc.klijent.forme.zicara.ZapamtiZicaruForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIZapamtiZicaru extends OpstiKontrolerKI {

    ZapamtiZicaruForma zzf;

    public KontrolerKIZapamtiZicaru(OpstaEkranskaForma oef) {
        this.oef = oef;
        zzf = (ZapamtiZicaruForma) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.addProperty("nazivZicare", zzf.getTxtNazivZicare().getText());
        obj.addProperty("radnoVreme", zzf.getTxtRadnoVreme().getText());
        obj.addProperty("kapacitet", (zzf.getTxtKapacitet().getText().isEmpty() ? null : Integer.parseInt(zzf.getTxtKapacitet().getText())));
        obj.addProperty("UFunkciji", (String) zzf.getCmbUfunkciji().getSelectedItem());
        JsonObject skiCentar = (JsonObject) gson.toJsonTree(zzf.getCmbSkiCentri().getSelectedItem());
        obj.add("skiCentar", skiCentar);
        jsonString = gson.toJson(obj);

    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(jsonString, JsonObject.class);
        zzf.getTxtSifraZicare().setText(obj.get("SifraZicare").getAsLong() + "");
    }

    public void pripremiKomboBoks() {
        Gson gson = new Gson();
        zzf.getCmbSkiCentri().removeAllItems();
        try {
            soUcitajListuSkiCentara();
            niz = gson.fromJson(jsonString, SkiCentar[].class);
            zzf.getCmbSkiCentri().setModel(new DefaultComboBoxModel(niz));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

}
