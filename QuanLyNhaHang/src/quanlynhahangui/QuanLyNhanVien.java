package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import quanlynhahang.model.ChucVu;
import quanlynhahang.model.ChucVuService;
import quanlynhahang.model.DanhMucBan;
import quanlynhahang.model.DanhMucBanService;
import quanlynhahang.model.SQLService;
import quanlynhahang.model.NhanVien;
import quanlynhahang.model.Ban;
import quanlynhahang.model.BanService;
import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.NhanVienService;
import quanlynhahang.model.SQLService;
import quanlynhahang.model.SanPham;
import quanlynhahang.model.SanPhamService;

public class QuanLyNhanVien extends JPanel {
	Connection conn;
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
	DefaultTableModel dtm;
	JTable tbl;
	JTextField txtMa, txtTen, txtSDT, txtNgay, txtDiaChi, txtChucvu, txtTim,txtAnh;
	JRadioButton chbNam, chbNu;
	Vector<NhanVien> dsNV;
	NhanVien nv;
	JButton btnThem, btnSua, btnXoa, btnTaoMoi, btnTim;
	JButton btnAnh;
	JFileChooser chooser=new JFileChooser();
	File selectedFile;
	JLabel lblImg;
	static JComboBox cboChucVu;
	SimpleDateFormat datefm=new SimpleDateFormat("dd/MM/yyy");
	String theDate="";
	JDateChooser chooser2;
	static Vector<ChucVu> dsCV;
	ChucVu CV;
	NhanVienService nvSv;
	public QuanLyNhanVien() {

		super();
		SQLService co = new SQLService();
		conn = co.connect1();
		addControls();
		addEvents();
		LayDanhSachCV();
	}

	public static void LayDanhSachCV() {
		// TODO Auto-generated method stub
		ChucVuService cv=new ChucVuService();
		dsCV=cv.DanhSachChucVu();
		for (ChucVu c : dsCV) {

			cboChucVu.addItem(c);
		}
	}

	public void addControls() {
		//setPreferredSize(new Dimension(1200, 700));
		setLayout(new GridLayout(1,1));
		JPanel pnTop = new JPanel();
		//pnTop.setPreferredSize(new Dimension(0, 400));
		pnTop.setLayout(new GridLayout(1, 3));

		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop,
				pnBottom);

		sp.setOneTouchExpandable(true);
		add(sp);

		JPanel pnLeftOfGrid = new JPanel();

