package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import quanlynhahang.model.Ban;
import quanlynhahang.model.BanService;
import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucBan;
import quanlynhahang.model.DanhMucBanService;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.SQLService;
import quanlynhahang.model.SanPham;
import quanlynhahang.model.SanPhamService;

public class DatTiec extends JPanel{
	JPanel  pnCenterOfBot,pnLeftofBottom,pnLeftofBot;
	JTextField txtSoLuong;
	static JTextField txtMaHDT;
	static JTextField txtTenKH,txtSoDT,txtDiaChi;
	JComboBox<DanhMuc> cboDanhMuc;
	JButton btnOrder;
	Vector<DanhMuc> dsDM;
	Vector<SanPham> dsSp;
	DanhMuc dm;
	SanPham sp;
	JComboBox<DanhMucBan> cboLoaiBan;
	Vector<DanhMucBan> dsDMB;
	DanhMucBan dmb;
	Vector<Ban> dsBan;
	Ban ba;
	JComboBox cboSize;
	String temp;
	int nvTemp, thanhtoantemp = 0;
	int Status = 0;
	int Status1 = 1;
	Calendar ca = Calendar.getInstance();
	Date t = ca.getTime();
	Double S = 0.0;
	static String maHDT = "";
	String SL="";
	JDateChooser chooser;
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat datefm=new SimpleDateFormat("dd/MM/yyy");
	String theDate="";
	static Connection connn;
	static PreparedStatement prepare0;
	public DatTiec()
	{
		super();
		SQLService co = new SQLService();
		connn = co.connect1();
		addControls();
		addEvents();
		LayMaHD();
	}
	
