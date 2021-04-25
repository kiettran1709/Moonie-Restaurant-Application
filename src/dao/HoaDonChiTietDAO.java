/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.DonHang;
import model.ChiTietDonHang;

/**
 *
 * @author Asus
 */
public class HoaDonChiTietDAO {
    public void insert(ChiTietDonHang DHCT){
        String sql = "insert into CHITIETDONHANG(MADH, MASP, TENSP, SOLUONG, DONGIA, GIAMGIA, THANHTIEN, MOTA) values (?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, DHCT.getMaDH(),
                                        DHCT.getMaSP(),
                                        DHCT.getTenSP(),
                                        DHCT.getSoLuong(),
                                        DHCT.getDonGia(),
                                        DHCT.getGiamGia(),
                                        DHCT.getThanhTien(),
                                        DHCT.getMoTa());
    }
    
     public List<ChiTietDonHang> select() {
        String sql = "SELECT * FROM CHITIETDONHANG";
        return select(sql);
    }
    public ChiTietDonHang FindbyID(int MaDH){
        String sql = " select * from CHITIETDONHANG where MADH = ?" ;
        List<ChiTietDonHang> list = select(sql,MaDH);
        return list.size() > 0 ? list.get(0) : null;
        
    }
     private List<ChiTietDonHang> select(String sql, Object...args){
        List<ChiTietDonHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    ChiTietDonHang model = readFromResultSet(rs);
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
    
    private ChiTietDonHang readFromResultSet (ResultSet rs) throws SQLException {
        ChiTietDonHang model = new ChiTietDonHang();
        model.setMaDH(rs.getString("MADH"));
        model.setMaSP(rs.getInt("MASP"));
        model.setTenSP(rs.getString("TENSP"));
        model.setSoLuong(rs.getInt("SOLUONG"));
        model.setDonGia(rs.getFloat("DONGIA"));
        model.setGiamGia(rs.getFloat("GIAMGIA"));
        model.setThanhTien(rs.getFloat("THANHTIEN"));
        model.setMoTa(rs.getString("MOTA"));
        
        return model;
    }
    
}
