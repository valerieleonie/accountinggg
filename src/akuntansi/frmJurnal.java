/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akuntansi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author COMPUTER
 */
public class frmJurnal extends javax.swing.JFrame {

    Connection conn;

    /**
     * Creates new form frmJurnal
     */
    public frmJurnal(Connection conn) {
        this.conn = conn;
        initComponents();
        loadAllDatabase();
        setLocationRelativeTo(null);
    }

    private void loadFromTill(Date from, Date till) {
        removeTableData();
        try {
            String sql = "SELECT * FROM jurnal where tanggal between ? and ? ;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblJurnal.getModel();

                    int chartno = rs.getInt("chartno");
                    Object data[] = {
                        rs.getDate("tanggal"),
                        rs.getInt("kode_jurnal"),
                        chartno,
                        getChartNamebyChartNo(chartno),
                        rs.getDouble("debit"),
                        rs.getDouble("kredit"),};
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmJurnal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getChartNamebyChartNo(int chartno) throws SQLException {
        String name = "";
        String sqlPenyewaLookup = "Select chartname from accountchart where chartno = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, chartno);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            name = rsPenyewaLookup.getString("chartname");
        }
        return name;
    }

    private void loadAllDatabase() {
        removeTableData();
        try {
            String sql = "SELECT * FROM jurnal;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblJurnal.getModel();

                    int chartno = rs.getInt("chartno");
                    Object data[] = {
                        rs.getDate("tanggal"),
                        rs.getInt("kode_jurnal"),
                        chartno,
                        getChartNamebyChartNo(chartno),
                        rs.getDouble("debit"),
                        rs.getDouble("kredit"),};
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();

            balanceDebit();
            balanceKredit();
        } catch (SQLException ex) {
            Logger.getLogger(frmJurnal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblJurnal = new javax.swing.JTable();
        dtcFrom = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        dtcTill = new com.toedter.calendar.JDateChooser();
        txtDebit = new javax.swing.JTextField();
        txtKredit = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(830, 520));

        tblJurnal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tanggal", "No Jurnal", "No Akun", "Nama Akun", "Debit", "Kredit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblJurnal.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblJurnal);

        jLabel1.setText("-");

        txtDebit.setEnabled(false);

        txtKredit.setEnabled(false);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setText("Period : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        loadPeriod();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void loadPeriod() {
        try {
            if (dtcFrom.getDate() == null || dtcTill.getDate() == null) {
                util.Sutil.mse(this, "Pilih tanggal !");
            } else if (dtcFrom.getDate() != null && dtcTill.getDate() != null) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String datef = sdf.format(dtcFrom.getDate());
                Date datefrom = sdf.parse(datef);

                String datet = sdf.format(dtcTill.getDate());
                Date datetill = sdf.parse(datet);

                loadFromTill(datefrom, datetill);
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmJurnal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double balanceDebit() {
        int i = 0;
        double debit = 0;
        if (tblJurnal.getRowCount() > 0) {
            for (i = 0; i < tblJurnal.getRowCount(); i++) {
                debit += Double.valueOf(tblJurnal.getValueAt(i, 4).toString());
                txtDebit.setText(String.valueOf(debit));
            }
        }
        return debit;
    }

    private double balanceKredit() {
        int i = 0;
        double kredit = 0;
        if (tblJurnal.getRowCount() > 0) {
            for (i = 0; i < tblJurnal.getRowCount(); i++) {
                kredit += Double.valueOf(tblJurnal.getValueAt(i, 5).toString());
                txtKredit.setText(String.valueOf(kredit));
            }
        }
        return kredit;
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblJurnal.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private com.toedter.calendar.JDateChooser dtcFrom;
    private com.toedter.calendar.JDateChooser dtcTill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblJurnal;
    private javax.swing.JTextField txtDebit;
    private javax.swing.JTextField txtKredit;
    // End of variables declaration//GEN-END:variables
}
