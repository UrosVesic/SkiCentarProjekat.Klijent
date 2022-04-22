/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.forme.modeli;

import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;

/**
 *
 * @author UrosVesic
 */
public class ModelTabeleSkiKarte extends AbstractTableModel {

    SkiKarta[] skiKarte;
    String[] kolone = {"Sifra ski karte", "Vrsta ski karte", "Cena ski karte", "Ski centar"};

    public ModelTabeleSkiKarte() {
        //skiKarte = new ArrayList<>();
    }

    public void setSkiKarte(SkiKarta[] skiKarte) {
        this.skiKarte = skiKarte;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return skiKarte.length;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SkiKarta skiKarta = (SkiKarta) skiKarte[rowIndex];
        switch (columnIndex) {
            case 0:
                return skiKarta.getSifraSkiKarte();
            case 1:
                return skiKarta.getVrstaSkiKarte();
            case 2:
                return skiKarta.getCenaSkiKarte();
            case 3:
                return skiKarta.getSkiCentar();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];//super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }

    public void add(SkiKarta skiKarta) {
        skiKarte[skiKarte.length] = skiKarta;
        fireTableDataChanged();
    }
}
