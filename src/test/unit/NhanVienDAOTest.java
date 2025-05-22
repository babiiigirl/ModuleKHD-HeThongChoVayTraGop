package test.unit;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;
import dao.DAO;
import dao.NhanVienDAO;
import model.NhanVien;

public class NhanVienDAOTest {
    NhanVienDAO nvdao = new NhanVienDAO();

    @Test
    public void testDangNhapThanhCong() {
        NhanVien nv = new NhanVien();
        nv.setUsername("an.nv");
        nv.setPassword("an123");
        boolean result = nvdao.dangNhap(nv);
        Assert.assertTrue(result);
        Assert.assertNotNull(nv.getTenNhanVien());
        Assert.assertNotNull(nv.getChucVu());
        return;
    }
}
