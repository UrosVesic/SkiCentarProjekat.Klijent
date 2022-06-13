/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiKarta;
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
        JsonObject obj1 = new JsonObject();
        try {
            obj1.addProperty("cenaSkiKarte", pskf.getTxtGornjaCena().getText().isEmpty() ? null : new BigDecimal(pskf.getTxtGornjaCena().getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(oef, "Cena mora biti uneta kao broj");
            throw new RuntimeException();
        }
        obj = new JsonObject();
        obj.add("parametar", obj1);
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
