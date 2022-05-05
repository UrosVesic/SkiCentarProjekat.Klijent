/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;
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
        JsonObject obj = new JsonObject();
        if (!pscf.getTxtSifraSkiCentra().getText().isEmpty()) {
            obj.addProperty("sifraSkiCentra", Long.parseLong(pscf.getTxtSifraSkiCentra().getText()));
        }
        obj.addProperty("nazivSkiCentra", pscf.getTxtNazivSkiCentra().getText());
        obj.addProperty("nazivPlanine", pscf.getTxtNazivPlanine().getText());
        obj.addProperty("radnoVreme", pscf.getTxtRadnoVreme().getText());
        jsonString = gson.toJson(obj);
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
