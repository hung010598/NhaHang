package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Color;
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

import quanlynhahang.model.Ban;
import quanlynhahang.model.BanService;
import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucBan;
import quanlynhahang.model.DanhMucBanService;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.NhanVien;
import quanlynhahang.model.NhanVienService;
import quanlynhahang.model.SQLService;
import quanlynhahang.model.SanPham;
import quanlynhahang.model.SanPhamService;

public class QuanLyTiec extends JPanel{
	JPanel pnLeftofBottom, pnRightofBottom, pnLeftofBot,pnRightOfBot,pnTienGiam;
	
	JButton btnThem, btnThanhToan, btnDat, btnIn, btnLog, btnThemB, btnXoaB,btnGiam;
	JTextField txtSoluong, txtKhach, txtTong, txtPhatSinh, txtTongT, txtNgay, txtMaHD,txtGiam;
	static Connection conn;
	Vector<Object> v = new Vector<Object>();
	JComboBox<NhanVien> cbNV;
	DefaultTableModel dtm;

	static DefaultTableModel dtm1;
	JTable tbl, tbl1;
	Vector<NhanVien> dsNV;
	String temp;
	int nvTemp, thanhtoantemp = 0;
	NhanVien nv;
	String maHD = "";
	int Status = 0;
	int Status1 = 1;
	int giam=0;
	Calendar ca = Calendar.getInstance();
	Date t = ca.getTime();
	Double S = 0.0;
	String st = "";
	String NgayHD = "";
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	JComboBox<DanhMucBan> cboLoaiBan;
	Vector<DanhMucBan> dsDMB;
	public QuanLyTiec() {
		super();
		SQLService co = new SQLService();
		conn = co.connect1();
		addControls();
		addEvents();
	}
	private void addEvents() {
		HienThiDanhSachTiec();
		HienThiDanhSachNV();
		cbNV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cbNV.getSelectedIndex() == -1)
					return;
				nv = (NhanVien) cbNV.getSelectedItem();

			}
		});
		tbl1.addMouseListener(new MouseListener() {
			
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
				int row=tbl1.getSelectedRow();
				if(row==-1)
					return;
				
				maHD=tbl1.getValueAt(row,0).toString();
				HienThiChiTiet();
				
			}
		});
		//HienThiDanhSachBan();
