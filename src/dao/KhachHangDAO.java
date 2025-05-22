package dao;

import model.KhachHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangDAO extends DAO{
    public ArrayList<KhachHang> timKH(String ten) {
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        String sql = "select * from khachhang where ten like '%"+ten+"%'";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMa(rs.getInt("ma"));
                kh.setTen(rs.getString("ten"));
                kh.setCccd(rs.getString("cccd"));
                kh.setDiaChi(rs.getString("diaChi"));
                kh.setSdt(rs.getString("sdt"));
                kh.setEmail(rs.getString("email"));
                kh.setGhiChu(rs.getString("ghiChu"));
                khachHangs.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHangs;
    }

    public void themKH(KhachHang kh) {
        String sql = "insert into khachhang(ten, cccd, diaChi, sdt, email, ghiChu) values(?,?,?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getTen());
            ps.setString(2, kh.getCccd());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSdt());
            ps.setString(5, kh.getEmail());
            ps.setString(6, kh.getGhiChu());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                kh.setMa(rs.getInt("ma"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
