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
import model.SanPham;

/**
 *
 * @author Ahihi
 */
public class SanPhamDAO {
    public void insert (SanPham model)
    {
        String sql = "INSERT INTO SANPHAM(MALOAISANPHAM, TENSP, SOLUONG, DONGIA, HINH, GHICHU) VALUES (?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, 
                model.getMALOAISANPHAM(),
                model.getTENSP(),
                model.getSOLUONG(),
                model.getDONGIA(),
                model.getHINH(),
                model.getGHICHU());
    }
    
    public void update(SanPham model)
    {
        String sql="UPDATE SANPHAM SET TENSP=?, MALOAISANPHAM=?, SOLUONG=?, DONGIA=?, HINH=?, GHICHU=? WHERE MASP=?";
        JdbcHelper.executeUpdate(sql,
        model.getTENSP(),
        model.getMALOAISANPHAM(),
        model.getSOLUONG(),
        model.getDONGIA(),
        model.getHINH(),
        model.getGHICHU(),
        model.getMASP());
    }
    
    public void delete(Integer MASP)
    {
        String sql="DELETE FROM SANPHAM WHERE MASP=?";
        JdbcHelper.executeUpdate(sql, MASP);
    }
    public List<SanPham> select()
    {
        String sql="SELECT * FROM SANPHAM";
        return select(sql);
    }
    
    public SanPham findById(Integer masp)
    {
        String sql="SELECT * FROM SANPHAM WHERE MASP=?";
        List<SanPham> list = select(sql, masp);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    private List<SanPham> select(String sql, Object...args)
    {
        List<SanPham> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try 
            {
                rs = JdbcHelper.executeQuery(sql, args);
                while(rs.next())
                {
                    SanPham model=readFromResultSet(rs);
                    list.add(model);
                }
            }
            finally
            {
                rs.getStatement().getConnection().close();
            }
        }
        catch (SQLException ex) 
        {
            throw new RuntimeException(ex);
        }
        return list;
    }
    private SanPham readFromResultSet(ResultSet rs) throws SQLException
    {
        SanPham model=new SanPham();
        model.setMASP(rs.getInt("MASP"));
        model.setMALOAISANPHAM(rs.getString("MALOAISANPHAM"));
        model.setTENSP(rs.getString("TENSP"));
        model.setSOLUONG(rs.getInt("SOLUONG"));
        model.setDONGIA(rs.getInt("DONGIA"));
        model.setHINH(rs.getString("HINH"));
        model.setGHICHU(rs.getString("GHICHU"));
        
        return model;
    }
    
       public List<SanPham> getSanPhamByMaLoai2(String maloai){
        String sql = "SELECT * FROM SANPHAM WHERE MALOAISANPHAM = ?";
        return select(sql, maloai);
    }
       
       public List<SanPham> findSanPhamAllTK(String tensp){
        String sql = "Select * from SanPham WHERE TENSP LIKE N'"+tensp+"%'";
        return select(sql);
    }
       
       public List<SanPham> findSanPhamTK(String tensp, String maloai){
        String sql = "Select * from SanPham WHERE TENSP LIKE (N'%"+tensp+"%') and (MALOAISANPHAM = '"+maloai+"') ";
        return select(sql);
    }
}
