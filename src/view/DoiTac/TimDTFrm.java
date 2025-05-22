package view.DoiTac;

import dao.DoiTacDAO;
import model.DoiTac;
import model.HopDong;
import view.MatHang.TimMHFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TimDTFrm extends JFrame implements ActionListener {
    private JTextField txtTen;
    private JButton btnTim;
    private JTable tblDoiTac;
    private ArrayList<DoiTac> listDT;
    private TimDTFrm mainFrm;
    private HopDong hopDong;

    public TimDTFrm(HopDong hopDong) {
        super("Tìm kiếm đối tác");
        this.hopDong = hopDong;
        listDT = new ArrayList<>();
        mainFrm = this;

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblTitle = new JLabel("Tìm kiếm đối tác");
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
        tblDoiTac = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblDoiTac);
        tblDoiTac.setFillsViewportHeight(false);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
        tblDoiTac.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblDoiTac.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblDoiTac.getRowHeight(); // get the row of the button

                // *Checking the row or column is valid or not
                if (e.getClickCount() == 2 && row < tblDoiTac.getRowCount() && row >= 0 && column < tblDoiTac.getColumnCount() && column >= 0) {
                    hopDong.setDoiTac(listDT.get(row));
                    (new TimMHFrm(hopDong)).setVisible(true);
                    mainFrm.dispose();
                }
            }
        });

        pnKetQua.add(scrollPane);
        pnMain.add(pnKetQua);

        this.setSize(800,500);
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
            DoiTacDAO dtdao = new DoiTacDAO();
            listDT = dtdao.timDT(txtTen.getText().trim());

            String[] columnNames = {"Mã", "Tên", "Địa chỉ", "Thông tin liên hệ", "Thông tin thanh toán"};
            String[][] value = new String[listDT.size()][5];
            for(int i = 0; i < listDT.size(); i++){
                value[i][0] = listDT.get(i).getMa() + "";
                value[i][1] = listDT.get(i).getTen();
                value[i][2] = listDT.get(i).getDiaChi();
                value[i][3] = listDT.get(i).getThongTinLienHe();
                value[i][4] = listDT.get(i).getThongTinThanhToan();
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //unable to edit cells
                    return false;
                }
            };
            tblDoiTac.setModel(tableModel);
        }
    }
//    public static void main(String[] args) {
//        HopDong hdTest = new HopDong();
//        (new TimDTFrm(hdTest)).setVisible(true);
//    }
}
