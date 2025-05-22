package model;

import java.io.Serializable;

public class MatHangHopDong implements Serializable {
    private int ma;
    private double donGia;
    private int soLuong;
    private double thanhTien;
    private MatHang matHang;

    public MatHangHopDong() {
    }

    public MatHangHopDong(int ma, double donGia, int soLuong, MatHang matHang) {
        this.ma = ma;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = donGia*soLuong;
        this.matHang = matHang;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public MatHang getMatHang() {
        return matHang;
    }

    public void setMatHang(MatHang matHang) {
        this.matHang = matHang;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}