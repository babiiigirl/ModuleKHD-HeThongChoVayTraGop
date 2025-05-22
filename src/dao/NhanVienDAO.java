package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.NhanVien;

public class NhanVienDAO extends DAO{
    public boolean dangNhap(NhanVien nhanVien) {
        boolean result = false;
        String sql = "SELECT ma, tenNhanVien, chucVu FROM nhanvien WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nhanVien.getUsername());
            ps.setString(2, nhanVien.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                nhanVien.setMa(rs.getInt("ma"));
                nhanVien.setTenNhanVien(rs.getString("tenNhanVien"));
                nhanVien.setChucVu(rs.getString("chucVu"));
                result = true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
