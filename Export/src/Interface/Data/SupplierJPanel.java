/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Data;

import Business.Supplier.Supplier;
import Business.Supplier.SupplierDirectory;
import Business.Enterprise.Enterprise;
import Business.User.User;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peichun
 */
public class SupplierJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private SupplierDirectory supplierDirectory;
    private Enterprise enterprise;
    private User user;
    /**
     * Creates new form SupplierJPanel
     */
    public SupplierJPanel (JPanel upc, SupplierDirectory sd, User user, Enterprise enterprise) {
        initComponents();
        userProcessContainer = upc;
        supplierDirectory = sd;
        this.enterprise = enterprise;
        this.user = user;
        
        refreshTable();
        supplierDirectory.setSpList(enterprise.getDatastore().getSupList());
    }
    
        public void refreshTable() {
        int rowCount = supplierTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel)supplierTable.getModel();
        for(int i = rowCount - 1; i >=0; i--) {
            model.removeRow(i);
        }
         
        
        for(Supplier s : enterprise.getDatastore().getSupList()) {
            Object row[] = new Object[1];
            row[0] = s;
            model.addRow(row);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        removejButton1 = new javax.swing.JButton();
        addsupplierjButton3 = new javax.swing.JButton();
        backjButton2 = new javax.swing.JButton();
        backjButton4 = new javax.swing.JButton();

        jLabel1.setText("Manage Supplier Work Area");

        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Supplier Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(supplierTable);

        removejButton1.setText("Remove");
        removejButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removejButton1ActionPerformed(evt);
            }
        });

        addsupplierjButton3.setText("Add Supplier");
        addsupplierjButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsupplierjButton3ActionPerformed(evt);
            }
        });

        backjButton2.setText("<<Back");
        backjButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButton2ActionPerformed(evt);
            }
        });

        backjButton4.setText("Next>>");
        backjButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(addsupplierjButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removejButton1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backjButton4)
                            .addComponent(backjButton2))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addsupplierjButton3)
                    .addComponent(removejButton1)
                    .addComponent(backjButton2))
                .addGap(18, 18, 18)
                .addComponent(backjButton4)
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void removejButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removejButton1ActionPerformed
        // TODO add your handling code here:
        int row = supplierTable.getSelectedRow();

        if(row<0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Supplier s = (Supplier)supplierTable.getValueAt(row, 0);
        enterprise.getDatastore().getSupList().remove(s);
        refreshTable();
    }//GEN-LAST:event_removejButton1ActionPerformed

    private void addsupplierjButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsupplierjButton3ActionPerformed
        // TODO add your handling code here:

        //int i=1;
        SupplierAdd as = new SupplierAdd(userProcessContainer, supplierDirectory);
        userProcessContainer.add("AddSupplier", as);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_addsupplierjButton3ActionPerformed

    private void backjButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButton2ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backjButton2ActionPerformed

    private void backjButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButton4ActionPerformed
        SupplierLogin la = new SupplierLogin(userProcessContainer, supplierDirectory, user, enterprise);
        userProcessContainer.add("LoginAirline",la);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_backjButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addsupplierjButton3;
    private javax.swing.JButton backjButton2;
    private javax.swing.JButton backjButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removejButton1;
    private javax.swing.JTable supplierTable;
    // End of variables declaration//GEN-END:variables
}
