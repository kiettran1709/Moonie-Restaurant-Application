package model;

import helper.JdbcHelper;
import java.sql.ResultSet;

public class LoaiTheKH {

    public int MaLoaiKhachHang;
    public String TenLoai;

    public LoaiTheKH(int MaLoaiKhachHang, String TenLoai) {
        this.MaLoaiKhachHang = MaLoaiKhachHang;
        this.TenLoai = TenLoai;
    }

    public LoaiTheKH() {
    }

    @Override
    public String toString() {
        return this.TenLoai;
    }

    public int getMaLoaiKhachHang() {
        return MaLoaiKhachHang;
    }

    public void setMaLoaiKhachHang(int MaLoaiKhachHang) {
        this.MaLoaiKhachHang = MaLoaiKhachHang;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
    
}
