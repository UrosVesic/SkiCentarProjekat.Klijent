/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.forme.modeli;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiPas;

/**
 *
 * @author draskovesic
 */
public class ModelTabeleSkiPas extends AbstractTableModel {

    SkiPas[] skiPasevi;
    String[] kolone = {"Sifra ski pasa", "Ukupna cena", "Kupac", "Datum izdavanja", "Sezona"};

    public ModelTabeleSkiPas() {
        skiPasevi = new SkiPas[0];
    }

    public SkiPas[] getSkiPasevi() {
        return skiPasevi;
    }

    public void setSkiPasevi(SkiPas[] skiPasevi) {
        this.skiPasevi = skiPasevi;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return skiPasevi.length;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SkiPas skiPas = skiPasevi[rowIndex];
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return skiPas.getSifraSkiPasa();
            case 1:
                return skiPas.getUkupnaCena();
            case 2:
                return skiPas.getKupac();
            case 3:
                return sdf.format(skiPas.getDatumIzdavanja());
            case 4:
                return skiPas.getSezona();
            default:
                return null;
        }
    }

    public void dodaj(SkiPas skiPas) {
        skiPasevi[skiPasevi.length] = skiPas;
        fireTableDataChanged();
    }

    public void azurirajSkiPas(SkiPas skiPas) {
        for (int i = 0; i < skiPasevi.length; i++) {
            if (skiPasevi[i].equals(skiPas)) {
                skiPasevi[i] = skiPas;
                fireTableDataChanged();
            }
        }

    }

}
