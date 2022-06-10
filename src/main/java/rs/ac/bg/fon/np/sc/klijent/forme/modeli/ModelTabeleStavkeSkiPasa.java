/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.forme.modeli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.np.sc.commonLib.validator.ValidationException;
import rs.ac.bg.fon.np.sc.commonLib.validator.Validator;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.commonLib.domen.StavkaSkiPasa;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.OpstiKontrolerKI;

/**
 *
 * @author UrosVesic
 */
public class ModelTabeleStavkeSkiPasa extends AbstractTableModel {

    private SkiPas skiPas;
    String[] kolone = {"Vrednost stavke", "Pocetak vazenja", "Zavrsetak vazenja", "Ski karta"};
    OpstiKontrolerKI ok;

    public ModelTabeleStavkeSkiPasa(SkiPas skiPas, OpstiKontrolerKI ok) {
        this.skiPas = skiPas;
        this.ok = ok;
    }

    public SkiPas getSkiPas() {
        return skiPas;
    }

    public void setSkiPas(SkiPas skiPas) {
        this.skiPas = skiPas;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (skiPas == null) {
            return 0;
        }
        return skiPas.getStavkeSkiPasa().size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaSkiPasa stavka = skiPas.getStavkeSkiPasa().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getVrednostStavke();
            case 1:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                return sdf.format(stavka.getPocetakVazenja());

            case 2:
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                return sdf.format(stavka.getZavrsetakVazenja());

            case 3:
                return stavka.getSkiKarta();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodaj(StavkaSkiPasa stavka) throws Exception {
        Validator.startValidation().validateIfItemsExistForPeriod(stavka, skiPas, "Vec postoje karte za izabrani period")
                .validateIfDateIsInSeason(stavka.getPocetakVazenja(), skiPas.getSezona(), "Stavka mora biti u istoj sezoni kad i ski pas")
                .throwIfInvalide();

        skiPas.getStavkeSkiPasa().add(stavka);
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StavkaSkiPasa stavkaSkiPasa = skiPas.getStavkeSkiPasa().get(rowIndex);
        Date prvobitni = stavkaSkiPasa.getPocetakVazenja();
        switch (columnIndex) {
            case 1:
                stavkaSkiPasa.setPocetakVazenja((Date) aValue);
                 {
                    try {
                        Validator.startValidation().validateIfItemsExistForPeriod(stavkaSkiPasa, skiPas, "Vec postoje karte za izabrani period").throwIfInvalide();
                        stavkaSkiPasa.generisiDatumZavrsetka();
                    } catch (ValidationException ex) {
                        stavkaSkiPasa.setPocetakVazenja(prvobitni);
                        ok.prikaziPorukuOGresci(ex.getMessage());
                    }
                }

                break;

            case 3:
                stavkaSkiPasa.setSkiKarta((SkiKarta) aValue);
                stavkaSkiPasa.setVrednostStavke(((SkiKarta) aValue).getCenaSkiKarte());
                stavkaSkiPasa.generisiDatumZavrsetka();
                fireTableDataChanged();
                ok.promeniCenu();
                break;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1 || columnIndex == 3;
    }

    public void obrisi(int selectedRow) {
        skiPas.getStavkeSkiPasa().remove(selectedRow);
        fireTableDataChanged();
    }

}
