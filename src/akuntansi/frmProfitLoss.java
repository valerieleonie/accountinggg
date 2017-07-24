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
import static java.util.Date.from;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import util.Sutil;

/**
 *
 * @author COMPUTER
 */
public class frmProfitLoss extends javax.swing.JFrame {

    private Connection conn;
    Integer endingvalue;

    /**
     * Creates new form frmProfitLoss
     */
    public frmProfitLoss(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void executeOk() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            if (dtcFrom.getDate() == null || dtcTill.getDate() == null) {
                Sutil.mse(this, "Pilih tanggal !");
            } else if (dtcFrom.getDate() != null && dtcTill.getDate() != null) {

                String datef = sdf.format(dtcFrom.getDate());
                Date datefrom = sdf.parse(datef);

                String datet = sdf.format(dtcTill.getDate());
                Date datetill = sdf.parse(datet);
                
                // sales
                loadSalesIncome(datefrom, datetill);
                loadSalesDiscount(datefrom, datetill);
                loadSalesReturn(datefrom, datetill);
                loadOthersIncome(datefrom, datetill);
                totalsales();
                
                //inventory
                loadBeginningInventory(datefrom, datetill);
                loadPurchasing(datefrom, datetill);
                loadPurchasingReturn(datefrom, datetill);
                loadEndingInventory(datefrom, datetill);
                totalCOGS();
                grossProvit();
                
                //expenditure
                loadGeneralExpenditure(datefrom, datetill);
                loadMarketingExpenditure(datefrom, datetill);
                loadHumanResourceExpenditure(datefrom, datetill);
                totalExpenditure();
                netProvit();
            } else {
                Sutil.mse(this, "Call your programmer ! =D , Good Luck !!!");
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSalesIncome(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.kredit)-SUM(j.debit) as salesincome from jurnal j where chartno = 4010 and "
                        + "tanggal between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int salesincome = rs.getInt("salesincome");
                        txtSalesIncome.setText(String.valueOf(salesincome));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSalesDiscount(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.debit)-SUM(j.kredit) as salesdiscount from jurnal j where chartno = 4020 and "
                        + "tanggal between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int salesdiscount = rs.getInt("salesdiscount");
                        txtSalesDiscount.setText(String.valueOf(salesdiscount));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSalesReturn(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.debit)-SUM(j.kredit) as salesreturn from jurnal j where chartno = 4030 and "
                        + "tanggal between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int salesreturn = rs.getInt("salesreturn");
                        txtSalesReturn.setText(String.valueOf(salesreturn));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadOthersIncome(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.kredit)-SUM(j.debit) as othersincome from jurnal j where chartno = 7010 and "
                        + "tanggal between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int othersincome = rs.getInt("othersincome");
                        txtOthersIncome.setText(String.valueOf(othersincome));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void totalsales() {
        int salesincome = Integer.parseInt(txtSalesIncome.getText());
        int salesdiscount = Integer.parseInt(txtSalesDiscount.getText());
        int salesreturn = Integer.parseInt(txtSalesReturn.getText());
        int othersincome = Integer.parseInt(txtOthersIncome.getText());
        int totalsales = salesincome + salesdiscount + salesreturn + othersincome;
        txtTotalSales.setText(String.valueOf(totalsales));
    }

    private void loadBeginningInventory(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select tb.opening as beginninginventory from trialbalance tb inner join"
                        + " jurnal j on tb.chartno = j.chartno where tanggal between ? and ? and tb.chartno = 1040;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int beginninginventory = rs.getInt("beginninginventory");
                        txtBeginningInventory.setText(String.valueOf(beginninginventory));

                    };
                } else {
                    txtBeginningInventory.setText("0");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPurchasing(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.debit)-SUM(j.kredit) as purchasing from jurnal j where chartno = 5110 and "
                        + "tanggal between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int purchasing = rs.getInt("purchasing");
                        txtPurchasing.setText(String.valueOf(purchasing));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPurchasingReturn(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.debit)-SUM(j.kredit) as purchasingreturn from jurnal j where chartno = 5130 and "
                        + "tanggal between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int purchasingreturn = rs.getInt("purchasingreturn");
                        txtPurchasingReturn.setText(String.valueOf(purchasingreturn));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadEndingInventory(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select tb.ending as endinginventory from trialbalance tb inner join jurnal j on tb.chartno = j.chartno "
                        + "where tanggal between ? and ? and tb.chartno = 1040;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int endinginventory = rs.getInt("endinginventory");
                        txtEndingInventory.setText(String.valueOf(endinginventory));

                    };
                } else {
                    txtEndingInventory.setText("0");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void totalCOGS() {
        int beginninginventory = Integer.parseInt(txtBeginningInventory.getText());
        int purchasing = Integer.parseInt(txtPurchasing.getText());
        int purchasingreturn = Integer.parseInt(txtPurchasingReturn.getText());
//        int endinginventory = Integer.parseInt(txtEndingInventory.getText());
        int totalCOGS = 0;
        totalCOGS += beginninginventory + purchasing + purchasingreturn + endingvalue;
        txtTotalCOGS.setText(String.valueOf(totalCOGS));
    }

    private void grossProvit() {
        int salesrevenue = Integer.parseInt(txtTotalSales.getText());
        int totalcogs = Integer.parseInt(txtTotalCOGS.getText());
        int grossprovit = salesrevenue - totalcogs;
        txtGrossProvit.setText(String.valueOf(grossprovit));
    }

    private void loadGeneralExpenditure(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.debit)-SUM(j.kredit) as generalexpenditure from jurnal j where chartno = 6010 and tanggal "
                        + "between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int generalexpenditure = rs.getInt("generalexpenditure");
                        txtGeneralExpenditure.setText(String.valueOf(generalexpenditure));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMarketingExpenditure(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.debit)-SUM(j.kredit) as marketingexpenditure from jurnal j where chartno = 6020 and tanggal "
                        + "between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int marketingexpenditure = rs.getInt("marketingexpenditure");
                        txtMarketingExpenditure.setText(String.valueOf(marketingexpenditure));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadHumanResourceExpenditure(Date from, Date till) {
        try {
            if (conn != null) {
                String sql = "select SUM(j.debit)-SUM(j.kredit) as humanresourceexpenditure from jurnal j where chartno = 6030 and tanggal "
                        + "between ? and ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                java.sql.Date datefrom = new java.sql.Date(from.getTime());
                java.sql.Date datetill = new java.sql.Date(till.getTime());

                pstatement.setDate(1, datefrom);
                pstatement.setDate(2, datetill);

                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int humanresourceexpenditure = rs.getInt("humanresourceexpenditure");
                        txtHumanResourceExpenditure.setText(String.valueOf(humanresourceexpenditure));

                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProfitLoss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void totalExpenditure() {
        int ge = Integer.parseInt(txtGeneralExpenditure.getText());
        int me = Integer.parseInt(txtMarketingExpenditure.getText());
        int hre = Integer.parseInt(txtHumanResourceExpenditure.getText());
        int totalexpenditure = ge + me + hre;
        txtTotalExpenditure.setText(String.valueOf(totalexpenditure));
    }

    private void netProvit() {
        int grossprovit = Integer.parseInt(txtGrossProvit.getText());
        int totalexpenditure = Integer.parseInt(txtTotalExpenditure.getText());
        int netprovit = grossprovit - totalexpenditure;
        txtNetProvit.setText(String.valueOf(netprovit));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dtcFrom = new com.toedter.calendar.JDateChooser();
        dtcTill = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSalesIncome = new javax.swing.JTextField();
        txtSalesDiscount = new javax.swing.JTextField();
        txtSalesReturn = new javax.swing.JTextField();
        txtOthersIncome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalSales = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtBeginningInventory = new javax.swing.JTextField();
        txtPurchasing = new javax.swing.JTextField();
        txtPurchasingReturn = new javax.swing.JTextField();
        txtEndingInventory = new javax.swing.JTextField();
        txtTotalCOGS = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtGrossProvit = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtGeneralExpenditure = new javax.swing.JTextField();
        txtMarketingExpenditure = new javax.swing.JTextField();
        txtHumanResourceExpenditure = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTotalExpenditure = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtNetProvit = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Provit Loss");

        jLabel2.setText("Period :");

        jLabel3.setText("-");

        jLabel4.setText("Sales Revenue");

        jLabel6.setText("Sales Income");

        jLabel7.setText("Sales Discount");

        jLabel8.setText("Sales Return");

        jLabel9.setText("Others Income");

        txtSalesIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalesIncomeActionPerformed(evt);
            }
        });

        jLabel10.setText("Total Sales Revenue");

        txtTotalSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalSalesActionPerformed(evt);
            }
        });

