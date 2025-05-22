package model;

import java.io.Serializable;

public class DoiTac implements Serializable {
    private int ma;
    private String ten;
    private String diaChi;
    private String thongTinLienHe;
    private String thongTinThanhToan;

    public DoiTac() {
    }

    public DoiTac(int ma, String ten, String diaChi, String thongTinLienHe, String thongTinThanhToan) {
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.thongTinLienHe = thongTinLienHe;
        this.thongTinThanhToan = thongTinThanhToan;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThongTinLienHe() {
        return thongTinLienHe;
    }

    public void setThongTinLienHe(String thongTinLienHe) {
        this.thongTinLienHe = thongTinLienHe;
    }

    public String getThongTinThanhToan() {
        return thongTinThanhToan;
    }

    public void setThongTinThanhToan(String thongTinThanhToan) {
        this.thongTinThanhToan = thongTinThanhToan;
    }
}