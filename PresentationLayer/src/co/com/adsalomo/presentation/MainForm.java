package co.com.adsalomo.presentation;

import co.com.adsalomo.business.PersonBusiness;
import co.com.adsalomo.common.model.PersonModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aye
 */
public class MainForm extends javax.swing.JFrame {

    private final PersonBusiness personBusiness;
    private DefaultTableModel model;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        personBusiness = new PersonBusiness();
        setModel();
        listPerson();
    }

    /**
     * Set Model JTable
     */
    private void setModel() {
        String[] columns = {"Id", "Nombre", "Apellido"};
        Object[][] object = null;
        model = new DefaultTableModel(object, columns) {
            // Si la columna es la 0 (Cero) q no sea editable (id)
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        jTablePerson.setModel(model);
    }

    /**
     * Obtiene los registros de la tabla persona
     */
    private void listPerson() {
        List<PersonModel> list = personBusiness.listPerson();
        model.setRowCount(0);

        if (list != null && list.size() > 0) {
            for (PersonModel row : list) {
                Object[] obj = new Object[3];
                obj[0] = row.getId();
                obj[1] = row.getName();
                obj[2] = row.getLastName();
                model.addRow(obj);
            }
        }
    }

    /**
     * Valida que los campos del formulario esten correctos
     *
     * @return
     */
    private boolean isValidText() {
        if (txtId.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo Id es requerido.");
            return false;
        }

        if (txtNombre.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo Nombre es requerido.");
            return false;
        }

        if (txtApellido.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Campo Apellido es requerido.");
            return false;
        }

        if (!isValidNumber(txtId.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Campo Id es solo acepta valores númericos.");
            return false;
        }
        return true;
    }

    /**
     * Inserta un nuevo registro en la tabla persona
     */
    private void insertPerson() {
        if (!isValidText()) {
            return;
        }

        PersonModel model = new PersonModel();
        model.setId(Integer.parseInt(txtId.getText().trim()));
        model.setName(txtNombre.getText().trim());
        model.setLastName(txtApellido.getText().trim());

        if (personBusiness.insertPerson(model)) {
            JOptionPane.showMessageDialog(null, "Operación completada con éxito.");
            listPerson();
            clearText();
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar la solicitud, contacte con el administrador.");
            clearText();
        }
    }

    /**
     * Limpia los campos del formulario
     */
    private void clearText() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
    }

    /**
     * Valida que el numero sea valido
     *
     * @param number
     * @return
     */
    private boolean isValidNumber(String number) {
        try {
            int resp = Integer.parseInt(number);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Actualiza un registro de la tabla persona
     */
    private void updatePerson() {
        DefaultTableModel tableModel = (DefaultTableModel) jTablePerson.getModel();

        if (jTablePerson.getSelectedRows().length > 0) {
            PersonModel model = new PersonModel();
            model.setId(Integer.parseInt(tableModel.getValueAt(jTablePerson.getSelectedRow(), 0).toString()));
            model.setName(tableModel.getValueAt(jTablePerson.getSelectedRow(), 1).toString());
            model.setLastName(tableModel.getValueAt(jTablePerson.getSelectedRow(), 2).toString());

            if (personBusiness.updatePerson(model)) {
                JOptionPane.showMessageDialog(null, "Operación completada con éxito.");
                listPerson();
                clearText();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar la solicitud, contacte con el administrador.");
                clearText();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un item de la tabla");
        }
    }
    
    /**
     * Elimina un registro de la tabla
     */
    private void deletePerson(){
        DefaultTableModel tableModel = (DefaultTableModel)jTablePerson.getModel();
        
        if(jTablePerson.getSelectedRows().length > 0){
            PersonModel personModel = new PersonModel();
            personModel.setId(Integer.parseInt(tableModel.getValueAt(jTablePerson.getSelectedRow(), 0).toString()));
            
            if (personBusiness.deletePerson(personModel)) {
                JOptionPane.showMessageDialog(null, "Operación completada con éxito.");
                listPerson();
                clearText();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar la solicitud, contacte con el administrador.");
                clearText();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un item de la tabla");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        btnInsertPersona = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePerson = new javax.swing.JTable();
        btnUpdatePerson = new javax.swing.JButton();
        btnDeletePerson = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Registro Persona");

        jLabel2.setText("Id");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        btnInsertPersona.setText("Registrar");
        btnInsertPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertPersonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtId)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtApellido)))
                    .addComponent(btnInsertPersona))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnInsertPersona)
                .addGap(151, 151, 151))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));

        jTablePerson.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePerson.setEditingColumn(0);
        jScrollPane1.setViewportView(jTablePerson);

        btnUpdatePerson.setText("Actualizar");
        btnUpdatePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePersonActionPerformed(evt);
            }
        });

        btnDeletePerson.setText("Eliminar");
        btnDeletePerson.setMaximumSize(new java.awt.Dimension(79, 23));
        btnDeletePerson.setMinimumSize(new java.awt.Dimension(79, 23));
        btnDeletePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePersonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnUpdatePerson)
                        .addGap(37, 37, 37)
                        .addComponent(btnDeletePerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 169, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdatePerson)
                    .addComponent(btnDeletePerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertPersonaActionPerformed
        insertPerson();
    }//GEN-LAST:event_btnInsertPersonaActionPerformed

    private void btnUpdatePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePersonActionPerformed
        updatePerson();
    }//GEN-LAST:event_btnUpdatePersonActionPerformed

    private void btnDeletePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePersonActionPerformed
        // TODO add your handling code here
        deletePerson();
    }//GEN-LAST:event_btnDeletePersonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletePerson;
    private javax.swing.JButton btnInsertPersona;
    private javax.swing.JButton btnUpdatePerson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePerson;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
