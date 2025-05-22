package dao;

import model.HopDong;
import model.MatHangHopDong;
import model.KyThanhToan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

public class HopDongDAO extends DAO{
    public boolean luuHD(HopDong hd) {
        boolean result = false;
        try {
            // Tắt auto-commit để sử dụng transaction
            con.setAutoCommit(false);
            
            // 1. Lưu hợp đồng
            String sql1 = "INSERT INTO HopDong(ngayKy, thoiHanVay, NhanVienma, KhachHangma, DoiTacma) VALUES(?,?,?,?,?)";
            PreparedStatement psHD = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            psHD.setDate(1, new Date(hd.getNgayKy().getTime()));
            psHD.setInt(2, hd.getThoiHanVay());
            psHD.setInt(3, hd.getNhanVien().getMa());
            psHD.setInt(4, hd.getKhachHang().getMa());
            psHD.setInt(5, hd.getDoiTac().getMa());
            
            psHD.executeUpdate();
            
            // Lấy mã hợp đồng vừa tạo
            ResultSet generatedKeys = psHD.getGeneratedKeys();
            int hopDongId;
            if (generatedKeys.next()) {
                hopDongId = generatedKeys.getInt(1);
                
                // 2. Lưu các mặt hàng của hợp đồng
                if (hd.getDsMatHangHopDong() != null && !hd.getDsMatHangHopDong().isEmpty()) {
                    String sql2 = "INSERT INTO MatHangHopDong(donGia, soLuong, MatHangma, HopDongma) VALUES(?,?,?,?)";
                    PreparedStatement psMH = con.prepareStatement(sql2);
                    
                    for (MatHangHopDong mh : hd.getDsMatHangHopDong()) {
                        psMH.setDouble(1, mh.getDonGia());
                        psMH.setInt(2, mh.getSoLuong());
                        psMH.setInt(3, mh.getMatHang().getMa());
                        psMH.setInt(4, hopDongId);
                        psMH.executeUpdate();
                    }
                    psMH.close();
                }
                
                // 3. Lưu các kỳ thanh toán
                if (hd.getDsKyThanhToan() != null) {
                    String sql3 = "INSERT INTO KyThanhToan(thoiDiemThanhToan, soTienThanhToan, HopDongma, trangThai) VALUES(?,?,?,?)";
                    PreparedStatement psKT = con.prepareStatement(sql3);
                    
                    for (KyThanhToan kt : hd.getDsKyThanhToan()) {
                        psKT.setDate(1, new Date(kt.getThoiDiemThanhToan().getTime()));
                        psKT.setDouble(2, kt.getSoTienThanhToan());
                        psKT.setInt(3, hopDongId);
                        psKT.setString(4, "Chưa đến hạn");
                        psKT.executeUpdate();
                    }
                    psKT.close();
                }
                
                // Commit transaction nếu mọi thứ OK
                con.commit();
                result = true;
                psHD.close();
            }
            
        } catch (Exception e) {
            try {
                // Rollback nếu có lỗi
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // Bật lại auto-commit
                con.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