		pnLeftOfGrid.setLayout(new BoxLayout(pnLeftOfGrid, BoxLayout.Y_AXIS));
		JPanel pnMa = new JPanel();
		pnTop.add(pnLeftOfGrid);
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnMa);
		JLabel lblMa = new JLabel("Mã Nhân Viên:");
		// lblMa.setForeground(Color.BLUE);
		txtMa = new JTextField(20);
		pnMa.add(lblMa);
		pnMa.add(txtMa);

		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnTen);
		JLabel lblTen = new JLabel("Tên Nhân Viên:");
		// lblTen.setForeground(Color.BLUE);
		txtTen = new JTextField(20);
		pnTen.add(lblTen);
		pnTen.add(txtTen);

		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnSDT);
		JLabel lblSDT = new JLabel("Số Điện Thoại:");
		// lblSDT.setForeground(Color.BLUE);
		txtSDT = new JTextField(20);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);

		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnButton);
		btnTaoMoi = new JButton("Refresh");

		btnThem = new JButton("Thêm");

		btnSua = new JButton("Sửa");
		
		btnXoa = new JButton("Xóa");

		pnButton.add(btnTaoMoi);
		pnButton.add(btnThem);
		pnButton.add(btnSua);
		pnButton.add(btnXoa);
		// btnTaoMoi.setForeground(Color.BLUE);
		// btnThem.setForeground(Color.BLUE);
		// btnSua.setForeground(Color.BLUE);
		// btnXoa.setForeground(Color.BLUE);
		JPanel pnTim = new JPanel();
		pnTim.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnTim);
		txtTim = new JTextField(17);
		//txtTim.setPreferredSize(new Dimension(150, 30));
		btnTim = new JButton("Tìm Theo Tên");
		btnTim.setIcon(new ImageIcon("img/13.png"));
		// btnTim.setForeground(Color.BLUE);
		pnTim.add(txtTim);
		pnTim.add(btnTim);
		JPanel pnRong = new JPanel();

		JLabel lbls = new JLabel(".");
		//lbls.setPreferredSize(new Dimension(0, 80));
		lbls.setForeground(Color.WHITE);
		pnRong.add(lbls);
		pnLeftOfGrid.add(pnRong);
		btnThem.setPreferredSize(btnTaoMoi.getPreferredSize());
		btnSua.setPreferredSize(btnTaoMoi.getPreferredSize());
		btnXoa.setPreferredSize(btnTaoMoi.getPreferredSize());

		JPanel pnCenterOfGrid = new JPanel();
		pnCenterOfGrid.setLayout(new BoxLayout(pnCenterOfGrid, BoxLayout.Y_AXIS));
		pnTop.add(pnCenterOfGrid);
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnCenterOfGrid.add(pnDiaChi);
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		// lblDiaChi.setForeground(Color.BLUE);
		txtDiaChi = new JTextField(20);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);

		JPanel pnNgay = new JPanel();
		pnNgay.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnCenterOfGrid.add(pnNgay);
		JLabel lblNgay = new JLabel("Ngày Vào Làm:");
		// lblNgay.setForeground(Color.BLUE);
		chooser2=new JDateChooser();
		pnNgay.add(lblNgay);
		pnNgay.add(chooser2);

		JPanel pnChuc = new JPanel();
		pnChuc.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnCenterOfGrid.add(pnChuc);
		JLabel lblChuc = new JLabel("Chức Vụ:");
		// lblChuc.setForeground(Color.BLUE);
		cboChucVu=new JComboBox();
		
		txtChucvu=new JTextField(20);
		
		pnChuc.add(lblChuc);
		//pnChuc.add(txtChucvu);
		pnChuc.add(cboChucVu);
		
		JPanel pnCheck = new JPanel();
		pnCheck.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnCenterOfGrid.add(pnCheck);
		JLabel lblCheck = new JLabel("Giới tính:");
		// lblCheck.setForeground(Color.BLUE);
		chbNam = new JRadioButton("Nam");
		chbNu = new JRadioButton("Nữ");
		ButtonGroup group = new ButtonGroup();
		group.add(chbNam);
		group.add(chbNu);
		pnCheck.add(lblCheck);
		pnCheck.add(chbNam);
		pnCheck.add(chbNu);

		JPanel pnTrong = new JPanel();
		pnCenterOfGrid.add(pnTrong);

		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblSDT.setPreferredSize(lblTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblNgay.getPreferredSize());
		lblChuc.setPreferredSize(lblNgay.getPreferredSize());
		lblCheck.setPreferredSize(lblNgay.getPreferredSize());
		
		JPanel pnRightOfTop=new JPanel();
		pnRightOfTop.setLayout(new BorderLayout());
		JPanel pnImage=new JPanel();
		pnImage.setLayout(new FlowLayout());
		lblImg=new JLabel();
		//lblImg.setPreferredSize(new Dimension(80, 80));
		pnImage.add(lblImg);
		pnRightOfTop.add(pnImage,BorderLayout.NORTH);
		
		
		
		JPanel pnAnh=new JPanel();	
		pnAnh.setLayout(new FlowLayout());
		btnAnh=new JButton("...");
		JLabel lblAnh=new JLabel("Ảnh");
		txtAnh=new JTextField(15);
		txtAnh.setEditable(false);
		pnAnh.add(lblAnh);
		pnAnh.add(txtAnh);
		pnAnh.add(btnAnh);
		
		pnRightOfTop.add(pnAnh,BorderLayout.CENTER);
		
		pnTop.add(pnRightOfTop);
		
		TitledBorder titleBorder = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED), "Thông tin chi tiết");
		titleBorder.setTitleColor(Color.BLUE);
		pnTop.setBorder(titleBorder);

		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Nhân Viên");
		dtm.addColumn("Tên Nhân Viên");
		dtm.addColumn("Giới Tính");
		dtm.addColumn("Địa Chỉ");
		dtm.addColumn("SĐT");
		dtm.addColumn("Ngày Vào Làm");
		dtm.addColumn("Chức vụ");
		tbl = new JTable(dtm);
		JScrollPane sc1 = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottom.add(sc1, BorderLayout.CENTER);
		// tbl.setForeground(Color.BLUE);
		//
	}

	public void addEvents() {
		//HienThiDanhSachNhanVien(dsNV);
		tbl.addMouseListener(new MouseListener() {

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

				int row = tbl.getSelectedRow();
				if (row == -1)
					return;
				nv = dsNV.get(row);
				txtMa.setText(nv.getMaNV());
				txtTen.setText(nv.getTenNV());
				if (nv.getGioiTinh().contains("Nam")) {
					chbNam.doClick();
				} else {
					chbNu.doClick();
				}
				txtDiaChi.setText(nv.getDiaChi());
				txtSDT.setText(nv.getSDT());
				String strDate=nv.getNgayVao();
				java.util.Date date = null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chooser2.setDate(date);
				//txtChucvu.setText(nv.getCongviec());
				txtAnh.setText(nv.getImage());
				lblImg.setIcon(new ImageIcon(txtAnh.getText()));
			}
		});
		btnTaoMoi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				txtMa.setText("");
				txtTen.setText("");

				txtDiaChi.setText("");
				txtSDT.setText("");
				//txtNgay.setText("");
				//txtChucvu.setText("");
				HienThiDanhSachNhanVien(dsNV);
			}
		});
		btnThem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					NhanVien nv = new NhanVien();
					nv.setMaNV(txtMa.getText());
					nv.setTenNV(txtTen.getText());
					if (chbNam.isSelected() == true) {
						nv.setGioiTinh(chbNam.getText());
					} else {
						nv.setGioiTinh(chbNu.getText());
					}
					nv.setDiaChi(txtDiaChi.getText());
					nv.setSDT(txtSDT.getText());
					
					theDate=datefm.format(chooser2.getDate());
					nv.setNgayVao(theDate);
					nv.setCongviec(CV.getMaCV());
					nv.setImage(txtAnh.getText());
					NhanVienService nvSV = new NhanVienService();
					if (nvSV.ThemNhanVien(nv) > 0) {
						JOptionPane.showMessageDialog(null, "Đã thêm mới");
						dsNV = nvSv.HienThiDanhSachNhanVien(CV.getMaCV());
						HienThiDanhSachNhanVien(dsNV);
					} else {
						JOptionPane.showMessageDialog(null, "Thêm thất bại");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnXoa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {

					String sql = "Delete from NhanVien where MaNV=?";

					PreparedStatement prepare = conn.prepareStatement(sql);

					prepare.setString(1, nv.getMaNV());
					int i = prepare.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						dsNV = nvSv.HienThiDanhSachNhanVien(CV.getMaCV());
						HienThiDanhSachNhanVien(dsNV);
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnSua.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null,
						"Bạn muốn cập nhật lại thay đổi", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION) {
					try {
						String gt = "";
						if (chbNam.isSelected() == true) {
							gt = gt + chbNam.getText();
						} else {
							gt = gt + chbNu.getText();
						}
						String sql = "Update NhanVien set TenNV=?,GioiTinh=?,DiaChi=?,SDT=?,NgayVaoLam=?,CongViec=?,Image=? where MaNV=?";
						PreparedStatement prepare = conn.prepareStatement(sql);
						prepare.setString(1, txtTen.getText());
						prepare.setString(2, gt);
						prepare.setString(3, txtDiaChi.getText());
						prepare.setString(4, txtSDT.getText());
						theDate=datefm.format(chooser2.getDate());
						prepare.setString(5, theDate);
						prepare.setString(6, CV.getMaCV());
						prepare.setString(7, txtAnh.getText());
						prepare.setString(8, txtMa.getText());
						
						int i = prepare.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null,
									"Cập nhật thành công");
							dsNV = nvSv.HienThiDanhSachNhanVien(CV.getMaCV());
							HienThiDanhSachNhanVien(dsNV);
						} else {
							JOptionPane.showMessageDialog(null,
									"Cập nhật thất bại");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});
		btnTim.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Object> vc = new Vector<Object>();
					String sql = "select * from NhanVien where TenNV like ?";
					PreparedStatement prepare = conn.prepareStatement(sql);
					prepare.setString(1, txtTim.getText());
					ResultSet result = prepare.executeQuery();
					dtm.setRowCount(0);
					while (result.next()) {
						vc.add(result.getString(1));
						vc.add(result.getString(2));
						vc.add(result.getString(3));
						vc.add(result.getString(4));
						vc.add(result.getString(5));
						vc.add(result.getString(6));
						vc.add(result.getString(7));

						dtm.addRow(vc);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnAnh.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LayAnh();
			}
		});
		cboChucVu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//txtChucvu.setText((String) cboChucVu.getSelectedItem());
				if (cboChucVu.getSelectedIndex() == -1)
					return;
				CV = (ChucVu) cboChucVu.getSelectedItem();
				nvSv = new NhanVienService();
				dsNV = nvSv.HienThiDanhSachNhanVien(CV.getMaCV());
				/*pnBottom.removeAll();
				HienThiThongTinBan(dsBan);
				pnBottom.repaint();
				pnBottom.revalidate();*/
				HienThiDanhSachNhanVien(dsNV);
			}
		});
	}

	protected void LayAnh() {
		// TODO Auto-generated method stub
		if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			try
			{
				selectedFile=chooser.getSelectedFile();
				txtAnh.setText(selectedFile.getPath());
				lblImg.setIcon(new ImageIcon(txtAnh.getText()));
				
			}
			catch(Exception ex)
			{
				
			}
		}
	}

	private void HienThiDanhSachNhanVien(Vector<NhanVien> dsNV) {
		// TODO Auto-generated method stub
		//NhanVienService nvSv = new NhanVienService();
		dtm.setRowCount(0);
		for (NhanVien nhanVien : dsNV) {
			
			Vector<Object> vec = new Vector<Object>();
			vec.add(nhanVien.getMaNV());
			vec.add(nhanVien.getTenNV());
			vec.add(nhanVien.getGioiTinh());
			vec.add(nhanVien.getDiaChi());
			vec.add(nhanVien.getSDT());
			vec.add(nhanVien.getNgayVao());
			vec.add(nhanVien.getCongviec());
			dtm.addRow(vec);
		}
	}

	/*public void showWindow() {
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}*/
}
