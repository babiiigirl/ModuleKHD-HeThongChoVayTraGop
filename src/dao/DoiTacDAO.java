package dao;

import model.DoiTac;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DoiTacDAO extends DAO{
    public ArrayList<DoiTac> timDT(String ten) {
        ArrayList<DoiTac> doiTacs = new ArrayList<>();
        String sql = "select * from doitac where ten like '%"+ten+"%'";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                DoiTac dt = new DoiTac();
                dt.setMa(rs.getInt("ma"));
                dt.setTen(rs.getString("ten"));
                dt.setDiaChi(rs.getString("diaChi"));
                dt.setThongTinLienHe(rs.getString("thongTinLienHe"));
                dt.setThongTinThanhToan(rs.getString("thongTinThanhToan"));
                doiTacs.add(dt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doiTacs;
    }
}
