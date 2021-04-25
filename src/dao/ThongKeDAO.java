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

/**
 *
 * @author Ahihi
 */
public class ThongKeDAO {
    public List<Object[]> getThongKeDoanhThang(int thang)
    {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call SP_THONGKEDOANHTHUTHANG (?)}";
                rs = JdbcHelper.executeQuery(sql, thang);
                while (rs.next()) {
                    Object[] model =
                    {
                        rs.getInt("THANG"),
                        rs.getDouble("TONGTIEN")
                    };
                    list.add(model);
                }
            } 
            finally
            {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw  new RuntimeException(ex);
        }
        return list;
    }
    
    public List<Object[]> getThongKeDoanhNam()
    {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call SP_THONGKEDOANHTHUNAM}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model =
                    {
                        rs.getInt("THANG"),
                        rs.getDouble("TONGTIEN")
                    };
                    list.add(model);
                }
            } 
            finally
            {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw  new RuntimeException(ex);
        }
        return list;
    }

    public List<Object[]> getThongKeDoanhThuNgay()
    {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call SP_THONGKEDOANHTHUNGAY}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model =
                    {
                        rs.getDate("NGAYBAN"),
                        rs.getDouble("TONGTIEN")
                    };
                    list.add(model);
                }
            } 
            finally
            {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw  new RuntimeException(ex);
        }
        return list;
    }

//    public List<Object[]> getThongKeSanPham()
//    {
//        List<Object[]> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                String sql="{call SP_THONGKESANPHAM}";
//                rs = JdbcHelper.executeQuery(sql);
//                while (rs.next()) {
//                    Object[] model =
//                    {
//                        rs.getInt("MASP"),
//                        rs.getInt("SOLUONG")
//                    };
//                    list.add(model);
//                }
//            } 
//            finally
//            {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException ex) {
//            throw  new RuntimeException(ex);
//        }
//        return list;
//    }
}
