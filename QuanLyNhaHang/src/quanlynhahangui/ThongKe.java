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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import quanlynhahang.model.SQLService;

public class ThongKe extends JPanel {
	static DefaultTableModel dtm;
	static DefaultTableModel dtm1;
	JTable tbl, tbl1;
	static Connection conn;
	JButton btnT, btnXoa, btnXoaAll;
	static String maHD = "";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
	static PreparedStatement prepare0;
	int ma = 0;

	public ThongKe() {
		super();
		SQLService co = new SQLService();
		conn = co.connect1();
		addControls();
		addEvent();
	}

	private void addEvent() {
		HienThiDoanhThu();
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

				maHD = tbl.getValueAt(row, 0).toString();
				HienThiChiTiet();
				

			}
		});
		btnXoaAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ret = JOptionPane.showConfirmDialog(null,
						"Bạn thật sự muốn xóa hết thống kê?", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION) {
					try {

						String sql = "delete from ChiTietHoaDon ";
						PreparedStatement prepare = conn.prepareStatement(sql);
						
						int i2=prepare.executeUpdate();
					}

					catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					
				}
				try {

					String sql1 = "delete from HoaDon ";
					prepare0 = conn.prepareStatement(sql1);
					int i = prepare0.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						HienThiDoanhThu();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ret = JOptionPane.showConfirmDialog(null,
						"Bạn thật sự muốn xóa hóa đơn này?", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION) {
					try {

						String sql = "delete from ChiTietHoaDon where MaHD=?";
						PreparedStatement prepare = conn.prepareStatement(sql);
						prepare.setString(1, maHD);
						int i2=prepare.executeUpdate();
					}

					catch (SQLException e3) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
					
				}
				try {

					String sql1 = "delete from HoaDon where HoaDon.MaHD=?";
					prepare0 = conn.prepareStatement(sql1);
					prepare0.setString(1, maHD);
					int i = prepare0.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						HienThiDoanhThu();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
	}


	protected static void HienThiChiTiet() {
		// TODO Auto-generated method stub

		try {

			dtm1.setRowCount(0);

			String Sql = "select SanPham.TenSP, Soluong,DonGia,DVT, DonGia*Soluong from SanPham,HoaDon,ChiTietHoaDon where SanPham.MaSP=ChiTietHoaDon.MaSP and HoaDon.MaHD=ChiTietHoaDon.MaHD and HoaDon.MaHD=?";
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
				// maHD = result.getString(8);

				dtm1.addRow(vec);
				// int i = tbl.getRowCount();
				// txtMaHD.setText(maHD);// hiển thị mã HD theo bàn

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void HienThiDoanhThu() {

		try {
			int i = 1;
			String sql = "Select HoaDon.MaHD,MaNV,MaKH,MaBan,NgayHD,ThanhTien from HoaDon where Status1=1";

			PreparedStatement prepare = conn.prepareStatement(sql);

			ResultSet rs = prepare.executeQuery();
			dtm.setRowCount(0);
			while (rs.next()) {
				Vector<Object> vec = new Vector<Object>();
				vec.add(rs.getString(1));
				vec.add(rs.getString(2));
				vec.add(rs.getString(3));
				vec.add(rs.getString(4));
				vec.add(rs.getString(5));
				vec.add(rs.getInt(6));
				dtm.addRow(vec);
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	HienThiChiTiet();
	}

	private void addControls() {

		setLayout(new GridLayout(1, 1));
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 200));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(0, 200));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop,
				pnBottom);
		sp.setOneTouchExpandable(true);
		add(sp, BorderLayout.CENTER);

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setLayout(new FlowLayout());
		dtm1 = new DefaultTableModel();
		dtm1.addColumn("Tên hàng hóa");
		dtm1.addColumn("Số lượng");
		dtm1.addColumn("Đơn giá");
		dtm1.addColumn("ĐVT");
		dtm1.addColumn("Thành tiền");
		tbl1 = new JTable(dtm1);
		JScrollPane sc11 = new JScrollPane(tbl1,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTieuDe.add(sc11);
		pnBottom.add(sc11, BorderLayout.CENTER);

		dtm = new DefaultTableModel();
		dtm.addColumn("Mã HĐ");
		dtm.addColumn("Mã Nhân Viên");
		dtm.addColumn("Mã Khách Hàng");
		dtm.addColumn("Bàn");
		dtm.addColumn("Thời gian");
		dtm.addColumn("Doanh thu");
		tbl = new JTable(dtm);
		JScrollPane sc = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(sc, BorderLayout.CENTER);

		JPanel pnXoa = new JPanel();
		pnXoa.setLayout(new FlowLayout());
		btnXoa = new JButton("Xóa");
		btnXoa.setPreferredSize(new Dimension(100, 30));
		btnXoaAll = new JButton("Xóa tất cả");
		btnXoaAll.setPreferredSize(new Dimension(100, 30));
		pnXoa.add(btnXoa);
		pnXoa.add(btnXoaAll);
		pnBottom.add(pnXoa, BorderLayout.SOUTH);
	}
}
