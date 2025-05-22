package model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int ma;
    private String tenNhanVien;
    private String chucVu;
    private String username;
    private String password;

    public NhanVien() {
    }

    public NhanVien(int ma, String tenNhanVien, String chucVu, String username, String password) {
        this.ma = ma;
        this.tenNhanVien = tenNhanVien;
        this.chucVu = chucVu;
        this.username = username;
        this.password = password;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}