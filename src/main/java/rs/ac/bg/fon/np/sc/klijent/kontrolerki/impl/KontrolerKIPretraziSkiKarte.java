/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.math.BigDecimal;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleSkiKarte;
import rs.ac.bg.fon.np.sc.klijent.forme.skikarta.PretraziSkiKarteForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIPretraziSkiKarte extends OpstiKontrolerKI {

    public KontrolerKIPretraziSkiKarte(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.addProperty("cenaSkiKarte", new BigDecimal(pskf.getTxtGornjaCena().getText()));
        jsonString = gson.toJson(obj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        Gson gson = new Gson();
        niz = gson.fromJson(jsonString, SkiKarta[].class);
        ModelTabeleSkiKarte model = (ModelTabeleSkiKarte) pskf.getTblSkiKarte().getModel();
        model.setSkiKarte((SkiKarta[]) niz);

    }

    public void pripremiTabelu() {
        PretraziSkiKarteForma pskf = (PretraziSkiKarteForma) oef;
        pskf.getTblSkiKarte().setModel(new ModelTabeleSkiKarte());
    }

}
