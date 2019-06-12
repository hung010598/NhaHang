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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import quanlynhahang.model.Ban;
import quanlynhahang.model.BanService;
import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucBan;
import quanlynhahang.model.DanhMucBanService;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.NhanVien;
import quanlynhahang.model.SQLService;
import quanlynhahang.model.SanPham;
import quanlynhahang.model.SanPhamService;

public class Order extends JPanel {
	JPanel pnCenterOfBot, pnLeftofBottom;
	public static JPanel pnLeftofBot;
	JTextField txtSoLuong;
	static JTextField txtMaHD;
	JComboBox<DanhMuc> cboDanhMuc;
	JButton btnOrder;
	Vector<DanhMuc> dsDM;
	Vector<SanPham> dsSp;
	DanhMuc dm;
	SanPham sp;
	static NhanVien nv;
	JComboBox<DanhMucBan> cboLoaiBan;
	Vector<DanhMucBan> dsDMB;
	DanhMucBan dmb;
	public static Vector<Ban> dsBan;
	static Ban ba;
	JComboBox cboSize;
	static String temp;
	static int nvTemp;
	int thanhtoantemp = 0;
	static int Status = 0;
	static int Status1 = 1;
	Calendar ca = Calendar.getInstance();
	Date t = ca.getTime();
	static Double S = 0.0;
	static String maHD = "";
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static Connection conn;
	static PreparedStatement prepare;

