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
    protected OpstiDomenskiObjekat[] niz;

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
                Komunikacija.getInstanca().setTrenutniKorisnikJson(objekat);
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

    public void soUcitajListuSkiCentara() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_CENTARA, null);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
            } else {
                throw odgovor.getException();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void soUcitajListuSkiKarata() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_LISTU_SKI_KARATA, null);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
            } else {
                throw odgovor.getException();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void SOZapamtiSkiKartu() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_KARTU, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski kartu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da sacuva ski kartu: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da sacuva ski kartu: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPretraziKarte() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_KARTE, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski karte");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski karte: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski karte: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soZapamtiStazu() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_STAZU, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio stazu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void soPretraziStaze() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_STAZE, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao staze");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi staze: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi staze: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soUcitajStazu() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_STAZU, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je ucitao stazu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita stazu: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita stazu: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPromeniStazu() throws Exception {
        int i = 0;
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_STAZU, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je promenio stazu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni stazu: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                i = 1;
                throw odgovor.getException();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (i == 0) {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni stazu: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
            throw ex;
        }
    }

    public void soZapamtiZicaru() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_ZICARU, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio zicaru");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soZapamtiSkiCentar() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_CENTAR, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski centar");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void soPretraziSkiCentar() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_SKI_CENTAR, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski centar");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski centar: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski centar: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPromeniSkiCentar() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_SKI_CENTAR, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je promenio ski centar");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni ski centar: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                throw odgovor.getException();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni ski centar: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soZapamtiSkiPas() {
        KonvertujGrafickiObjekatUJson();
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_SKI_PAS, objekat);
        Odgovor odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(zahtev);
            if (odgovor.isUspesno()) {
                objekat = odgovor.getRezultat();
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski pas");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas: " + odgovor.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void promeniCenu() {

    }

    public void prikaziPorukuOGresci(String message) {
        JOptionPane.showMessageDialog(oef, message, "Greska", JOptionPane.ERROR_MESSAGE);
    }
}
