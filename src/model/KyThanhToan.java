package model;

import java.io.Serializable;
import java.util.Date;

public class KyThanhToan implements Serializable {
    private int ma;
    private Date thoiDiemThanhToan;
    private double soTienThanhToan;
    private double duNoConLai;

    public KyThanhToan() {
    }

    public KyThanhToan(int ma, Date thoiDiemThanhToan, double soTienThanhToan, double duNoConLai) {
        this.ma = ma;
        this.thoiDiemThanhToan = thoiDiemThanhToan;
        this.soTienThanhToan = soTienThanhToan;
        this.duNoConLai = duNoConLai;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public Date getThoiDiemThanhToan() {
        return thoiDiemThanhToan;
    }

    public void setThoiDiemThanhToan(Date thoiDiemThanhToan) {
        this.thoiDiemThanhToan = thoiDiemThanhToan;
    }

    public double getSoTienThanhToan() {
        return soTienThanhToan;
    }

    public void setSoTienThanhToan(double soTienThanhToan) {
        this.soTienThanhToan = soTienThanhToan;
    }

    public double getDuNoConLai() {
        return duNoConLai;
    }

    public void setDuNoConLai(double duNoConLai) {
        this.duNoConLai = duNoConLai;
    }
}