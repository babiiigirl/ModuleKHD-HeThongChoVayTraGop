package view.MatHang;

import dao.MatHangDAO;
import model.MatHang;
import model.HopDong;
import model.MatHangHopDong;
import view.HopDong.TinhLichThanhToanFrm;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimMHFrm extends JFrame implements ActionListener {
    private JTextField txtTen;
    private JButton btnTim, btnTiepTuc;
    private JTable tblMatHang;
    private ArrayList<MatHang> listMH;
    private DefaultTableModel tableModel;

    private TimMHFrm mainFrm;
    private HopDong hopDong;

    private String[] columnNames = {"Mã", "Tên", "Đơn vị tính", "Đơn giá", "Số lượng"};
    private Map<Integer, Integer> map;
    private Map<Integer, MatHang> allSelectedMH;

    public TimMHFrm(HopDong hopDong) {
        super("Tìm kiếm mặt hàng");
        this.hopDong = hopDong;
        listMH = new ArrayList<>();
        mainFrm = this;
        map = new HashMap<>();
        allSelectedMH = new HashMap<>();

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblTitle = new JLabel("Tìm kiếm mặt hàng");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        pnMain.add(lblTitle);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.X_AXIS));
        pnTimKiem.add(new JLabel("Tên: "));
        txtTen = new JTextField(20);
        pnTimKiem.add(txtTen);
        pnTimKiem.add(Box.createRigidArea(new Dimension(5, 0)));

        btnTim = new JButton("Tìm");
        btnTim.addActionListener(this);
        pnTimKiem.add(btnTim);
        pnTimKiem.setMaximumSize(new Dimension(Integer.MAX_VALUE, pnTimKiem.getPreferredSize().height));
        pnMain.add(pnTimKiem);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel pnKetQua = new JPanel();
        pnKetQua.setLayout(new BoxLayout(pnKetQua, BoxLayout.Y_AXIS));

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        tblMatHang = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblMatHang);
        tblMatHang.setFillsViewportHeight(false);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    if (column == 4 && row >= 0 && row < tableModel.getRowCount()) {
                        int ma = (Integer) tableModel.getValueAt(row, 0);
                        int soLuong = Integer.parseInt(tableModel.getValueAt(row, 4).toString());
                        map.put(ma, soLuong);
                    }
                    tblMatHang.setModel(tableModel);
                }
            }
        });

        pnKetQua.add(scrollPane);
        pnMain.add(pnKetQua);

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

        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.getRootPane().setDefaultButton(btnTim);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnTim)){
            if((txtTen.getText() == null)||(txtTen.getText().length() == 0))
                return;

            if (tblMatHang.isEditing()) {
                tblMatHang.getCellEditor().stopCellEditing();
            }

            // Lưu lại các số lượng đã nhập trước đó
            for(int i = 0; i < tableModel.getRowCount(); i++) {
                int ma = (Integer)tableModel.getValueAt(i, 0);
                if(tableModel.getValueAt(i, 4) != null) {
                    int soLuong = 0;
                    try {
                        soLuong = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
                    } catch(NumberFormatException ex) {
                    }
                    if(soLuong > 0) {
                        map.put(ma, soLuong);
                        // Lưu lại mặt hàng đã chọn vào allSelectedMH
                        for (MatHang m : listMH) {
                            if (m.getMa() == ma) {
                                allSelectedMH.put(ma, m);
                                break;
                            }
                        }
                    }
                }
            }

            // Xóa dữ liệu cũ trong bảng
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }

            MatHangDAO mhdao = new MatHangDAO();
            listMH = mhdao.timMH(txtTen.getText().trim());

            for(int i = 0; i < listMH.size(); i++){
                Object[] row = new Object[5];
                row[0] = listMH.get(i).getMa();
                row[1] = listMH.get(i).getTen();
                row[2] = listMH.get(i).getDonViTinh();
                row[3] = listMH.get(i).getDonGia();
                row[4] = map.containsKey(listMH.get(i).getMa()) ? map.get(listMH.get(i).getMa()) : 0;
                tableModel.addRow(row);
            }
        }

        if (btnClicked.equals(btnTiepTuc)) {
            if (tblMatHang.isEditing()) {
                tblMatHang.getCellEditor().stopCellEditing();
            }
            // Lưu lại các số lượng đã nhập trên bảng hiện tại
            for(int i = 0; i < tableModel.getRowCount(); i++) {
                int ma = (Integer)tableModel.getValueAt(i, 0);
                if(tableModel.getValueAt(i, 4) != null) {
                    int soLuong = 0;
                    try {
                        soLuong = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
                    } catch(NumberFormatException ex) {}
                    if(soLuong > 0) {
                        map.put(ma, soLuong);
                        // Lưu lại mặt hàng đã chọn vào allSelectedMH
                        for (MatHang m : listMH) {
                            if (m.getMa() == ma) {
                                allSelectedMH.put(ma, m);
                                break;
                            }
                        }
                    }
                }
            }
            ArrayList<MatHangHopDong> listMHHD = new ArrayList<>();
            for (Integer ma : map.keySet()) {
                int soLuong = map.get(ma);
                MatHang m = allSelectedMH.get(ma);
                if (m != null && soLuong > 0) {
                    MatHangHopDong mhhd = new MatHangHopDong();
                    mhhd.setMatHang(m);
                    mhhd.setSoLuong(soLuong);
                    mhhd.setDonGia(m.getDonGia());
                    mhhd.setThanhTien(soLuong * m.getDonGia());
                    listMHHD.add(mhhd);
                }
            }
            hopDong.setDsMatHangHopDong(listMHHD);
//            (new TinhLichThanhToanFrm(hopDong)).setVisible(true);
//            System.out.println("Số mặt hàng: " + listMHHD.size());
//            for (MatHangHopDong mhhd : hopDong.getDsMatHangHopDong()) {
//                System.out.println("Mã: " + mhhd.getMatHang().getMa() + ", SL: " + mhhd.getSoLuong() + ", Đơn giá: " + mhhd.getDonGia() + ", Thành tiền: " + mhhd.getThanhTien());
//            }
            new TinhLichThanhToanFrm(hopDong).setVisible(true);
            mainFrm.dispose();
        }
    }

//    public static void main(String[] args) {
//        HopDong hdtest = new HopDong();
//        (new TimMHFrm(hdtest)).setVisible(true);
//    }
}
