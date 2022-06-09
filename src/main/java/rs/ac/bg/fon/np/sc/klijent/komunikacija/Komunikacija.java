/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.komunikacija;

import com.google.gson.Gson;
import java.net.Socket;
import rs.ac.bg.fon.np.sc.commonlib.domen.Korisnik;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Odgovor;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Posiljalac;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Primalac;
import rs.ac.bg.fon.np.sc.commonlib.komunikacija.Zahtev;

/**
 *
 * @author UrosVesic
 */
public class Komunikacija {

    static Komunikacija instanca;
    Socket socket;
    Korisnik trenutniKorisnik;

    private Komunikacija() {

    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik = trenutniKorisnik;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }
    
    public String pozivSo(String zahtev) throws Exception {
        new Posiljalac(socket).posalji(zahtev);
        return  (String) new Primalac(socket).primi();
    }

    public void setTrenutniKorisnikJson(Korisnik korisnik) {
        trenutniKorisnik = korisnik;
    }

}
