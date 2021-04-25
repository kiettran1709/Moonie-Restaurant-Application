/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JInternal;

import dao.LoaiSPDAO;
import dao.SanPhamDAO;
import helper.DialogHepler;
import helper.ShareHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.LoaiSP;
import model.SanPham;

/**
 *
 * @author AhihiDoNgoc
 */
public class LoaiSPJInternalFrame extends javax.swing.JInternalFrame {

    public LoaiSPJInternalFrame() {
        this.RemoveHeader();
        initComponents();
        this.setTitle("Loại sản phẩm");
        load();
        this.setStatus(true);
    }

    int index = 0;
    LoaiSPDAO dao = new LoaiSPDAO();

    void load() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        try {
            List<LoaiSP> list = dao.select();
            for (LoaiSP loaisp : list) {
                Object[] row
                        = {
                            loaisp.getMALOAISANPHAM(),
                            loaisp.getTENLOAI()
                        };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        LoaiSP model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHepler.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHepler.alert(this, "Thêm mới thất bại!");
        }
    }

    void update() {
        LoaiSP model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHepler.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHepler.alert(this, "Cập nhật thất bại!");
        }
    }

    void delete() {
        if (DialogHepler.confirm(this, "Bạn có muốn xóa hay không?")) {
            String maloaisp = txtMaLoai.getText();
            try {
                dao.delete(maloaisp);
                this.load();
                this.clear();
                DialogHepler.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHepler.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        this.setModel(new LoaiSP());
        this.setStatus(true);
    }

    void edit() {
        try {
            String maloaisp = (String) jTable1.getValueAt(this.index, 0);
            LoaiSP model = dao.findById(maloaisp);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(LoaiSP model) {
        txtMaLoai.setText(model.getMALOAISANPHAM());
        txtTenLoai.setText(model.getTENLOAI());
    }

    LoaiSP getModel() {
        LoaiSP model = new LoaiSP();
        model.setMALOAISANPHAM(txtMaLoai.getText());
        model.setTENLOAI(txtTenLoai.getText());
        return model;
    }

    void setStatus(boolean insertable) {
        txtMaLoai.setEditable(insertable);
        btnThem.setEnabled(insertable);
        btnSua.setEnabled(!insertable);
        btnXoa.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < jTable1.getRowCount() - 1;
    }


    private void RemoveHeader() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        txtMaLoai = new javax.swing.JTextField();
        sptMaSP = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lblTieuDe = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblLoai = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        btnMoi = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(220, 238, 252));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaLoai.setBackground(new java.awt.Color(220, 238, 252));
        txtMaLoai.setBorder(null);
        txtMaLoai.setOpaque(false);
        jPanel1.add(txtMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 270, -1));

        sptMaSP.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(sptMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 270, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Mã loại");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Add-32.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 490, 160, 50));

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Refresh-32.png"))); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, 160, 50));

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Delete-32.png"))); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 490, 160, 50));

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTieuDe.setText("QUẢN LÝ SẢN PHẨM");
        jPanel1.add(lblTieuDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "MALOAISANPHAM", "TENLOAI"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 820, 360));

        lblLoai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLoai.setText("Tên loại");
        jPanel1.add(lblLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));
        jPanel1.add(txtTenLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 270, -1));

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 510, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1465, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 1) {
            index = jTable1.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblLoai;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JSeparator sptMaSP;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtTenLoai;
    // End of variables declaration//GEN-END:variables
}
