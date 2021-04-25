/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.NhanVien;
import DateConvert.DateConvert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import helper.JdbcHelper;

/**
 *
 * @author Asus
 */
public class NhanVienDAO {
    private ResultSet rs;
  
// Thêm Nhân Viên
    public void insertNV(NhanVien model){
        String sql = "insert into NHANVIEN(MANV,TENNV,MATKHAU,GIOITINH,NGAYSINH,SDT,EMAIL, DIACHI,LOAI,CHUCVU,HINH,QRCODE) "
                           + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,model.getMaNV(),model.getTenNV(),model.getPass(),model.getGioitinh(),DateConvert.LocaltoSqlDate(model.getNgaySinh()),
                                     model.getSDT(),model.getEmail(),model.getDiaChi(),model.getLoai(),model.getController(),model.getIMG(),model.getQRCode());
    }
    
//    Update Nhân Viên
    public void updateNV(NhanVien model){
        String sql = "update NHANVIEN set TENNV=?, MATKHAU=?,  NGAYSINH=?, GIOITINH=?, SDT=?, EMAIL=?, LOAI=?, HINH=?, QRCODE=?, CHUCVU=? where MANV=?";
        JdbcHelper.executeUpdate(sql, model.getTenNV(), model.getPass(),DateConvert.LocaltoSqlDate( model.getNgaySinh()), model.getGioitinh(), model.getSDT(), model.getEmail(), model.getLoai(), model.getIMG(), model.getQRCode(), model.getController(), model.getMaNV());
    }
    
// Update Mã nhân viên
    public void updateMaNV(NhanVien model){
        String sql = " update NHANVIEN set MANV=? where TENNV like %N'?'";
        JdbcHelper.executeUpdate(sql, model.getMaNV(),model.getTenNV());
    }
    
//    Cập nhật mật khẩu mới
    public void upadtePass(String MaNV,String MatKhau){
        String sql = "update NHANVIEN set MATKHAU = '" +MatKhau+ "' where MANV = '" +MaNV+ "'";
        JdbcHelper.executeUpdate(sql);
    }
    
//    Xóa nhân viên
    public void DelNV(String MaNV){
        String sql = "delete from NHANVIEN where MANV =?";
        JdbcHelper.executeUpdate(sql, MaNV);
        
    }
//    Lấy tất cả Nhân viên có trong DB
    public List<NhanVien> select(){
        String sql = "select * from NHANVIEN";
        return select(sql);
    }
    
    
//    Tìm nhân viên qua MaNV
    public NhanVien FindbyID(String manv){
        String sql = "select * from NHANVIEN where MANV=?";
        List<NhanVien> list = select(sql,manv);
        return list.size()>0 ?list.get(0):null;
    }
    
//    
    private List<NhanVien> select(String sql, Object...args){
        List<NhanVien> list = new ArrayList<>();
        try{
            rs=null;
            try{
                rs = JdbcHelper.executeQuery(sql, args);
              while(rs.next()){
                 NhanVien model = readFromResult(rs);
                 list.add(model);
              }  
            }finally{
                rs.getStatement().getConnection().close();
            }
        }catch(Exception e){
            System.out.println("Lỗi phần Đọc nhân viên "+e);
        }
        return list;
    }
    
//    Đọc nhân viên
    private NhanVien readFromResult(ResultSet result) throws SQLException{
        this.rs = result;
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MANV"));
        model.setTenNV(rs.getString("TENNV"));
        model.setPass(rs.getString("MATKHAU"));
        model.setGioitinh(rs.getBoolean("GIOITINH"));
        model.setNgaySinh(rs.getDate("NGAYSINH").toLocalDate());
        model.setSDT(rs.getString("SDT"));
        model.setEmail(rs.getString("EMAIL"));
        model.setDiaChi(rs.getString("DIACHI"));
        model.setLoai(rs.getBoolean("LOAI"));
        model.setController(rs.getBoolean("CHUCVU"));
        model.setIMG(rs.getString("HINH"));
        model.setQRCode(rs.getString("QRCODE"));
        
        
        return model;
        
    }

    
}
