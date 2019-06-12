package quanlynhahangui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




import main.NhanVienNhaHang;
import main.QuanLyNhaHang;
import quanlynhahang.model.SQLService;

public class DangNhap extends JFrame {
	Connection conn;
	public JTextField txtUser;
	public JPasswordField txtPass;
	public JButton btnDang, btnThoat;

	public DangNhap(String title) {
		super(title);
		SQLService co = new SQLService();
		conn = co.connect1();
		addControls();
		addEvents();

	}

	public void addEvents() {

		txtPass.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {// nut enter
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == '\n') {
					kiemtradangnhap();
				}
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnDang.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				kiemtradangnhap();

			}
		});
		btnThoat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

	}

	public void kiemtradangnhap() {
		if ((txtUser.getText().length() == 0)
				|| (String.valueOf(txtPass.getPassword()).length() == 0)) {
			JOptionPane.showMessageDialog(null,
					"Vui lòng điền đầy đủ thông tin và mật khẩu");

		} else {
			try {
				String sql = "Select * from User1 where TenUser=? and PassWord1=?";
				PreparedStatement prepare = conn.prepareStatement(sql);
				prepare.setString(1, txtUser.getText());
				prepare.setString(2, String.valueOf(txtPass.getPassword()));
				ResultSet rs = prepare.executeQuery();

				if (rs.next()) {
					if (rs.getString(4).contains("Admin")) {
						this.hide();
						QuanLyNhaHang ui = new QuanLyNhaHang();
						ui.showWindow();
					} else {
						this.hide();
						NhanVienNhaHang ui = new NhanVienNhaHang();
						ui.showWindow();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Sai tên tài khoản hoặc mật khẩu");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void addControls() {
		Container con = getContentPane();

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		JPanel pnDangNhap = new JPanel();

		pnDangNhap.setLayout(new FlowLayout());

		pnMain.add(pnDangNhap);
		JLabel lblDangnhap = new JLabel();
		pnDangNhap.setBackground(Color.WHITE);
		lblDangnhap.setForeground(Color.BLACK);
		lblDangnhap.setIcon(new ImageIcon(getClass().getResource("/IMG/account.png")));
		Font font = new Font("arial", Font.BOLD, 20);
		lblDangnhap.setFont(font);
		pnDangNhap.add(lblDangnhap);

		JPanel pnUser = new JPanel();
		pnUser.setLayout(new FlowLayout());
		pnMain.add(pnUser);
		pnUser.setBackground(Color.WHITE);
		JLabel lblUser = new JLabel("Tài khoản");
		lblUser.setForeground(Color.BLACK);

		txtUser = new JTextField(18);
		pnUser.add(lblUser);
		pnUser.add(txtUser);

		JPanel pnPass = new JPanel();
		pnPass.setLayout(new FlowLayout());
		pnMain.add(pnPass);
		pnPass.setBackground(Color.WHITE);
		JLabel lblPass = new JLabel("Mật khẩu");
		lblPass.setForeground(Color.BLACK);
		txtPass = new JPasswordField(18);
		pnPass.add(lblPass);
		pnPass.add(txtPass);

		JPanel pnBut = new JPanel();
		pnBut.setLayout(new FlowLayout());
		pnMain.add(pnBut);
		JPanel pn = new JPanel();
		pn.setPreferredSize(new Dimension(34, 0));
		pnBut.add(pn);
		pnBut.setBackground(Color.WHITE);
		btnDang = new JButton("Đăng nhập");

		btnThoat = new JButton("Thoát");
		pnBut.add(btnDang);
		pnBut.add(btnThoat);

		btnThoat.setPreferredSize(btnDang.getPreferredSize());
		lblPass.setPreferredSize(lblUser.getPreferredSize());

	}

	public void showWindow() {
		this.setSize(440, 284);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

}