        jLabel11.setText("Beginning Inventory");

        jLabel12.setText("Cost of Goods Sold");

        jLabel13.setText("Purchasing");

        jLabel14.setText("Purchasing Return");

        jLabel15.setText("Ending Inventory");

        jLabel16.setText("Total COGS");

        jLabel17.setText("Gross Provit");

        jLabel18.setText("Operational Expenditure");

        jLabel19.setText("General Expenditure");

        jLabel20.setText("Marketing Expenditure");

        jLabel21.setText("Human Resource Expenditure");

        jLabel22.setText("Total Expenditure");

        jLabel23.setText("Net Provit");

        txtNetProvit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNetProvitActionPerformed(evt);
            }
        });

        jButton1.setText("Find");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(26, 26, 26)
                                .addComponent(txtSalesReturn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtSalesDiscount))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(26, 26, 26)
                                .addComponent(txtSalesIncome))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(16, 16, 16)
                                .addComponent(txtTotalSales))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(9, 9, 9)
                                .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(16, 16, 16)
                                .addComponent(txtOthersIncome)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel16))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtEndingInventory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                                .addComponent(txtTotalCOGS, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtPurchasingReturn))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(txtBeginningInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtPurchasing))))
                                .addGap(105, 105, 105))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(33, 33, 33)
                        .addComponent(txtGrossProvit)
                        .addGap(425, 425, 425))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNetProvit)
                                .addGap(367, 367, 367))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGeneralExpenditure, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(txtMarketingExpenditure)
                                    .addComponent(txtHumanResourceExpenditure)
                                    .addComponent(txtTotalExpenditure))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtSalesIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtSalesDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtSalesReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtOthersIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTotalSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtBeginningInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPurchasing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPurchasingReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEndingInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalCOGS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(txtGrossProvit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtGeneralExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtHumanResourceExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMarketingExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTotalExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNetProvit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNetProvitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNetProvitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNetProvitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        executeOk();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSalesIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalesIncomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalesIncomeActionPerformed

    private void txtTotalSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalSalesActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTotalSalesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtcFrom;
    private com.toedter.calendar.JDateChooser dtcTill;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBeginningInventory;
    private javax.swing.JTextField txtEndingInventory;
    private javax.swing.JTextField txtGeneralExpenditure;
    private javax.swing.JTextField txtGrossProvit;
    private javax.swing.JTextField txtHumanResourceExpenditure;
    private javax.swing.JTextField txtMarketingExpenditure;
    private javax.swing.JTextField txtNetProvit;
    private javax.swing.JTextField txtOthersIncome;
    private javax.swing.JTextField txtPurchasing;
    private javax.swing.JTextField txtPurchasingReturn;
    private javax.swing.JTextField txtSalesDiscount;
    private javax.swing.JTextField txtSalesIncome;
    private javax.swing.JTextField txtSalesReturn;
    private javax.swing.JTextField txtTotalCOGS;
    private javax.swing.JTextField txtTotalExpenditure;
    private javax.swing.JTextField txtTotalSales;
    // End of variables declaration//GEN-END:variables
}
