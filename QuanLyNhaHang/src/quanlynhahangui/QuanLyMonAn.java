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
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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

import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.SanPham;
import quanlynhahang.model.SanPhamService;

public class QuanLyMonAn extends JPanel {
	JList<DanhMuc> listDM;
	JComboBox<SanPham> cbSanPham;
	DefaultTableModel dtm = null;
	JTable tbl = null;
	JTextField txtMa, txtTen, txtSo, txtDVT, txtDonGia, txtMa1, txtTen1,txtAnh;
	JButton btnThem, btnXoa, btnTaoMoi, btnTim, btnThem1, btnXoa1, btnSua1,
			btnUpdate, btnLog,btnAnh;
	Vector<SanPham> dsSP;
	Vector<DanhMuc> dsDM;
	DanhMuc dmSelected;
	SanPham spSelected;
	JLabel lblAnh;
	JFileChooser chooser=new JFileChooser();
	File selectedFile;
	public QuanLyMonAn() {
		super();
		addControls();
		addEvents();
		LayDanhSachDanhMuc();
	}

	private void LayDanhSachDanhMuc() {
		// TODO Auto-generated method stub
		DanhMucService dm = new DanhMucService();
		dsDM = dm.DanhSachDanhMuc();
		listDM.setListData(dsDM);
	}

