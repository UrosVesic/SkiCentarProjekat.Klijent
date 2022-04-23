/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleStaza;
import rs.ac.bg.fon.np.sc.klijent.forme.staza.PronadjiStazeForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.np.sc.commonlib.domen.Staza;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKIPronadjiStaze extends OpstiKontrolerKI {

    PronadjiStazeForma psf;

    public KontrolerKIPronadjiStaze(OpstaEkranskaForma oef) {
        this.oef = oef;
        psf = (PronadjiStazeForma) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.addProperty("nazivSkiCentra", psf.getTxtSkiCentar().getText());
        objekat = gson.toJson(obj);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Staza>>() {
        }.getType();
        List<Staza> staze = gson.fromJson(objekat, collectionType);
        ModelTabeleStaza model = new ModelTabeleStaza();
        model.setStaze(staze);
        psf.getTblStaze().setModel(model);
    }

    public void pripremiTabelu() {
        psf.getTblStaze().setModel(new ModelTabeleStaza());
    }

    public Staza vratiIzabranuStazu() throws Exception {
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        if (psf.getTblStaze().getSelectedRow() == -1) {
            throw new Exception("Nije izabran red");
        }
        return model.getStaze().get(psf.getTblStaze().getSelectedRow());
    }

    public void azurirajTabelu(Staza staza) {
        ModelTabeleStaza model = (ModelTabeleStaza) psf.getTblStaze().getModel();
        model.azurirajStazu(staza);
    }

}
