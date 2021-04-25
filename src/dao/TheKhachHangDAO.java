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
import java.util.List;
import model.TheKhachHang;

/**
 *
 * @author Hp
 */
public class TheKhachHangDAO {

    public void insert(TheKhachHang model) {
        String sql = "INSERT INTO THEKHACHHANG VALUES(?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getTenKH(),
                model.getSDT(),
                model.getEmail(),
                model.getDiemTich(),
                model.getMaLoaiThe());
    }

    public void update(TheKhachHang model) {
        String sql = "UPDATE THEKHACHHANG SET TENKH=?, SDT=?, EMAIL=?, TICHDIEM=?, MALOAIKHACHHANG=? WHERE MAKH=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenKH(),
                model.getSDT(),
                model.getEmail(),
                model.getDiemTich(),
                model.getMaLoaiThe(),
                model.getMaKH());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM THEKHACHHANG WHERE MAKH=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<TheKhachHang> select() {
        String sql = "Select * from TheKhachHang";
        return select(sql);
    }

    public List<TheKhachHang> selectTheKhachHang() {
        String sql = "SELECT MAKH, TENKH, SDT, EMAIL, TICHDIEM, LOAITHEKHACHHANG.TENLOAI  FROM LOAITHEKHACHHANG JOIN THEKHACHHANG ON LOAITHEKHACHHANG.MALOAIKHACHHANG = THEKHACHHANG.MALOAIKHACHHANG";
        return select(sql);
    }

    public List<TheKhachHang> selectByMaKH(String keyword) {
        String sql = "SELECT * FROM TheKhachHang WHERE MaKH LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public List<TheKhachHang> selectBySDT(String keyword) {
        String sql = "SELECT * FROM TheKhachHang WHERE SDT LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public TheKhachHang findById(String MaKH) {
        String sql = "Select * from TheKhachHang Where MaKH=?";
        List<TheKhachHang> list = select(sql, MaKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<TheKhachHang> select(String sql, Object... args) {
        List<TheKhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    TheKhachHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private TheKhachHang readFromResultSet(ResultSet rs) throws SQLException {
        TheKhachHang model = new TheKhachHang();
        model.setMaKH(rs.getInt("MAKH"));
        model.setTenKH(rs.getString("TENKH"));
        model.setSDT(rs.getString("SDT"));
        model.setEmail(rs.getString("EMAIL"));
        model.setDiemTich(rs.getInt("TICHDIEM"));
        model.setMaLoaiThe(rs.getInt("MALOAIKHACHHANG"));
        return model;
    }
    
    //   TÌm tên nhân viên để xuất hóa đơn
    public TheKhachHang findBySDT(String SDT) throws SQLException{
        String sql = "select * from THEKHACHHANG where SDT = ?";
        java.util.List<TheKhachHang> list = select(sql, SDT);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    //    Update Điểm sau khi thanh toán
    public void UpdateDiem(int Diem, int Ma){
        String sql = "UPDATE THEKHACHHANG SET TICHDIEM = ? WHERE MAKH = ?";
       JdbcHelper.executeUpdate(sql, Diem,Ma);
    }
}