	private void addEvents() {
		listDM.addMouseListener(new MouseListener() {

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
				LayDanhSachHangHoa();
				HienThiThongTinDM();
			}
		});
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
				HienThiHangHoa();

			}
		});

		btnThem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				XulyThemHangHoa();

			}
		});
		btnXoa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				XyLyXoa();

			}
		});
		btnTim.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				XuLyTim();

			}
		});
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CapNhatHangHoa();
			}
		});
		btnThem1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DanhMucService dmSv = new DanhMucService();
				DanhMuc dm = new DanhMuc();
				dm.setMaDM(Integer.parseInt(txtMa1.getText()));
				dm.setTenDM(txtTen1.getText());
				if (dmSv.LuuDanhMuc(dm) > 0) {
					JOptionPane.showMessageDialog(null, "Lưu thành công");
					LayDanhSachDanhMuc();

				} else {
					JOptionPane.showMessageDialog(null, "Lưu thất bại");
				}

			}
		});
		btnXoa1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DanhMucService dm = new DanhMucService();
				if (dm.XoaDanhMuc(dmSelected) > 0) {
					JOptionPane.showMessageDialog(null, "Xóa thành công");
					LayDanhSachDanhMuc();
				} else {
					JOptionPane.showMessageDialog(null, "Xóa thất bại");
				}
			}
		});
		btnSua1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DanhMucService dmSv = new DanhMucService();
				DanhMuc dm = new DanhMuc();
				dm.setMaDM(Integer.parseInt(txtMa1.getText()));
				dm.setTenDM(txtTen1.getText());
				if (dmSv.SuaDanhMuc(dm) > 0) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					LayDanhSachDanhMuc();
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
				}

			}
		});
		btnAnh.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LayAnh();
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
				lblAnh.setIcon(new ImageIcon(txtAnh.getText()));
				
			}
			catch(Exception ex)
			{
				
			}
		}
	}

	protected void CapNhatHangHoa() {
		SanPham hh = new SanPham();
		hh.setMaSP(txtMa.getText());
		hh.setTenSP(txtTen.getText());
		hh.setDVT(txtDVT.getText());
		hh.setDonGia(Integer.parseInt(txtDonGia.getText()));
		hh.setImage(txtAnh.getText());
		SanPhamService hhSv = new SanPhamService();
		if (hhSv.UpdateHH(hh) > 0) {
			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
			LayDanhSachHangHoa();
		} else {
			JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
		}

	}

	protected void HienThiThongTinDM() {
		txtTen1.setText(dmSelected.getTenDM());
		txtMa1.setText(dmSelected.getMaDM() + "");

	}

	protected void XuLyTim() {

		SanPhamService hh = new SanPhamService();
		dsSP = hh.TimHangHoa(txtTen.getText());
		dtm.setRowCount(0);
		for (SanPham sanPham : dsSP) {
			Vector<Object> vc = new Vector<Object>();
			vc.add(sanPham.getMaSP());
			vc.add(sanPham.getTenSP());
			vc.add(sanPham.getDVT());
			vc.add(sanPham.getDonGia());
			dtm.addRow(vc);
		}
		tbl.getCursor();
	}

	protected void XyLyXoa() {
		SanPhamService h = new SanPhamService();
		if (h.XoaHangHoa(txtMa.getText()) > 0) {
			JOptionPane.showMessageDialog(null, "Đã xóa");
			LayDanhSachHangHoa();

		} else {
			JOptionPane.showMessageDialog(null, "Xoa that bai");

		}

	}

	protected void HienThiHangHoa() {
		int row = tbl.getSelectedRow();
		if (row == -1)
			return;
		SanPham hh = dsSP.get(row);
		txtMa.setText(hh.getMaSP());
		txtTen.setText(hh.getTenSP());
		txtDVT.setText(hh.getDVT());
		txtDonGia.setText(hh.getDonGia() + "");
		txtAnh.setText(hh.getImage());
		lblAnh.setIcon(new ImageIcon(txtAnh.getText()));

	}

	protected void XulyThemHangHoa() {
		SanPham hh = new SanPham();
		hh.setMaDM(dmSelected.getMaDM());
		hh.setMaSP(txtMa.getText());
		hh.setTenSP(txtTen.getText());
		hh.setDVT(txtDVT.getText());
		hh.setDonGia(Integer.parseInt(txtDonGia.getText()));
		hh.setImage(txtAnh.getText());
		SanPhamService hhService = new SanPhamService();
		if (hhService.LuuHangHoa(hh) > 0) {
			JOptionPane.showMessageDialog(null, "Lưu  thành công");

			LayDanhSachHangHoa();

		} else {
			JOptionPane.showMessageDialog(null, "Lưu  thất bại");
		}
	}

	protected void LayDanhSachHangHoa() {
		if (listDM.getSelectedIndex() == -1)
			return;
		dmSelected = listDM.getSelectedValue();
		SanPhamService hh = new SanPhamService();
		dsSP = hh.DanhSachHangHoa(listDM.getSelectedValue().getMaDM());
		dtm.setRowCount(0);
		for (SanPham sanPham : dsSP) {
			Vector<Object> vc = new Vector<Object>();
			vc.add(sanPham.getMaSP());
			vc.add(sanPham.getTenSP());
			vc.add(sanPham.getDVT());
			vc.add(sanPham.getDonGia());
			dtm.addRow(vc);
		}

	}

	private void addControls() {

		
		setLayout(new GridLayout(1,1));
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 50));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop,
				pnBottom);
		sp.setOneTouchExpandable(true);
		add(sp, BorderLayout.CENTER);

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setLayout(new FlowLayout());
		JLabel lblTieuDe = new JLabel("Quản lý Món Ăn");
		lblTieuDe.setForeground(Color.BLUE);
		Font fontTieuDe = new Font("arial", Font.BOLD, 25);
		lblTieuDe.setFont(fontTieuDe);
		pnTieuDe.add(lblTieuDe);
		pnTop.add(pnTieuDe, BorderLayout.CENTER);

		JPanel pnLeftofBottom = new JPanel();
		pnLeftofBottom.setPreferredSize(new Dimension(240, 0));
		pnLeftofBottom.setLayout(new BorderLayout());
		JPanel pnRightofBottom = new JPanel();
		pnRightofBottom.setLayout(new BorderLayout());
		JSplitPane spBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				pnLeftofBottom, pnRightofBottom);
		pnBottom.add(spBottom, BorderLayout.CENTER);

		listDM = new JList<DanhMuc>();
		JScrollPane sc = new JScrollPane(listDM,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnLeftofBottom.add(sc, BorderLayout.CENTER);

		TitledBorder titleBorder1 = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED), "Danh mục sản phẩm",
				TitledBorder.CENTER, 0);
		titleBorder1.setTitleColor(Color.BLUE);
		pnLeftofBottom.setBorder(titleBorder1);

		JPanel pnBottomOfLeftOfBottom = new JPanel();
		pnBottomOfLeftOfBottom.setLayout(new BoxLayout(pnBottomOfLeftOfBottom,
				BoxLayout.Y_AXIS));
		pnLeftofBottom.add(pnBottomOfLeftOfBottom, BorderLayout.SOUTH);

		JPanel pnMa1 = new JPanel();
		pnMa1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnBottomOfLeftOfBottom.add(pnMa1);
		JLabel lblMa1 = new JLabel("Mã Danh Mục");
		txtMa1 = new JTextField(10);
		pnMa1.add(lblMa1);
		pnMa1.add(txtMa1);

		JPanel pnTen1 = new JPanel();
		pnTen1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnBottomOfLeftOfBottom.add(pnTen1);
		JLabel lblTen1 = new JLabel("Tên Danh Mục");
		txtTen1 = new JTextField(10);
		pnTen1.add(lblTen1);
		pnTen1.add(txtTen1);

		JPanel pnBtn1 = new JPanel();
		pnBtn1.setLayout(new FlowLayout());
		pnBottomOfLeftOfBottom.add(pnBtn1);
		String var = "";

		btnThem1 = new JButton();
		btnThem1.setText("Thêm");
		btnXoa1 = new JButton("Xóa");
		btnSua1 = new JButton("Sửa");
		pnBtn1.add(btnThem1);
		pnBtn1.add(btnXoa1);
		pnBtn1.add(btnSua1);

		JPanel pnTopOfRightofBottom = new JPanel();
		pnTopOfRightofBottom.setLayout(new BorderLayout());
		pnTopOfRightofBottom.setPreferredSize(new Dimension(0, 200));
		pnRightofBottom.add(pnTopOfRightofBottom, BorderLayout.CENTER);

		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Sản Phẩm");
		dtm.addColumn("Tên Sản Phẩm");
		dtm.addColumn("ĐVT");
		dtm.addColumn("Đơn Giá");
		tbl = new JTable(dtm);
		JScrollPane sc1 = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfRightofBottom.add(sc1, BorderLayout.CENTER);

		JPanel pnBottomOfRightofBottom = new JPanel();
		pnBottomOfRightofBottom.setLayout(new BoxLayout(
				pnBottomOfRightofBottom, BoxLayout.Y_AXIS));
		pnBottomOfRightofBottom.setPreferredSize(new Dimension(0, 250));
		pnRightofBottom.add(pnBottomOfRightofBottom, BorderLayout.SOUTH);

		JPanel pnThongTin=new JPanel();
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));
		pnBottomOfRightofBottom.add(pnThongTin);
		JPanel pnLeft=new JPanel();
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
		pnThongTin.add(pnLeft);
		
		JPanel pnAnhm=new JPanel();
		pnAnhm.setLayout(new FlowLayout());
		lblAnh=new JLabel();
		pnAnhm.add(lblAnh);
		pnThongTin.add(pnAnhm);
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeft.add(pnMa);
		JLabel lblMa = new JLabel("Mã Sản Phẩm");
		txtMa = new JTextField(25);
		pnMa.add(lblMa);
		pnMa.add(txtMa);

		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeft.add(pnTen);
		JLabel lblTen = new JLabel("Tên Sản Phẩm");
		txtTen = new JTextField(25);
		pnTen.add(lblTen);
		pnTen.add(txtTen);

		JPanel pnDVT = new JPanel();
		pnDVT.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeft.add(pnDVT);
		JLabel lblDVT = new JLabel("ĐVT");
		txtDVT = new JTextField(25);
		pnDVT.add(lblDVT);
		pnDVT.add(txtDVT);

		JPanel pnDonGia = new JPanel();
		pnDonGia.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeft.add(pnDonGia);
		JLabel lblDonGia = new JLabel("Đơn Giá");
		txtDonGia = new JTextField(25);
		pnDonGia.add(lblDonGia);
		pnDonGia.add(txtDonGia);
		
		JPanel pnAnh=new JPanel();
		pnAnh.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeft.add(pnAnh);
		btnAnh=new JButton("Chọn ảnh:");
		txtAnh=new JTextField(20);
		txtAnh.setEditable(false);
		pnAnh.add(btnAnh);
		pnAnh.add(txtAnh);
		TitledBorder titleBorder = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED), "Thông tin chi tiết");
		titleBorder.setTitleColor(Color.BLUE);
		pnBottomOfRightofBottom.setBorder(titleBorder);

		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblDVT.setPreferredSize(lblTen.getPreferredSize());
		lblDonGia.setPreferredSize(lblTen.getPreferredSize());
		lblMa1.setPreferredSize(lblTen1.getPreferredSize());

		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout());
		pnBottomOfRightofBottom.add(pnBtn);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(getClass().getResource("/IMG/11.png")));
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(getClass().getResource("/IMG/xoa.png")));
		btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(getClass().getResource("/IMG/13.png")));
		btnUpdate = new JButton("Sửa");
		btnUpdate.setIcon(new ImageIcon(getClass().getResource("/IMG/sua.png")));
		btnThem1.setIcon(new ImageIcon(getClass().getResource("/IMG/11.png")));
		btnXoa1.setIcon(new ImageIcon(getClass().getResource("/IMG/xoa.png")));
		btnSua1.setIcon(new ImageIcon(getClass().getResource("/IMG/sua.png")));

		pnBtn.add(btnThem);
		pnBtn.add(btnXoa);
		pnBtn.add(btnUpdate);
		pnBtn.add(btnTim);

		btnXoa.setPreferredSize(btnThem.getPreferredSize());
		btnTim.setPreferredSize(btnThem.getPreferredSize());
		btnUpdate.setPreferredSize(btnThem.getPreferredSize());
	}

	
}
