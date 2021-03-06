/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JInternal;

import dao.LoaiTheKHDAO;
import dao.TheKhachHangDAO;
import helper.DialogHepler;
import helper.JdbcHelper;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.LoaiTheKH;
import model.NhanVien;
import model.TheKhachHang;

/**
 *
 * @author AhihiDoNgoc
 */
public class KhachHangJInternalFrame extends javax.swing.JInternalFrame {

    public KhachHangJInternalFrame() {
        initComponents();
        this.RemoveHeader();
        this.loadKH();
        this.loadTheKH();
        this.loadComboBox();
        this.disableDiemLoaiThe();
    }

    int index = 0;
    TheKhachHangDAO khdao = new TheKhachHangDAO();
    LoaiTheKHDAO ltdao = new LoaiTheKHDAO();

    public void loadKH() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            List<TheKhachHang> list = khdao.select();
            for (TheKhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getSDT(),
                    kh.getEmail(),
                    kh.getDiemTich(),
                    ltdao.findten(kh.getMaLoaiThe())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "L???i truy v???n d??? li???u Kh??ch H??ng!");
        }
    }

    public void timMaKH() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            String MaKH = txtTimMaKH.getText();
            List<TheKhachHang> list = khdao.selectByMaKH(MaKH);
            for (TheKhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getSDT(),
                    kh.getEmail(),
                    kh.getDiemTich(),
                    ltdao.findten(kh.getMaLoaiThe())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Kh??ng t??m ???????c m?? kh??ch h??ng!");
        }
    }

    public void timSDT() {
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            String SDT = txtNhapSoDT.getText();
            List<TheKhachHang> list = khdao.selectBySDT(SDT);
            for (TheKhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getSDT(),
                    kh.getEmail(),
                    kh.getDiemTich(),
                    ltdao.findten(kh.getMaLoaiThe())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "Kh??ng t??m ???????c s??? ??i???n tho???i kh??ch h??ng!");
        }
    }
  
//    G???i email cho kh??ch h??ng
     private void sendEmailQRCode() throws AddressException, MessagingException, UnsupportedEncodingException {
        Properties mailSeverPropeties;
        Session getMailSession;
        MimeMessage mailMessage;

//        Kh???i t???o Mail Server
        mailSeverPropeties = System.getProperties();
        mailSeverPropeties.put("mail.smtp.port", "587");
        mailSeverPropeties.put("mail.smtp.auth", "true");
        mailSeverPropeties.put("mail.smtp.starttls.enable", "true");

//        Get Mail Session
        getMailSession = Session.getDefaultInstance(mailSeverPropeties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(txtEmail.getText())); // Th??m ?????a ch??? ng?????i nh???n

// set Subject
        mailMessage.setSubject("Nh?? h??ng The Restaurant g???i nh??n vi??n m?? QRCode");

//    T???o ph???n g???i g???i Message
        BodyPart messagePart = new MimeBodyPart();
        messagePart.setText("Nh?? h??ng The Reastaurant g???i b???n " + txtTenKH.getText() + " m?? QRCode ????? ????ng nh???p t??ch ??i???m v?? nh???n ??u ????i");

        BodyPart filePart = new MimeBodyPart();
        filePart.setFileName("QR CODE");
        String qrz = txtSDT.getText();
        String qr = Base64.getMimeEncoder().encodeToString(qrz.getBytes("UTF-8"));
        DataSource source = new FileDataSource(QRHelper.QRHelper.getQRImage(qr, 400, 400));
        filePart.setDataHandler(new DataHandler(source));

//     G???p Message v?? File v??o ????? g???i ??i
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messagePart);
        multipart.addBodyPart(filePart);
        mailMessage.setContent(multipart);

//  G???i mail
        Transport transpot = getMailSession.getTransport("smtp");

