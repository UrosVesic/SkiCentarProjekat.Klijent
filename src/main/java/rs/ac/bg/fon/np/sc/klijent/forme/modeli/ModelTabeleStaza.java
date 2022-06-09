/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.forme.modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonlib.domen.Staza;

/**
 *
 * @author UrosVesic
 */
public class ModelTabeleStaza extends AbstractTableModel {

    List<Staza> staze;
    String[] kolone = new String[]{"Broj staze", "Naziv staze", "TIp staze", "Ski centar"};

    public ModelTabeleStaza() {
        staze = new ArrayList<>();
    }

    public List<Staza> getStaze() {
        return staze;
    }

    public void setStaze(List<Staza> staze) {
        this.staze = staze;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return staze.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Staza s = staze.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return s.getBrojStaze();
            case 1:
                return s.getNazivStaze();
            case 2:
                return s.getTipStaze();
            case 3:
                return s.getSkiCentar();
            default:
                return "Greska";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Staza s = staze.get(rowIndex);
        switch (columnIndex) {
            case 0:
                s.setBrojStaze((String) aValue);
                break;
            case 1:
                s.setNazivStaze((String) aValue);
                break;
            case 2:
                s.setTipStaze((String) aValue);
                break;
            case 3:
                s.setSkiCentar((SkiCentar) aValue);
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodaj(Staza s) {
        staze.add(s);
        fireTableDataChanged();
    }

    public void remove(int i) {
        staze.remove(i);
        fireTableDataChanged();
    }

    public void removeAll() {
        staze = new ArrayList<>();
        fireTableDataChanged();
    }

    public void azurirajStazu(Staza staza) {
        for (int i = 0; i < staze.size(); i++) {
            if (staze.get(i).equals(staza)) {
                staze.get(i).setBrojStaze(staza.getBrojStaze());
                staze.get(i).setNazivStaze(staza.getNazivStaze());
                staze.get(i).setTipStaze(staza.getTipStaze());
                staze.get(i).setSkiCentar(staza.getSkiCentar());
                fireTableDataChanged();
            }
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void obrisi(int index) {
        staze.remove(index);
        fireTableDataChanged();
    }

    public Staza vratiPoslednju() throws Exception {
        if (staze.size() > 0) {
            return staze.get(staze.size() - 1);
        }
        throw new Exception();
    }

}
