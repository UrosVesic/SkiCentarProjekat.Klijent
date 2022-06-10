/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleSkiPas;
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.PronadjiSkiPasoveForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIPronadjiSkiPasove extends OpstiKontrolerKI {

    PronadjiSkiPasoveForma pspf;

    public KontrolerKIPronadjiSkiPasove(OpstaEkranskaForma oef) {
        this.oef = oef;
        pspf = (PronadjiSkiPasoveForma) oef;

    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        JsonObject obj1 = new JsonObject();
        String[] imePrezime = pspf.getTxtImePrezimeKupca().getText().split(" ");
        obj1.addProperty("ime", imePrezime[0]);
        try {
            obj1.addProperty("prezime", imePrezime[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        obj = new JsonObject();
        obj.add("parametar", obj1);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        niz = gson.fromJson(jsonString, SkiPas[].class);
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        model.setSkiPasevi((SkiPas[]) niz);
    }

    public void pripremiTabelu() {
        pspf.getTblSkiPasevi().setModel(new ModelTabeleSkiPas());
    }

    public long vratiSelektovanSP() throws Exception {
        int row = pspf.getTblSkiPasevi().getSelectedRow();
        if (row == -1) {
            throw new Exception();
        }
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        SkiPas[] skiPasevi = model.getSkiPasevi();
        return skiPasevi[row].getSifraSkiPasa();
    }

    public void azurirajTabelu(SkiPas skiPas) {
        ModelTabeleSkiPas model = (ModelTabeleSkiPas) pspf.getTblSkiPasevi().getModel();
        SkiPas[] skiPasevi = model.getSkiPasevi();
        for (SkiPas skiPas1 : skiPasevi) {
            if (skiPas.equals(skiPas1)) {
                skiPas1.setDatumIzdavanja(skiPas.getDatumIzdavanja());
                skiPas1.setKupac(skiPas.getKupac());
                skiPas1.setSezona(skiPas.getSezona());
                skiPas1.setUkupnaCena(skiPas.getUkupnaCena());

                model.fireTableDataChanged();
            }
        }
    }

}
