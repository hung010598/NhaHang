package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import quanlynhahangui.*;
public class QuanLy extends JFrame {
	Connection conn;
	JButton btnNV, btnHH, btnBan, btnThong, btnLog,btnQLBan,btnKhachHang;
	public QuanLy(String title) {
		super(title);
		addControls();
		addEvents();

	}

	private void addEvents() {

		/*btnHH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				GiaoDienQLHH();

			}

			private void GiaoDienQLHH() {
				QuanLyMonAn S = new QuanLyMonAn("Quản lý món ăn");
				S.showWindow();

			}

		});*/

		/*btnNV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				QuanLyNhanVien S = new QuanLyNhanVien("Quản lý nhân viên");
				S.showWindow();

			}
		});*/
		/*btnBan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				QuanLyBanHang S = new QuanLyBanHang("Quản lý bán hàng");
				S.showWindow();

			}
		});*/
		/*btnThong.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ThongKe S = new ThongKe("Thống kê");
				S.showWindow();

			}
		});*/
		btnLog.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				logout();
				DangNhap S = new DangNhap("Đăng nhập");
				S.showWindow();

			}
		});
		/*btnQLBan.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QuanLyBanAn ui=new QuanLyBanAn("Quản lý bàn ăn");
				ui.showWindow();
			}
		});*/
		/*btnKhachHang.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QuanLyKhachHang kh=new QuanLyKhachHang("Khách Hàng");
				kh.showWindow();
			}
		});*/

	}

	public void logout() {
		this.setVisible(false);
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		JLabel lbl1 = new JLabel();
		ImageIcon img1 = new ImageIcon("img/welcome.png");

		lbl1.setIcon(img1);

		//pnBottom.add(lbl1, BorderLayout.CENTER);
		JLabel lbl = new JLabel();
		ImageIcon img = new ImageIcon("img/33.jpg");
		
		lbl.setIcon(img);
		//lbl.setPreferredSize(new Dimension(0,200));
		//pnBottom.add(lbl, BorderLayout.SOUTH);

		con.add(pnBottom, BorderLayout.CENTER);
		JPanel pnBut = new JPanel();
		pnBut.setLayout(new FlowLayout());
		btnNV = new JButton("Nhân Viên");
		btnHH = new JButton("Món Ăn");
		btnBan = new JButton("Bán Hàng");
		btnThong = new JButton("Thống kê");
		btnLog = new JButton();
		btnLog.setIcon(new ImageIcon("img/31.png"));
		pnBut.add(btnHH);
		btnHH.setIcon(new ImageIcon("img/monan.png"));
		btnQLBan=new JButton("Bàn ăn");
		btnQLBan.setIcon(new ImageIcon("img/banan.png"));
		btnKhachHang=new JButton("Khách hàng");
		btnKhachHang.setIcon(new ImageIcon("img/banan.png"));
		pnBut.add(btnNV);
		btnNV.setIcon(new ImageIcon("img/nhanvien.png"));
		pnBut.add(btnBan);
		btnBan.setIcon(new ImageIcon("img/ic1s1.png"));
		//btnBan.setEnabled(false);
		pnBut.add(btnThong);
		btnThong.setIcon(new ImageIcon("img/1.png"));
		pnBut.add(btnQLBan);
		pnBut.add(btnKhachHang);
		pnBut.add(btnLog);
		
		pnBottom.add(pnBut, BorderLayout.CENTER);
		
	}

	public void showWindow() {
		this.setSize(580, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}
