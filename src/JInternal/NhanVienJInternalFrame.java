/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JInternal;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import dao.NhanVienDAO;
import model.NhanVien;
import DateConvert.DateConvert;
//import com.kingaspx.main.Menu;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import helper.DialogHepler;

/**
 *
 * @author AhihiDoNgoc
 */
public class NhanVienJInternalFrame extends javax.swing.JInternalFrame {

    private String Path = "";
    private String[] TieuDe = {"Mã Nhân Viên", "Tên Nhân Viên", "Chức Vụ", "Hình thức", "Email", "Số Điện Thoại"};
    private DefaultTableModel table = new DefaultTableModel(TieuDe, 0);
    private NhanVien nv = new NhanVien();
    private NhanVienDAO DAO = new NhanVienDAO();
    private List<NhanVien> list;
    private int index = 0;

    public NhanVienJInternalFrame() {
        initComponents();
        RemoveHeader();
        XuLi();
        Load();
        }

    private void XuLi() {
        ButtonGroup a = new ButtonGroup();
        a.add(rdoNu);
        a.add(rdoNam);
    }

    private void RemoveHeader() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
    }

    private void Load() {
        table.setRowCount(0);
        try {
            this.list = DAO.select();
            for (NhanVien nv : list) {
                Object[] row = {nv.getMaNV(), nv.getTenNV(), nv.getController() ? "Admin" : "Nhân viên", nv.getLoai() ? "Full-time" : "Part-time", nv.getEmail(), nv.getSDT()};
                table.addRow(row);
                tbNhanVien.setModel(table);
            }
        } catch (Exception e) {
            System.out.println("Lỗi phần load dữ liệu " + e);
        }
    }