// truy c???p mail
        transpot.connect("smtp.gmail.com", "baubau2358@gmail.com", "giabao1998");
        transpot.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transpot.close();

    }
    public void loadTheKH() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiThe.getModel();
        model.setRowCount(0);
        try {
            List<LoaiTheKH> list = ltdao.select();
            for (LoaiTheKH lt : list) {
                Object[] row = {lt.getMaLoaiKhachHang(), lt.getTenLoai()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHepler.alert(this, "L???i truy v???n d??? li???u Lo???i Th???!");
        }
    }

    public void loadComboBox() {
        ResultSet rs = null;
        try {
            String sql = "select * from LOAITHEKHACHHANG";
            rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                String tenLoai = rs.getString("TENLOAI");
                cboLoaiThe.addItem(tenLoai);
            }
        } catch (Exception e) {
        }
    }

    public void insertLT() {
        String model = txtTenThe.getText();
        try {
            ltdao.insert(model);
            this.loadTheKH();
            this.clearLT();
            DialogHepler.alert(this, "Th??m th??nh c??ng!");
        } catch (Exception e) {
            DialogHepler.alert(this, "Th??m th???t b???i!");
            System.out.println(e.getMessage());
        }
    }

    public void insert() {
        TheKhachHang model = getModel();
        try {
            khdao.insert(model);
            this.loadKH();
            this.clear();
            DialogHepler.alert(this, "Th??m th??nh c??ng!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            DialogHepler.alert(this, "Th??m th???t b???i!");
        }
    }

    public void update() {
        try {
            TheKhachHang model = getModel();
            khdao.update(model);
            this.loadKH();
            this.clear();
            DialogHepler.alert(this, "S???a th??nh c??ng!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            DialogHepler.alert(this, "S???a th???t b???i!");
        }
    }

    public void deleteLT() {
        if (DialogHepler.confirm(this, "B???n th???c s??? mu???n x??a lo???i th??? n??y!")) {
            String malt = txtMaThe.getText();
            try {
                ltdao.Delete(malt);
                this.loadTheKH();
                this.clearLT();
                DialogHepler.alert(this, "X??a th??nh c??ng!");
            } catch (Exception e) {
                DialogHepler.alert(this, "X??a th???t b???i!");
            }
        }
    }

    public void delete() {
        if (DialogHepler.confirm(this, "B???n th???c s??? mu???n x??a kh??ch h??ng n??y!")) {
            String makh = txtMaKH.getText();
            try {
                khdao.delete(makh);
                this.loadKH();
                this.clear();
                DialogHepler.alert(this, "X??a th??nh c??ng!");
            } catch (Exception e) {
                DialogHepler.alert(this, "X??a th???t b???i!");
            }
        }
    }

    public void disableDiemLoaiThe() {
        cboLoaiThe.setEnabled(false);
        txtDiemTich.setEditable(false);
    }

    public void clear() {
        this.setModel(new TheKhachHang());
        this.disableDiemLoaiThe();
        cboLoaiThe.setSelectedIndex(0);
    }

    public void clearLT() {
        this.setModel(new LoaiTheKH());
    }

    private void RemoveHeader() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
    }

    public void setModel(TheKhachHang model) {
        txtMaKH.setText(Integer.toString(model.getMaKH()));
        txtTenKH.setText(model.getTenKH());
        txtSDT.setText(model.getSDT());
        txtEmail.setText(model.getEmail());
        txtDiemTich.setText(Integer.toString(model.getDiemTich()));
        cboLoaiThe.setSelectedIndex(model.getMaLoaiThe() - 1);
    }

    public void setModel(LoaiTheKH model) {
        txtMaThe.setText(Integer.toString(model.getMaLoaiKhachHang()));
        txtTenThe.setText(model.getTenLoai());
    }

    TheKhachHang getModel() {
        TheKhachHang model = new TheKhachHang();
        model.setMaKH(Integer.parseInt(txtMaKH.getText()));
        model.setTenKH(txtTenKH.getText());
        model.setSDT(txtSDT.getText());
        model.setEmail(txtEmail.getText());
        model.setDiemTich(Integer.parseInt(txtDiemTich.getText()));
        model.setMaLoaiThe(cboLoaiThe.getSelectedIndex() + 1);
        return model;
    }
    LoaiTheKH getModelLT() {
        LoaiTheKH model = new LoaiTheKH();
        model.setMaLoaiKhachHang(Integer.parseInt(txtMaThe.getText()));
        model.setTenLoai(txtTenThe.getText());
        return model;
    }
    
    boolean flag=false;
    void check()
    {   
        String patternSDT = "(03|04|05|07|08|09)[0-9]{8}";
        String testEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        
        //HoVaTen
        if(txtTenKH.getText().length()==0)
        {
            DialogHepler.alert(this, "H??? t??n kh??ng ???????c ????? tr???ng!");
        }
        //sdt
        else if (txtSDT.getText().length()==0) {
            DialogHepler.alert(this, "Kh??ng ????? tr???ng s??? ??i???n tho???i!");
        } else if (!txtSDT.getText().matches(patternSDT)) {
            DialogHepler.alert(this, "S??? ??i???n tho???i sai ?????nh d???ng!");
        }
        //email
        else if (txtEmail.getText().length()==0) {
            DialogHepler.alert(this, "Kh??ng ????? tr???ng email!");
        }
        else if (!txtEmail.getText().matches(testEmail)) {
            DialogHepler.alert(this, "Ph???i ????ng ?????nh d???ng Email!");
            flag = false;
        }
        
        else
        {
            flag=true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDiemTich = new javax.swing.JLabel();
        lblLoaiThe = new javax.swing.JLabel();
        cboLoaiThe = new javax.swing.JComboBox<>();
        lblTimMaKH = new javax.swing.JLabel();
        btnTimMa = new javax.swing.JButton();
        lblNhapSoDT = new javax.swing.JLabel();
        btnTimSDT = new javax.swing.JButton();
        lblMaThe = new javax.swing.JLabel();
        lblTenThe = new javax.swing.JLabel();
        btnThemLoaiThe = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        btnXoaLoaiThe = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoaiThe = new javax.swing.JTable();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiemTich = new javax.swing.JTextField();
        txtTimMaKH = new javax.swing.JTextField();
        txtNhapSoDT = new javax.swing.JTextField();
        txtMaThe = new javax.swing.JTextField();
        txtTenThe = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTieuDe.setText("QU???N L?? KH??CH H??NG");
        pnlMain.add(lblTieuDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        lblMaKH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaKH.setText("M?? kh??ch h??ng");
        pnlMain.add(lblMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        lblTenKH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenKH.setText("T??n kh??ch h??ng");
        pnlMain.add(lblTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        lblSDT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDT.setText("S??? ??i???n tho???i");
        pnlMain.add(lblSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email");
        pnlMain.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        lblDiemTich.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDiemTich.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiemTich.setText("??i???m t??ch");
        pnlMain.add(lblDiemTich, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        lblLoaiThe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLoaiThe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoaiThe.setText("Lo???i th???");
        pnlMain.add(lblLoaiThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        cboLoaiThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiTheActionPerformed(evt);
            }
        });
        pnlMain.add(cboLoaiThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 270, -1));

        lblTimMaKH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTimMaKH.setText("Nh???p m?? KH");
        pnlMain.add(lblTimMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, -1, -1));

        btnTimMa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Find-32.png"))); // NOI18N
        btnTimMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaActionPerformed(evt);
            }
        });
        pnlMain.add(btnTimMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, 90, 50));

        lblNhapSoDT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNhapSoDT.setText("Nh???p s??? ??T");
        pnlMain.add(lblNhapSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, -1, -1));

        btnTimSDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Find-32.png"))); // NOI18N
        btnTimSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSDTActionPerformed(evt);
            }
        });
        pnlMain.add(btnTimSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 90, 50));

        lblMaThe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaThe.setText("M?? th???");
        pnlMain.add(lblMaThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 90, -1, -1));

        lblTenThe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenThe.setText("T??n th???");
        pnlMain.add(lblTenThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 140, -1, -1));

        btnThemLoaiThe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Add-New-32.png"))); // NOI18N
        btnThemLoaiThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiTheActionPerformed(evt);
            }
        });
        pnlMain.add(btnThemLoaiThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 570, 90, 50));

        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Erase-32.png"))); // NOI18N
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });
        pnlMain.add(btnXoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 570, 90, 50));

        btnXoaLoaiThe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Garbage-Closed-32.png"))); // NOI18N
        btnXoaLoaiThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiTheActionPerformed(evt);
            }
        });
        pnlMain.add(btnXoaLoaiThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 570, 90, 50));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Add-32.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlMain.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 160, 50));

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Refresh-32.png"))); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlMain.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 160, 50));

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Delete-32.png"))); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlMain.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 160, 50));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Erase-32.png"))); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnlMain.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 160, 50));

        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "M?? KH", "T??N KH", "S??T", "EMAIL", "??I???M T??CH", "LO???I TH???"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblGridView.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblGridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGridView);
        if (tblGridView.getColumnModel().getColumnCount() > 0) {
            tblGridView.getColumnModel().getColumn(5).setResizable(false);
        }

        pnlMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 660, 290));

        tblLoaiThe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "M?? LO???I TH???", "T??N LO???I TH???"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblLoaiThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiTheMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoaiThe);

        pnlMain.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 370, 90));

        txtMaKH.setEditable(false);
        txtMaKH.setText("0");
        pnlMain.add(txtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 270, -1));
        pnlMain.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 270, -1));
        pnlMain.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 270, -1));
        pnlMain.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 270, -1));

        txtDiemTich.setEditable(false);
        txtDiemTich.setText("0");
        pnlMain.add(txtDiemTich, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 270, -1));
        pnlMain.add(txtTimMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 230, -1));
        pnlMain.add(txtNhapSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 230, -1));

        txtMaThe.setEditable(false);
        txtMaThe.setText("0");
        pnlMain.add(txtMaThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 90, 230, -1));
        pnlMain.add(txtTenThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 140, 230, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/icons8_email_45px.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlMain.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblLoaiTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiTheMouseClicked
        int _index = tblLoaiThe.getSelectedRow();
        String ma = tblLoaiThe.getValueAt(_index, 0).toString();
        LoaiTheKH ltkh = ltdao.findById(ma);
        this.setModel(ltkh);
    }//GEN-LAST:event_tblLoaiTheMouseClicked

    private void tblGridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseClicked
        int _index = tblGridView.getSelectedRow();
        String ma = tblGridView.getValueAt(_index, 0).toString();
        TheKhachHang tkh = khdao.findById(ma);
        this.setModel(tkh);
        this.disableDiemLoaiThe();
    }//GEN-LAST:event_tblGridViewMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        check();
        if(flag==true)
        {
            this.insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaLoaiTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiTheActionPerformed
        this.deleteLT();
    }//GEN-LAST:event_btnXoaLoaiTheActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        this.clearLT();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void btnThemLoaiTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiTheActionPerformed
        this.insertLT();
    }//GEN-LAST:event_btnThemLoaiTheActionPerformed

    private void btnTimSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSDTActionPerformed
        this.timSDT();
    }//GEN-LAST:event_btnTimSDTActionPerformed

    private void btnTimMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaActionPerformed
        this.timMaKH();
    }//GEN-LAST:event_btnTimMaActionPerformed

    private void cboLoaiTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiTheActionPerformed

    }//GEN-LAST:event_cboLoaiTheActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            sendEmailQRCode();
        } catch (MessagingException ex) {
            Logger.getLogger(NhanVienJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NhanVienJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemLoaiThe;
    private javax.swing.JButton btnTimMa;
    private javax.swing.JButton btnTimSDT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaLoaiThe;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cboLoaiThe;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDiemTich;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblLoaiThe;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblMaThe;
    private javax.swing.JLabel lblNhapSoDT;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenThe;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTimMaKH;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTable tblLoaiThe;
    private javax.swing.JTextField txtDiemTich;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaThe;
    private javax.swing.JTextField txtNhapSoDT;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenThe;
    private javax.swing.JTextField txtTimMaKH;
    // End of variables declaration//GEN-END:variables

}
