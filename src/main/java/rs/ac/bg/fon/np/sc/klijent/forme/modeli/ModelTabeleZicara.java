/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.forme.modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np.sc.commonLib.domen.Zicara;

/**
 *
 * @author UrosVesic
 */
public class ModelTabeleZicara extends AbstractTableModel {

    List<Zicara> zicare;
    String[] kolone = {"Sifra zicare", "Naziv zicare", "Radno vreme", "Kapacitet", "U funkciji", "SkiCentar"};

    public ModelTabeleZicara() {
        zicare = new ArrayList<>();
    }

    public List<Zicara> getZicare() {
        return zicare;
    }

    public void setZicare(List<Zicara> zicare) {
        this.zicare = zicare;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return zicare.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zicara z = zicare.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getSifraZicare();
            case 1:
                return z.getNazivZicare();
            case 2:
                return z.getRadnoVreme();
            case 3:
                return z.getKapacitet();
            case 4:
                return (z.isUFunkciji() ? "Da" : "Ne");
            case 5:
                return z.getSkiCentar();
            default:
                return null;

        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodaj(Zicara z) {
        zicare.add(z);
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Zicara zicara = zicare.get(rowIndex);
        switch (columnIndex) {
            case 1:
                zicara.setNazivZicare((String) aValue);
                break;
            case 2:
                zicara.setRadnoVreme((String) aValue);
                break;
            case 3:
                zicara.setKapacitet(Integer.parseInt((String) aValue));
                break;
            case 4:
                String ufji = (String) aValue;
                if (ufji.equals("Da")) {
                    zicara.setUFunkciji(true);
                } else {
                    zicara.setUFunkciji(false);
                }
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex > 0 && columnIndex < 5);
    }

    public void obrisi(int selectedRow) {
        zicare.remove(selectedRow);
        fireTableDataChanged();
    }

    public Zicara vratiPoslednju() throws Exception {
        if (zicare.size() > 0) {
            return zicare.get(zicare.size() - 1);
        }
        throw new Exception();
    }

}
