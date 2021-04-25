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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.LoaiSP;
import model.NhanVien;
import model.SanPham;

/**
 *
 * @author AhihiDoNgoc
 */
public class SanPhamJInternalFrame extends javax.swing.JInternalFrame {

    public SanPhamJInternalFrame() {
        initComponents();
        this.RemoveHeader();
        this.setTitle("Sản phẩm");
        load();
        fillComboBox();
        clear();
        this.setStatus(true);
        selectComboBox();
        tblGridView.setDefaultEditor(Object.class, null);
    }

    int index = 0;
    SanPhamDAO dao = new SanPhamDAO();
    LoaiSPDAO loaidao = new LoaiSPDAO();

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = dao.select();
            for (SanPham cd : list) {
                Object[] row
                        = {
                            cd.getMASP(),
                            cd.getMALOAISANPHAM(),
                            cd.getTENSP(),
                            cd.getSOLUONG(),
                            cd.getDONGIA(),
                            cd.getHINH(),
                            cd.getGHICHU()
                        };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        SanPham model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHepler.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHepler.alert(this, "Thêm mới thất bại!");
            e.printStackTrace();
        }
    }

    void update() {
        SanPham model = getModel();
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
            Integer masp =  Integer.valueOf(cboLoai.getToolTipText());
            try {
                dao.delete(masp);
                this.load();
                this.clear();
                DialogHepler.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHepler.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        SanPham model = new SanPham();
        LoaiSP loaisp = (LoaiSP) cboLoai.getSelectedItem();
        model.setMALOAISANPHAM(loaisp.getMALOAISANPHAM());
        this.setModel(model);
    }

    void edit() {
        try {
            Integer masp = (Integer) tblGridView.getValueAt(this.index, 0);
            String loaisp = (String) tblGridView.getValueAt(this.index, 1);
            
            List<LoaiSP> ds = loaidao.select();
            int indextam = 0;
            
            for (int i = 0; i < ds.size(); i++)
            {
                if (ds.get(i).getMALOAISANPHAM().equals(loaisp))
                {
                    indextam = i;
                    break;
                }
            }
            cboLoai.setSelectedIndex(indextam);
            SanPham model = dao.findById(masp);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(SanPham model) {
        cboLoai.setToolTipText(String.valueOf(model.getMASP()));
        cboLoai.setSelectedItem(loaidao.findById(model.getMALOAISANPHAM()));
        txtTenSP.setText(model.getTENSP());
        txtSoLuong.setText(String.valueOf(model.getSOLUONG()));
        txtDonGia.setText(String.valueOf(model.getDONGIA()));
        lblHinh.setToolTipText(model.getHINH());
        if (model.getHINH() != null) {
            lblHinh.setIcon(ShareHelper.readLogo(model.getHINH()));
        }
        txtGhiChu.setText(model.getGHICHU());
    }

    SanPham getModel() {
        SanPham model = new SanPham();
        LoaiSP loaisp = (LoaiSP) cboLoai.getSelectedItem();
        model.setMALOAISANPHAM(loaisp.getMALOAISANPHAM());
        model.setTENSP(txtTenSP.getText());
        model.setSOLUONG(Integer.valueOf(txtSoLuong.getText()));
        model.setDONGIA(Integer.valueOf(txtDonGia.getText()));
        model.setHINH(lblHinh.getToolTipText());
        model.setGHICHU(txtGhiChu.getText());
        model.setMASP(Integer.valueOf(cboLoai.getToolTipText()));
        return model;
    }

    void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnSua.setEnabled(!insertable);
        btnXoa.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblGridView.getRowCount() - 1;
    }

    void selectImage() {
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
            if (ShareHelper.saveLogo(file)) {
                // Hiển thị hình lên form
                lblHinh.setIcon(ShareHelper.readLogo(file.getName()));
                lblHinh.setToolTipText(file.getName());
            }
        }
    }
    
    void selectComboBox()
    {
        LoaiSP loaisp = (LoaiSP) cboLoai.getSelectedItem();
    }
    
    void fillComboBox()
    {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoai.getModel();
        model.removeAllElements();
        try {
            List<LoaiSP> list = loaidao.select();
            for (LoaiSP loai :list)
            {
                model.addElement(loai);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private void RemoveHeader() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
    }
    
    boolean flag = false;

    void check() {
        if(txtTenSP.getText().length()==0)
        {
            DialogHepler.alert(this, "Tên sản phẩm không được để trống!");
        }
        List<SanPham> testTrung= dao.select();
        for (SanPham sanPham : testTrung) 
        {
            if(txtTenSP.getText().equals(sanPham.getTENSP()))
            {
                DialogHepler.alert(this, "Tên sản phẩm không được trùng!");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lblTieuDe = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();
        cboLoai = new javax.swing.JComboBox<>();
        lblLoai = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();

        setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 630));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setText("Hình");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tên sản phẩm");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Số lượng");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Đơn giá");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        jPanel1.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 150, 170));

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

        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MASP", "MALOAISANPHAM", "TENSP", "SOLUONG", "DONGIA", "HINH", "GHICHU"
            }
        ));
        tblGridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridViewMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGridView);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 820, 360));

        jPanel1.add(cboLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 190, -1));

        lblLoai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLoai.setText("Loại");
        jPanel1.add(lblLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        lblGhiChu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGhiChu.setText("Ghi chú");
        jPanel1.add(lblGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, -1));
        jPanel1.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 270, -1));
        jPanel1.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 270, -1));
        jPanel1.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 270, -1));
        jPanel1.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, 280, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblGridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseClicked

        if (evt.getClickCount() == 1) {
            index = tblGridView.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblGridViewMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        check();
        if (flag == true) {
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        this.selectImage();
    }//GEN-LAST:event_lblHinhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblLoai;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
