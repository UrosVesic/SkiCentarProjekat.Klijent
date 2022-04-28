/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleSkiPas;
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.PronadjiSkiPasoveForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIPronadjiSkiPasove extends OpstiKontrolerKI {

    PronadjiSkiPasoveForma pspf;

    public KontrolerKIPronadjiSkiPasove(OpstaEkranskaForma oef) {
        this.oef = oef;
        pspf = (PronadjiSkiPasoveForma) oef;

    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.addProperty("imePrezimeKupca", pspf.getTxtImePrezimeKupca().getText());
        objekat = gson.toJson(obj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        niz = gson.fromJson(objekat, SkiPas[].class);
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        model.setSkiPasevi((SkiPas[]) niz);
    }

    public void pripremiTabelu() {
        pspf.getTblSkiPasevi().setModel(new ModelTabeleSkiPas());
    }

}