/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import dao.NhanVienDAO;
import helper.DialogHepler;
import helper.ShareHelper;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.JPanel;
import model.NhanVien;

/**
 *
 * @author This my name
 */
public class SignInFrame extends javax.swing.JFrame {

    /**
     * Creates new form signin
     */
    NhanVienDAO dao = new NhanVienDAO();
    int x;
    int y;
    public SignInFrame() {
        initComponents();
//        setSize(1011, 550);
        this.setLocationRelativeTo(null);
    }
    
    public void Login(){
        String manv = txtNhapTK.getText();
        String matkhau = new String(txtPassword.getPassword());
        try {
            NhanVien nhanvien = dao.FindbyID(manv);
            if(nhanvien!=null){
                
                String matkhau2 = nhanvien.getPass();
                if(matkhau2.equalsIgnoreCase(matkhau)){
                    ShareHelper.USER = nhanvien;
                    if(matkhau2.equals("1111")){
                        ResetPass RP = new ResetPass();
                        RP.setVisible(true);
                        
                    }else{
                    DialogHepler.alert(this, "Đăng nhập thành công!");
                    System.out.println(ShareHelper.USER);
                    new MainFrame().setVisible(true);
                    this.dispose();
                    }
                }
                else{
                    DialogHepler.alert(this, "Mật khẩu không đúng!");
                }
            }else{
                DialogHepler.alert(this, "Tên tài khoản không đúng!");
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
   
    
//    public

      public void setColor(JPanel p)
    {
        p.setBackground(new Color(68,154,207));
    }
    
    public void resetColor(JPanel p1)
    {
        p1.setBackground(new Color(52,116,131));
    }
    
    public void openQRCode(){
       new Menu().setVisible(true);
       this.dispose();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQRCode = new javax.swing.JLabel();
        btnMinimize = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblDrinkAndThink = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtNhapTK = new javax.swing.JTextField();
        pnlDN = new javax.swing.JPanel();
        lblDN = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        lblMK = new javax.swing.JLabel();
        lblTenNguoiDung = new javax.swing.JLabel();
        lblBgr = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1002, 498));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQRCode.setBackground(new java.awt.Color(102, 51, 0));
        lblQRCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblQRCode.setForeground(new java.awt.Color(255, 255, 255));
        lblQRCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQRCode.setText("QR CODE");
        lblQRCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255), 4));
        lblQRCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQRCodeMouseClicked(evt);
            }
        });
        getContentPane().add(lblQRCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 130, 50));

        btnMinimize.setBackground(new java.awt.Color(255, 255, 255));
        btnMinimize.setForeground(new java.awt.Color(255, 255, 255));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minus-16(1).png"))); // NOI18N
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minus-16.png"))); // NOI18N
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseExited(evt);
            }
        });
        getContentPane().add(btnMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, -1, -1));

        btnExit.setBackground(new java.awt.Color(52, 116, 131));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close-16-1.png"))); // NOI18N
        btnExit.setContentAreaFilled(false);
        btnExit.setOpaque(true);
        btnExit.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close-16.png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, -1, -1));

        lblDrinkAndThink.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        lblDrinkAndThink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDrinkAndThink.setText("The Dream Maker");
        getContentPane().add(lblDrinkAndThink, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 320, 80));

        txtPassword.setBackground(new java.awt.Color(52, 116, 131));
        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(204, 255, 255));
        txtPassword.setText("jPasswordField1");
        txtPassword.setBorder(null);
        txtPassword.setOpaque(false);
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 380, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/logo.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 390, 120, 100));

        txtNhapTK.setBackground(new java.awt.Color(52, 116, 131));
        txtNhapTK.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNhapTK.setForeground(new java.awt.Color(204, 255, 255));
        txtNhapTK.setText("Nhập tài khoản");
        txtNhapTK.setBorder(null);
        txtNhapTK.setOpaque(false);
        txtNhapTK.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNhapTKFocusGained(evt);
            }
        });
        getContentPane().add(txtNhapTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 380, -1));

        pnlDN.setBackground(new java.awt.Color(52, 116, 131));
        pnlDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlDNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlDNMouseExited(evt);
            }
        });

        lblDN.setBackground(new java.awt.Color(102, 51, 0));
        lblDN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDN.setForeground(new java.awt.Color(255, 255, 255));
        lblDN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDN.setText("Đăng nhập");
        lblDN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255), 4));
        lblDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDNMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDNLayout = new javax.swing.GroupLayout(pnlDN);
        pnlDN.setLayout(pnlDNLayout);
        pnlDNLayout.setHorizontalGroup(
            pnlDNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDNLayout.createSequentialGroup()
                .addComponent(lblDN, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDNLayout.setVerticalGroup(
            pnlDNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDNLayout.createSequentialGroup()
                .addComponent(lblDN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnlDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, 130, 50));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 380, 10));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 380, 10));

        lblMK.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblMK.setForeground(new java.awt.Color(255, 255, 255));
        lblMK.setText("Mật khẩu");
        getContentPane().add(lblMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, -1, -1));

        lblTenNguoiDung.setBackground(new java.awt.Color(220, 238, 252));
        lblTenNguoiDung.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblTenNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        lblTenNguoiDung.setText("Tài khoản");
        getContentPane().add(lblTenNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        lblBgr.setBackground(new java.awt.Color(255, 255, 102));
        lblBgr.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblBgr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBgr.setMaximumSize(new java.awt.Dimension(1002, 498));
        lblBgr.setMinimumSize(new java.awt.Dimension(1002, 498));
        lblBgr.setPreferredSize(new java.awt.Dimension(1002, 498));
        getContentPane().add(lblBgr, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 510));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bgrleft.gif"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bgrright.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1002, 498));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        txtPassword.setText("");
    }//GEN-LAST:event_txtPasswordFocusGained

    private void txtNhapTKFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNhapTKFocusGained
        txtNhapTK.setText("");
    }//GEN-LAST:event_txtNhapTKFocusGained

    private void pnlDNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDNMouseEntered
        setColor(pnlDN);
    }//GEN-LAST:event_pnlDNMouseEntered

    private void pnlDNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDNMouseExited
        resetColor(pnlDN);
    }//GEN-LAST:event_pnlDNMouseExited

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setBackground(new Color(229, 229, 229));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(232, 17, 35));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(52,116,131));
    }//GEN-LAST:event_btnExitMouseExited

    private void lblQRCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQRCodeMouseClicked
        openQRCode();
    }//GEN-LAST:event_lblQRCodeMouseClicked

    private void lblDNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDNMouseClicked
        Login();
    }//GEN-LAST:event_lblDNMouseClicked

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
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBgr;
    private javax.swing.JLabel lblDN;
    private javax.swing.JLabel lblDrinkAndThink;
    private javax.swing.JLabel lblMK;
    private javax.swing.JLabel lblQRCode;
    private javax.swing.JLabel lblTenNguoiDung;
    private javax.swing.JPanel pnlDN;
    private javax.swing.JTextField txtNhapTK;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
