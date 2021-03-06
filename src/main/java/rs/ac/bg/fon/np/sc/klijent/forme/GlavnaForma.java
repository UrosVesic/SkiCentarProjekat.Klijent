/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.forme;

import javax.swing.JFrame;
import rs.ac.bg.fon.np.sc.klijent.forme.skicentar.PromeniSkiCentarForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skicentar.ZapamtiSkiCentarForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skikarta.PretraziSkiKarteForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skikarta.ZapamtiSkiKartuForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.PronadjiSkiPasoveForma;
import rs.ac.bg.fon.np.sc.klijent.forme.skipas.ZapamtiSkiPasForma;
import rs.ac.bg.fon.np.sc.klijent.forme.staza.PronadjiStazeForma;
import rs.ac.bg.fon.np.sc.klijent.forme.staza.ZapamtiStazuForma;
import rs.ac.bg.fon.np.sc.klijent.forme.zicara.ZapamtiZicaruForma;
import rs.ac.bg.fon.np.sc.klijent.komunikacija.Komunikacija;

/**
 *
 * @author UrosVesic
 */
public class GlavnaForma extends javax.swing.JFrame {

    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        txtKorisnik.setText(Komunikacija.getInstanca().getTrenutniKorisnik().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtKorisnik = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmSkiPas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmiIzmeniSkiPas = new javax.swing.JMenuItem();
        jmSkiCentar = new javax.swing.JMenu();
        jmiKreirajNoviSkiCentar = new javax.swing.JMenuItem();
        jmiIzmeniSkiCentar = new javax.swing.JMenuItem();
        jmStaza = new javax.swing.JMenu();
        jmiKreirajNovuStazu = new javax.swing.JMenuItem();
        jmiPronadjiIzmeniStazu = new javax.swing.JMenuItem();
        jmZicara = new javax.swing.JMenu();
        jmiKreirajNovuZicaru = new javax.swing.JMenuItem();
        jmSkiKarta = new javax.swing.JMenu();
        jmiKreirajSkiKartu = new javax.swing.JMenuItem();
        jmiPretraziSkiKarte = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Prijavljeni korisnik:");

        txtKorisnik.setEditable(false);

        jmSkiPas.setText("Ski pas");

        jMenuItem1.setText("Kreiraj novi ski pas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmSkiPas.add(jMenuItem1);

        jmiIzmeniSkiPas.setText("Izmeni ski pas");
        jmiIzmeniSkiPas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIzmeniSkiPasActionPerformed(evt);
            }
        });
        jmSkiPas.add(jmiIzmeniSkiPas);

        jMenuBar1.add(jmSkiPas);

        jmSkiCentar.setText("SkiCentar");

        jmiKreirajNoviSkiCentar.setText("Kreiraj novi");
        jmiKreirajNoviSkiCentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiKreirajNoviSkiCentarActionPerformed(evt);
            }
        });
        jmSkiCentar.add(jmiKreirajNoviSkiCentar);

        jmiIzmeniSkiCentar.setText(" Izmeni");
        jmiIzmeniSkiCentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIzmeniSkiCentarActionPerformed(evt);
            }
        });
        jmSkiCentar.add(jmiIzmeniSkiCentar);

        jMenuBar1.add(jmSkiCentar);

        jmStaza.setText("Staza");

        jmiKreirajNovuStazu.setText("Kreiraj novu");
        jmiKreirajNovuStazu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiKreirajNovuStazuActionPerformed(evt);
            }
        });
        jmStaza.add(jmiKreirajNovuStazu);

        jmiPronadjiIzmeniStazu.setText("Pronadji i izmeni");
        jmiPronadjiIzmeniStazu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPronadjiIzmeniStazuActionPerformed(evt);
            }
        });
        jmStaza.add(jmiPronadjiIzmeniStazu);

        jMenuBar1.add(jmStaza);

        jmZicara.setText("Zicara");

        jmiKreirajNovuZicaru.setText("Kreiraj novu");
        jmiKreirajNovuZicaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiKreirajNovuZicaruActionPerformed(evt);
            }
        });
        jmZicara.add(jmiKreirajNovuZicaru);

        jMenuBar1.add(jmZicara);

        jmSkiKarta.setText("Ski karta");

        jmiKreirajSkiKartu.setText("Kreiraj ski kartu");
        jmiKreirajSkiKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiKreirajSkiKartuActionPerformed(evt);
            }
        });
        jmSkiKarta.add(jmiKreirajSkiKartu);

        jmiPretraziSkiKarte.setText("Pretrazi ski karte");
        jmiPretraziSkiKarte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPretraziSkiKarteActionPerformed(evt);
            }
        });
        jmSkiKarta.add(jmiPretraziSkiKarte);

        jMenuBar1.add(jmSkiKarta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(867, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(603, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiKreirajNoviSkiCentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiKreirajNoviSkiCentarActionPerformed
        // TODO add your handling code here:
        new ZapamtiSkiCentarForma().setVisible(true);
    }//GEN-LAST:event_jmiKreirajNoviSkiCentarActionPerformed

    private void jmiKreirajNovuStazuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiKreirajNovuStazuActionPerformed
        // TODO add your handling code here:
        new ZapamtiStazuForma().setVisible(true);
    }//GEN-LAST:event_jmiKreirajNovuStazuActionPerformed

    private void jmiPronadjiIzmeniStazuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPronadjiIzmeniStazuActionPerformed
        // TODO add your handling code here:
        new PronadjiStazeForma().setVisible(true);
    }//GEN-LAST:event_jmiPronadjiIzmeniStazuActionPerformed

    private void jmiIzmeniSkiCentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIzmeniSkiCentarActionPerformed
        // TODO add your handling code here:
        new PromeniSkiCentarForma().setVisible(true);
    }//GEN-LAST:event_jmiIzmeniSkiCentarActionPerformed

    private void jmiKreirajNovuZicaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiKreirajNovuZicaruActionPerformed
        // TODO add your handling code here:
        new ZapamtiZicaruForma().setVisible(true);
    }//GEN-LAST:event_jmiKreirajNovuZicaruActionPerformed

    private void jmiKreirajSkiKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiKreirajSkiKartuActionPerformed
        // TODO add your handling code here:
        new ZapamtiSkiKartuForma().setVisible(true);
    }//GEN-LAST:event_jmiKreirajSkiKartuActionPerformed

    private void jmiPretraziSkiKarteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPretraziSkiKarteActionPerformed
        // TODO add your handling code here:
        new PretraziSkiKarteForma().setVisible(true);
    }//GEN-LAST:event_jmiPretraziSkiKarteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new ZapamtiSkiPasForma().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiIzmeniSkiPasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIzmeniSkiPasActionPerformed
        // TODO add your handling code here:
        new PronadjiSkiPasoveForma().setVisible(true);
    }//GEN-LAST:event_jmiIzmeniSkiPasActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jmSkiCentar;
    private javax.swing.JMenu jmSkiKarta;
    private javax.swing.JMenu jmSkiPas;
    private javax.swing.JMenu jmStaza;
    private javax.swing.JMenu jmZicara;
    private javax.swing.JMenuItem jmiIzmeniSkiCentar;
    private javax.swing.JMenuItem jmiIzmeniSkiPas;
    private javax.swing.JMenuItem jmiKreirajNoviSkiCentar;
    private javax.swing.JMenuItem jmiKreirajNovuStazu;
    private javax.swing.JMenuItem jmiKreirajNovuZicaru;
    private javax.swing.JMenuItem jmiKreirajSkiKartu;
    private javax.swing.JMenuItem jmiPretraziSkiKarte;
    private javax.swing.JMenuItem jmiPronadjiIzmeniStazu;
    private javax.swing.JTextField txtKorisnik;
    // End of variables declaration//GEN-END:variables
}
