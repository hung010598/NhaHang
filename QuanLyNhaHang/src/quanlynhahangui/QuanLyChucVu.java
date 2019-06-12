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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import quanlynhahang.model.ChucVu;
import quanlynhahang.model.ChucVuService;
import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.SQLService;

public class QuanLyChucVu extends JPanel{
	static DefaultTableModel dtm;
	
	JTable tbl;
	static Connection conn;
	JButton btnThem,btnXoa,btnSua;
	JLabel lblMa,lblTen,lblLuong;
	JTextField txtMa,txtTen,txtLuong;
	static ChucVuService cvSv;
	static Vector<ChucVu> dsCV;
	ChucVu cv;
	public QuanLyChucVu() {
		super();
		SQLService co = new SQLService();
		conn = co.connect1();
		addControls();
		addEvent();
	}

	private void addEvent() {
		HienThi();
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
				// TODO Auto-generated method stub
				int row = tbl.getSelectedRow();
				if (row == -1)
					return;
				cv = dsCV.get(row);
				txtMa.setText(cv.getMaCV());
				txtTen.setText(cv.getTenCV());
				
				txtLuong.setText(String.valueOf(cv.getLuong()));
			
			}
		});
		btnThem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChucVuService dmSv = new ChucVuService();
				ChucVu dm = new ChucVu();
				dm.setMaCV(txtMa.getText());
				dm.setTenCV(txtTen.getText());
				dm.setLuong(Integer.parseInt(txtLuong.getText()));
				if (dmSv.LuuChucVu(dm) > 0) {
					JOptionPane.showMessageDialog(null, "Lưu thành công");
					HienThi();

				} else {
					JOptionPane.showMessageDialog(null, "Lưu thất bại");
				}

			}
		});
		btnXoa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ChucVuService dm = new ChucVuService();
				if (dm.XoaChucVu(cv) > 0) {
					JOptionPane.showMessageDialog(null, "Xóa thành công");
					HienThi();
				} else {
					JOptionPane.showMessageDialog(null, "Xóa thất bại");
				}
			}
		});
		btnSua.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ChucVuService dmSv = new ChucVuService();
				ChucVu dm = new ChucVu();
				dm.setMaCV(txtMa.getText());
				dm.setTenCV(txtTen.getText());
				dm.setLuong(Integer.parseInt(txtLuong.getText()));
				if (dmSv.SuaChucVu(dm) > 0) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					HienThi();
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
				}

			}
		});
	}


	public static void HienThi() {
		// TODO Auto-generated method stub
		cvSv=new ChucVuService();
		dsCV=cvSv.DanhSachChucVu();
		try {
			int i = 1;
			String sql = "Select ChucVu.MaCV,TenCV,Luong from ChucVu ";

			PreparedStatement prepare = conn.prepareStatement(sql);

			ResultSet rs = prepare.executeQuery();
			dtm.setRowCount(0);
			while (rs.next()) {
				Vector<Object> vec = new Vector<Object>();
				vec.add(i);
				vec.add(rs.getString(1));
				vec.add(rs.getString(2));
				vec.add(rs.getString(3));
				dtm.addRow(vec);
				i++;
			}
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
		pnBottom.setPreferredSize(new Dimension(0, 200));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop,
				pnBottom);
		sp.setOneTouchExpandable(true);
		add(sp, BorderLayout.CENTER);

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setLayout(new FlowLayout());
		JLabel lblTieuDe=new JLabel("Quản Lý Chức Vụ");
		Font font = new Font("arial", Font.BOLD, 25);
		lblTieuDe.setFont(font);
		lblTieuDe.setForeground(Color.BLUE);
		pnTieuDe.add(lblTieuDe);
		pnTop.add(pnTieuDe);
		
		dtm = new DefaultTableModel();
		dtm.addColumn("STT");
		dtm.addColumn("Mã Chức vụ");
		dtm.addColumn("Tên chức vụ");
		dtm.addColumn("Tiền lương");
		tbl = new JTable(dtm);
		JScrollPane sc = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottom.add(sc, BorderLayout.CENTER);

		JPanel pnRightOfBottom=new JPanel();
		pnRightOfBottom.setLayout(new BorderLayout());
		pnRightOfBottom.setPreferredSize(new Dimension(300, 0));
		
		JPanel pnRong=new JPanel();
		pnRong.setPreferredSize(new Dimension(0, 300));
		pnRightOfBottom.add(pnRong,BorderLayout.NORTH);
		JPanel pnThongTin=new JPanel();
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
		
		JPanel pnMa=new JPanel();
		pnMa.setLayout(new FlowLayout());
		lblMa=new JLabel("Mã chức vụ:");
		txtMa=new JTextField(10);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnThongTin.add(pnMa);
		
		JPanel pnTen=new JPanel();
		pnTen.setLayout(new FlowLayout());
		lblTen=new JLabel("Tên chức vụ:");
		txtTen=new JTextField(10);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnThongTin.add(pnTen);
		
		JPanel pnLuong=new JPanel();
		pnLuong.setLayout(new FlowLayout());
		lblLuong=new JLabel("Tiền lương:");
		txtLuong=new JTextField(10);
		pnLuong.add(lblLuong);
		pnLuong.add(txtLuong);
		pnThongTin.add(pnLuong);
		
		pnRightOfBottom.add(pnThongTin,BorderLayout.CENTER);
		
		JPanel pnBut=new JPanel();
		pnBut.setLayout(new FlowLayout());
		btnThem=new JButton("Thêm");
		btnSua=new JButton("Sửa");
		btnXoa=new JButton("Xóa");
		pnBut.add(btnThem);
		pnBut.add(btnSua);
		pnBut.add(btnXoa);
		pnRightOfBottom.add(pnBut,BorderLayout.SOUTH);
		
		pnBottom.add(pnRightOfBottom,BorderLayout.WEST);
		
	}
}
