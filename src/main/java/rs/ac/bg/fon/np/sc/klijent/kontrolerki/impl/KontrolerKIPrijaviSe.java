/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rs.ac.bg.fon.np.sc.commonlib.domen.Korisnik;
import rs.ac.bg.fon.np.sc.commonlib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.PrijaviSeForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIPrijaviSe extends OpstiKontrolerKI {

    public KontrolerKIPrijaviSe(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        PrijaviSeForma pf = (PrijaviSeForma) oef;
        JsonObject obj = new JsonObject();
        obj.addProperty("email", pf.getTxtEmail().getText());
        obj.addProperty("sifra", String.valueOf(pf.getTxtSifra().getPassword()));
        objekat = new Gson().toJson(obj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected OpstiDomenskiObjekat konvertujJsonUDomenskiObjekat(String obj) {
        Gson gson = new Gson();
        return gson.fromJson(obj, Korisnik.class);
    }

}
