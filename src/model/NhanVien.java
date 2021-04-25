/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Asus
 */
public class NhanVien {
  private  String MaNV;
  private  String Pass;
  private  String TenNV;
  private  LocalDate NgaySinh;
  private  boolean Gioitinh;
  private  String DiaChi;
  private  String SDT;
  private  String Email;
  private  boolean Loai;
  private  String IMG;
  private  String QRCode;
  private  boolean Controller;
  
    
   
    
    
    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
  

    public boolean getLoai() {
        return Loai;
    }

    public void setLoai(boolean Loai) {
        this.Loai = Loai;
    }
  

    public boolean getController() {
        return Controller;
    }

    public void setController(boolean Controller) {
        this.Controller = Controller;
    }
  
    @Override
    public String toString(){
        return this.TenNV;
    }
    
    public NhanVien(){
        
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(boolean Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }
    
    
}
