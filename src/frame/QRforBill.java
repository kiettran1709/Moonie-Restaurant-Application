/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import JInternal.HDJInternalFrame;
import JInternal.HDJInternalFrame;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import dao.TheKhachHangDAO;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Base64;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JOptionPane;
import model.TheKhachHang;
import org.omg.CosNaming.NamingContextPackage.NotFound;




/**
 *
 * @author Asus
 */
public class QRforBill extends javax.swing.JFrame implements Runnable, ThreadFactory {
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static  final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private TheKhachHangDAO dao = new TheKhachHangDAO();
    
    
    
    /**
     * Creates new form QRforBill
     */
    public QRforBill() {
        initComponents();
        setLocationRelativeTo(null);
        initWebcam();
    }
    private void Exit(){
        this.dispose();
    }
    
    private void initWebcam(){
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); // 0 is defaukt camera
        webcam.setViewSize(size);
        
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        
       pnCam.add(panel,  new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,470,300));
       executor.execute(this);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnCam = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btExit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnCam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnCam.setPreferredSize(new java.awt.Dimension(470, 300));
        pnCam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(pnCam, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("THẺ KHÁCH HÀNG");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 140, 20));

        btExit.setText("Thoát");
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });
        jPanel1.add(btExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, -1));

        jButton1.setText("Số điện thoại");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExitActionPerformed
        Exit();
    }//GEN-LAST:event_btExitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String SDT = JOptionPane.showInputDialog(this,"Hãy nhập số điện thoại khách hàng");
        try{
           HDJInternalFrame.khachHang  = dao.findBySDT(SDT);
        if(SDT.equalsIgnoreCase(HDJInternalFrame.khachHang.getSDT())){
                         
                        JOptionPane.showMessageDialog(this, "Khách hàng "+ HDJInternalFrame.khachHang.getTenKH() + "\n Điểm hiện có: " + HDJInternalFrame.khachHang.getDiemTich());   
                        HDJInternalFrame.GiamGia(HDJInternalFrame.khachHang.getDiemTich());
                        this.dispose();
            }
        }catch(Exception e){
         HDJInternalFrame.khachHang = null;
            JOptionPane.showMessageDialog(this, "Không có khách hàng trong danh sách ");
            this.dispose();    
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(QRforBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QRforBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QRforBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QRforBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QRforBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnCam;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
       do{
           try{
               Thread.sleep(100);
           }catch(InterruptedException e){
               e.printStackTrace();
           }
           Result result = null;
           BufferedImage image = null;
           
           if(webcam.isOpen()){
               if((image = webcam.getImage())==null){
                   continue;
               }
           }
           
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                
            try{
                result = new MultiFormatReader().decode(bitmap);
            }catch(NotFoundException e){
                //No result...
            }    
            
            if(result != null){
                byte[] decode = Base64.getMimeDecoder().decode(result.getText());
                try{
                    String resultDecode = new String(decode,"UTF-8");
                    HDJInternalFrame.khachHang = dao.findBySDT(resultDecode);
                    
                   
                    if(HDJInternalFrame.khachHang.getSDT().equalsIgnoreCase(resultDecode)){
                        HDJInternalFrame.khachHang = HDJInternalFrame.khachHang;
                        JOptionPane.showMessageDialog(this, "Khách hàng "+ HDJInternalFrame.khachHang.getTenKH());
                        HDJInternalFrame.GiamGia(HDJInternalFrame.khachHang.getDiemTich());
                        webcam.close();
                        this.dispose();
                    }
                }catch(Exception e){
                     HDJInternalFrame.khachHang = null;
                     JOptionPane.showMessageDialog(this, "Không có khách hàng trong danh sách");
                     this.dispose();
                }
            }
       }while(true);
    }

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r,"My Thread");
      t.setDaemon(true);
      return t;
    }
}
