/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki;

import javax.swing.JOptionPane;
import rs.ac.bg.fon.np.sc.commonlib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;

/**
 *
 * @author UrosVesic
 */
public abstract class OpstiKontrolerKI {

    protected OpstiDomenskiObjekat odo;
    protected OpstaEkranskaForma oef;

    public abstract void KonvertujGrafickiObjekatUJsonObjekat();

    public abstract void KonvertujJsonObjekatUGrafickeKomponente();

    public void isprazniGrafickiObjekat() {}
}