	public void addControls()
	{
		setLayout(new GridLayout(1, 1));
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 100));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,pnTop,pnBottom);
		sp.setOneTouchExpandable(true);
		add(sp,BorderLayout.CENTER);
		JPanel pnT = new JPanel();
		pnT.setPreferredSize(new Dimension(0, 40));
		pnTop.add(pnT,BorderLayout.NORTH);
		
		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setLayout(new FlowLayout());
		JLabel lblTieuDe = new JLabel();
		lblTieuDe.setText("Order");
		lblTieuDe.setBackground(Color.LIGHT_GRAY);
		lblTieuDe.setForeground(Color.BLUE);
		Font fontTieuDe = new Font("arial",Font.BOLD,23);
		lblTieuDe.setFont(fontTieuDe);
		pnTieuDe.add(lblTieuDe);
		pnTop.add(pnTieuDe,BorderLayout.NORTH);
		
		JPanel pnCenterOfBottom=new JPanel();
		pnCenterOfBottom.setLayout(new BorderLayout());
		pnBottom.add(pnCenterOfBottom,BorderLayout.CENTER);
		
		JPanel pnDM=new JPanel();
		pnDM.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDM=new JLabel("Danh mục:");
		cboDanhMuc=new JComboBox();
		pnDM.add(lblDM);
		pnDM.add(cboDanhMuc);
		pnCenterOfBottom.add(pnDM,BorderLayout.NORTH);
		
		pnCenterOfBot=new JPanel();
		pnCenterOfBot.setLayout(new GridLayout(6,5));
		JScrollPane crBottom=new JScrollPane(pnCenterOfBot, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfBottom.add(crBottom,BorderLayout.CENTER);
		
		JPanel pnBotOfBot=new JPanel();
		pnBotOfBot.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel lblSoLuong=new JLabel("Số lượng:");
		txtSoLuong=new JTextField(2);
		pnBotOfBot.add(lblSoLuong);
		pnBotOfBot.add(txtSoLuong);
		
		btnOrder=new JButton("Gọi món");
		pnBotOfBot.add(btnOrder);
		pnCenterOfBottom.add(pnBotOfBot,BorderLayout.SOUTH);
		
		pnLeftofBottom = new JPanel();
		pnLeftofBottom.setPreferredSize(new Dimension(280, 0));
		pnLeftofBottom.setLayout(new BorderLayout());
		pnLeftofBot = new JPanel();
		pnLeftofBot.setLayout(new BoxLayout(pnLeftofBot, BoxLayout.Y_AXIS));
		
		JPanel pnMaHD=new JPanel();
		pnMaHD.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaHD=new JLabel("Mã HĐ:");
		txtMaHDT=new JTextField(5);
		pnMaHD.add(lblMaHD);
		pnMaHD.add(txtMaHDT);
		pnLeftofBot.add(pnMaHD);
		
		JPanel pnTen=new JPanel();
		pnTen.setLayout(new FlowLayout());
		pnLeftofBot.add(pnTen);
		JLabel lblTen=new JLabel("Họ Tên:");
		txtTenKH=new JTextField(20);
		pnTen.add(lblTen);
		pnTen.add(txtTenKH);
		
		JPanel pnSoDT=new JPanel();
		pnTen.setLayout(new FlowLayout());
		pnLeftofBot.add(pnSoDT);
		JLabel lblSoDT=new JLabel("Số ĐT:");
		txtSoDT=new JTextField(20);
		pnSoDT.add(lblSoDT);
		pnSoDT.add(txtSoDT);
		
		JPanel pnDiaChi=new JPanel();
		pnTen.setLayout(new FlowLayout());
		pnLeftofBot.add(pnDiaChi);
		JLabel lblDiaChi=new JLabel("Địa chỉ:");
		txtDiaChi=new JTextField(20);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		
		JPanel pnNgayGiao=new JPanel();
		pnNgayGiao.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayGiao=new JLabel("Ngày giao:");
		chooser=new JDateChooser();
		pnNgayGiao.add(lblNgayGiao);
		pnNgayGiao.add(chooser);
		pnLeftofBot.add(pnNgayGiao);
		
		JPanel pnFake=new JPanel();
		pnFake.setPreferredSize(new Dimension(0, 300));
		pnLeftofBottom.add(pnFake,BorderLayout.NORTH);
		pnLeftofBottom.add(pnLeftofBot,BorderLayout.CENTER);
		pnBottom.add(pnLeftofBottom,BorderLayout.WEST);
		lblDiaChi.setPreferredSize(lblDiaChi.getPreferredSize());
		
	}
	public void addEvents()
	{
		HienThiDanhMuc();
		//HienThiSanPham(dsSp);
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

		btnOrder.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int m = 1;
				ResultSet result;
				thanhtoantemp = 0;// mục in hóa đơn(chưa thanh toán)
				try {
					String s = "Select * from HoaDonTiec where MaHD=? and HoaDonTiec.status1=0";
					PreparedStatement P = connn.prepareStatement(s);
					P.setString(1, txtMaHDT.getText());
					result = P.executeQuery();
					if (result.next())// bàn này đã có người dùng
					{
						if (m == 1)// Tức là khi chọn món ăn thì chưa có món ăn
									// này
						{
							try {
								String sqe = "Insert into ChiTietHoaDonTiec values(?,?,?,?)";
								PreparedStatement prepe = connn
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
								String a = "Update ChiTietHoaDonTiec set Soluong=Soluong+? , ThanhTien=ThanhTien+? where MaSP=? and MaHD=?";
								PreparedStatement aa = connn.prepareStatement(a);
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

							String sq = "Insert into HoaDonTiec values(?,?,?,?,NULL,?,?,?,NULL)";// Thêm
																					// mới
																					// hóa
																					// đơn

							PreparedStatement prep3 = connn.prepareStatement(sq);
							prep3.setString(1, txtMaHDT.getText());
							prep3.setString(2, txtTenKH.getText());
							prep3.setString(3, txtSoDT.getText());
							prep3.setString(4, txtDiaChi.getText());
							// prep3.setString(3, nv.getMaNV());
							prep3.setString(5, spf.format(t));
							theDate=datefm.format(chooser.getDate());
							prep3.setString(6, theDate);
							prep3.setInt(7, 0);

							int i11 = prep3.executeUpdate();

							//maHD = txtMaHD.getText();// mục in hóa đơn

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null,
									"Đã tồn tại mã hóa đơn này");
						}

						try {
							String sqe = "Insert into ChiTietHoaDonTiec values(?,?,?,?)";
							PreparedStatement prepe = connn.prepareStatement(sqe);
							prepe.setString(1, txtMaHDT.getText());
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
								JOptionPane.showMessageDialog(null, "Gọi món thành công");
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
			JTextField txtsl=new JTextField(5);
			p.add(btn);
			pn.add(lbl);
			sp = sanPham;
			pnCenterOfBot.add(pn);
			btn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					sp=sanPham;
				}
			});
		}
	}
	private void HienThiDanhMuc() {
		// TODO Auto-generated method stub
		DanhMucService dmSv=new DanhMucService();
		dsDM=dmSv.DanhSachDanhMuc();
		for (DanhMuc d : dsDM) {

			cboDanhMuc.addItem(d);
		}
		SanPhamService spSv = new SanPhamService();
		dsSp = spSv.DanhSachHangHoa(1);
		HienThiSanPham(dsSp);
	}
	public static void LayMaHD() {
		// TODO Auto-generated method stub
		txtTenKH.setText("");
		txtSoDT.setText("");
		txtDiaChi.setText("");
		try {

			String sql = "Select HoaDonTiec.MaHD from HoaDonTiec";
			prepare0 = connn.prepareStatement(sql);
			ResultSet re = prepare0.executeQuery();
			int ma = 0;
			while (re.next()) {
				ma = re.getInt(1);
			}
			// maHD=String.valueOf(ma);// mục in hóa đơn
			maHDT=String.valueOf(ma+1);
			txtMaHDT.setText(maHDT);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
