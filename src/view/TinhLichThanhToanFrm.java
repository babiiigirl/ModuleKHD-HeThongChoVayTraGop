package view;

import model.HopDong;
import model.KyThanhToan;
import model.MatHangHopDong;
import view.HopDong.XacNhanFrm;
import view.MatHang.TimMHFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TinhLichThanhToanFrm extends JFrame implements ActionListener {
    private JTextField txtThoiHan;
    private JTextField txtLaiSuat;
    private JTable tblKyThanhToan;
    private JButton btnTiepTuc;
    private DefaultTableModel tableModel;

    private HopDong hopDong;
    private ArrayList<KyThanhToan> listKTT;

    public TinhLichThanhToanFrm(HopDong hopDong) {
        super("Tính lịch thanh toán");
        this.hopDong = hopDong;
        listKTT = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel lblTitle = new JLabel("Tính lịch thanh toán");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        pnMain.add(lblTitle);

        JPanel pnInput = new JPanel(new GridLayout(2, 2, 10, 10));
        pnInput.setMaximumSize(new Dimension(600, 60));
        pnInput.add(new JLabel("Thời hạn vay:"));
        txtThoiHan = new JTextField();
        pnInput.add(txtThoiHan);
        pnInput.add(new JLabel("Lãi suất:"));
        txtLaiSuat = new JTextField();
        pnInput.add(txtLaiSuat);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        pnMain.add(pnInput);

        String[] columnNames = {"Kỳ", "Thời điểm thanh toán", "Số tiền thanh toán", "Dư nợ còn lại"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblKyThanhToan = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblKyThanhToan);
        scrollPane.setPreferredSize(new Dimension(700, 150));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        pnMain.add(scrollPane);

        this.setContentPane(pnMain);
        this.setSize(550, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel pnTiepTuc = new JPanel();
        pnTiepTuc.setLayout(new BoxLayout(pnTiepTuc, BoxLayout.X_AXIS));
        pnTiepTuc.add(Box.createRigidArea(new Dimension(5, 0)));

        btnTiepTuc = new JButton("Tiếp tục");
        pnTiepTuc.add(Box.createHorizontalGlue());
        btnTiepTuc.addActionListener(this);
        pnTiepTuc.add(btnTiepTuc);
        pnTiepTuc.setMaximumSize(new Dimension(Integer.MAX_VALUE, pnTiepTuc.getPreferredSize().height));
        pnMain.add(pnTiepTuc);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String thoiHanStr = txtThoiHan.getText().trim();
                String laiSuatStr = txtLaiSuat.getText().trim();

                // Kiểm tra đầu vào hợp lệ
                if (thoiHanStr.isEmpty() || laiSuatStr.isEmpty()) {
                    tableModel.setRowCount(0);
                    return;
                }
                int thoiHanVay;
                double laiSuat;
                try {
                    thoiHanVay = Integer.parseInt(thoiHanStr);
                    laiSuat = Double.parseDouble(laiSuatStr);
                } catch (NumberFormatException ex) {
                    tableModel.setRowCount(0);
                    return;
                }

                double tongTienVay = 0;
                if (hopDong.getDsMatHangHopDong() != null) {
                    for (MatHangHopDong mhhd : hopDong.getDsMatHangHopDong()) {
                        tongTienVay += mhhd.getThanhTien();
                    }
                }
                hopDong.setTongTienVay(tongTienVay);

                tableModel.setRowCount(0);

                // Tính tiền gốc hàng tháng (cố định)
                double tienGocHangThang = tongTienVay / thoiHanVay;
                // Chuyển lãi suất từ %/năm sang %/tháng
                double laiSuatThang = laiSuat / thoiHanVay / 100;

                double duNoConLai = tongTienVay;
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date ngayKy = cal.getTime();
                hopDong.setNgayKy(ngayKy);

                for (int i = 1; i <= thoiHanVay; i++) {
                    // Tính lãi suất dựa trên dư nợ còn lại
                    double tienLai = duNoConLai * laiSuatThang;
                    // Tổng số tiền phải trả trong tháng
                    double soTienPhaiThanhToan = tienGocHangThang + tienLai;

                    // Cập nhật dư nợ còn lại
                    duNoConLai -= tienGocHangThang;
                    duNoConLai = Math.max(duNoConLai, 0);

                    Date thoiDiemThanhToan = cal.getTime();
                    tableModel.addRow(new Object[]{
                            i,
                            sdf.format(thoiDiemThanhToan),
                            soTienPhaiThanhToan,
                            duNoConLai
                    });
                    cal.add(Calendar.MONTH, 1);
                }
            }
        };
        txtThoiHan.addKeyListener(keyListener);
        txtLaiSuat.addKeyListener(keyListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTiepTuc) {
            int rowCount = tableModel.getRowCount();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < rowCount; i++) {
                int ky = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
                Date thoiDiem = null;
                try {
                    thoiDiem = sdf.parse(tableModel.getValueAt(i, 1).toString());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                double soTien = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
                double duNo = Double.parseDouble(tableModel.getValueAt(i, 3).toString());
                listKTT.add(new KyThanhToan(ky, thoiDiem, soTien, duNo));
            }
            hopDong.setDsKyThanhToan(listKTT);

//            new XacNhanFrm(hopDong).setVisible(true);
            this.dispose();
        }
    }

//    public static void main(String[] args) {
//        HopDong hdtest = new HopDong();
//        (new TinhLichThanhToanFrm(hdtest)).setVisible(true);
//    }
}
