/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Ahihi
 */
public class DonHang {
 private String MaDH;
    private int MaKH;
    private String TenKH;
    private LocalDate NgayBan;
    private float TongTien;
    private String MaNV;
    
    @Override
    public String toString() {
        
        return  NgayBan.getYear()+"-";
    }
    
    
    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String MaDH) {
        this.MaDH = MaDH;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public LocalDate getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(LocalDate NgayBan) {
        this.NgayBan = NgayBan;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    
}
