/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Order.Order;
import Business.Supplier.Product;
import Business.Supplier.ProductDirectory;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class SummaryJPanel extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private EcoSystem system;
    private ProductDirectory revenueProList;
    private ProductDirectory uniqueProList;
    /**
     * Creates new form SummaryJPanel
     */
    public SummaryJPanel(JPanel userProcessContainer, EcoSystem system) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = system;
        revenueProList=new ProductDirectory();
        uniqueProList=new ProductDirectory();
        populateTable();
    }
    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) productTbl.getModel();
        model.setRowCount(0);
        List<Product> products = new ArrayList<>();
        for (Network network : system.getNetworkList()) {
            for (Enterprise enterprise : network.getEntList().getEnterpriseList()) {
                for(Order order : enterprise.getDatastore().getOrderList().getOrders()){
                    for(Product pro : order.getProducts()){
                        products.add(pro);
                    }
                }
            }  
        }
        List<String> nameAndSupplier = new ArrayList<>();
        for(Product p : products){
            if(!nameAndSupplier.contains(p.getName()+" "+p.getSupplierName())){
                nameAndSupplier.add(p.getName()+" "+p.getSupplierName());
            }
        }
        List<Product> mergedProduct = new ArrayList<>();
        for(String s : nameAndSupplier){
            List<Product> sum = new ArrayList<>();
            for(Product p : products){
                if((p.getName()+" "+p.getSupplierName()).equals(s)){
                    sum.add(p);
                }
            }
            int revenue = 0;
            String name = "";
            String supplier = "";
            for(Product p : sum){
                name = p.getName();
                supplier = p.getSupplierName();
                revenue += p.getSellPrice()-p.getOriginPrice()-p.getShippingCost();
            }
            Product pro = new Product(name,supplier,revenue);
            mergedProduct.add(pro);
        }
        for(Product p : mergedProduct){
            Object[] row = new Object[3];
            row[0] = p;
            row[1] = p.getSupplierName();
            row[2] = p.getRevenue();
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

        back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTbl = new javax.swing.JTable();

        back.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        back.setText("<<  Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel1.setText("Overall Top 5 Profitable Products");

        productTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Supplier", "Revenue"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productTbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(back))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        Component[] componentArray = userProcessContainer.getComponents();
        Component component = componentArray[componentArray.length - 1];
        ManageStatisticsJPanel msjp = (ManageStatisticsJPanel) component;
        msjp.populateNetworkComboBox();
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable productTbl;
    // End of variables declaration//GEN-END:variables
}
