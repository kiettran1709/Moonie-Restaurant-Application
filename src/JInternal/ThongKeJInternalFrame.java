/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JInternal;

import dao.DonHangDAO;
import dao.ThongKeDAO;
import helper.SQLConnect;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.DonHang;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;


/**
 *
 * @author AhihiDoNgoc
 */
public class ThongKeJInternalFrame extends javax.swing.JInternalFrame {

    public ThongKeJInternalFrame() {
        this.RemoveHeader();
        initComponents();
        this.setTitle("Thống kê");
        fillComboBoxThang();
        fillTableThongKeDoanhThuThang();
        fillTableThongKeDoanhThuNam();
//        fillComboBoxNgay();
        fillTableThongKeDoanhThuNgay();
//        fillTableThongKeSanPham();
    }

    ThongKeDAO dao = new ThongKeDAO();
    DonHangDAO dhdao = new DonHangDAO();

    void fillComboBoxThang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboThang.getModel();
        model.removeAllElements();

        List<DonHang> list = dhdao.select();
        for (DonHang dh : list) {
            int thang = dh.getNgayBan().getYear();
            if (model.getIndexOf(thang) < 0) {
                model.addElement(thang);
            }
        }
        cboThang.setSelectedIndex(0);
    }

//    void fillComboBoxNgay() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNgay.getModel();
//        model.removeAllElements();
//        List<DonHang> list = dhdao.select();
//        for (DonHang dh : list) {
//           
//                model.addElement(dh);
//            
//        }
//        cboNgay.setSelectedIndex(0);
//    }
    void fillTableThongKeDoanhThuThang() {
        DefaultTableModel model = (DefaultTableModel) tblTKDTThang.getModel();
        model.setRowCount(0);

        if (cboThang.getSelectedItem() == null) {

        } else {
            int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
            List<Object[]> list = dao.getThongKeDoanhThang(thang);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }

    void fillTableThongKeDoanhThuNam() {
        DefaultTableModel model = (DefaultTableModel) tblTKDTNam.getModel();
        model.setRowCount(0);

        List<Object[]> list = dao.getThongKeDoanhNam();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    void fillTableThongKeDoanhThuNgay() {
        DefaultTableModel model = (DefaultTableModel) tblTKDTNgay.getModel();
        model.setRowCount(0);

        List<Object[]> list = dao.getThongKeDoanhThuNgay();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

//    void fillTableThongKeSanPham()
//    {
//        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
//        model.setRowCount(0);
//        
//        List<Object[]> list = dao.getThongKeSanPham();
//        for(Object[] row : list)
//        {
//            model.addRow(row);
//        }
//    }
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
        tabs = new javax.swing.JTabbedPane();
        pnlThang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTKDTThang = new javax.swing.JTable();
        btnTKThang = new javax.swing.JButton();
        cboThang = new javax.swing.JComboBox<>();
        lblThang = new javax.swing.JLabel();
        pnlNam = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTKDTNam = new javax.swing.JTable();
        btnTKNam = new javax.swing.JButton();
        pnlNgay = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTKDTNgay = new javax.swing.JTable();
        btnTKNgay = new javax.swing.JButton();
        jdcTo = new com.toedter.calendar.JDateChooser();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        lblFrom = new javax.swing.JLabel();
        lblTo = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setForeground(new java.awt.Color(0, 51, 51));

        pnlThang.setBackground(new java.awt.Color(255, 255, 255));
        pnlThang.setForeground(new java.awt.Color(255, 102, 0));

        tblTKDTThang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "THÁNG", "TỔNG TIỀN"
            }
        ));
        jScrollPane1.setViewportView(tblTKDTThang);

        btnTKThang.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnTKThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/graphic.png"))); // NOI18N
        btnTKThang.setText("Thống kê theo tháng");
        btnTKThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKThangActionPerformed(evt);
            }
        });

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "Item 2", "Item 3", "Item 4" }));
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });

        lblThang.setForeground(new java.awt.Color(255, 255, 255));
        lblThang.setText("THÁNG");

        javax.swing.GroupLayout pnlThangLayout = new javax.swing.GroupLayout(pnlThang);
        pnlThang.setLayout(pnlThangLayout);
        pnlThangLayout.setHorizontalGroup(
            pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1431, Short.MAX_VALUE)
                    .addGroup(pnlThangLayout.createSequentialGroup()
                        .addComponent(lblThang)
                        .addGap(18, 18, 18)
                        .addComponent(cboThang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThangLayout.createSequentialGroup()
                        .addComponent(btnTKThang)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThangLayout.setVerticalGroup(
            pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThang)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnTKThang)
                .addContainerGap())
        );

        tabs.addTab("THÁNG", pnlThang);

        pnlNam.setBackground(new java.awt.Color(255, 255, 255));
        pnlNam.setForeground(new java.awt.Color(0, 51, 51));

        tblTKDTNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NĂM", "TỔNG TIỀN"
            }
        ));
        jScrollPane2.setViewportView(tblTKDTNam);

        btnTKNam.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnTKNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/graphic.png"))); // NOI18N
        btnTKNam.setText("Thống kê theo năm");
        btnTKNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNamLayout = new javax.swing.GroupLayout(pnlNam);
        pnlNam.setLayout(pnlNamLayout);
        pnlNamLayout.setHorizontalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1431, Short.MAX_VALUE)
                    .addGroup(pnlNamLayout.createSequentialGroup()
                        .addComponent(btnTKNam)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlNamLayout.setVerticalGroup(
            pnlNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNamLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(btnTKNam)
                .addContainerGap())
        );

        tabs.addTab("NĂM", pnlNam);

        pnlNgay.setBackground(new java.awt.Color(255, 255, 255));
        pnlNgay.setForeground(new java.awt.Color(255, 102, 0));

        tblTKDTNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "THÁNG", "TỔNG TIỀN"
            }
        ));
        jScrollPane3.setViewportView(tblTKDTNgay);

        btnTKNgay.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnTKNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/graphic.png"))); // NOI18N
        btnTKNgay.setText("Thống kê theo ngày");
        btnTKNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKNgayActionPerformed(evt);
            }
        });

        jdcTo.setBackground(new java.awt.Color(255, 255, 255));
        jdcTo.setDateFormatString("dd-MM-yyyy");

        jdcFrom.setBackground(new java.awt.Color(255, 255, 255));
        jdcFrom.setDateFormatString("dd-MM-yyyy");

        lblFrom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFrom.setText("From");

        lblTo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTo.setText("To");

        javax.swing.GroupLayout pnlNgayLayout = new javax.swing.GroupLayout(pnlNgay);
        pnlNgay.setLayout(pnlNgayLayout);
        pnlNgayLayout.setHorizontalGroup(
            pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1431, Short.MAX_VALUE))
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(lblFrom)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215)
                        .addComponent(lblTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcTo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTKNgay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNgayLayout.setVerticalGroup(
            pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFrom)
                    .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jdcTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNgayLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTo))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTKNgay)
                .addContainerGap())
        );

        tabs.addTab("NGÀY", pnlNgay);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1460, 390));

        lblTitle.setBackground(new java.awt.Color(0, 0, 0));
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setText("THỐNG KÊ DOANH THU");
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1534, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTKNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKNamActionPerformed
        try {
            String query = "SELECT YEAR(NGAYBAN), SUM(TONGTIEN) \n"
                    + "FROM DONHANG\n"
                    + "GROUP BY YEAR(NGAYBAN)";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(SQLConnect.ConnectDb(), query);
            JFreeChart chart = ChartFactory.createBarChart("Thống kê doanh thu từng năm", "NĂM BÁN", "TỔNG TIỀN", dataset, PlotOrientation.VERTICAL, true, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;

            ChartFrame frame = new ChartFrame("Bảng thống kê", chart);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTKNamActionPerformed

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        fillTableThongKeDoanhThuThang();
    }//GEN-LAST:event_cboThangActionPerformed

    private void btnTKThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKThangActionPerformed

        try {
            int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
            String query = "SELECT MONTH(NGAYBAN) THANG, SUM(TONGTIEN) TONGTIEN\n"
                    + "FROM DONHANG\n"
                    + "WHERE YEAR(NGAYBAN) = " + thang + "\n"
                    + "GROUP BY MONTH(NGAYBAN)";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(SQLConnect.ConnectDb(), query);
            JFreeChart chart = ChartFactory.createBarChart("Thống kê doanh thu theo tháng", "THÁNG BÁN", "TỔNG TIỀN", dataset, PlotOrientation.VERTICAL, true, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;

            ChartFrame frame = new ChartFrame("Bảng thống kê", chart);
            frame.setSize(1000, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTKThangActionPerformed

    private void btnTKNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKNgayActionPerformed
        // TODO add your handling code here
       try {
            SimpleDateFormat dateFrom1 = new SimpleDateFormat("yyyy-MM-dd");
            String theDateFrom = dateFrom1.format(jdcFrom.getDate());

            SimpleDateFormat dateTo1 = new SimpleDateFormat("yyyy-MM-dd");
            String theDateTo = dateTo1.format(jdcTo.getDate());

            String query = "SELECT NGAYBAN, SUM(TONGTIEN) as TONGTIEN\n"
                    + "	FROM DONHANG\n"
                    + "	WHERE NGAYBAN BETWEEN '"+theDateFrom+"' AND '"+theDateTo+"'\n"
                    + "	GROUP BY NGAYBAN";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(SQLConnect.ConnectDb(), query);
            JFreeChart chart = ChartFactory.createLineChart("Thống kê doanh thu từng ngày", "NGÀY BÁN", "TỔNG TIỀN", dataset, PlotOrientation.VERTICAL, true, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;

            ChartFrame frame = new ChartFrame("Bảng thống kê", chart);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTKNgayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTKNam;
    private javax.swing.JButton btnTKNgay;
    private javax.swing.JButton btnTKThang;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTo;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblThang;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTo;
    private javax.swing.JPanel pnlNam;
    private javax.swing.JPanel pnlNgay;
    private javax.swing.JPanel pnlThang;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblTKDTNam;
    private javax.swing.JTable tblTKDTNgay;
    private javax.swing.JTable tblTKDTThang;
    // End of variables declaration//GEN-END:variables
}
