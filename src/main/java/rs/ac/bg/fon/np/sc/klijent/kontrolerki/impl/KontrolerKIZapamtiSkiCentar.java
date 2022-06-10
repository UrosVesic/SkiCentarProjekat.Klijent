/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skicentar.ZapamtiSkiCentarForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIZapamtiSkiCentar extends OpstiKontrolerKI {

    ZapamtiSkiCentarForma zscf;

    public KontrolerKIZapamtiSkiCentar(OpstaEkranskaForma oef) {
        this.oef = oef;
        zscf = (ZapamtiSkiCentarForma) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        obj = new JsonObject();
        JsonObject domObj = new JsonObject();
        domObj.addProperty("nazivSkiCentra", zscf.getTxtNazivSkiCentra().getText());
        domObj.addProperty("nazivPlanine", zscf.getTxtNazivPlanine().getText());
        domObj.addProperty("radnoVreme", zscf.getTxtRadnoVreme().getText());
        obj.add("parametar", domObj);
        //jsonString = gson.toJson(domObj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(jsonString, JsonObject.class);
        zscf.getTxtSifraSkiCentra().setText(obj.get("sifraSkiCentra").getAsLong() + "");
    }

}
