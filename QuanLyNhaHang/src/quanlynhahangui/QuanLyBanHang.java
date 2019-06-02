package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import quanlynhahang.model.DanhMucBan;
import quanlynhahang.model.DanhMucBanService;
import quanlynhahang.model.NhanVien;
import quanlynhahang.model.Ban;
import quanlynhahang.model.BanService;
import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.NhanVienService;
import quanlynhahang.model.SQLService;
import quanlynhahang.model.SanPham;
import quanlynhahang.model.SanPhamService;

public class QuanLyBanHang extends JPanel {
	JPanel pnLeftofBottom, pnRightofBottom;
	public static JPanel pnLeftofBot;
	JPanel pnRightOfBot;
	JPanel pnTienGiam;
	JButton btnThem, btnThanhToan, btnDat, btnIn, btnLog, btnThemB, btnXoaB,
			btnRefresh, btnGiam;
	JTextField txtSoluong, txtKhach;
	static JTextField txtTong;
	JTextField txtPhatSinh;
	static JTextField txtTongT;
	JTextField txtNgay;
	static JTextField txtMaHD;
	JTextField txtGiam;
	static Connection conn;
	Vector<Object> v = new Vector<Object>();
	Vector<Ban> dsBan;
	JComboBox<DanhMuc> cbDM;
	JComboBox<NhanVien> cbNV;
	static DefaultTableModel dtm;
	DefaultTableModel dtm1;
	static JTable tbl;
	JTable tbl1;
	Vector<DanhMuc> dsDM;
	Vector<SanPham> dsHH;
	Vector<NhanVien> dsNV;
	JList<SanPham> listHH;
	DanhMuc dm;
	DanhMucBan dmb;
	static String temp;
	static int nvTemp;
	int thanhtoantemp = 0;
	SanPham hhoa;
	static NhanVien nv;
	static Ban ba;
	static String maHD = "";
	static int Status = 0;
	static int Status1 = 1;
	Calendar ca = Calendar.getInstance();
	Date t = ca.getTime();
	static Double S = 0.0;
	String st = "";
	String NgayHD = "";
	int ma = 0;
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	JComboBox<DanhMucBan> cboLoaiBan;
	Vector<DanhMucBan> dsDMB;

	public QuanLyBanHang() {
		super();
		SQLService co = new SQLService();
		conn = co.connect1();
		addControls();
		addEvents();
		LayDanhSachDanhMuc();
	}

	private void LayDanhSachDanhMuc() {
		// TODO Auto-generated method stub
		DanhMucBanService dm = new DanhMucBanService();
		dsDMB = dm.DanhSachDanhMuc();
		for (DanhMucBan d : dsDMB) {

			cboLoaiBan.addItem(d);
		}
	}

