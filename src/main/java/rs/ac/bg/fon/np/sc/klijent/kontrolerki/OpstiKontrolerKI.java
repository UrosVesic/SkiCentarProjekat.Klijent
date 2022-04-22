/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np.sc.commonlib.domen.Korisnik;
import rs.ac.bg.fon.np.sc.commonlib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Odgovor;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Operacije;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Zahtev;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.komunikacija.Komunikacija;

/**
 *
 * @author UrosVesic
 */
public abstract class OpstiKontrolerKI {

    protected String objekat;
    protected OpstaEkranskaForma oef;
    protected List<OpstiDomenskiObjekat> lista = new ArrayList<>();

    public abstract void KonvertujGrafickiObjekatUJson();

    public abstract void KonvertujJsonObjekatUGrafickeKomponente();

    public void isprazniGrafickiObjekat() {
    }

    public boolean soPrijaviSe() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.PRIJAVI_SE, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                Komunikacija.getInstanca().setTrenutniKorisnik((Korisnik) konvertujJsonUDomenskiObjekat(objekat));
                oef.dispose();
                return true;
            } else {
                JOptionPane.showMessageDialog(oef, "Neuspesno prijavljivanje: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Neuspesno prijavljivanje", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Odgovor soUcitajListuSkiCentara() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_CENTARA, null);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                return odgovor;
            } else {
                throw odgovor.getException();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void SOZapamtiSkiKartu() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_CENTAR, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da sacuva ski kartu: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da sacuva ski kartu: ", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected abstract OpstiDomenskiObjekat konvertujJsonUDomenskiObjekat(String obj);

}
