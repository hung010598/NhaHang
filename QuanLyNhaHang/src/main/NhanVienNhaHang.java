package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import quanlynhahangui.DangNhap;
import quanlynhahangui.DatTiec;
import quanlynhahangui.Order;
import quanlynhahangui.QuanLyBanAn;
import quanlynhahangui.QuanLyBanHang;
import quanlynhahangui.QuanLyChucVu;
import quanlynhahangui.QuanLyKhachHang;
import quanlynhahangui.QuanLyMonAn;
import quanlynhahangui.QuanLyNhanVien;
import quanlynhahangui.QuanLyTiec;
import quanlynhahangui.ThongKe;
import quanlynhahangui.ThongKeTiec;

public class NhanVienNhaHang extends JFrame {
	JTabbedPane tab, tab1, tab2, tab3, tab4;
	JMenuBar mnubar;
	JMenu mnu;
	JMenuItem mnuExit;
	
	
	public NhanVienNhaHang() {
		super();
		addControls();
		addEvents();
		showWindow();
		
		
	}

	public void addControls() {
		Container con = getContentPane();

		mnubar = new JMenuBar();
		setJMenuBar(mnubar);
		mnu = new JMenu("Menu");
		mnubar.add(mnu);
		mnuExit = new JMenuItem("Đăng xuất");
		mnu.add(mnuExit);

		tab = new JTabbedPane(JTabbedPane.LEFT);

		tab.setBackground(Color.YELLOW);

		tab1 = new JTabbedPane();
		tab.add(tab1, "Quản Lý Bán Hàng");
		tab1.add(new QuanLyBanHang(), "Chi tiết hóa đơn");
		tab1.add(new Order(), "Gọi món");

		tab4 = new JTabbedPane();
		tab4.add(new QuanLyChucVu(), "Chức vụ");
		tab4.add(new QuanLyNhanVien(), "Nhân viên");
		// tab.setLayout(new FlowLayout());
		con.add(tab);
		
		tab.add(new QuanLyKhachHang(), "Khách Hàng");
		tab.add(new QuanLyMonAn(), "Món Ăn");
		tab.add(new QuanLyBanAn(), "Bàn Ăn");

		tab2 = new JTabbedPane();
		tab.add(tab2, "Quản lý đặt tiệc");
		tab2.add(new QuanLyTiec(), "Chi tiết tiệc");
		tab2.add(new DatTiec(), "Đặt tiệc");

		tab3 = new JTabbedPane();
		
		tab3.add(new ThongKe(), "Tại Chỗ");
		tab3.add(new ThongKeTiec(), "Đặt Tiệc");

		JLabel lblKhachHang = new JLabel("Khách Hàng");
		lblKhachHang.setIcon(new ImageIcon(getClass().getResource("/IMG/khachhang.gif")));
		tab.setTabComponentAt(1, lblKhachHang);

		JLabel lblMonAn = new JLabel("Món Ăn");
		lblMonAn.setIcon(new ImageIcon(getClass().getResource("/IMG/monan.png")));
		tab.setTabComponentAt(2, lblMonAn);

		JLabel lblBanAn = new JLabel("Bàn Ăn");
		lblBanAn.setIcon(new ImageIcon(getClass().getResource("/IMG/banan.png")));
		tab.setTabComponentAt(3, lblBanAn);

		JLabel lblBanHang = new JLabel("Bán Hàng");
		lblBanHang.setIcon(new ImageIcon(getClass().getResource("/IMG/112.png")));
		tab.setTabComponentAt(0, lblBanHang);

		JLabel lblDatTiec = new JLabel("Đặt Tiệc");
		lblDatTiec.setIcon(new ImageIcon(getClass().getResource("/IMG/dattiec.png")));
		tab.setTabComponentAt(4, lblDatTiec);

		tab1.setBackground(Color.GREEN);
		tab2.setBackground(Color.BLUE);
		tab3.setBackground(Color.RED);
		// lblNhanVien.setPreferredSize(new Dimension(50, 50));
		
	}

	public void addEvents() {
		tab.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mousePressed(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseExited(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseEntered(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseClicked(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub
				QuanLyTiec.HienThiDanhSachTiec();
				ThongKeTiec.HienThiDoanhThu();
				ThongKe.HienThiDoanhThu();
				Order.pnLeftofBot.removeAll();
				Order.HienThiDanhSachBan(Order.dsBan);
				Order.pnLeftofBot.repaint();
				Order.pnLeftofBot.revalidate();
				QuanLyBanHang.pnLeftofBot.removeAll();
				QuanLyBanHang.HienThiDanhSachBan(Order.dsBan);
				QuanLyBanHang.pnLeftofBot.repaint();
				QuanLyBanHang.pnLeftofBot.revalidate();
			}
		});
		tab1.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mousePressed(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseExited(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseEntered(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseClicked(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

				Order.pnLeftofBot.removeAll();
				Order.HienThiDanhSachBan(Order.dsBan);
				Order.pnLeftofBot.repaint();
				Order.pnLeftofBot.revalidate();

				QuanLyBanHang.pnLeftofBot.removeAll();
				QuanLyBanHang.HienThiDanhSachBan(Order.dsBan);
				QuanLyBanHang.pnLeftofBot.repaint();
				QuanLyBanHang.pnLeftofBot.revalidate();
			}
		});
		tab2.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mousePressed(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseExited(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseEntered(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub

			}

			public void mouseClicked(MouseEvent e) {
				// TOrderO Auto-generated methOrder stub
				QuanLyTiec.HienThiDanhSachTiec();
				DatTiec.LayMaHD();
			}
		});
		
		
		mnuExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TOrderO Auto-generated methOrder stub
				logout();
				DangNhap ui = new DangNhap("Đăng nhập");
				// System.exit(0);
				ui.showWindow();

			}
		});
	}

	public void logout() {
		this.setVisible(false);
	}

	public void showWindow() {
		// kich thuoc cua so
		this.setSize(1200, 700);
		// chon de thoat
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// nam giua man hinh
		this.setLocationRelativeTo(null);
		// khong cho phep thay doi kick thuoc cua so
		// this.setResizable(false);
		// hien thi len man hinh
		this.setVisible(true);
	}
}
