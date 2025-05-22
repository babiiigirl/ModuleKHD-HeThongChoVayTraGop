package dao;

import model.MatHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MatHangDAO extends DAO{
    public ArrayList<MatHang> timMH(String ten) {
        ArrayList<MatHang> matHangs = new ArrayList<>();
        String sql = "select * from mathang where ten like '%"+ten+"%'";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                MatHang mh = new MatHang();
                mh.setMa(rs.getInt("ma"));
                mh.setTen(rs.getString("ten"));
                mh.setDonGia(rs.getDouble("donGia"));
                mh.setDonViTinh(rs.getString("donViTinh"));
                matHangs.add(mh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matHangs;
    }
}
