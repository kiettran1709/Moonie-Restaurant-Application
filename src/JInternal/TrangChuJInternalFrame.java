package JInternal;

import javax.swing.JDesktopPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class TrangChuJInternalFrame extends javax.swing.JInternalFrame {

    public TrangChuJInternalFrame() {
        initComponents();
        this.RemoveHeader();
    }

    private void RemoveHeader() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblThemNV = new javax.swing.JLabel();
        lblThemSP = new javax.swing.JLabel();
        lblThemKH = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(1500, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThemNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThemNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThemNVMouseExited(evt);
            }
        });
        getContentPane().add(lblThemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 258, 252, 324));

        lblThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThemSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThemSPMouseExited(evt);
            }
        });
        getContentPane().add(lblThemSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 258, 254, 324));

        lblThemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemKHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThemKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThemKHMouseExited(evt);
            }
        });
        getContentPane().add(lblThemKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(1122, 258, 254, 324));

        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bg.png"))); // NOI18N
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        setSize(new java.awt.Dimension(1500, 660));
    }// </editor-fold>//GEN-END:initComponents

    private void lblThemNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemNVMouseEntered
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bg1.png")));
    }//GEN-LAST:event_lblThemNVMouseEntered

    private void lblThemNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemNVMouseExited
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bg.png")));
    }//GEN-LAST:event_lblThemNVMouseExited

    private void lblThemSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemSPMouseEntered
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bg2.png")));
    }//GEN-LAST:event_lblThemSPMouseEntered

    private void lblThemSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemSPMouseExited
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bg.png")));
    }//GEN-LAST:event_lblThemSPMouseExited

    private void lblThemKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemKHMouseEntered
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bg3.png")));
    }//GEN-LAST:event_lblThemKHMouseEntered

    private void lblThemKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemKHMouseExited
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/bg.png")));
    }//GEN-LAST:event_lblThemKHMouseExited

    private void lblThemNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemNVMouseClicked
        try {
            JDesktopPane desktop = this.getDesktopPane();
            NhanVienJInternalFrame nvjif = new NhanVienJInternalFrame();
            this.dispose();
            desktop.add(nvjif);
            nvjif.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_lblThemNVMouseClicked

    private void lblThemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemSPMouseClicked
        try {
            JDesktopPane desktop = this.getDesktopPane();
            SanPhamJInternalFrame spjif = new SanPhamJInternalFrame();
            this.dispose();
            desktop.add(spjif);
            spjif.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_lblThemSPMouseClicked

    private void lblThemKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemKHMouseClicked
        try {
            JDesktopPane desktop = this.getDesktopPane();
            KhachHangJInternalFrame khjif = new KhachHangJInternalFrame();
            this.dispose();
            desktop.add(khjif);
            khjif.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_lblThemKHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblThemKH;
    private javax.swing.JLabel lblThemNV;
    private javax.swing.JLabel lblThemSP;
    // End of variables declaration//GEN-END:variables
}
