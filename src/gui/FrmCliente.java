package gui;

import clases.Cliente;
import com.sun.glass.events.KeyEvent;
import java.io.IOException;

/**
 * @author Jheyson Gaona
 */
public class FrmCliente extends javax.swing.JFrame {

    private String urlIp;
    private final String puertoPagHtm = "8080";

    public FrmCliente() {
        initComponents();
        setLocationRelativeTo(null);
        this.btnEnviar.setEnabled(false);
    }

    private void enviar() {
        String solicitudUrl = lblUrl.getText();
        String ip = txtIp.getText();
        int puerto = Integer.parseInt(txtPuerto.getText());
        Cliente objClient = new Cliente(solicitudUrl, ip, puerto);
        try {
            Cliente.main();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void activarBtnEnviar(java.awt.event.KeyEvent evt) {
        char enter = evt.getKeyChar();
        if (enter == KeyEvent.VK_ENTER) {
            btnEnviar.doClick();
        } else {
            urlIp = txtIp.getText();
            lblUrl.setText("http://" + urlIp + ":" + puertoPagHtm + "/index");
            if (txtIp.getText().isEmpty() || txtPuerto.getText().isEmpty()) {
                this.btnEnviar.setEnabled(false);
            } else {
                this.btnEnviar.setEnabled(true);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlClienteHttp = new javax.swing.JPanel();
        lblClienteHttp = new javax.swing.JLabel();
        lblIp = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        lblPuerto = new javax.swing.JLabel();
        txtPuerto = new javax.swing.JTextField();
        lblSolicitudHttp = new javax.swing.JLabel();
        lblUrl = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlClienteHttp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblClienteHttp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblClienteHttp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClienteHttp.setText("Cliente Http");
        lblClienteHttp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIp.setText("Direccion IP Servidor:");

        txtIp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIp.setText("127.0.0.1");
        txtIp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIpKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIpKeyReleased(evt);
            }
        });

        lblPuerto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPuerto.setText("Puerto del Servidor:");

        txtPuerto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPuerto.setText("4444");
        txtPuerto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPuertoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPuertoKeyReleased(evt);
            }
        });

        lblSolicitudHttp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSolicitudHttp.setText("Solicitud Http:");

        lblUrl.setText("http:// :8080/index");

        btnEnviar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEnviar.setText("Enviar Solicitud");
        btnEnviar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlClienteHttpLayout = new javax.swing.GroupLayout(pnlClienteHttp);
        pnlClienteHttp.setLayout(pnlClienteHttpLayout);
        pnlClienteHttpLayout.setHorizontalGroup(
            pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClienteHttp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlClienteHttpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClienteHttpLayout.createSequentialGroup()
                        .addGroup(pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIp))
                        .addGap(38, 38, 38)
                        .addGroup(pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPuerto)
                            .addComponent(txtPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(pnlClienteHttpLayout.createSequentialGroup()
                        .addComponent(lblSolicitudHttp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUrl)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClienteHttpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        pnlClienteHttpLayout.setVerticalGroup(
            pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteHttpLayout.createSequentialGroup()
                .addComponent(lblClienteHttp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIp)
                    .addComponent(lblPuerto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlClienteHttpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSolicitudHttp)
                    .addComponent(lblUrl))
                .addGap(18, 18, 18)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlClienteHttp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlClienteHttp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviar();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txtIpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIpKeyReleased
        activarBtnEnviar(evt);
    }//GEN-LAST:event_txtIpKeyReleased

    private void txtPuertoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuertoKeyReleased
        activarBtnEnviar(evt);
    }//GEN-LAST:event_txtPuertoKeyReleased

    private void txtPuertoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuertoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPuertoKeyTyped

    private void txtIpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIpKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIpKeyTyped

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
            java.util.logging.Logger.getLogger(FrmCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel lblClienteHttp;
    private javax.swing.JLabel lblIp;
    private javax.swing.JLabel lblPuerto;
    private javax.swing.JLabel lblSolicitudHttp;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JPanel pnlClienteHttp;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtPuerto;
    // End of variables declaration//GEN-END:variables
}
