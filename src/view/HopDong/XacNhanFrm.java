package view.HopDong;

import model.*;
import dao.HopDongDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class XacNhanFrm extends JFrame implements ActionListener {
    private JButton btnHuy, btnLuuVaIn;
    private JTable tblMatHang, tblLichThanhToan;
    private JLabel lblTenNhanVien, lblTenKhachHang, lblCCCD, lblDiaChiKH, lblSdtKH, lblEmailKH, lblGhiChuKH;
    private JLabel lblTenDoiTac, lblDiaChiDT, lblLienHeDT, lblThanhToanDT;
    private JLabel lblNgayKy, lblTongTien, lblThoiHanVay;
    private HopDong hopDong;
    private boolean isSaving = false;

    public XacNhanFrm(HopDong hopDong) {
        super("Xác nhận hợp đồng");
        this.hopDong = hopDong;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 900);
        setLocationRelativeTo(null);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Tiêu đề
        JLabel lblTitle = new JLabel("Xác nhận hợp đồng");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        pnMain.add(lblTitle);

        JLabel lblSubTitle = new JLabel("Thông tin hợp đồng");
        lblSubTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pnMain.add(lblSubTitle);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        // Thông tin hợp đồng
        JPanel pnInfo = new JPanel();
        pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));
        pnInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnInfo.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 20));

        // 1. Thông tin đại diện công ty
        JLabel lblNhanVien = new JLabel("1. Thông tin đại diện công ty");
        lblTenNhanVien = new JLabel("Tên: " + hopDong.getNhanVien().getTenNhanVien());
        pnMain.add(lblNhanVien);
        pnMain.add(lblTenNhanVien);
        pnMain.add(Box.createRigidArea(new Dimension(0, 5)));

        // 2. Thông tin khách hàng
        KhachHang kh = hopDong.getKhachHang();
        JLabel lblKhachHang = new JLabel("2. Thông tin khách hàng");
        lblTenKhachHang = new JLabel("Tên: " + kh.getTen());
        lblCCCD = new JLabel("CCCD: " + kh.getCccd());
        lblSdtKH = new JLabel("Sdt: " + kh.getSdt());
        lblDiaChiKH = new JLabel("Địa chỉ: " + kh.getDiaChi());
        lblEmailKH = new JLabel("Email: " + kh.getEmail());
        lblGhiChuKH = new JLabel("Ghi chú: " + kh.getGhiChu());
        pnMain.add(lblKhachHang);
        pnMain.add(lblTenKhachHang);
        pnMain.add(lblCCCD);
        pnMain.add(lblSdtKH);
        pnMain.add(lblDiaChiKH);
        pnMain.add(lblEmailKH);
        pnMain.add(lblGhiChuKH);
        pnMain.add(Box.createRigidArea(new Dimension(0, 5)));

        // 3. Thông tin đối tác
        DoiTac dt = hopDong.getDoiTac();
        JLabel lblDoiTac = new JLabel("3. Thông tin đối tác");
        lblTenDoiTac = new JLabel("Tên: " + dt.getTen());
        lblDiaChiDT = new JLabel("Địa chỉ: " + dt.getDiaChi());
        lblLienHeDT = new JLabel("Thông tin liên hệ: " + dt.getThongTinLienHe());
        lblThanhToanDT = new JLabel("Thông tin thanh toán: " + dt.getThongTinThanhToan());
        pnMain.add(lblDoiTac);
        pnMain.add(lblTenDoiTac);
        pnMain.add(lblDiaChiDT);
        pnMain.add(lblLienHeDT);
        pnMain.add(lblThanhToanDT);
        pnMain.add(Box.createRigidArea(new Dimension(0, 5)));

        // 4. Ngày ký hợp đồng
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        lblNgayKy = new JLabel("4. Ngày ký hợp đồng: " + sdf.format(hopDong.getNgayKy()));
        pnMain.add(lblNgayKy);
        pnMain.add(Box.createRigidArea(new Dimension(0, 5)));

        // 5. Danh sách mặt hàng
        ArrayList<MatHangHopDong> listMHHD = hopDong.getDsMatHangHopDong();
        JLabel lblDSMH = new JLabel("5. Danh sách mặt hàng");
        pnMain.add(lblDSMH);

        String[] colMatHang = {"STT", "Mã", "Tên", "Đơn vị tính", "Đơn giá", "Số lượng", "Thành tiền"};
        DefaultTableModel modelMatHang = new DefaultTableModel(colMatHang, 0);
        // Đổ dữ liệu mặt hàng hợp đồng vào bảng
        if (listMHHD != null) {
            int stt = 1;
            for (MatHangHopDong mh : listMHHD) {
                MatHang matHang = mh.getMatHang();
                modelMatHang.addRow(new Object[] {
                        stt++,
                        matHang.getMa(),
                        matHang.getTen(),
                        matHang.getDonViTinh(),
                        mh.getDonGia(),
                        mh.getSoLuong(),
                        mh.getThanhTien()
                });
            }
        }
        tblMatHang = new JTable(modelMatHang);
        JScrollPane paneMH = new JScrollPane(tblMatHang);
        paneMH.setPreferredSize(new Dimension(750, 80));
        pnMain.add(paneMH);



        // Tổng tiền
        lblTongTien = new JLabel("Tổng tiền: " + hopDong.getTongTienVay());
        pnMain.add(lblTongTien);
        pnMain.add(Box.createRigidArea(new Dimension(0, 5)));

        // 6. Thời hạn vay
        lblThoiHanVay = new JLabel("6. Thời hạn vay: " + hopDong.getThoiHanVay() + " tháng");
        pnMain.add(lblThoiHanVay);
        pnMain.add(Box.createRigidArea(new Dimension(0, 5)));

        // Bảng lịch thanh toán
        ArrayList<KyThanhToan> listKTT = hopDong.getDsKyThanhToan();
        String[] colLich = {"Kỳ", "Thời điểm thanh toán", "Số tiền thanh toán", "Dư nợ còn lại"};
        DefaultTableModel modelLich = new DefaultTableModel(colLich, 0);
        // Đổ dữ liệu vào bảng kỳ thanh toán
        if (listKTT != null) {
            for (KyThanhToan ktt : listKTT) {
                modelLich.addRow(new Object[] {
                        ktt.getMa(),
                        sdf.format(ktt.getThoiDiemThanhToan()),
                        Math.round(ktt.getSoTienThanhToan() * 100.0) / 100.0,
                        Math.round(ktt.getDuNoConLai() * 100.0) / 100.0
                });
            }
        }
        tblLichThanhToan = new JTable(modelLich);
        JScrollPane paneLich = new JScrollPane(tblLichThanhToan);
        paneLich.setPreferredSize(new Dimension(750, 60));
        pnMain.add(paneLich);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        // Nút Hủy và Lưu và in
        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnHuy = new JButton("Hủy");
        btnHuy.setBackground(Color.RED);
        btnHuy.setForeground(Color.WHITE);
        btnLuuVaIn = new JButton("Lưu và in");
        btnLuuVaIn.setBackground(Color.GREEN);
        btnLuuVaIn.setForeground(Color.BLACK);
        btnHuy.addActionListener(this);
        btnLuuVaIn.addActionListener(this);
        pnButton.add(btnHuy);
        pnButton.add(btnLuuVaIn);
        pnMain.add(pnButton);

        setContentPane(pnMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHuy) {
            this.dispose();
        } else if (e.getSource() == btnLuuVaIn) {
            // Lưu hợp đồng vào database
            HopDongDAO hopDongDAO = new HopDongDAO();
            boolean result = hopDongDAO.luuHD(hopDong);
            
            if (result) {
                JOptionPane.showMessageDialog(this, 
                    "Lưu hợp đồng thành công!", 
                    "Thông báo", 
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Lưu hợp đồng thất bại! Vui lòng thử lại.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
