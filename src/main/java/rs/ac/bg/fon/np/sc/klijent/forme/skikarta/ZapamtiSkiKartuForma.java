/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.forme.skikarta;

import javax.swing.JButton;
import rs.ac.bg.fon.np.sc.commonLib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.klijent.forme.OpstaEkranskaForma;
import rs.ac.bg.fon.np.sc.klijent.kontrolerki.impl.KontrolerKIZapamtiSkiKartu;

/**
 *
 * @author UrosVesic
 */
public class ZapamtiSkiKartuForma extends OpstaEkranskaForma {

    /**
     * Creates new form KreirajSkiKartuForma
     */
    KontrolerKIZapamtiSkiKartu kkiZapamtiSkiKartu;

    public ZapamtiSkiKartuForma() {
        initComponents();
        kkiZapamtiSkiKartu = new KontrolerKIZapamtiSkiKartu(this);
        prepare();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        cmbSkiCentar = new javax.swing.JComboBox();
        txtSifraSkiKarte = new javax.swing.JTextField();
        btnZapamti = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCenaSkiKarte = new javax.swing.JTextField();
        cmbVrstaSkiKarte = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kreiraj ski kartu");

        jLabel4.setText("Ski Centar: ");

        txtSifraSkiKarte.setEditable(false);

        btnZapamti.setText("zapamti");
        btnZapamti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZapamtiActionPerformed(evt);
            }
        });

        jLabel1.setText("Sifra ski karte:");

        jLabel2.setText("Vrsta ski karte: ");

        jLabel3.setText("Cena ski karte:");

        cmbVrstaSkiKarte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jednodnevna", "Dvodnevna", "Trodnevna", "Cetvorodnevna", "Petodnevna", "Sestodnevna", "Sedmodnevna", " " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbSkiCentar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCenaSkiKarte)
                                    .addComponent(cmbVrstaSkiKarte, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtSifraSkiKarte, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnZapamti, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSifraSkiKarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbVrstaSkiKarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCenaSkiKarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbSkiCentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnZapamti, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZapamtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZapamtiActionPerformed
        kkiZapamtiSkiKartu.SOZapamtiSkiKartu();
    }//GEN-LAST:event_btnZapamtiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnZapamti;
    private javax.swing.JComboBox cmbSkiCentar;
    private javax.swing.JComboBox<String> cmbVrstaSkiKarte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCenaSkiKarte;
    private javax.swing.JTextField txtSifraSkiKarte;
    // End of variables declaration//GEN-END:variables

    private void prepare() {
        kkiZapamtiSkiKartu.pripremiKombobox();

    }

    public javax.swing.JComboBox getCmbSkiCentar() {
        return cmbSkiCentar;
    }

    public javax.swing.JComboBox<String> getCmbVrstaSkiKarte() {
        return cmbVrstaSkiKarte;
    }

    public javax.swing.JTextField getTxtCenaSkiKarte() {
        return txtCenaSkiKarte;
    }

    public javax.swing.JTextField getTxtSifraSkiKarte() {
        return txtSifraSkiKarte;
    }

    @Override
    public OpstiDomenskiObjekat kreirajObjekat() {
        return new SkiKarta();
    }

    public JButton getBtnZapamti() {
        return btnZapamti;
    }

}
