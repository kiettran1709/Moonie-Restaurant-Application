package frame;

import JInternal.HDJInternalFrame;
import JInternal.HDJInternalFrame;
import JInternal.KhachHangJInternalFrame;
import JInternal.NhanVienJInternalFrame;
import JInternal.SanPhamJInternalFrame;
import JInternal.ThongKeJInternalFrame;
import JInternal.TrangChuJInternalFrame;
import helper.DialogHepler;
import helper.ShareHelper;
import java.awt.Color;
import java.awt.Frame;
import org.hamcrest.DiagnosingMatcher;

public class MainFrame extends javax.swing.JFrame {

    boolean check = true;
    int x;
    int y;

    public MainFrame() {
        initComponents();
        lblLogout.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblTrangChu = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblThongKe = new javax.swing.JLabel();
        lblDangXuat = new javax.swing.JLabel();
        lblDoiMK = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblPanel_Main = new javax.swing.JLabel();
        Desktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeaderMouseDragged(evt);
            }
        });
        pnlHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeaderMousePressed(evt);
            }
        });
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMinimize.setBackground(new java.awt.Color(51, 51, 51));
        btnMinimize.setForeground(new java.awt.Color(51, 51, 51));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Minus-16(1).png"))); // NOI18N
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setOpaque(true);
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
        pnlHeader.add(btnMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1625, 0, -1, 25));

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Close-16-1.png"))); // NOI18N
        btnExit.setContentAreaFilled(false);
        btnExit.setOpaque(true);
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
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        pnlHeader.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1675, 0, -1, 25));

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1720, 30));

        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseExited(evt);
            }
        });
        getContentPane().add(lblTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 220, 50));

        lblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseExited(evt);
            }
        });
        getContentPane().add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 220, 50));

        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseExited(evt);
            }
        });
        getContentPane().add(lblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 220, 50));

        lblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseExited(evt);
            }
        });
        getContentPane().add(lblKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 220, 50));

        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseExited(evt);
            }
        });
        getContentPane().add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 220, 50));

        lblThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThongKeMouseExited(evt);
            }
        });
        getContentPane().add(lblThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 220, 50));

        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseExited(evt);
            }
        });
        getContentPane().add(lblDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 522, 180, 56));

        lblDoiMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDoiMKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDoiMKMouseExited(evt);
            }
        });
        getContentPane().add(lblDoiMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 580, 180, 57));

        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/logout-doimatkhau.png"))); // NOI18N
        lblLogout.setPreferredSize(new java.awt.Dimension(181, 115));
        getContentPane().add(lblLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 522, -1, 115));

        lblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserMouseClicked(evt);
            }
        });
        getContentPane().add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 610, 30, 35));

        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_main.png"))); // NOI18N
        getContentPane().add(lblPanel_Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        Desktop.setBackground(new java.awt.Color(255, 255, 255));
        Desktop.setForeground(new java.awt.Color(255, 255, 255));
        Desktop.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                DesktopAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        getContentPane().add(Desktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 1500, 630));

        setSize(new java.awt.Dimension(1720, 660));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMouseClicked
        if (check) {
            lblLogout.setVisible(true);
            check = false;
        } else {
            lblLogout.setVisible(false);
            check = true;
        }
    }//GEN-LAST:event_lblUserMouseClicked

    private void lblTrangChuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseEntered
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_hover1.png")));
    }//GEN-LAST:event_lblTrangChuMouseEntered

    private void lblTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseExited
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_main.png")));
    }//GEN-LAST:event_lblTrangChuMouseExited

    private void lblNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseEntered
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_hover2.png")));
    }//GEN-LAST:event_lblNhanVienMouseEntered

    private void lblNhanVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseExited
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_main.png")));
    }//GEN-LAST:event_lblNhanVienMouseExited

    private void lblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseEntered
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_hover3.png")));
    }//GEN-LAST:event_lblSanPhamMouseEntered

    private void lblSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseExited
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_main.png")));
    }//GEN-LAST:event_lblSanPhamMouseExited

    private void lblKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseEntered
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_hover4.png")));
    }//GEN-LAST:event_lblKhachHangMouseEntered

    private void lblKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseExited
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_main.png")));
    }//GEN-LAST:event_lblKhachHangMouseExited

    private void lblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseEntered
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_hover5.png")));
    }//GEN-LAST:event_lblHoaDonMouseEntered

    private void lblHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseExited
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_main.png")));
    }//GEN-LAST:event_lblHoaDonMouseExited

    private void lblThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseEntered
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_hover6.png")));
    }//GEN-LAST:event_lblThongKeMouseEntered

    private void lblThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseExited
        lblPanel_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/panel_main.png")));
    }//GEN-LAST:event_lblThongKeMouseExited

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        try {
            TrangChuJInternalFrame tcjif = new TrangChuJInternalFrame();
            Desktop.removeAll();
            Desktop.add(tcjif);
            tcjif.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void pnlHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlHeaderMousePressed

    private void pnlHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMouseDragged
        int x1 = evt.getXOnScreen();
        int y1 = evt.getYOnScreen();
        this.setLocation(x1 - x, y1 - y);
    }//GEN-LAST:event_pnlHeaderMouseDragged

    private void lblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseClicked
        if (ShareHelper.USER.getController() == false) {
            DialogHepler.alert(this, "Chỉ có Admin được phép truy cập!");
        } else {
            try {
                NhanVienJInternalFrame nvjif = new NhanVienJInternalFrame();
                Desktop.removeAll();
                Desktop.add(nvjif);
                nvjif.show();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }//GEN-LAST:event_lblNhanVienMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        if (ShareHelper.USER.getController() == false) {
            DialogHepler.alert(this, "Chỉ có Admin được phép truy cập!");
        } else {
            try {
                SanPhamJInternalFrame spjif = new SanPhamJInternalFrame();
                Desktop.removeAll();
                Desktop.add(spjif);
                spjif.show();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseClicked
        if (ShareHelper.USER.getController() == false) {
            DialogHepler.alert(this, "Chỉ có Admin được phép truy cập!");
        } else {
            try {
                KhachHangJInternalFrame khjif = new KhachHangJInternalFrame();
                Desktop.removeAll();
                Desktop.add(khjif);
                khjif.show();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }//GEN-LAST:event_lblKhachHangMouseClicked

    private void lblThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseClicked
        try {
            ThongKeJInternalFrame tkjif = new ThongKeJInternalFrame();
            Desktop.removeAll();
            Desktop.add(tkjif);
            tkjif.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_lblThongKeMouseClicked

    private void DesktopAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_DesktopAncestorAdded
        try {
            TrangChuJInternalFrame tcjif = new TrangChuJInternalFrame();
            Desktop.removeAll();
            Desktop.add(tcjif);
            tcjif.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_DesktopAncestorAdded

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        if (ShareHelper.USER.getController() == false) {
            DialogHepler.alert(this, "Chỉ có Admin được phép tắt ứng dụng!");
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(232, 17, 35));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnExitMouseExited

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
        try {
            HDJInternalFrame HDJInternalFrame = new HDJInternalFrame();
            Desktop.removeAll();
            Desktop.add(HDJInternalFrame);
            HDJInternalFrame.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void lblDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseEntered
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/logout-doimatkhau_hover1.png")));
    }//GEN-LAST:event_lblDangXuatMouseEntered

    private void lblDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseExited
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/logout-doimatkhau.png")));
    }//GEN-LAST:event_lblDangXuatMouseExited

    private void lblDoiMKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMKMouseEntered
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/logout-doimatkhau-hover2.png")));
    }//GEN-LAST:event_lblDoiMKMouseEntered

    private void lblDoiMKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMKMouseExited
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/logout-doimatkhau.png")));
    }//GEN-LAST:event_lblDoiMKMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (ShareHelper.USER.getController() == false) {
            DialogHepler.alert(this, "Chỉ có Admin mới được thoát chương trình!");
        } else {
            System.exit(0);
        }

    }//GEN-LAST:event_btnExitActionPerformed

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        // TODO add your handling code here:
        DangXuat DX = new DangXuat();
        DX.setVisible(true);
    }//GEN-LAST:event_lblDangXuatMouseClicked

    private void lblDoiMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMKMouseClicked
       ResetPass RP = new ResetPass();
       RP.setVisible(true);
    }//GEN-LAST:event_lblDoiMKMouseClicked

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblDoiMK;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPanel_Main;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlHeader;
    // End of variables declaration//GEN-END:variables
}
