/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import rs.ac.bg.fon.np.sc.commonLib.domen.Korisnik;
import rs.ac.bg.fon.np.sc.commonLib.domen.OpstiDomenskiObjekat;
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
        obj = new JsonObject();
        JsonObject domObj = new JsonObject();
        domObj.addProperty("email", pf.getTxtEmail().getText());
        domObj.addProperty("sifra", String.valueOf(pf.getTxtSifra().getPassword()));
        obj.add("parametar", domObj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
