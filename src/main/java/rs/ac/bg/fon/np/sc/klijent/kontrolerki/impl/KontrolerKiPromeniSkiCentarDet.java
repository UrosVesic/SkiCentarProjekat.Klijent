/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import rs.ac.bg.fon.np.sc.commonLib.dto.SkiCentarDto;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonLib.domen.Staza;
import rs.ac.bg.fon.np.sc.commonLib.domen.Zicara;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleStaza;
import rs.ac.bg.fon.np.sc.klijent.forme.modeli.ModelTabeleZicara;
import rs.ac.bg.fon.np.sc.klijent.forme.skicentar.PromeniSkiCentarDetalji;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class KontrolerKiPromeniSkiCentarDet extends OpstiKontrolerKI {

    PromeniSkiCentarDetalji pscd;

    public KontrolerKiPromeniSkiCentarDet(OpstaEkranskaForma oef) {
        this.oef = oef;
        pscd = (PromeniSkiCentarDetalji) oef;
    }

    @Override
    public void KonvertujGrafickiObjekatUJson() {

        ModelTabeleStaza modelStaze = (ModelTabeleStaza) pscd.getTblStaze().getModel();
        ModelTabeleZicara modelZicare = (ModelTabeleZicara) pscd.getTblZicare().getModel();

        Gson gson = new Gson();
        JsonObject obj1 = new JsonObject();
        JsonObject objSkiCentar = new JsonObject();
        objSkiCentar.addProperty("sifraSkiCentra", pscd.getTxtSifraSkiCentra().getText().isEmpty() ? null : pscd.getTxtSifraSkiCentra().getText());
        objSkiCentar.addProperty("nazivSkiCentra", pscd.getTxtNazivSkiCentra().getText().isEmpty() ? null : pscd.getTxtNazivSkiCentra().getText());
        objSkiCentar.addProperty("nazivPlanine", pscd.getTxtNazivPlanine().getText().isEmpty() ? null : pscd.getTxtNazivPlanine().getText());
        objSkiCentar.addProperty("radnoVreme", pscd.getTxtRadnoVreme().getText().isEmpty() ? null : pscd.getTxtRadnoVreme().getText());
        JsonArray arrStaze = (JsonArray) gson.toJsonTree(modelStaze.getStaze());
        JsonArray arrZicare = (JsonArray) gson.toJsonTree(modelZicare.getZicare());
        obj1.add("skiCentar", objSkiCentar);
        obj1.add("staze", arrStaze);
        obj1.add("zicare", arrZicare);
        obj = new JsonObject();
        obj.add("parametar", obj1);
    }

    @Override
    public void KonvertujJsonObjekatUGrafickeKomponente() {
        Gson gson = new Gson();
        SkiCentarDto scDto = gson.fromJson(jsonString, SkiCentarDto.class);
        pscd.getTxtNazivSkiCentra().setText(scDto.getSkiCentar().getNazivSkiCentra());
        pscd.getTxtNazivPlanine().setText(scDto.getSkiCentar().getNazivPlanine());
        pscd.getTxtRadnoVreme().setText(scDto.getSkiCentar().getRadnoVreme());

        ModelTabeleStaza modelStaza = (ModelTabeleStaza) pscd.getTblStaze().getModel();
        modelStaza.setStaze(scDto.getStaze());

        ModelTabeleZicara modelZicara = (ModelTabeleZicara) pscd.getTblZicare().getModel();
        modelZicara.setZicare(scDto.getZicare());
    }

    public void pripremiFormu(SkiCentar skiCentar) {
        pscd.getTxtSifraSkiCentra().setText(skiCentar.getSifraSkiCentra() + "");
        pscd.getTxtNazivSkiCentra().setText(skiCentar.getNazivSkiCentra() + "");
        ModelTabeleStaza model = new ModelTabeleStaza();
        model.setCollNonEditable(3);
        pscd.getTblStaze().setModel(model);
        pscd.getTblZicare().setModel(new ModelTabeleZicara());
        TableColumn tc = pscd.getTblStaze().getColumnModel().getColumn(3);
        niz = new Gson().fromJson(jsonString, SkiCentar[].class);
        tc.setCellEditor(new DefaultCellEditor(new JComboBox(niz)));
    }

    public void dodajStazu() {
        ModelTabeleStaza modelStaze = (ModelTabeleStaza) pscd.getTblStaze().getModel();
        Staza s = new Staza();
        SkiCentar sc = new SkiCentar(Long.parseLong(pscd.getTxtSifraSkiCentra().getText()), pscd.getTxtNazivSkiCentra().getText(),
                pscd.getTxtNazivPlanine().getText(), pscd.getTxtRadnoVreme().getText());
        s.setSkiCentar(sc);
        modelStaze.dodaj(s);
    }

    public void dodajZicaru() {
        ModelTabeleZicara model = (ModelTabeleZicara) pscd.getTblZicare().getModel();
        model.dodaj(new Zicara());
    }

    public void obrisiStazu(int row) {
        ModelTabeleStaza modelStaze = (ModelTabeleStaza) pscd.getTblStaze().getModel();
        modelStaze.obrisi(row);
    }

    public void obrisiZicaru(int row) {
        ModelTabeleZicara model = (ModelTabeleZicara) pscd.getTblZicare().getModel();
        model.obrisi(row);
    }

}
