package test.unit;

import dao.KhachHangDAO;
import model.KhachHang;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class KhachHangDAOTest {
    KhachHangDAO khdao = new KhachHangDAO();

    @Test
    public void testTimThayKhachHang() {
        String ten = "Nguy";
        ArrayList<KhachHang> khachHangs = khdao.timKH(ten);
        Assert.assertNotNull(khachHangs);
        Assert.assertEquals(1, khachHangs.size());
        for(int i = 0; i < khachHangs.size(); i++){
            Assert.assertTrue(khachHangs.get(i).getTen().toLowerCase().contains(ten.toLowerCase()));
        }
    }
}
