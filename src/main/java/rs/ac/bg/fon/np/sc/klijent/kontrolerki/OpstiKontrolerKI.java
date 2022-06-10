/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np.sc.commonLib.domen.Korisnik;
import rs.ac.bg.fon.np.sc.commonLib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.commonLib.komunikacija.Operacije;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.komunikacija.Komunikacija;

/**
 *
 * @author UrosVesic
 */
public abstract class OpstiKontrolerKI {

    protected JsonObject obj;
    protected String jsonString;
    protected OpstaEkranskaForma oef;
    protected OpstiDomenskiObjekat[] niz;
    protected final Gson gson = new Gson();

    public abstract void KonvertujGrafickiObjekatUJson();

    public abstract void KonvertujJsonObjekatUGrafickeKomponente();

    public void soZapamtiSvePodatkeOSkiCentru() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.ZAPAMTI_SKI_CENTAR_DETALJNIJE);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski centar");
            } else {
                JOptionPane.showMessageDialog(oef, "Neuspesno pamcenje ski centra: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Neuspesno pamcenje ski centra: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void SOUcitajSkiCentarDetalji() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.UCITAJ_SKI_CENTAR_DETALJNIJE);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
            } else {
                JOptionPane.showMessageDialog(oef, "Neuspesno ucitavanje ski centra: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Neuspesno ucitavanje ski centra: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean soPrijaviSe() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PRIJAVI_SE);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                Komunikacija.getInstanca().setTrenutniKorisnikJson(gson.fromJson(element.getAsJsonObject().get("rezultat"), Korisnik.class));
                oef.dispose();
                return true;
            } else {
                Exception ex = gson.fromJson(element.getAsJsonObject().get("exception"), Exception.class);
                JOptionPane.showMessageDialog(oef, "Neuspesno prijavljivanje: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Neuspesno prijavljivanje", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void soUcitajListuSkiCentara() throws Exception {
        obj = new JsonObject();
        KonvertujOperacijuUJson(Operacije.UCITAJ_LISTU_SKI_CENTARA);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
            } else {
               throw new Exception(gson.fromJson(element.getAsJsonObject().get("exception"), String.class));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje liste ski centara");

        }
    }

    public void soUcitajListuSkiKarata() throws Exception {
        obj = new JsonObject();
        KonvertujOperacijuUJson(Operacije.UCITAJ_LISTU_SKI_KARATA);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
            } else {
               throw new Exception(gson.fromJson(element.getAsJsonObject().get("exception"), String.class));
            }
        } catch (Exception ex) {
            throw new Exception("Neuspesno ucitavanje liste ski karata");
        }
    }

    public void SOZapamtiSkiKartu() {
        KonvertujGrafickiObjekatUJson();
        String odgovor;
        KonvertujOperacijuUJson(Operacije.ZAPAMTI_SKI_KARTU);
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski kartu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da sacuva ski kartu: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da sacuva ski kartu: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPretraziKarte() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PRETRAZI_SKI_KARTE);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski karte");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski karte: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski karte: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soZapamtiStazu() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.ZAPAMTI_STAZU);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio stazu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti stazu: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void soPretraziStaze() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PRETRAZI_STAZE);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao staze");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi staze: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi staze: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soUcitajStazu() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.UCITAJ_STAZU);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je ucitao stazu");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita stazu: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita stazu: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPromeniStazu() throws Exception {
        int i = 0;
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PROMENI_STAZU);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je promenio stazu");
                
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni stazu: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
                i = 1;
               throw new Exception(gson.fromJson(element.getAsJsonObject().get("exception"), String.class));
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
        KonvertujOperacijuUJson(Operacije.ZAPAMTI_ZICARU);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio zicaru");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti zicaru: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soZapamtiSkiCentar() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.ZAPAMTI_SKI_CENTAR);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski centar");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski centar: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void soPretraziSkiCentar() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PRETRAZI_SKI_CENTAR);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski centar");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski centar: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski centar: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPromeniSkiCentar() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PROMENI_SKI_CENTAR);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je promenio ski centar");
            } else {
               throw new Exception(gson.fromJson(element.getAsJsonObject().get("exception"), String.class));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni ski centar: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soZapamtiSkiPas() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.ZAPAMTI_SKI_PAS);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je zapamtio ski pas");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da zapamti ski pas: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPretraziSkiPas() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PRETRAZI_SKI_PASOVE);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je pronasao ski pasove");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi ski pasove: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da pretrazi pasove: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void promeniCenu() {

    }

    public void prikaziPorukuOGresci(String message) {
        JOptionPane.showMessageDialog(oef, message, "Greska", JOptionPane.ERROR_MESSAGE);
    }

    public void soUcitajSkiPas() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.UCITAJ_SKI_PAS);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je ucitao ski pas");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita ski pas: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da ucita ski pas: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soPromeniSkiPas() {
        KonvertujGrafickiObjekatUJson();
        KonvertujOperacijuUJson(Operacije.PROMENI_SKI_PAS);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
                KonvertujJsonObjekatUGrafickeKomponente();
                JOptionPane.showMessageDialog(oef, "Sistem je promenio ski pas");
            } else {
                JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni ski pas: " + gson.fromJson(element.getAsJsonObject().get("exception"), String.class), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(oef, "Sistem ne moze da promeni ski pas: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soUcitajListuKupaca() throws Exception {
        KonvertujOperacijuUJson(Operacije.UCITAJ_LISTU_KUPACA);
        String odgovor;
        try {
            odgovor = Komunikacija.getInstanca().pozivSo(gson.toJson(obj));
            JsonElement element = JsonParser.parseString(odgovor);
            if (element.getAsJsonObject().get("uspesno").getAsBoolean()) {
                jsonString = gson.toJson(element.getAsJsonObject().get("rezultat"));
            } else {
               throw new Exception(gson.fromJson(element.getAsJsonObject().get("exception"), String.class));
            }
        } catch (Exception ex) {
            throw new Exception("Neuspesno ucitavanje kupaca");
        }
    }

    protected void KonvertujOperacijuUJson(int op) {
        obj.addProperty("operacija", op);
    }

}
