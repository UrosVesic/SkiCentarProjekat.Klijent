/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np.sc.commonlib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Odgovor;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skikarta.ZapamtiSkiKartuForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIZapamtiSkiKartu extends OpstiKontrolerKI {

    public KontrolerKIZapamtiSkiKartu(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected OpstiDomenskiObjekat konvertujJsonUDomenskiObjekat(String obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pripremiKombobox() {
        ZapamtiSkiKartuForma zskf = (ZapamtiSkiKartuForma) oef;
        zskf.getCmbSkiCentar().removeAllItems();
        try {
            Odgovor odgovor = soUcitajListuSkiCentara();
            lista = konvertujJsonUListuSkiCentara(odgovor.getRezultat());
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
                zskf.getCmbSkiCentar().addItem((SkiCentar) opstiDomenskiObjekat);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita listu ski centara: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<OpstiDomenskiObjekat> konvertujJsonUListuSkiCentara(String rezultat) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<SkiCentar>>() {
        }.getType();
        List<OpstiDomenskiObjekat> konvertovana = gson.fromJson(rezultat, collectionType);
        return konvertovana;
    }

}
