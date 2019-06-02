package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;


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

public class QuanLyNhaHang extends JFrame {
	JTabbedPane tab, tab1, tab2,tab3,tab4;

	public QuanLyNhaHang() {
		super();
		addControls();
		addEvents();
		showWindow();
	}

	public void addControls() {
		Container con = getContentPane();

		tab = new JTabbedPane(JTabbedPane.LEFT);

		tab.setBackground(Color.YELLOW);
		
		tab1 = new JTabbedPane();
		tab.add(tab1, "Quản Lý Bán Hàng");
		tab1.add(new QuanLyBanHang(), "Chi tiết hóa đơn");
		tab1.add(new Order(), "Gọi món");
		
		tab4=new JTabbedPane();
		tab4.add(new QuanLyChucVu(),"Chức vụ");
		tab4.add(new QuanLyNhanVien(),"Nhân viên");
		// tab.setLayout(new FlowLayout());
		con.add(tab);
		tab.add(tab4, "Nhân Viên");
		tab.add(new QuanLyKhachHang(), "Khách Hàng");
		tab.add(new QuanLyMonAn(), "Món Ăn");
		tab.add(new QuanLyBanAn(), "Bàn Ăn");

		

		tab2 = new JTabbedPane();
		tab.add(tab2, "Quản lý đặt tiệc");
		tab2.add(new QuanLyTiec(), "Chi tiết tiệc");
		tab2.add(new DatTiec(), "Đặt tiệc");
		
		
		tab3=new JTabbedPane();
		tab.add(tab3, "Thống Kê Hóa Đơn");
		tab3.add(new ThongKe(),"Tại Chỗ");
		tab3.add(new ThongKeTiec(),"Đặt Tiệc");
		
		
		
		JLabel lblNhanVien=new JLabel("Nhân Viên");
		lblNhanVien.setIcon(new ImageIcon("img/nhanvien.png"));		
		tab.setTabComponentAt(1, lblNhanVien);
		
		JLabel lblKhachHang=new JLabel("Khách Hàng");
		lblKhachHang.setIcon(new ImageIcon("img/khachhang.gif"));		
		tab.setTabComponentAt(2, lblKhachHang);
		
		JLabel lblMonAn=new JLabel("Món Ăn");
		lblMonAn.setIcon(new ImageIcon("img/monan.png"));		
		tab.setTabComponentAt(3, lblMonAn);
		
		JLabel lblBanAn=new JLabel("Bàn Ăn");
		lblBanAn.setIcon(new ImageIcon("img/banan.png"));		
		tab.setTabComponentAt(4, lblBanAn);
		
		JLabel lblBanHang=new JLabel("Bán Hàng");
		lblBanHang.setIcon(new ImageIcon("img/112.png"));		
		tab.setTabComponentAt(0, lblBanHang);
		
		JLabel lblDatTiec=new JLabel("Đặt Tiệc");
		lblDatTiec.setIcon(new ImageIcon("img/dattiec.png"));		
		tab.setTabComponentAt(5, lblDatTiec);
		
		JLabel lblThongKe=new JLabel("Thống Kê");
		lblThongKe.setIcon(new ImageIcon("img/1.png"));		
		tab.setTabComponentAt(6, lblThongKe);
		
		tab1.setBackground(Color.GREEN);
		tab2.setBackground(Color.BLUE);
		tab3.setBackground(Color.RED);
		//lblNhanVien.setPreferredSize(new Dimension(50, 50));
	
	}

	public void addEvents() {
		tab.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				QuanLyTiec.HienThiDanhSachTiec();
				DatTiec.LayMaHD();
			}
		});
		tab3.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ThongKeTiec.HienThiDoanhThu();
				ThongKe.HienThiDoanhThu();
			
			}
		});
		tab4.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				QuanLyChucVu.HienThi();
				QuanLyNhanVien.LayDanhSachCV();
			}
		});
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
