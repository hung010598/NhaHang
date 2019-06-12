package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import quanlynhahang.model.Ban;
import quanlynhahang.model.BanService;
import quanlynhahang.model.DanhMuc;
import quanlynhahang.model.DanhMucBan;
import quanlynhahang.model.DanhMucBanService;
import quanlynhahang.model.DanhMucService;
import quanlynhahang.model.SanPhamService;

public class QuanLyBanAn extends JPanel {
	JButton btnThem,btnXoa,btnSua,btnRefresh;
	JLabel lblMaBan,lblTenBan,lblLoai,lblSoCho;
	JTextField txtMaBan,txtTenBan,txtSoCho;
	JComboBox<DanhMucBan> cboLoaiBan;
	Vector<DanhMucBan> dsDM;
	DanhMucBan dm;
	Vector<Ban>dsBan;
	Ban ba;
	JPanel pnBottom;
	public QuanLyBanAn()
	{
		super();
		addControls();
		addEvents();
		LayDanhSachDanhMuc();
	}
	private void LayDanhSachDanhMuc() {
		// TODO Auto-generated method stub
		DanhMucBanService dm=new DanhMucBanService();
		dsDM=dm.DanhSachDanhMuc();
		for (DanhMucBan d : dsDM) {

			cboLoaiBan.addItem(d);
		}
	}
	public void addControls()
	{
		
		setLayout(new GridLayout(1,1));
		JPanel pnTop = new JPanel();
		//pnTop.setPreferredSize(new Dimension(0, 100));
		pnTop.setLayout(new BorderLayout());

		pnBottom = new JPanel();
		pnBottom.setLayout(new GridLayout(6, 2));
		JScrollPane crBottom=new JScrollPane(pnBottom, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTop,
				crBottom);

		sp.setOneTouchExpandable(true);
		add(sp);

		JPanel pnLeftOfGrid = new JPanel();
		JPanel pnX=new JPanel();
		pnX.setPreferredSize(new Dimension(0, 300));;
		pnTop.add(pnX,BorderLayout.NORTH);
		pnLeftOfGrid.setLayout(new BoxLayout(pnLeftOfGrid, BoxLayout.Y_AXIS));
		JPanel pnLoai=new JPanel();
		pnLoai.setLayout(new FlowLayout(FlowLayout.LEFT));
		lblLoai=new JLabel("Loại bàn");
		cboLoaiBan=new JComboBox();
		
		pnLoai.add(lblLoai);
		pnLoai.add(cboLoaiBan);
		pnLeftOfGrid.add(pnLoai);
		JPanel pnMa = new JPanel();
		pnTop.add(pnLeftOfGrid,BorderLayout.CENTER);
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnMa);
		lblMaBan = new JLabel("Mã bàn:");
		// lblMa.setForeground(Color.BLUE);
		txtMaBan = new JTextField(10);
		pnMa.add(lblMaBan);
		pnMa.add(txtMaBan);

		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnTen);
		lblTenBan = new JLabel("Tên bàn:");
		// lblTen.setForeground(Color.BLUE);
		txtTenBan = new JTextField(10);
		pnTen.add(lblTenBan);
		pnTen.add(txtTenBan);
		
		JPanel pnSoCho=new JPanel();
		pnSoCho.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnSoCho);
		lblSoCho=new JLabel("Số chỗ ngồi:");
		txtSoCho=new JTextField(10);
		pnSoCho.add(lblSoCho);
		pnSoCho.add(txtSoCho);
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnLeftOfGrid.add(pnButton);
		btnRefresh = new JButton("Refresh");

		btnThem = new JButton("Thêm");

		btnSua = new JButton("Sửa");
		
		btnXoa = new JButton("Xóa");

		pnButton.add(btnRefresh);
		pnButton.add(btnThem);
		pnButton.add(btnSua);
		pnButton.add(btnXoa);
		// btnTaoMoi.setForeground(Color.BLUE);
		// btnThem.setForeground(Color.BLUE);
		// btnSua.setForeground(Color.BLUE);
		// btnXoa.setForeground(Color.BLUE);
		btnThem.setPreferredSize(btnRefresh.getPreferredSize());
		btnSua.setPreferredSize(btnRefresh.getPreferredSize());
		btnXoa.setPreferredSize(btnRefresh.getPreferredSize());
		
		lblLoai.setPreferredSize(lblSoCho.getPreferredSize());
		lblMaBan.setPreferredSize(lblSoCho.getPreferredSize());
		lblTenBan.setPreferredSize(lblSoCho.getPreferredSize());
		
		/*JPanel pnRightOfGrid=new JPanel();
		pnRightOfGrid.setLayout(new BoxLayout(pnRightOfGrid, BoxLayout.Y_AXIS));
		pnTop.add(pnRightOfGrid);*/
		JPanel pnX2=new JPanel();
		pnX2.setPreferredSize(new Dimension(0, 100));
		pnTop.add(pnX2,BorderLayout.SOUTH);
		
	}
	public void addEvents()
	{
		cboLoaiBan.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboLoaiBan.getSelectedIndex() == -1)
					return;
				dm = (DanhMucBan) cboLoaiBan.getSelectedItem();
				BanService bSv = new BanService();
				dsBan = bSv.DanhSachBan(dm.getMaDMB());
				pnBottom.removeAll();
				HienThiThongTinBan(dsBan);
				pnBottom.repaint();
				pnBottom.revalidate();
			}
		});
		btnThem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BanService bSv=new BanService();
				Ban b=new Ban();
				b.setMaBan(txtMaBan.getText());
				b.setTenBan(txtTenBan.getText());
				b.setSoCho(txtSoCho.getText());
				b.setMaDMB(dm.getMaDMB());
				if (bSv.LuuBan(b) > 0) {
					JOptionPane.showMessageDialog(null, "Lưu thành công");
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Lưu thất bại");
				}
				pnBottom.removeAll();
				HienThiThongTinBan(dsBan);
				pnBottom.repaint();
				pnBottom.revalidate();
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BanService b = new BanService();
				if (b.XoaBan(txtMaBan.getText()) > 0) {
					JOptionPane.showMessageDialog(null, "Xóa thành công");
				} else {
					JOptionPane.showMessageDialog(null, "Xóa thất bại");
				}
			}
		});
		btnSua.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BanService bSv = new BanService();
				Ban b = new Ban();
				b.setMaDMB(dm.getMaDMB());
				b.setMaBan(txtMaBan.getText());
				b.setTenBan(txtTenBan.getText());
				b.setSoCho(txtSoCho.getText());
				if (bSv.UpdateB(b) > 0) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
				}

			}
		});
	}
	protected void HienThiThongTinBan(Vector<Ban> dsBan) {
		// TODO Auto-generated method stub
		for (final Ban ban : dsBan) {
			JPanel pn = new JPanel();
			pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
			JPanel p = new JPanel();
			pn.add(p);
			final JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(75, 75));
			btn.setIcon(new ImageIcon(getClass().getResource("/IMG/n.jpg")));
			btn.setBackground(Color.CYAN);
			JLabel lbl = new JLabel(ban.getTenBan());
			lbl.setAlignmentX(CENTER_ALIGNMENT);
			p.add(btn);
			pn.add(lbl);
			ba = ban;
			pnBottom.add(pn);
			btn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					txtMaBan.setText(ban.getMaBan());
					txtTenBan.setText(ban.getTenBan());
					txtSoCho.setText(ban.getSoCho());
				}
			});
		}
	}
	
}
