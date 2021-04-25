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
import model.LoaiTheKH;

/**
 *
 * @author This my name
 */
public class LoaiTheKHDAO {

    public void insert(String model) {
        String sql = "insert into LOAITHEKHACHHANG values (?)";
        JdbcHelper.executeUpdate(sql, model);
    }

    public void Delete(String MaLoaiKH) {
        String sql = "delete from LOAITHEKHACHHANG where MALOAIKHACHHANG = ?";
        JdbcHelper.executeUpdate(sql, MaLoaiKH);
    }

    public List<LoaiTheKH> select() {
        String sql = "select * from LOAITHEKHACHHANG";
        return select(sql);
    }

    public LoaiTheKH findById(String MaLoaiKH) {
        String sql = "Select * from LOAITHEKHACHHANG Where MALOAIKHACHHANG=?";
        List<LoaiTheKH> list = select(sql, MaLoaiKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<LoaiTheKH> select(String sql, Object... args) {
        List<LoaiTheKH> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiTheKH model = readFromResultSet(rs);
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

    private LoaiTheKH readFromResultSet(ResultSet rs) throws SQLException {
        LoaiTheKH model = new LoaiTheKH();
        model.setMaLoaiKhachHang(rs.getInt("MALOAIKHACHHANG"));
        model.setTenLoai(rs.getString("TENLOAI"));
        return model;
    }
    public String findten(int ma1){
        String ten="";
        try {
            String sql="Select tenloai from loaithekhachhang where maloaikhachhang=?";
            ResultSet rs=JdbcHelper.executeQuery(sql, ma1);
            if(rs.next()){
                ten= rs.getString(1);
            }
        } catch (Exception e) {
            return "loi";
        }
        return ten;
    }
}
