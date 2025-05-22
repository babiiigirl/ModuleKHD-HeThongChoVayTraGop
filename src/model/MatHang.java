package model;

import java.io.Serializable;

public class MatHang implements Serializable {
    private int ma;
    private String ten;
    private String donViTinh;
    private double donGia;

    public MatHang() {
    }

    public MatHang(int ma, String ten, String donViTinh, double donGia) {
        this.ma = ma;
        this.ten = ten;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
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

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}