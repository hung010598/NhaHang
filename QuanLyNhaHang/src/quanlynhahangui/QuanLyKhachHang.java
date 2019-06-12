package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import quanlynhahang.model.KhachHang;
import quanlynhahang.model.KhachHangService;
import quanlynhahang.model.NhanVien;
import quanlynhahang.model.NhanVienService;
import quanlynhahang.model.SQLService;

public class QuanLyKhachHang extends JPanel {
	Connection conn;
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
	DefaultTableModel dtm;
	JTable tbl;
	JTextField txtMa, txtTen, txtSDT, txtNgay, txtDiaChi, txtChucvu, txtTim,txtAnh;
	JRadioButton chbNam, chbNu;
	Vector<KhachHang> dsKH;
	KhachHang kh;
	JButton btnThem, btnSua, btnXoa, btnTaoMoi, btnTim;
	JButton btnAnh;
	JFileChooser chooser=new JFileChooser();
	File selectedFile;
	JLabel lblImg;
	SimpleDateFormat datefm=new SimpleDateFormat("dd/MM/yyy");
	String theDate="";
	JDateChooser chooser2;
	JComboBox cboChucVu;
	public QuanLyKhachHang() {

		super();
		SQLService co = new SQLService();
		conn = co.connect1();
		addControls();
		addEvents();

	}

	public void addControls() {
		
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
		JLabel lblMa = new JLabel("Mã Khách Hàng:");
		// lblMa.setForeground(Color.BLUE);
		txtMa = new JTextField(20);
		pnMa.add(lblMa);
		pnMa.add(txtMa);

		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnTen);
		JLabel lblTen = new JLabel("Tên Khách Hàng:");
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
		txtTim.setPreferredSize(new Dimension(150, 30));
		btnTim = new JButton("Tìm Theo Tên");
		btnTim.setIcon(new ImageIcon("img/13.png"));
		// btnTim.setForeground(Color.BLUE);
		pnTim.add(txtTim);
		pnTim.add(btnTim);
		JPanel pnRong = new JPanel();

		JLabel lbls = new JLabel(".");
		lbls.setPreferredSize(new Dimension(0, 80));
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
		JLabel lblNgay = new JLabel("Ngày Đăng Ký:");
		// lblNgay.setForeground(Color.BLUE);
		chooser2=new JDateChooser();
		pnNgay.add(lblNgay);
		pnNgay.add(chooser2);

		/*JPanel pnChuc = new JPanel();
		pnChuc.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnCenterOfGrid.add(pnChuc);
		JLabel lblChuc = new JLabel("Chức Vụ:");
		// lblChuc.setForeground(Color.BLUE);
		cboChucVu=new JComboBox();
		cboChucVu.addItem("Quản lý");
		cboChucVu.addItem("Bán hàng");
		cboChucVu.addItem("Bếp");
		txtChucvu = new JTextField(20);
		pnChuc.add(lblChuc);
		pnChuc.add(txtChucvu);*/

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
		//lblChuc.setPreferredSize(lblNgay.getPreferredSize());
		lblCheck.setPreferredSize(lblNgay.getPreferredSize());
		
		JPanel pnRightOfTop=new JPanel();
		pnRightOfTop.setLayout(new BorderLayout());
		/*JPanel pnImage=new JPanel();
		pnImage.setLayout(new FlowLayout());
		lblImg=new JLabel();
		
		pnImage.add(lblImg);
		pnRightOfTop.add(pnImage,BorderLayout.NORTH);*/
		
		
		
		/*JPanel pnAnh=new JPanel();	
		pnAnh.setLayout(new FlowLayout());
		btnAnh=new JButton("...");
		JLabel lblAnh=new JLabel("Ảnh");
		txtAnh=new JTextField(15);
		txtAnh.setEditable(false);
		pnAnh.add(lblAnh);
		pnAnh.add(txtAnh);
		pnAnh.add(btnAnh);
		
		pnRightOfTop.add(pnAnh,BorderLayout.CENTER);*/
		
