/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skicentar.PromeniSkiCentarForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIPromeniSkiCentar extends OpstiKontrolerKI {

    PromeniSkiCentarForma pscf;

    public KontrolerKIPromeniSkiCentar(OpstaEkranskaForma oef) {
        this.oef = oef;
        pscf = (PromeniSkiCentarForma) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        JsonObject obj1= new JsonObject();
        if (!pscf.getTxtSifraSkiCentra().getText().isEmpty()) {
            obj1.addProperty("sifraSkiCentra", Long.parseLong(pscf.getTxtSifraSkiCentra().getText()));
        }
        obj1.addProperty("nazivSkiCentra", pscf.getTxtNazivSkiCentra().getText());
        obj1.addProperty("nazivPlanine", pscf.getTxtNazivPlanine().getText());
        obj1.addProperty("radnoVreme", pscf.getTxtRadnoVreme().getText());
        obj = new JsonObject();
        obj.add("parametar", obj1);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        SkiCentar sc = gson.fromJson(jsonString, SkiCentar.class);
        pscf.getTxtSifraSkiCentra().setText(sc.getSifraSkiCentra() + "");
        pscf.getTxtNazivSkiCentra().setText(sc.getNazivSkiCentra());
        pscf.getTxtNazivPlanine().setText(sc.getNazivPlanine());
        pscf.getTxtRadnoVreme().setText(sc.getRadnoVreme());
    }

}