//		HienThiDanhSachNV();
//		cbNV.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				if (cbNV.getSelectedIndex() == -1)
//					return;
//				nv = (NhanVien) cbNV.getSelectedItem();
//
//			}
//		});
		btnThanhToan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null,
						"Bạn muốn thanh toán hóa đơn này '"
								+ "'", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION) {
					NgayHD = spf.format(t = ca.getTime());

					try {
						String sqll = "Update HoaDonTiec set HoaDonTiec.status1=1,ThanhTien=?,MaNV=? where HoaDonTiec.MaHD=? ";// status=1																							// toán
						PreparedStatement ppp = conn.prepareStatement(sqll);
						
						ppp.setFloat(1, Float.parseFloat(txtTong.getText().toString()));
						ppp.setString(2, nv.getMaNV());
						ppp.setString(3, maHD);
						int i = ppp.executeUpdate();
						if (i > 0) {

							JOptionPane.showMessageDialog(null,
									"Thanh toán thành công");
							
							
							HienThiDanhSachTiec();
							maHD="";
							HienThiChiTiet();
							Status1 = 0;
							
							thanhtoantemp = 1;// mục in hóa đơn(đã thanh toán)
							
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
		/*btnGiam.addActionListener(new ActionListener() {
			
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
					if(vc.size()>0)
					{
						JLabel lbl=new JLabel("Bạn được giảm 10%");
						pnTienGiam.removeAll();
						pnTienGiam.add(lbl);
						pnTienGiam.revalidate();
						Double T;
						T=S*9/10;
						txtTong.setText(T.toString());
						giam=1;
						
					}
					else
					{
						
						pnTienGiam.removeAll();
						pnTienGiam.revalidate();
						JOptionPane.showMessageDialog(null, "Mã khách hàng không đúng");
						txtTong.setText(S.toString());
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});*/
//		btnThem.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//				int m = 1;
//				ResultSet result;
//				thanhtoantemp = 0;// mục in hóa đơn(chưa thanh toán)
//				try {
//					String s = "Select * from HoaDon where HoaDon.MaBan=? and HoaDon.status1=0";
//					PreparedStatement P = conn.prepareStatement(s);
//					P.setString(1, ba.getMaBan());
//					result = P.executeQuery();
//					if (result.next())// bàn này đã có người dùng
//					{
//						if (m == 1)// Tức là khi chọn món ăn thì chưa có món ăn
//									// này
//						{
//							try {
//								String sqe = "Insert into ChiTietHoaDon values(?,?,?,?)";
//								PreparedStatement prepe = conn
//										.prepareStatement(sqe);
//								prepe.setString(1, result.getString(1));
//								prepe.setString(2, hhoa.getMaSP());
//								prepe.setInt(3,
//										Integer.parseInt(txtSoluong.getText()));
//								prepe.setInt(
//										4,
//										hhoa.getDonGia()
//												* Integer.parseInt(txtSoluong
//														.getText()));
//								int i2 = prepe.executeUpdate();
//								HienThiThongTin();
//							} catch (Exception ex) {
//
//								m--;// neu khong them duoc thi trong chi tiết
//									// hóa đơn món ăn này đã tồn tại.
//
//							}
//						}
//						if (m == 0)// Món ăn đã tồn tại nên chỉ việc + thêm số
//									// lượng thay vì thêm mới.
//						{
//							try {
//								m = 1;// tra m ve lai gia tri ban dau
//								String a = "Update ChiTietHoaDon set Soluong=Soluong+? , ThanhTien=ThanhTien+? where MaSP=? and MaHD=?";
//								PreparedStatement aa = conn.prepareStatement(a);
//								aa.setInt(1,
//										Integer.parseInt(txtSoluong.getText()));
//								aa.setInt(
//										2,
//										hhoa.getDonGia()
//												* Integer.parseInt(txtSoluong
//														.getText()));
//								aa.setString(3, hhoa.getMaSP());
//								aa.setString(4, result.getString(1));
//
//								int i = aa.executeUpdate();
//								HienThiThongTin();
//							} catch (Exception ex) {
//								ex.printStackTrace();
//							}
//						}
//
//					} else// bàn này chưa có người dùng, tức là chưa có hóa đơn
//					{
//						try {
//
//							String sq = "Insert into HoaDon values(?,?,NULL,?,?)";// Thêm
//																					// mới
//																					// hóa
//																					// đơn
//
//							PreparedStatement prep3 = conn.prepareStatement(sq);
//							prep3.setString(1, txtMaHD.getText());
//							prep3.setString(2, ba.getMaBan());
//							// prep3.setString(3, nv.getMaNV());
//							prep3.setString(3, spf.format(t));
//							prep3.setInt(4, 0);
//
//							int i11 = prep3.executeUpdate();
//
//							maHD = txtMaHD.getText();// mục in hóa đơn
//
//							String Ss = "Update Ban set Ban.Status2=1 where Ban.MaBan=?";// Cập
//																							// nhật
//																							// lại
//																							// bàn
//																							// đã
//																							// có
//																							// người
//																							// dùng
//							PreparedStatement p3 = conn.prepareStatement(Ss);
//							p3.setString(1, ba.getMaBan());
//							int ii = p3.executeUpdate();
//							Status = 1;// Đã ttoonf tại hóa đơn
//							temp = ba.getMaBan();// mục đich chủ yếu là khi bàn
//													// đã có người dùng thì khi
//													// kich vào lại button bàn
//													// thì chuyển sang icon khac
//						} catch (Exception ex) {
//							JOptionPane.showMessageDialog(null,
//									"Đã tồn tại mã hóa đơn này");
//						}
//
//						try {
//							String sqe = "Insert into ChiTietHoaDon values(?,?,?,?)";
//							PreparedStatement prepe = conn
//									.prepareStatement(sqe);
//							prepe.setString(1, txtMaHD.getText());
//							prepe.setString(2, hhoa.getMaSP());
//							prepe.setInt(3,
//									Integer.parseInt(txtSoluong.getText()));
//							prepe.setInt(
//									4,
//									hhoa.getDonGia()
//											* Integer.parseInt(txtSoluong
//													.getText()));
//							int i2 = prepe.executeUpdate();
//							HienThiThongTin();
//						} catch (Exception ex) {
//
//							ex.printStackTrace();
//						}
//
//					}
//				} catch (Exception ex) {
//					ex.printStackTrace();
//
//				}
//				/*Order ui=new Order("Order");
//				ui.showWindow();*/
//			}
//		});
//		btnIn.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					if (thanhtoantemp == 0) {
//						JOptionPane.showMessageDialog(null,
//								"Bạn chưa thanh toán");
//						return;
//					} else if (nv == null) {
//						JOptionPane.showMessageDialog(null,
//								"Vui lòng chọn nhân viên để thu ngân");
//						return;
//					} else {
//						if (nvTemp == 0 && maHD != "")// nvTemp=0 tức la chưa có
//														// nhân viên thanh toán
//														// hóa đơn này, maHD !""
//														// tức là đã tồn tại hóa
//														// đơn này
//						{
//							String sql = "update HoaDon set MaNV=? where MaHD=?";																					
//							PreparedStatement pr = conn.prepareStatement(sql);
//							pr.setString(1, nv.getMaNV());
//							pr.setString(2, maHD);
//							pr.executeUpdate();
//							nvTemp = 1;// Đã có nhân viên thanh toán hóa đơn này
//
//						}
//
//					}
//					HoaDon U = new HoaDon("Phiếu thanh toán", ba, NgayHD,
//							maHD);
//					U.showWindow();
//
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//
//			}
//		});
//		btnRefresh.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				//removeAll();
//				pnLeftofBot.removeAll();
//				//HienThiDanhSachBan();
//				pnLeftofBot.repaint();
//				pnLeftofBot.revalidate();
//			}
//		});
//		/*btnChiTiet.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				ChiTietHoaDon ui=new ChiTietHoaDon("Chi tiết hóa đơn");
//				ui.showWindow();
//			}
//		});*/
//		cboLoaiBan.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if (cboLoaiBan.getSelectedIndex() == -1)
//					return;
//				dmb = (DanhMucBan) cboLoaiBan.getSelectedItem();
//				BanService bSv = new BanService();
//				dsBan = bSv.DanhSachBan(dmb.getMaDMB());
//				pnLeftofBot.removeAll();
//				HienThiDanhSachBan(dsBan);
//				pnLeftofBot.repaint();
//				pnLeftofBot.revalidate();
//			}
//		});
		
	}

	private void HienThiDanhSachNV() {
		// TODO Auto-generated method stub
		NhanVienService nvSv = new NhanVienService();
		dsNV = nvSv.HienThiDanhSachNhanVien("CV1");
		for (NhanVien nv : dsNV) {

			cbNV.addItem(nv);
		}

	}
	public static void HienThiDanhSachTiec()
	{
		try {
			int i = 1;
			String sql = "Select HoaDonTiec.MaHD,TenKH,SoDT,DiaChi,NgayHD,NgayGiao from HoaDonTiec where HoaDonTiec.Status1=0";

			PreparedStatement prepare = conn.prepareStatement(sql);

			ResultSet rs = prepare.executeQuery();
			dtm1.setRowCount(0);
			while (rs.next()) {
				Vector<Object> vec = new Vector<Object>();
				
				vec.add(rs.getString(1));
				vec.add(rs.getString(2));
				vec.add(rs.getString(3));
				vec.add(rs.getString(4));
				vec.add(rs.getString(5));
				vec.add(rs.getString(6));
				dtm1.addRow(vec);
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	private void HienThiChiTiet()
	{
		S = 0.0;// mục thanh toán
		txtTong.setText("");// mục thanh toán
		//txtMaHD.setText("");// mục thanh toán
		try {

			dtm.setRowCount(0);

			String Sql = "select SanPham.TenSP, Soluong,DonGia,DVT, DonGia*Soluong from SanPham,HoaDonTiec,ChiTietHoaDonTiec where SanPham.MaSP=ChiTietHoaDonTiec.MaSP and HoaDonTiec.MaHD=ChiTietHoaDonTiec.MaHD and HoaDonTiec.MaHD=?";
			PreparedStatement prepare = conn.prepareStatement(Sql);
			prepare.setString(1, maHD);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				Vector<Object> vec = new Vector<Object>();
				vec.add(result.getString(1));
				vec.add(result.getInt(2));
				vec.add(result.getInt(3));
				vec.add(result.getString(4));
				vec.add(result.getInt(5));
				//maHD = result.getString(8);
				S = S + result.getInt(5);// Thành tiền= số lượng *
											// đơn giá
				dtm.addRow(vec);
				//int i = tbl.getRowCount();
				//txtMaHD.setText(maHD);// hiển thị mã HD theo bàn

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		txtTong.setText(S.toString());
		
	}
	
//	public void HienThiThongTin() {
//		S = 0.0;
//		txtTong.setText("");
//		try {
//
//			dtm.setRowCount(0);
//
//			String Sql = "select SanPham.TenSP, Soluong,DonGia,DVT, DonGia*Soluong,Ban.TenBan,Ban.MaBan,HoaDon.MaHD from SanPham,HoaDon,ChiTietHoaDon,Ban where SanPham.MaSP=ChiTietHoaDon.MaSP and HoaDon.MaHD=ChiTietHoaDon.MaHD and Ban.MaBan=HoaDon.MaBan and Ban.MaBan=?  and HoaDon.status1=0";
//			PreparedStatement prepare = conn.prepareStatement(Sql);
//			prepare.setString(1, ba.getMaBan());
//			ResultSet result = prepare.executeQuery();
//
//			while (result.next()) {
//				Vector<Object> vec = new Vector<Object>();
//				vec.add(result.getString(1));
//				vec.add(result.getInt(2));
//				vec.add(result.getInt(3));
//				vec.add(result.getString(4));
//				vec.add(result.getInt(5));
//
//				S = S + result.getInt(5);// Thành tiền= số lượng * đơn giá
//				dtm.addRow(vec);
//			}
//
//			txtTong.setText(S.toString());// Hiển thị tổng tiền của món ăn
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//	}
	private void addControls() {

		
		setLayout(new GridLayout(1,1));
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

		pnRightofBottom = new JPanel();
		pnRightofBottom.setLayout(new BorderLayout());
		pnBottom.add(pnRightofBottom, BorderLayout.CENTER);
		
		JPanel pnNorthOfBottom=new JPanel();
		pnNorthOfBottom.setLayout(new BoxLayout(pnNorthOfBottom, BoxLayout.Y_AXIS));
		pnNorthOfBottom.setPreferredSize(new Dimension(0, 300));
		TitledBorder title1 = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "Danh sách tiệc",
				TitledBorder.CENTER, 0);
		title1.setTitleColor(Color.BLUE);
		pnNorthOfBottom.setBorder(title1);
		dtm1=new DefaultTableModel();
		dtm1.addColumn("Mã hóa đơn");
		dtm1.addColumn("Tên khách hàng");
		dtm1.addColumn("Số điện thoại");
		dtm1.addColumn("Địa chỉ");
		dtm1.addColumn("Ngày đặt");
		dtm1.addColumn("Ngày giao hàng");
		tbl1=new JTable(dtm1);
		JScrollPane sc1 = new JScrollPane(tbl1,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnNorthOfBottom.add(sc1, BorderLayout.NORTH);
		
		JPanel pnCenterOfBottom=new JPanel();
		pnCenterOfBottom.setLayout(new BoxLayout(pnCenterOfBottom, BoxLayout.Y_AXIS));
		
		TitledBorder title = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "Chi tiết",
				TitledBorder.LEFT, 0);
		title.setTitleColor(Color.BLUE);
		pnCenterOfBottom.setBorder(title);
		dtm = new DefaultTableModel();
		dtm.addColumn("Tên hàng hóa");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Đơn giá");
		dtm.addColumn("ĐVT");
		dtm.addColumn("Thành tiền");
		tbl = new JTable(dtm);
		JScrollPane sc11 = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfBottom.add(sc11, BorderLayout.CENTER);
		
		pnRightofBottom.add(pnNorthOfBottom,BorderLayout.NORTH);
		pnRightofBottom.add(pnCenterOfBottom,BorderLayout.CENTER);
		JPanel pnBottomOfRightOfBottom = new JPanel();
		pnBottomOfRightOfBottom.setLayout(new BorderLayout());
		pnBottomOfRightOfBottom.setPreferredSize(new Dimension(0, 45));
	
		TitledBorder title11 = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "Danh Mục Bàn",
				TitledBorder.LEFT, 0);
		title11.setTitleColor(Color.BLUE);
		
		
		
		pnRightOfBot=new JPanel();
		pnRightOfBot.setLayout(new BorderLayout());
		pnRightOfBot.setPreferredSize(new Dimension(400, 0));
		JPanel pnTFake=new JPanel();
		pnTFake.setPreferredSize(new Dimension(0, 300));
		pnRightOfBot.add(pnTFake,BorderLayout.NORTH);
		
		Font font = new Font("arial", Font.BOLD, 15);
		
		JPanel pnChiTiet=new JPanel();
		pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
		
		JPanel pnNhan = new JPanel();
		pnNhan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNhan = new JLabel("Nhân viên:");
		lblNhan.setForeground(Color.RED);
		cbNV = new JComboBox<NhanVien>();
		cbNV.setPreferredSize(new Dimension(175, 22));
		pnNhan.add(lblNhan);
		pnNhan.add(cbNV); 
		pnChiTiet.add(pnNhan);
		
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
		
		pnChiTiet.add(pnTongTien);
		
		
		pnRightOfBot.add(pnChiTiet,BorderLayout.CENTER);
		JPanel pnThanhToan = new JPanel();
		pnThanhToan.setLayout(new FlowLayout());
		
		pnBottomOfRightOfBottom.add(pnThanhToan, BorderLayout.EAST);
		
		btnIn = new JButton();
		btnIn.setIcon(new ImageIcon("img/412.png"));
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setForeground(Color.BLUE);
		
		pnThanhToan.add(btnThanhToan);
		pnThanhToan.add(btnIn);
		
		btnThanhToan.setPreferredSize(new Dimension(100, 35));
		btnIn.setPreferredSize(btnThanhToan.getPreferredSize());
		pnChiTiet.add(pnThanhToan,BorderLayout.SOUTH);
		pnBottom.add(pnRightOfBot,BorderLayout.EAST);
		
		lblNhan.setPreferredSize(lblTong.getPreferredSize());
		lblNhan.setFont(font);
	}
	
}