		pnTop.add(pnRightOfTop);
		
		TitledBorder titleBorder = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED), "Thông tin chi tiết");
		titleBorder.setTitleColor(Color.BLUE);
		pnTop.setBorder(titleBorder);

		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Khách Hàng");
		dtm.addColumn("Tên Khách Hàng");
		dtm.addColumn("Giới Tính");
		dtm.addColumn("Địa Chỉ");
		dtm.addColumn("SĐT");
		dtm.addColumn("Ngày Đăng Ký");
		
		tbl = new JTable(dtm);
		JScrollPane sc1 = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottom.add(sc1, BorderLayout.CENTER);
		// tbl.setForeground(Color.BLUE);
		//
	}

	public void addEvents() {
		HienThiDanhSachKhachHang();
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
				kh = dsKH.get(row);
				txtMa.setText(kh.getMaKH());
				txtTen.setText(kh.getTenKH());
				if (kh.getGioiTinh().contains("Nam")) {
					chbNam.doClick();
				} else {
					chbNu.doClick();
				}
				txtDiaChi.setText(kh.getDiaChi());
				txtSDT.setText(kh.getSDT());
				String strDate=kh.getNgayDK();
				Date date = null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chooser2.setDate(date);
			}
		});
		btnTaoMoi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				txtMa.setText("");
				txtTen.setText("");

				txtDiaChi.setText("");
				txtSDT.setText("");
	
				HienThiDanhSachKhachHang();
			}
		});
		btnThem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					KhachHang kh = new KhachHang();
					kh.setMaKH(txtMa.getText());
					kh.setTenKH(txtTen.getText());
					if (chbNam.isSelected() == true) {
						kh.setGioiTinh(chbNam.getText());
					} else {
						kh.setGioiTinh(chbNu.getText());
					}
					kh.setDiaChi(txtDiaChi.getText());
					kh.setSDT(txtSDT.getText());
					theDate=datefm.format(chooser2.getDate());
					kh.setNgayDK(theDate);
					KhachHangService khSV = new KhachHangService();
					if (khSV.ThemKhachHang(kh) > 0) {
						JOptionPane.showMessageDialog(null, "Đã thêm mới");
						HienThiDanhSachKhachHang();
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

					String sql = "Delete from KhachHang where MaKH=?";

					PreparedStatement prepare = conn.prepareStatement(sql);

					prepare.setString(1, kh.getMaKH());
					int i = prepare.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						HienThiDanhSachKhachHang();
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
						String sql = "Update KhachHang set TenKH=?,GioiTinh=?,DiaChi=?,SDT=?,NgayDK=? where MaKH=?";
						PreparedStatement prepare = conn.prepareStatement(sql);
						prepare.setString(1, txtTen.getText());
						prepare.setString(2, gt);
						prepare.setString(3, txtDiaChi.getText());
						prepare.setString(4, txtSDT.getText());
						theDate=datefm.format(chooser2.getDate());
						prepare.setString(5, theDate);
				
						prepare.setString(6, kh.getMaKH());
						int i = prepare.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null,
									"Cập nhật thành công");
							HienThiDanhSachKhachHang();
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
					String sql = "select * from KhachHang where TenKH like ?";
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
						dtm.addRow(vc);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
	}
	private void HienThiDanhSachKhachHang() {
		// TODO Auto-generated method stub
		KhachHangService khSv = new KhachHangService();
		dtm.setRowCount(0);
		dsKH=khSv.HienThiDanhSachKhachHang();
		for (KhachHang khachHang : dsKH) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(khachHang.getMaKH());
			vec.add(khachHang.getTenKH());
			vec.add(khachHang.getGioiTinh());
			vec.add(khachHang.getDiaChi());
			vec.add(khachHang.getSDT());
			vec.add(khachHang.getNgayDK());
			dtm.addRow(vec);
		}
	}
}