	private void addEvents() {
		// HienThiDanhSachBan();

		HienThiDanhSachNV();
		cbNV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cbNV.getSelectedIndex() == -1)
					return;
				nv = (NhanVien) cbNV.getSelectedItem();

			}
		});

		btnGiam.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Vector<Object> vc = new Vector<Object>();
					String sql = "select * from KhachHang where MaKH like ?";
					PreparedStatement prepare = conn.prepareStatement(sql);
					prepare.setString(1, txtGiam.getText());
					ResultSet result = prepare.executeQuery();

					while (result.next()) {
						vc.add(result.getString(1));
					}
					if (vc.size() > 0) {
						JLabel lbl = new JLabel("Bạn được giảm 10%");

						pnTienGiam.removeAll();
						pnTienGiam.add(lbl);
						pnTienGiam.revalidate();
						Double T;
						T = S * 9 / 10;
						txtTong.setText(T.toString());
						ma = 1;

					} else {

						pnTienGiam.removeAll();
						pnTienGiam.revalidate();
						JOptionPane.showMessageDialog(null,
								"Mã khách hàng không đúng");
						txtTong.setText(S.toString());
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnThanhToan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null,
						"Bạn muốn thanh toán hóa đơn cho '" + ba.getTenBan()
								+ "'", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION) {
					NgayHD = spf.format(t = ca.getTime());

					try {
						String sqll = "Update HoaDon set HoaDon.status1=1,NgayHD=?,MaKH=?,ThanhTien=?,MaNV=? where HoaDon.MaHD=?";// status=1
																																	// //
																																	// toán
						PreparedStatement ppp = conn.prepareStatement(sqll);
						ppp.setString(1, spf.format(t = ca.getTime()));
						if (ma == 1)
							ppp.setString(2, txtGiam.getText());
						else
							ppp.setString(2, null);
						ppp.setFloat(3, Float.parseFloat(txtTong.getText()));
						ppp.setString(4, nv.getMaNV());
						ppp.setString(5, txtMaHD.getText());
						int i = ppp.executeUpdate();
						if (i > 0) {

							JOptionPane.showMessageDialog(null,
									"Thanh toán thành công");
							ba.setStatus(0);
							String S = "Update Ban set Ban.Status2=0 where Ban.MaBan=?";// Status2=0
																						// bàn
																						// trống
																						// ,hết
																						// người
																						// dùng
							PreparedStatement p = conn.prepareStatement(S);
							p.setString(1, ba.getMaBan());
							p.executeUpdate();
							HienThiThongTin();
							Status1 = 0;
							temp = ba.getMaBan();
							thanhtoantemp = 1;// mục in hóa đơn(đã thanh toán)
							ba.setStatus(Status);

						} else {
							JOptionPane.showMessageDialog(null,
									"Thanh toán thất bại");
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else
					return;
			}
		});

		btnIn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					if (thanhtoantemp == 0) {
						JOptionPane.showMessageDialog(null,
								"Bạn chưa thanh toán");
						return;
					} else if (nv == null) {
						JOptionPane.showMessageDialog(null,
								"Vui lòng chọn nhân viên để thu ngân");
						return;
					} else {
						if (nvTemp == 0 && maHD != "")// nvTemp=0 tức la chưa có
														// nhân viên thanh toán
														// hóa đơn này, maHD !""
														// tức là đã tồn tại hóa
														// đơn này
						{
							String sql = "update HoaDon set MaNV=? where MaHD=?";
							PreparedStatement pr = conn.prepareStatement(sql);
							pr.setString(1, nv.getMaNV());
							pr.setString(2, maHD);
							pr.executeUpdate();
							nvTemp = 1;// Đã có nhân viên thanh toán hóa đơn này

						}

					}
					HoaDon U = new HoaDon("Phiếu thanh toán", ba, NgayHD, maHD);
					U.showWindow();

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		/*
		 * btnChiTiet.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub ChiTietHoaDon ui=new ChiTietHoaDon("Chi tiết hóa đơn");
		 * ui.showWindow(); } });
		 */
		cboLoaiBan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboLoaiBan.getSelectedIndex() == -1)
					return;
				dmb = (DanhMucBan) cboLoaiBan.getSelectedItem();
				BanService bSv = new BanService();
				dsBan = bSv.DanhSachBan(dmb.getMaDMB());
				pnLeftofBot.removeAll();
				HienThiDanhSachBan(dsBan);
				pnLeftofBot.repaint();
				pnLeftofBot.revalidate();
			}
		});

	}

	private void HienThiDanhSachNV() {
		// TODO Auto-generated method stub
		NhanVienService nvSv = new NhanVienService();
		dsNV = nvSv.HienThiDanhSachNhanVien("CV1");
		for (NhanVien nv : dsNV) {

			cbNV.addItem(nv);
		}

	}

	public static void HienThiDanhSachBan(Vector<Ban> dsBan) {
		// TODO Auto-generated method stub

		for (final Ban ban : dsBan) {
			JPanel pn = new JPanel();
			pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
			JPanel p = new JPanel();
			pn.add(p);
			final JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(75, 75));
			btn.setIcon(new ImageIcon("img/n.jpg"));
			JLabel lbl = new JLabel(ban.getTenBan());
			lbl.setAlignmentX(CENTER_ALIGNMENT);
			p.add(btn);
			pn.add(lbl);
			ba = ban;

			switch (ban.getStatus()) {
			case 0:// Bàn chưa có người dùng
				btn.setBackground(Color.CYAN);
				btn.setIcon(new ImageIcon("img/n.jpg"));
				break;
			default:// Bàn đã có người dùng
				btn.setIcon(new ImageIcon("img/u.jpg"));
				btn.setBackground(Color.GREEN);
				break;
			}
			pnLeftofBot.add(pn);
			btn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					S = 0.0;// má»¥c thanh toÃ¡n
					txtTong.setText("");// mục thanh toán
					txtTongT.setText("");
					txtMaHD.setText("");// mục thanh toán
					try {

						dtm.setRowCount(0);

						String Sql = "select SanPham.TenSP, Soluong,DonGia,DVT, DonGia*Soluong,Ban.TenBan,Ban.MaBan,HoaDon.MaHD from SanPham,HoaDon,ChiTietHoaDon,Ban where SanPham.MaSP=ChiTietHoaDon.MaSP and HoaDon.MaHD=ChiTietHoaDon.MaHD and Ban.MaBan=HoaDon.MaBan and Ban.MaBan=? and HoaDon.status1=0";
						PreparedStatement prepare = conn.prepareStatement(Sql);
						prepare.setString(1, ban.getMaBan());
						ResultSet result = prepare.executeQuery();
						ba = ban;

						while (result.next()) {
							Vector<Object> vec = new Vector<Object>();
							vec.add(result.getString(1));
							vec.add(result.getInt(2));
							vec.add(result.getInt(3));
							vec.add(result.getString(4));
							vec.add(result.getInt(5));
							maHD = result.getString(8);
							S = S + result.getInt(5);// Thành tiền= số lượng *
														// đơn giá
							dtm.addRow(vec);
							int i = tbl.getRowCount();
							txtMaHD.setText(maHD);// hiển thị mã HD theo bàn

						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					txtTong.setText(S.toString());
					txtTongT.setText(S.toString());
					if (Status == 1 && ba.getMaBan() == temp)// Status==1 :đã có
																// hóa đơn,
																// ba.getMaBan()==temp:tức
																// là chuyển đôi
																// icon button
																// theo mã
					{
						btn.setIcon(new ImageIcon("img/u.jpg"));
						btn.setBackground(Color.GREEN);
						Status = 0;
						temp = "";
					}
					if (Status1 == 0 && ba.getMaBan() == temp) {
						btn.setBackground(Color.CYAN);
						btn.setIcon(new ImageIcon("img/n.jpg"));
						Status1 = 1;
						temp = "";
					}
					// Khi click vào 1 button(1 bàn ) thì nvTemp=0,nv=null;
					nvTemp = 0;// mục in hóa đơn(thiết lập chưa có nhân vvieen
								// thanh toán hóa đơn này)
					nv = null;// mục in hóa đơn(thiết lập chưa chọn nhân viên
								// thanh toán hóa đơn)

				}

			});

		}
	}

	public void HienThiThongTin() {
		S = 0.0;
		txtTong.setText("");
		try {

			dtm.setRowCount(0);

			String Sql = "select SanPham.TenSP, Soluong,DonGia,DVT, DonGia*Soluong,Ban.TenBan,Ban.MaBan,HoaDon.MaHD from SanPham,HoaDon,ChiTietHoaDon,Ban where SanPham.MaSP=ChiTietHoaDon.MaSP and HoaDon.MaHD=ChiTietHoaDon.MaHD and Ban.MaBan=HoaDon.MaBan and Ban.MaBan=?  and HoaDon.status1=0";
			PreparedStatement prepare = conn.prepareStatement(Sql);
			prepare.setString(1, ba.getMaBan());
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				Vector<Object> vec = new Vector<Object>();
				vec.add(result.getString(1));
				vec.add(result.getInt(2));
				vec.add(result.getInt(3));
				vec.add(result.getString(4));
				vec.add(result.getInt(5));

				S = S + result.getInt(5);// Thành tiền= số lượng * đơn giá
				dtm.addRow(vec);
			}

			txtTong.setText(S.toString());// Hiển thị tổng tiền của món ăn
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void addControls() {

		setLayout(new GridLayout(1, 1));
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 100));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop,
				pnBottom);
		sp.setOneTouchExpandable(true);
		add(sp, BorderLayout.CENTER);

		JPanel pnT = new JPanel();
		pnT.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl = new JLabel("Quản lý bán hàng");
		Font ff = new Font("arial", Font.BOLD, 23);
		lbl.setFont(ff);
		lbl.setForeground(Color.BLUE);
		pnT.add(lbl);
		pnTop.add(pnT, BorderLayout.CENTER);

		pnLeftofBottom = new JPanel();
		pnLeftofBottom.setPreferredSize(new Dimension(280, 0));
		pnLeftofBottom.setLayout(new BorderLayout());
		pnLeftofBot = new JPanel();

		pnLeftofBot.setLayout(new GridLayout(6, 3));
		pnRightofBottom = new JPanel();
		pnRightofBottom.setLayout(new BorderLayout());

		JScrollPane sc1 = new JScrollPane(pnLeftofBot,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnLeftofBottom.add(sc1, BorderLayout.CENTER);
		pnBottom.add(pnLeftofBottom, BorderLayout.WEST);
		pnBottom.add(pnRightofBottom, BorderLayout.CENTER);

		JPanel pnTopOfRightOfBottom = new JPanel();
		pnTopOfRightOfBottom.setLayout(new BorderLayout());
		pnRightofBottom.add(pnTopOfRightOfBottom, BorderLayout.NORTH);
		pnTopOfRightOfBottom.setPreferredSize(new Dimension(0, 50));

		JPanel pnThemMoi = new JPanel();
		pnThemMoi.setLayout(new BoxLayout(pnThemMoi, BoxLayout.Y_AXIS));
		pnTopOfRightOfBottom.add(pnThemMoi, BorderLayout.CENTER);

		JPanel pnMaHD = new JPanel();
		pnMaHD.setLayout(new BoxLayout(pnMaHD, BoxLayout.X_AXIS));
		pnThemMoi.add(pnMaHD);
		JPanel pnM = new JPanel();
		pnMaHD.add(pnM);
		pnM.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMa = new JLabel("Mã HĐ :");
		txtMaHD = new JTextField(5);
		pnM.add(lblMa);
		pnM.add(txtMaHD);
		txtMaHD.setPreferredSize(new Dimension(40, 20));
		lblMa.setForeground(Color.BLUE);

		JPanel pnNgayLap = new JPanel();
		pnNgayLap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgay = new JLabel("Ngày Lập HĐ:");
		lblNgay.setForeground(Color.BLUE);
		txtNgay = new JTextField();
		pnNgayLap.add(lblNgay);
		pnNgayLap.add(txtNgay);
		// pnThemMoi.add(pnNgayLap);
		txtNgay.setPreferredSize(new Dimension(175, 20));

		JPanel pnNhan = new JPanel();
		pnNhan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNhan = new JLabel("Nhân viên:");
		lblNhan.setForeground(Color.RED);
		cbNV = new JComboBox<NhanVien>();
		cbNV.setPreferredSize(new Dimension(175, 22));
		pnNhan.add(lblNhan);
		pnNhan.add(cbNV);

		// lblNhan.setPreferredSize(lblNgay.getPreferredSize());
		lblMa.setPreferredSize(lblNgay.getPreferredSize());

		JPanel pnBan = new JPanel();
		pnBan.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblLoaiBan = new JLabel("Loại bàn:");
		cboLoaiBan = new JComboBox();
		pnBan.add(lblLoaiBan);
		pnBan.add(cboLoaiBan);
		pnLeftofBottom.add(pnBan, BorderLayout.SOUTH);
		dtm = new DefaultTableModel();
		dtm.addColumn("Tên Hàng Hóa");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Đơn giá");
		dtm.addColumn("ĐVT");
		dtm.addColumn("Thành tiền");
		tbl = new JTable(dtm);
		JScrollPane sc11 = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnRightofBottom.add(sc11, BorderLayout.CENTER);

		JPanel pnChiTiet = new JPanel();
		pnRightOfBot = new JPanel();
		pnRightOfBot.setLayout(new BorderLayout());
		// pnRightOfBot.setPreferredSize(new Dimension(400, 0));
		JPanel pnTFake = new JPanel();
		pnTFake.setPreferredSize(new Dimension(0, 300));
		// pnRightOfBot.add(pnTFake,BorderLayout.NORTH);

		Font font = new Font("arial", Font.BOLD, 15);

		pnChiTiet = new JPanel();
		pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
		pnChiTiet.add(pnNhan);
		JPanel pnTongCong = new JPanel();
		pnTongCong.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTongCong = new JLabel("Tổng Cộng:");
		lblTongCong.setPreferredSize(new Dimension(110, 30));
		lblTongCong.setForeground(Color.RED);
		lblTongCong.setFont(font);
		txtTongT = new JTextField(20);
		txtTongT.setPreferredSize(new Dimension(300, 30));
		txtTongT.setForeground(Color.RED);
		txtTongT.setEditable(false);
		pnTongCong.add(lblTongCong);
		pnTongCong.add(txtTongT);
		pnChiTiet.add(pnTongCong);

		JPanel pnTongTien = new JPanel();
		pnTongTien.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTong = new JLabel("Thành tiển:");
		lblTong.setPreferredSize(new Dimension(110, 30));
		lblTong.setForeground(Color.RED);
		lblTong.setFont(font);

		txtTong = new JTextField(20);
		txtTong.setPreferredSize(new Dimension(300, 30));
		txtTong.setForeground(Color.RED);
		txtTong.setEditable(false);
		pnTongTien.add(lblTong);
		pnTongTien.add(txtTong);

		JLabel lblNhapMa = new JLabel(
				"Nhập mã khách hàng hoặc mã giảm giá(nếu có)");
		pnChiTiet.add(lblNhapMa);

		JPanel pnGiam = new JPanel();
		pnGiam.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnGiam = new JButton("Xác nhận:");
		btnGiam.setFont(font);
		btnGiam.setForeground(Color.RED);
		txtGiam = new JTextField(20);
		txtGiam.setPreferredSize(new Dimension(300, 30));
		txtGiam.setForeground(Color.RED);
		pnGiam.add(btnGiam);
		pnGiam.add(txtGiam);
		pnChiTiet.add(pnGiam);

		pnTienGiam = new JPanel();
		pnTienGiam.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnChiTiet.add(pnTienGiam);
		pnChiTiet.add(pnTongTien);

		btnGiam.setPreferredSize(new Dimension(110, 30));
		pnRightOfBot.add(pnChiTiet, BorderLayout.CENTER);
		JPanel pnThanhToan = new JPanel();
		pnThanhToan.setLayout(new FlowLayout());

		btnIn = new JButton();
		btnIn.setIcon(new ImageIcon("img/412.png"));
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setForeground(Color.BLUE);
		btnThanhToan.setPreferredSize(new Dimension(100, 35));

		pnThanhToan.add(btnThanhToan);
		pnThanhToan.add(btnIn);
		pnRightOfBot.add(pnThanhToan, BorderLayout.SOUTH);
		Font font1 = new Font("arial", Font.BOLD, 15);
		lblTong.setFont(font1);
		pnRightofBottom.add(pnRightOfBot, BorderLayout.SOUTH);
		lblNhan.setPreferredSize(lblTong.getPreferredSize());
		lblNhan.setFont(font1);
	}
}
