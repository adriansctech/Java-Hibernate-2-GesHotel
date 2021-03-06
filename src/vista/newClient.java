/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
Null pointer al introducir nuevo huesped
*/




package vista;

import controlador.Operaciones;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Huesped;

/**
 *
 * @author vesprada
 */
public class newClient extends javax.swing.JInternalFrame {

    /**
     * Creates new form newClient
     */
    
    
    private boolean check = false;
    private boolean checkDni = false;    
    private Huesped huesped;
    private char individual;
    private Operaciones oper ;
    
    public newClient(Operaciones oper) {
        initComponents();
        cleanAllTxt();
        setEnableTxt(true);
        this.oper = oper;
    }    
    
    public void cleanAllTxt(){
        txtNewClientDni.setText("");
        txtNameNewClient.setText("");
        txtSurnameNewClient.setText("");
        txtCityNewClient.setText("");
        txtAdressNewClient.setText("");
    }
        
    public void setEnableTxt(boolean pass){
        txtNewClientDni.setEnabled(pass);
        txtNameNewClient.setEnabled(pass);
        txtSurnameNewClient.setEnabled(pass);
        txtCityNewClient.setEnabled(pass);
        txtAdressNewClient.setEnabled(pass);
    }
    
    //This method check the user has inserted all the information of the new client
    public boolean checkFields(){
        if(txtNewClientDni.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert a DNI of new guest.");
            txtNewClientDni.requestFocus();
        }
        else if(txtNameNewClient.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert a name of new guest.");
            txtNameNewClient.requestFocus();
        }else if(txtSurnameNewClient.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert a surname of new guest.");
            txtSurnameNewClient.requestFocus();
        }else if(txtAdressNewClient.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert a adress of new guest.");
            txtAdressNewClient.requestFocus();
        }else if(txtCityNewClient.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert a city of new guest.");
            txtCityNewClient.requestFocus();
        }else{
            check = true;
        }
        return check ;
    }
    
    
    //Method to check the last letter of dni field
    public boolean checkDni(){
        if(txtNewClientDni.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Insert a valid DNI number.");
            txtNewClientDni.requestFocus();
            checkDni = false;
        }else{
            String dni = txtNewClientDni.getText();
            dni.trim().toLowerCase();
            char letra= dni.charAt(8);
            System.out.println("CHAR DNI IS; "+letra);        
                if(letra=='1'||letra=='2'||letra=='3'||letra=='4'||letra=='5'
                    ||letra=='6'||letra=='7'||letra=='8'||letra=='9'||letra=='0'){
                    JOptionPane.showMessageDialog(null, "Please insert a correctly word of DNI.");
                    txtNewClientDni.requestFocus();
                }else{
                    checkDni= true;
                } 
        }
        return checkDni;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNewClientDni = new javax.swing.JTextField();
        txtNameNewClient = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSurnameNewClient = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAdressNewClient = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel6 = new javax.swing.JLabel();
        txtCityNewClient = new javax.swing.JTextField();
        jbtSaveNewClient = new javax.swing.JButton();
        jbtCancelNewClient = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jCheckBoxNewClient = new javax.swing.JCheckBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new java.awt.Dimension(480, 450));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Insert information of new Client", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Nombre; ");

        jLabel2.setText("DNI; ");

        txtNewClientDni.setText("jTextField1");
        txtNewClientDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNewClientDniKeyTyped(evt);
            }
        });

        txtNameNewClient.setText("jTextField2");

        jLabel3.setText("Apellidos; ");

        txtSurnameNewClient.setText("jTextField1");

        jLabel4.setText("Dirección; ");

        txtAdressNewClient.setText("jTextField1");

        jLabel5.setText("Fecha Nacimiento; ");

        jLabel6.setText("Población; ");

        txtCityNewClient.setText("jTextField2");

        jbtSaveNewClient.setText("Guardar");
        jbtSaveNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSaveNewClientActionPerformed(evt);
            }
        });

        jbtCancelNewClient.setText("Cancelar");
        jbtCancelNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelNewClientActionPerformed(evt);
            }
        });

        jLabel7.setText("Individual; ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNewClientDni, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtAdressNewClient)
                            .addComponent(txtSurnameNewClient)
                            .addComponent(txtNameNewClient)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(32, 32, 32)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxNewClient)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jbtSaveNewClient)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtCancelNewClient))
                                .addComponent(txtCityNewClient)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNewClientDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtNameNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtSurnameNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAdressNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCityNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCheckBoxNewClient))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSaveNewClient)
                    .addComponent(jbtCancelNewClient))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //Method to save new Guest-- Work correctly
    private void jbtSaveNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSaveNewClientActionPerformed
       huesped = new Huesped();
        if(jCheckBoxNewClient.isSelected()){
           individual = 'Y';
       }else{
           individual = 'N';
       }
        huesped.setDni(txtNewClientDni.getText());
        huesped.setNombre(txtNameNewClient.getText());
        huesped.setApellidos(txtSurnameNewClient.getText());
        huesped.setDireccion(txtAdressNewClient.getText());
        huesped.setPoblacion(txtCityNewClient.getText());
        huesped.setFechaNac(dateChooserCombo1.getSelectedDate().getTime());
        huesped.setIndividual(individual);
        oper.insertNewClient(huesped);
        this.dispose();
    }//GEN-LAST:event_jbtSaveNewClientActionPerformed

    private void txtNewClientDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewClientDniKeyTyped
        if(txtNewClientDni.getText().length()>8){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Insert a ccorectly DNI please.");
        }
    }//GEN-LAST:event_txtNewClientDniKeyTyped

    private void jbtCancelNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelNewClientActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbtCancelNewClientActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JCheckBox jCheckBoxNewClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtCancelNewClient;
    private javax.swing.JButton jbtSaveNewClient;
    private javax.swing.JTextField txtAdressNewClient;
    private javax.swing.JTextField txtCityNewClient;
    private javax.swing.JTextField txtNameNewClient;
    private javax.swing.JTextField txtNewClientDni;
    private javax.swing.JTextField txtSurnameNewClient;
    // End of variables declaration//GEN-END:variables
}
