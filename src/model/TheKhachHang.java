package model;

public class TheKhachHang {

    public int MaKH;
    public String TenKH;
    public String SDT;
    public String Email;
    public int DiemTich;
    public int MaLoaiThe;
    public String TenLoai;

    @Override
    public String toString() {
        return this.TenKH;
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

    public int getDiemTich() {
        return DiemTich;
    }

    public void setDiemTich(int DiemTich) {
        this.DiemTich = DiemTich;
    }

    public int getMaLoaiThe() {
        return MaLoaiThe;
    }

    public void setMaLoaiThe(int MaLoaiThe) {
        this.MaLoaiThe = MaLoaiThe;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

}
