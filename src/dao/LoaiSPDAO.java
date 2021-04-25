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
import model.LoaiSP;
import model.SanPham;

/**
 *
 * @author Ahihi
 */
public class LoaiSPDAO {
    public void insert (LoaiSP model)
    {
        String sql = "INSERT INTO LOAISANPHAM(MALOAISANPHAM, TENLOAI) VALUES (?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMALOAISANPHAM(),
                model.getTENLOAI());
    }
    
    public void update(LoaiSP model)
    {
        String sql="UPDATE LOAISANPHAM SET TENLOAI=? WHERE MALOAISANPHAM=?";
        JdbcHelper.executeUpdate(sql,
        model.getTENLOAI(),
        model.getMALOAISANPHAM());
    }
    
    public void delete(String MALOAISANPHAM)
    {
        String sql="DELETE FROM LOAISANPHAM WHERE MALOAISANPHAM=?";
        JdbcHelper.executeUpdate(sql, MALOAISANPHAM);
    }
    
    public List<LoaiSP> select()
    {
        String sql="SELECT * FROM LOAISANPHAM";
        return select(sql);
    }
    
    public LoaiSP findById(String MALOAISANPHAM)
    {
        String sql="SELECT * FROM LOAISANPHAM WHERE MALOAISANPHAM=?";
        List<LoaiSP> list = select(sql, MALOAISANPHAM);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    private List<LoaiSP> select(String sql, Object...args)
    {
        List<LoaiSP> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try 
            {
                rs = JdbcHelper.executeQuery(sql, args);
                while(rs.next())
                {
                    LoaiSP model=readFromResultSet(rs);
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
    private LoaiSP readFromResultSet(ResultSet rs) throws SQLException
    {
        LoaiSP model=new LoaiSP();
        model.setMALOAISANPHAM(rs.getString("MAlOAISANPHAM"));
        model.setTENLOAI(rs.getString("TENLOAI"));
        return model;
    }
}
