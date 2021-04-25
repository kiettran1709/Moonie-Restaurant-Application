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
public class SanPham {
    private int MASP;
    private String MALOAISANPHAM;
    private String TENSP;
    private int SOLUONG;
    private int DONGIA;
    private String HINH;
    private String GHICHU;

    public SanPham() {
    }

    public SanPham(int MASP, String TENSP, String MALOAISANPHAM, int SOLUONG,int DONGIA, String HINH, String GHICHU) {
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.MALOAISANPHAM = MALOAISANPHAM;
        this.SOLUONG = SOLUONG;
        this.DONGIA = DONGIA;
        this.HINH = HINH;
        this.GHICHU = GHICHU;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public String getMALOAISANPHAM() {
        return MALOAISANPHAM;
    }

    public void setMALOAISANPHAM(String MALOAISANPHAM) {
        this.MALOAISANPHAM = MALOAISANPHAM;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(int DONGIA) {
        this.DONGIA = DONGIA;
    }

    public String getHINH() {
        return HINH;
    }

    public void setHINH(String HINH) {
        this.HINH = HINH;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }

    
}
