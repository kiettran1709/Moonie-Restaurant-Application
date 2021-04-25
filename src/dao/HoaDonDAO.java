/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.DonHang;
import model.TheKhachHang;

/**
 *
 * @author Asus
 */
public class HoaDonDAO {
    
    public void insert(DonHang HD){
        String sql = "insert into DONHANG(MADH,MAKH,TENKH,NGAYBAN,TONGTIEN,MANV) values (?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,HD.getMaDH(),
                                    HD.getMaKH(),
                                    HD.getTenKH(),
                                    java.sql.Date.valueOf(HD.getNgayBan()),
                                    HD.getTongTien(),
                                    HD.getMaNV());
    }
    
      public List<DonHang> select() {
        String sql = "SELECT * FROM DONHANG";
        return select(sql);
    }
        public DonHang FindbyID(String MaDH){
        String sql = " select * from DONHANG where MADH = ?" ;
        java.util.List<DonHang> list = select(sql, MaDH);
        return list.size() > 0 ? list.get(0) : null;
        
    }
     private List<DonHang> select(String sql, Object...args){
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    DonHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    private DonHang readFromResultSet (ResultSet rs) throws SQLException {
        DonHang model = new DonHang();
        model.setMaDH(rs.getString("MADH"));
        model.setMaKH(rs.getInt("MAKH"));
        model.setTenKH(rs.getString("TENKH"));
        model.setNgayBan(rs.getDate("NGAYBAN").toLocalDate());
        model.setTongTien(rs.getFloat("TONGTIEN"));
        model.setMaNV(rs.getString("MANV"));
        return model;
    }
   
    
}
