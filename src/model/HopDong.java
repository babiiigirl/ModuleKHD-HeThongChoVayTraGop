package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class HopDong implements Serializable {
    private int ma;
    private Date ngayKy;
    private int thoiHanVay;
    private double tongTienVay;

    private NhanVien nhanVien;
    private KhachHang khachHang;
    private DoiTac doiTac;

    private ArrayList<MatHangHopDong> dsMatHangHopDong;
    private ArrayList<KyThanhToan> dsKyThanhToan;

    public HopDong() {
    }

    public HopDong(int ma, Date ngayKy, int thoiHanVay,
                   NhanVien nhanVien, KhachHang khachHang, DoiTac doiTac,
                   ArrayList<MatHangHopDong> dsMatHangHopDong, ArrayList<KyThanhToan> dsKyThanhToan) {
        this.ma = ma;
        this.ngayKy = ngayKy;
        this.thoiHanVay = thoiHanVay;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.doiTac = doiTac;
        this.dsMatHangHopDong = new ArrayList<>();
        this.dsKyThanhToan = new ArrayList<>();
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public Date getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(Date ngayKy) {
        this.ngayKy = ngayKy;
    }

    public int getThoiHanVay() {
        return thoiHanVay;
    }

    public void setThoiHanVay(int thoiHanVay) {
        this.thoiHanVay = thoiHanVay;
    }

    public double getTongTienVay() {
//        double tongTien = 0;
//        for (MatHangHopDong mh: this.dsMatHangHopDong) {
//            tongTien += mh.getThanhTien();
//        }
        return tongTienVay;
    }

    public void setTongTienVay(double tongTien) {
        this.tongTienVay = tongTien;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public DoiTac getDoiTac() {
        return doiTac;
    }

    public void setDoiTac(DoiTac doiTac) {
        this.doiTac = doiTac;
    }

    public ArrayList<MatHangHopDong> getDsMatHangHopDong() {
        return dsMatHangHopDong;
    }

    public void setDsMatHangHopDong(ArrayList<MatHangHopDong> dsMatHangHopDong) {
        this.dsMatHangHopDong = dsMatHangHopDong;
    }

    public ArrayList<KyThanhToan> getDsKyThanhToan() {
        return dsKyThanhToan;
    }

    public void setDsKyThanhToan(ArrayList<KyThanhToan> dsKyThanhToan) {
        this.dsKyThanhToan = dsKyThanhToan;
    }
}