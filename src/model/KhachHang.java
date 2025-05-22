package model;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private int ma;
    private String ten;
    private String cccd;
    private String diaChi;
    private String sdt;
    private String email;
    private String ghiChu;

    public KhachHang() {
    }

    public KhachHang(int ma, String ten, String cccd, String diaChi, String sdt, String email, String ghiChu) {
        this.ma = ma;
        this.ten = ten;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.ghiChu = ghiChu;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}