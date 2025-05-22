package view.KhachHang;

import dao.KhachHangDAO;
import model.HopDong;
import model.KhachHang;
import model.NhanVien;
import view.DoiTac.TimDTFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TimKHFrm extends JFrame implements ActionListener {
    private JTextField txtTen;
    private JButton btnTim;
    private JButton btnThemMoi;
    private JTable tblKhachHang;
    private ArrayList<KhachHang> listKH;
    private NhanVien nv;
    private TimKHFrm mainFrm;

    public TimKHFrm(NhanVien nv) {
        super("Tìm kiếm khách hàng");
        this.nv = nv;
        listKH = new ArrayList<>();
        mainFrm = this;

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblTitle = new JLabel("Tìm kiếm khách hàng");
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
        tblKhachHang = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
        tblKhachHang.setFillsViewportHeight(false);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
        tblKhachHang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblKhachHang.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblKhachHang.getRowHeight(); // get the row of the button

                // *Checking the row or column is valid or not
                if (e.getClickCount() == 2 && row < tblKhachHang.getRowCount() && row >= 0 && column < tblKhachHang.getColumnCount() && column >= 0) {
                    HopDong hopDong = new HopDong();
                    hopDong.setKhachHang(listKH.get(row));
                    hopDong.setNhanVien(nv);
                    (new TimDTFrm(hopDong)).setVisible(true);
                    mainFrm.dispose();
                }
            }
        });

        pnKetQua.add(scrollPane);
        pnMain.add(pnKetQua);

        JPanel pnThemMoi = new JPanel();
        pnThemMoi.setLayout(new BoxLayout(pnThemMoi, BoxLayout.X_AXIS));
        pnThemMoi.add(Box.createRigidArea(new Dimension(5, 0)));

        btnThemMoi = new JButton("Thêm mới");
        pnThemMoi.add(Box.createHorizontalGlue());
        btnThemMoi.addActionListener(this);
        pnThemMoi.add(btnThemMoi);
        pnThemMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, pnThemMoi.getPreferredSize().height));
        pnMain.add(pnThemMoi);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        this.setSize(900,500);
        this.setLocationRelativeTo(null); // Hiển thị frame ở giữa màn hình
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
            KhachHangDAO khdao = new KhachHangDAO();
            listKH = khdao.timKH(txtTen.getText().trim());

            String[] columnNames = {"Mã", "Tên", "CCCD/CMND", "Địa chỉ", "SDT", "Email", "Ghi chú"};
            String[][] value = new String[listKH.size()][7];
            for(int i = 0; i < listKH.size(); i++){
                value[i][0] = listKH.get(i).getMa() +"";
                value[i][1] = listKH.get(i).getTen();
                value[i][2] = listKH.get(i).getCccd();
                value[i][3] = listKH.get(i).getDiaChi();
                value[i][4] = listKH.get(i).getSdt();
                value[i][5] = listKH.get(i).getEmail();
                value[i][6] = listKH.get(i).getGhiChu();
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //unable to edit cells
                    return false;
                }
            };
            tblKhachHang.setModel(tableModel);
        }


    }
//    public static void main(String[] args) {
//        NhanVien nvTest = new NhanVien();
//        (new TimKHFrm(nvTest)).setVisible(true);
//    }
}
