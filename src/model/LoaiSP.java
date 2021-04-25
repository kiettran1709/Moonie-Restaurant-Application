/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ahihi
 */
public class LoaiSP {
    private String MALOAISANPHAM;
    private String TENLOAI;
    
    @Override
    public String toString() 
    {
        return this.TENLOAI;
    }

    public String getMALOAISANPHAM() {
        return MALOAISANPHAM;
    }

    public void setMALOAISANPHAM(String MALOAISANPHAM) {
        this.MALOAISANPHAM = MALOAISANPHAM;
    }

    public String getTENLOAI() {
        return TENLOAI;
    }

    public void setTENLOAI(String TENLOAI) {
        this.TENLOAI = TENLOAI;
    }
    
}
