package view.NhanVien;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import dao.NhanVienDAO;
import model.NhanVien;

public class DangNhapFrm extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnDangNhap;

    public DangNhapFrm(){
        super("Đăng nhập hệ thống");
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');
        btnDangNhap = new JButton("Đăng Nhập");

        JPanel pnMain = new JPanel();
//        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.PAGE_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel lblTitle = new JLabel("Đăng Nhập");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnMain.add(lblTitle);
        pnMain.add(Box.createRigidArea(new Dimension(0,20)));

        JPanel pnUsername = new JPanel();
        pnUsername.setLayout(new FlowLayout());
        pnUsername.add(new JLabel("Tên đăng nhập:"));
        pnUsername.add(txtUsername);
        pnMain.add(pnUsername);

        JPanel pnPass = new JPanel();
        pnPass.setLayout(new FlowLayout());
        pnPass.add(new JLabel("Mật khẩu:          "));
        pnPass.add(txtPassword);
        pnMain.add(pnPass);;

        pnMain.add(btnDangNhap);
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));
        btnDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangNhap.addActionListener(this);

        this.setSize(400,200);
        this.setLocationRelativeTo(null); // Hiển thị frame ở giữa màn hình
        // GÁN ENTER CHO NÚT btnKyHopDong
        this.getRootPane().setDefaultButton(btnDangNhap);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDangNhap) {
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtUsername.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtPassword.requestFocus();
                return;
            }

            NhanVien nhanVien = new NhanVien();
            nhanVien.setUsername(username);
            nhanVien.setPassword(password);

            NhanVienDAO nhanVienDAO = new NhanVienDAO();
            boolean DangNhapThanhCong = nhanVienDAO.dangNhap(nhanVien);
            if (DangNhapThanhCong) {
                if (nhanVien.getChucVu().equalsIgnoreCase("Nhân viên")) {
                    (new gdChinhNVFrm(nhanVien)).setVisible(true);
                    this.dispose();
                }
            }
        }
    }

    public static void main(String[] args) {
        DangNhapFrm myFrame = new DangNhapFrm();
        myFrame.setVisible(true);
    }
}
