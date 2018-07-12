package gui;

import clases.Cliente;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author Jheyson Gaona
 */
public class FrmCliente extends javax.swing.JFrame {

    private String urlIp;
    private final String puertoPagHtm = "8080";

    public FrmCliente() {
        initComponents();
        setLocationRelativeTo(null);
        config();
    }

    private void config() {
        this.btnEnviar.setEnabled(false);
        lblClienteHttp.setOpaque(true);
        pnlClienteHttp.setBackground(Color.white);
        cargarImg("/img/redes.jpg", lblImg);
    }

    private void cargarImg(String link, JLabel etiqueta) {
        ImageIcon fondo = new ImageIcon(getClass().getResource(link));
        ImageIcon Automatico = new ImageIcon(fondo.getImage().getScaledInstance(
                etiqueta.getWidth(), etiqueta.getHeight(), Image.SCALE_DEFAULT));
        etiqueta.setIcon(Automatico);
    }

    private void enviar() {
        String solicitudUrl = lblUrl.getText();
        String ip = txtIp.getText();
        int puerto = Integer.parseInt(txtPuerto.getText());
        Cliente objClient = new Cliente(solicitudUrl, ip, puerto);
        try {
            objClient.cliente();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
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
        lblImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor Http");
        setResizable(false);

        pnlClienteHttp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlClienteHttp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblClienteHttp.setBackground(new java.awt.Color(60, 196, 172));
        lblClienteHttp.setFont(new java.awt.Font("FreeSerif", 1, 24)); // NOI18N
        lblClienteHttp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClienteHttp.setText("Cliente Http");
        lblClienteHttp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlClienteHttp.add(lblClienteHttp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 391, 40));

        lblIp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIp.setForeground(new java.awt.Color(255, 125, 0));
        lblIp.setText("Direccion IP Servidor: ");
        pnlClienteHttp.add(lblIp, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 53, -1, -1));

        txtIp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIpKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIpKeyReleased(evt);
            }
        });
        pnlClienteHttp.add(txtIp, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 74, 185, 35));

        lblPuerto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPuerto.setForeground(new java.awt.Color(255, 125, 0));
        lblPuerto.setText("Puerto del Servidor: ");
        pnlClienteHttp.add(lblPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 53, -1, -1));

        txtPuerto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPuerto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPuertoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPuertoKeyReleased(evt);
            }
        });
        pnlClienteHttp.add(txtPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 74, 140, 35));

        lblSolicitudHttp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSolicitudHttp.setForeground(new java.awt.Color(255, 125, 0));
        lblSolicitudHttp.setText("Solicitud Http:");
        pnlClienteHttp.add(lblSolicitudHttp, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 128, -1, -1));

        lblUrl.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        lblUrl.setForeground(new java.awt.Color(254, 254, 254));
        lblUrl.setText("http:// :8080/index");
        pnlClienteHttp.add(lblUrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 127, -1, -1));

        btnEnviar.setBackground(new java.awt.Color(1, 172, 1));
        btnEnviar.setFont(new java.awt.Font("Liberation Serif", 1, 18)); // NOI18N
        btnEnviar.setText("Enviar Solicitud");
        btnEnviar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        pnlClienteHttp.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 162, 217, 40));
        pnlClienteHttp.add(lblImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 40, 390, 172));

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
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblIp;
    private javax.swing.JLabel lblPuerto;
    private javax.swing.JLabel lblSolicitudHttp;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JPanel pnlClienteHttp;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtPuerto;
    // End of variables declaration//GEN-END:variables
}