//    fill Ngược
    private void Edit() {
        this.index = tbNhanVien.getSelectedRow();
        try {
            String MaNV = (String) tbNhanVien.getValueAt(index, 0);
            NhanVien nv = DAO.FindbyID(MaNV);
            System.out.println(nv.getMaNV());
            System.out.println(nv.getController());
            FillNguoc(nv);
        } catch (Exception e) {
            System.out.println("Lỗi phần fill ngược " + e);
        }
    }
    
    private void New()
    {
        txtMaNV.setText("");
        txtPass.setText("");
        txtName.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }

    private void FillNguoc(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtPass.setText(model.getPass());
        txtName.setText(model.getTenNV());
        jdcDate.setDate(Date.from(model.getNgaySinh().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (model.getGioitinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(false);
        }
        txtSDT.setText(model.getSDT());
        txtEmail.setText(model.getEmail());
        if (nv.getLoai()) {
            cbbLoai.setSelectedIndex(0);
        } else {
            cbbLoai.setSelectedIndex(1);
        }
        lbImg.setIcon(ResizeImage(model.getIMG()));
        lblQRCode.setIcon(new ImageIcon(QRHelper.QRHelper.getQRCodeImage(model.getQRCode(), 150, 100)));
        QRHelper.QRHelper.getQRImage(model.getQRCode(), 150, 100);
        if (model.getController()) {
            rbAmin.setSelected(true);
        } else {
            rbAmin.setSelected(false);
        }
    }

    //    Gửi Email mã QRcode cho nhân viên  vừa đăng kí
    private void sendEmailQRCode() throws AddressException, MessagingException, UnsupportedEncodingException {
        Properties mailSeverPropeties;
        Session getMailSession;
        MimeMessage mailMessage;

//        Khởi tạo Mail Server
        mailSeverPropeties = System.getProperties();
        mailSeverPropeties.put("mail.smtp.port", "587");
        mailSeverPropeties.put("mail.smtp.auth", "true");
        mailSeverPropeties.put("mail.smtp.starttls.enable", "true");

//        Get Mail Session
        getMailSession = Session.getDefaultInstance(mailSeverPropeties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(txtEmail.getText())); // Thêm địa chỉ người nhận

// set Subject
        mailMessage.setSubject("Nhà hàng The Restaurant gửi nhân viên mã QRCode");

//    Tạo phần gửi gửi Message
        BodyPart messagePart = new MimeBodyPart();
        messagePart.setText("Nhà hàng The Restaurant gửi bạn " + txtName.getText() + " mã QRCode để đăng nhập hệ thống cũng như chấm công \n Lưu ý: Không được đưa mã này cho bất kì ai");

        BodyPart filePart = new MimeBodyPart();
        filePart.setFileName("QR CODE");
        String qrz = txtMaNV.getText();
        String qr = Base64.getMimeEncoder().encodeToString(qrz.getBytes("UTF-8"));
        DataSource source = new FileDataSource(QRHelper.QRHelper.getQRImage(qr, 400, 400));
        filePart.setDataHandler(new DataHandler(source));

//     Gộp Message và File vào để gửi đi
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messagePart);
        multipart.addBodyPart(filePart);
        mailMessage.setContent(multipart);

//  Gửi mail
        Transport transpot = getMailSession.getTransport("smtp");

// truy cập mail
        transpot.connect("smtp.gmail.com", "baubau2358@gmail.com", "giabao1998");
        transpot.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transpot.close();

    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon IMG = new ImageIcon(ImagePath);
        Image img = IMG.getImage();
        Image newImg = img.getScaledInstance(lbImg.getWidth(), lbImg.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);

        return image;
    }

    public NhanVien getModel() {
        nv.setMaNV(txtMaNV.getText());
        nv.setPass(new String(txtPass.getPassword()));
        nv.setTenNV(txtName.getText());
        nv.setNgaySinh(DateConvert.DatetoLocal(jdcDate.getDate()));
        if (rdoNam.isSelected()) {
            nv.setGioitinh(true);
        } else {
            nv.setGioitinh(false);
        }
        nv.setSDT(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        if (cbbLoai.getSelectedItem().equals("Full-time")) {
            nv.setLoai(true);
        } else {
            nv.setLoai(false);
        }
        nv.setIMG(Path);
        nv.setQRCode(txtMaNV.getText());
        if (rbAmin.isSelected()) {
            nv.setController(true);
        } else {
            nv.setController(false);
        }
        nv.setDiaChi(txtDiaChi.getText());

        return nv;
    }
    
    boolean flag=false;
    void check()
    {   
        String patternSDT = "(03|04|05|07|08|09)[0-9]{8}";
        String testEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        //MaNV không để trống, không trùng
        if(txtMaNV.getText().length()==0)
        {
            DialogHepler.alert(this, "Mã nhân viên không được để trống!");
        }
        List<NhanVien> testTrung= DAO.select();
        for (NhanVien nhanVien : testTrung) 
        {
            if(txtMaNV.getText().equals(nhanVien.getMaNV()))
            {
                DialogHepler.alert(this, "Mã nhân viên không được trùng!");
            }
        }
        
        //MatKhau ít nhất 3 ký tự
        if(txtPass.getText().length()<3)
        {
            DialogHepler.alert(this, "Mật khẩu phải ít nhất 3 ký tự!");
        }
        
        //HoVaTen
        else if(txtName.getText().length()==0)
        {
            DialogHepler.alert(this, "Họ tên không được để trống!");
        }
        //sdt
        else if (txtSDT.getText().length()==0) {
            DialogHepler.alert(this, "Không để trống số điện thoại!");
        } else if (!txtSDT.getText().matches(patternSDT)) {
            DialogHepler.alert(this, "Số điện thoại sai định dạng!");
        }
        //email
        else if (txtEmail.getText().length()==0) {
            DialogHepler.alert(this, "Không để trống email!");
        }
        else if (!txtEmail.getText().matches(testEmail)) {
            DialogHepler.alert(this, "Phải đúng định dạng Email!");
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

        pnlBgr = new javax.swing.JPanel();
        pnlBgrRight = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblQRCode = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblHoTenNV = new javax.swing.JLabel();
        lblBirthDay = new javax.swing.JLabel();
        lblSoD = new javax.swing.JLabel();
        lblSex = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jdcDate = new com.toedter.calendar.JDateChooser();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cbbLoai = new javax.swing.JComboBox<>();
        lbImg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        btTao = new javax.swing.JButton();
        btXoa = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        rbAmin = new javax.swing.JRadioButton();
        lblMaNV = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btQRCode = new javax.swing.JLabel();
        btSendGmail = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();

        pnlBgr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBgrRight.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Made by BHNK");

        lblQRCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQRCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 3));

        lblHoTenNV.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblHoTenNV.setText("Họ tên NV");

        lblBirthDay.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblBirthDay.setText("Ngày sinh:");

        lblSoD.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSoD.setText("Số Điện Thoại:");

        lblSex.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSex.setText("Giới tính:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel22.setText("Email:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel23.setText("Hình Thức Làm Việc:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel24.setText("Địa Chỉ:");

        jdcDate.setBackground(new java.awt.Color(255, 255, 255));
        jdcDate.setDateFormatString("dd/MM/yyyy");

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        rdoNam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        rdoNu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoNu.setText("Nữ");

        cbbLoai.setBackground(new java.awt.Color(146, 97, 67));
        cbbLoai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbbLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full-time", "Part-time" }));

        lbImg.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        lbImg.setForeground(new java.awt.Color(255, 255, 255));
        lbImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 3));
        lbImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImgMouseClicked(evt);
            }
        });

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setText("Nhân Viên Cửa Hàng");

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbNhanVien);

        btTao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Add-32.png"))); // NOI18N
        btTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTaoActionPerformed(evt);
            }
        });

        btXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Delete-32.png"))); // NOI18N
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        btSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Refresh-32.png"))); // NOI18N
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        rbAmin.setBackground(new java.awt.Color(220, 238, 252));
        rbAmin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbAmin.setText("Admin");

        lblMaNV.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblMaNV.setText("Mã nhân viên");

        lblMatKhau.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblMatKhau.setText("Mật khẩu");

        btQRCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/QR-Code-32.png"))); // NOI18N
        btQRCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btQRCodeMouseClicked(evt);
            }
        });

        btSendGmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/icons8_email_45px.png"))); // NOI18N
        btSendGmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSendGmailActionPerformed(evt);
            }
        });

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/Data-Erase-32.png"))); // NOI18N
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBgrRightLayout = new javax.swing.GroupLayout(pnlBgrRight);
        pnlBgrRight.setLayout(pnlBgrRightLayout);
        pnlBgrRightLayout.setHorizontalGroup(
            pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBgrRightLayout.createSequentialGroup()
                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBgrRightLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                        .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbImg, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                        .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btTao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(69, 69, 69)
                                        .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                        .addGap(138, 138, 138)
                                        .addComponent(btSendGmail, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSex)
                                    .addComponent(lblBirthDay)
                                    .addComponent(lblHoTenNV)
                                    .addComponent(lblMaNV)
                                    .addComponent(lblMatKhau))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoNu))
                                    .addComponent(jdcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSoD)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBgrRightLayout.createSequentialGroup()
                                            .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(rbAmin))
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBgrRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(498, 498, 498))
        );
        pnlBgrRightLayout.setVerticalGroup(
            pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                        .addComponent(lbImg, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lblQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                .addComponent(btTao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSendGmail))
                    .addGroup(pnlBgrRightLayout.createSequentialGroup()
                        .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMaNV)
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btQRCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMatKhau)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHoTenNV)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBirthDay, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jdcDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSex, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoNam)
                                        .addComponent(rdoNu))))
                            .addGroup(pnlBgrRightLayout.createSequentialGroup()
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSoD, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbbLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbAmin, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBgrRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74)
                .addComponent(jLabel15)
                .addContainerGap())
        );

        pnlBgr.add(pnlBgrRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1490, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBgr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBgr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImgMouseClicked
        try {
            JFileChooser file = new JFileChooser();
            int result = file.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File seletedFile = file.getSelectedFile();
                FileInputStream input = new FileInputStream(seletedFile);
                String TenHinh = seletedFile.getName();

                String chuoiLuu = Paths.get("").toAbsolutePath() + "/Img/" + TenHinh;
                FileOutputStream output = new FileOutputStream(chuoiLuu);
                System.out.println(chuoiLuu);
                byte[] buffer = new byte[1024];
                int len;

                while ((len = input.read(buffer)) > 0) {
                    output.write(buffer, 0, len);

                }
                input.close();
                output.close();
                Path = Paths.get("").toAbsolutePath().toString() + "/Img/" + TenHinh;

                lbImg.setIcon(ResizeImage(Path));

            } else {
                if (result == JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(this, "Không có ảnh nào được chọn");
                    lbImg.setText("NONE");
                }
            }
        } catch (Exception e) {
            System.out.println(" " + e.toString());
        }
    }//GEN-LAST:event_lbImgMouseClicked

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        if (evt.getClickCount() == 1) {
            index = tbNhanVien.getSelectedRow();
            this.Edit();

        }
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTaoActionPerformed
        check();
        if(flag==true)
        {
            try {
                nv = getModel();
                DAO.insertNV(nv);
                this.Load();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } catch (Exception e) {
                System.out.println("Lỗi phần thêm nhân viên " + e);
            }
        }
    }//GEN-LAST:event_btTaoActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        nv = getModel();
        try {
            int click = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?");
            if (click == JOptionPane.YES_OPTION) {

                DAO.DelNV(nv.getMaNV());

                this.Load();

                JOptionPane.showMessageDialog(this, "Đã xóa nhân viên");
            } else {
                JOptionPane.showMessageDialog(this, "Bạn rảnh ghê");
            }
        } catch (Exception e) {
            System.out.println("Lỗi Xóa nhân viên " + e);
        }
    }//GEN-LAST:event_btXoaActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
      
            try {
                nv = getModel();
                DAO.updateNV(nv);
                Load();
                DialogHepler.alert(this, "Sửa thông tin thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                DialogHepler.alert(this, "Sửa thông tin thất bại!");
            }
        
        
    }//GEN-LAST:event_btSuaActionPerformed

    private void btQRCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btQRCodeMouseClicked
        String Qr = txtMaNV.getText();
        lblQRCode.setIcon(new ImageIcon(QRHelper.QRHelper.getQRCodeImage(Qr, 150, 100)));
    }//GEN-LAST:event_btQRCodeMouseClicked

    private void btSendGmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSendGmailActionPerformed
        try {
            sendEmailQRCode();
        } catch (MessagingException ex) {
            Logger.getLogger(NhanVienJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NhanVienJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSendGmailActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        New();
    }//GEN-LAST:event_btnMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btQRCode;
    private javax.swing.JButton btSendGmail;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btTao;
    private javax.swing.JButton btXoa;
    private javax.swing.JButton btnMoi;
    private javax.swing.JComboBox<String> cbbLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcDate;
    private javax.swing.JLabel lbImg;
    private javax.swing.JLabel lblBirthDay;
    private javax.swing.JLabel lblHoTenNV;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblQRCode;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblSoD;
    private javax.swing.JPanel pnlBgr;
    private javax.swing.JPanel pnlBgrRight;
    private javax.swing.JRadioButton rbAmin;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