	public Order() {
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

	public void addControls() {
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
		pnT.setPreferredSize(new Dimension(0, 40));
		pnTop.add(pnT, BorderLayout.NORTH);

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setLayout(new FlowLayout());
		JLabel lblTieuDe = new JLabel();
		lblTieuDe.setText("Order");
		lblTieuDe.setBackground(Color.LIGHT_GRAY);
		Font fontTieuDe = new Font("arial", Font.PLAIN, 20);
		lblTieuDe.setFont(fontTieuDe);
		pnTieuDe.add(lblTieuDe);
		pnTop.add(pnTieuDe, BorderLayout.NORTH);

		JPanel pnCenterOfBottom = new JPanel();
		pnCenterOfBottom.setLayout(new BorderLayout());
		pnBottom.add(pnCenterOfBottom, BorderLayout.CENTER);

		JPanel pnDM = new JPanel();
		pnDM.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDM = new JLabel("Danh mục:");
		cboDanhMuc = new JComboBox();
		pnDM.add(lblDM);
		pnDM.add(cboDanhMuc);
		pnCenterOfBottom.add(pnDM, BorderLayout.NORTH);

		pnCenterOfBot = new JPanel();
		pnCenterOfBot.setLayout(new GridLayout(6, 5));
		JScrollPane crBottom = new JScrollPane(pnCenterOfBot,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfBottom.add(crBottom, BorderLayout.CENTER);

		JPanel pnBotOfBot = new JPanel();
		pnBotOfBot.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblMaHD = new JLabel("Mã HĐ:");
		txtMaHD = new JTextField(5);
		pnBotOfBot.add(lblMaHD);
		pnBotOfBot.add(txtMaHD);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		txtSoLuong = new JTextField(2);
		pnBotOfBot.add(lblSoLuong);
		pnBotOfBot.add(txtSoLuong);

		btnOrder = new JButton("Gọi món");
		pnBotOfBot.add(btnOrder);
		pnCenterOfBottom.add(pnBotOfBot, BorderLayout.SOUTH);

		pnLeftofBottom = new JPanel();
		pnLeftofBottom.setPreferredSize(new Dimension(280, 0));
		pnLeftofBottom.setLayout(new BorderLayout());
		pnLeftofBot = new JPanel();
		pnLeftofBot.setLayout(new GridLayout(6, 3));

		JPanel pnBan = new JPanel();
		pnBan.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblLoaiBan = new JLabel("Loại bàn:");
		cboLoaiBan = new JComboBox();
		pnBan.add(lblLoaiBan);
		pnBan.add(cboLoaiBan);
		pnLeftofBottom.add(pnBan, BorderLayout.SOUTH);
		TitledBorder title = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "Danh Mục Bàn",
				TitledBorder.CENTER, 0);
		title.setTitleColor(Color.BLUE);
		pnLeftofBottom.setBorder(title);
		JScrollPane sc1 = new JScrollPane(pnLeftofBot,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnLeftofBottom.add(sc1, BorderLayout.CENTER);
		pnBottom.add(pnLeftofBottom, BorderLayout.WEST);
	}

	public void addEvents() {
		LayMaHD();
		HienThiDanhMuc();
		// HienThiSanPham(dsSp);
		cboDanhMuc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboDanhMuc.getSelectedIndex() == -1)
					return;
				dm = (DanhMuc) cboDanhMuc.getSelectedItem();
				SanPhamService spSv = new SanPhamService();
				dsSp = spSv.DanhSachHangHoa(dm.getMaDM());
				pnCenterOfBot.removeAll();
				HienThiSanPham(dsSp);
				pnCenterOfBot.repaint();
				pnCenterOfBot.revalidate();
			}
		});
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
		btnOrder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int m = 1;
				ResultSet result;
				thanhtoantemp = 0;// mục in hóa đơn(chưa thanh toán)
				try {
					String s = "Select * from HoaDon where HoaDon.MaBan=? and HoaDon.status1=0";
					PreparedStatement P = conn.prepareStatement(s);
					P.setString(1, ba.getMaBan());
					result = P.executeQuery();
					if (result.next())// bàn này đã có người dùng
					{
						if (m == 1)// Tức là khi chọn món ăn thì chưa có món ăn
									// này
						{
							try {
								String sqe = "Insert into ChiTietHoaDon values(?,?,?,?)";
								PreparedStatement prepe = conn
										.prepareStatement(sqe);
								prepe.setString(1, result.getString(1));
								prepe.setString(2, sp.getMaSP());
								prepe.setInt(3,
										Integer.parseInt(txtSoLuong.getText()));
								prepe.setInt(
										4,
										sp.getDonGia()
												* Integer.parseInt(txtSoLuong
														.getText()));
								int i2 = prepe.executeUpdate();
								if (i2>0) {
									JOptionPane.showMessageDialog(null,"Gọi món thành công.");
								}
							} catch (Exception ex) {

								m--;// neu khong them duoc thi trong chi tiết
									// hóa đơn món ăn này đã tồn tại.

							}
						}
						if (m == 0)// Món ăn đã tồn tại nên chỉ việc + thêm số
									// lượng thay vì thêm mới.
						{
							try {
								m = 1;// tra m ve lai gia tri ban dau
								String a = "Update ChiTietHoaDon set Soluong=Soluong+? , ThanhTien=ThanhTien+? where MaSP=? and MaHD=?";
								PreparedStatement aa = conn.prepareStatement(a);
								aa.setInt(1,
										Integer.parseInt(txtSoLuong.getText()));
								aa.setInt(
										2,
										sp.getDonGia()
												* Integer.parseInt(txtSoLuong
														.getText()));
								aa.setString(3, sp.getMaSP());
								aa.setString(4, result.getString(1));

								int i = aa.executeUpdate();
								if (i>0) {
									JOptionPane.showMessageDialog(null,"Gọi món thành công.");
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

					} else// bàn này chưa có người dùng, tức là chưa có hóa đơn
					{
						try {

							String sq = "Insert into HoaDon values(?,?,NULL,?,?,NULL,NULL)";// Thêm
							// mới
							// hóa
							// đơn

							PreparedStatement prep3 = conn.prepareStatement(sq);
							prep3.setString(1, txtMaHD.getText());
							prep3.setString(2, ba.getMaBan());
							// prep3.setString(3, nv.getMaNV());
							prep3.setString(3, spf.format(t));
							prep3.setInt(4, 0);

							int i11 = prep3.executeUpdate();

							// maHD = txtMaHD.getText();// mục in hóa đơn

							String Ss = "Update Ban set Ban.Status2=1 where Ban.MaBan=?";// Cập
																							// nhật
																							// lại
																							// bàn
																							// đã
																							// có
																							// người
																							// dùng
							PreparedStatement p3 = conn.prepareStatement(Ss);
							p3.setString(1, ba.getMaBan());
							int ii = p3.executeUpdate();
							Status = 1;// Đã ttoonf tại hóa đơn
							temp = ba.getMaBan();// mục đich chủ yếu là khi bàn
													// đã có người dùng thì khi
													// kich vào lại button bàn
													// thì chuyển sang icon khac
							ba.setStatus(Status);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null,
									"Đã tồn tại mã hóa đơn này");
						}

						try {
							String sqe = "Insert into ChiTietHoaDon values(?,?,?,?)";
							PreparedStatement prepe = conn
									.prepareStatement(sqe);
							prepe.setString(1, txtMaHD.getText());
							prepe.setString(2, sp.getMaSP());
							prepe.setInt(3,
									Integer.parseInt(txtSoLuong.getText()));
							prepe.setInt(
									4,
									sp.getDonGia()
											* Integer.parseInt(txtSoLuong
													.getText()));
							int i2 = prepe.executeUpdate();
							if (i2>0) {
								JOptionPane.showMessageDialog(null,"Gọi món thành công.");
							}

						} catch (Exception ex) {

							ex.printStackTrace();
						}

					}
				} catch (Exception ex) {
					ex.printStackTrace();

				}
			}
		});
	}

	private static void LayMaHD() {
		// TODO Auto-generated method stub
		try {

			String sql = "Select HoaDon.MaHD from HoaDon";
			prepare = conn.prepareStatement(sql);
			ResultSet re = prepare.executeQuery();
			int ma = 0;
			while (re.next()) {
				ma = re.getInt(1);
			}
			// maHD=String.valueOf(ma);// mục in hóa đơn
			maHD=String.valueOf(ma+1);
			txtMaHD.setText(maHD);
		} catch (Exception e) {
			// TODO: handle exception
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
			// btn.setIcon(new ImageIcon("img/n.jpg"));

			JLabel lbl = new JLabel(ban.getTenBan());
			lbl.setAlignmentX(CENTER_ALIGNMENT);
			p.add(btn);
			pn.add(lbl);
			ba = ban;

			switch (ban.getStatus()) {
			case 0:// Bàn chưa có người dùng
				btn.setBackground(Color.CYAN);
				btn.setIcon(new ImageIcon("src/IMG/n.jpg"));
				break;
			default:// Bàn đã có người dùng
				btn.setIcon(new ImageIcon("src/IMG/u.jpg"));
				btn.setBackground(Color.GREEN);
				break;
			}
			pnLeftofBot.add(pn);

			btn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					LayMaHD();
					S = 0.0;// mục thanh toán

					//txtMaHD.setText("");// mục thanh toán
					try {

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

							txtMaHD.setText(maHD);// hiển thị mã HD theo bàn

						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}

					if (Status == 1 && ba.getMaBan() == temp)// Status==1 :đã có
																// hóa đơn,
																// ba.getMaBan()==temp:tức
																// là chuyển đôi
																// icon button
																// theo mã
					{
						btn.setIcon(new ImageIcon(getClass().getResource("/IMG/u.jpg")));
						btn.setBackground(Color.GREEN);
						Status = 0;
						temp = "";
					}
					if (Status1 == 0 && ba.getMaBan() == temp) {
						btn.setBackground(Color.CYAN);
						btn.setIcon(new ImageIcon(getClass().getResource("/IMG/n.jpg")));
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

	protected void HienThiSanPham(Vector<SanPham> dsSp) {
		// TODO Auto-generated method stub
		for (final SanPham sanPham : dsSp) {
			JPanel pn = new JPanel();
			pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
			JPanel p = new JPanel();
			pn.add(p);
			final JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(75, 75));
			btn.setIcon(new ImageIcon(sanPham.getImage()));
			JLabel lbl = new JLabel(sanPham.getTenSP());
			lbl.setAlignmentX(CENTER_ALIGNMENT);
			p.add(btn);
			pn.add(lbl);
			sp = sanPham;
			pnCenterOfBot.add(pn);
			btn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					sp = sanPham;
				}
			});

		}
	}

	private void HienThiDanhMuc() {
		// TODO Auto-generated method stub
		DanhMucService dmSv = new DanhMucService();
		dsDM = dmSv.DanhSachDanhMuc();
		for (DanhMuc d : dsDM) {

			cboDanhMuc.addItem(d);
		}
		SanPhamService spSv = new SanPhamService();
		dsSp = spSv.DanhSachHangHoa(1);
		HienThiSanPham(dsSp);
	}

}
