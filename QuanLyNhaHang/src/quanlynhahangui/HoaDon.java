package quanlynhahangui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;





import quanlynhahang.model.Ban;
import quanlynhahang.model.SQLService;


public class HoaDon extends JFrame {
	JLabel lbl8, lblls, lblr, llblT, llblT2, llblT4, lblNV;

	DefaultTableModel dtm;
	JTable tbl;
	Connection conn;
	JButton btn;
	NhanVien nv;
	Ban ban;
	String maBan,NgayH,maH,tenBan;
	SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public HoaDon(String title,Ban ba,String NgayH,String MaHd)
	{
		super(title);
		this.ban =ba;
		
		this.NgayH=NgayH;
		this.maH=MaHd;
		SQLService co = new SQLService();
		conn =co.connect1();
		addControls();
		addEvents();
	}

	private void addEvents() {
		HienThiThongTin();
	}

	private void HienThiThongTin() {
		// TODO Auto-generated method stub
		try{
			Double S=0.0;
			Double T=0.0;
			Double ST=0.0;
			int i=1;
			String Sql="select SanPham.TenSP, Soluong,DonGia, DonGia*Soluong,NhanVien.TenNV from SanPham,HoaDon,NhanVien,ChiTietHoaDon,Ban where SanPham.MaSP=ChiTietHoaDon.MaSP and HoaDon.MaHD=ChiTietHoaDon.MaHD and Ban.MaBan=HoaDon.MaBan and HoaDon.MaNV=NhanVien.MaNV and Ban.MaBan=? and HoaDon.NgayHD=? and HoaDon.MaHD=?";
			PreparedStatement prepare = conn.prepareStatement(Sql);
			prepare.setString(1,ban.getMaBan());
			prepare.setString(2,NgayH);
			prepare.setString(3, maH);
			lbl8.setText(maH);
			lblls.setText(NgayH);
			lblr.setText(ban.getTenBan());
			
			ResultSet result =prepare.executeQuery();
			dtm.setRowCount(0);
			while(result.next())
			{
				Vector<Object>vec = new Vector<Object>();
				vec.add(i);
				vec.add(result.getString(1));
				vec.add(result.getInt(2));
				vec.add(result.getInt(3));
			
				vec.add(result.getInt(4));
			
				lblNV.setText("thu ngân: "+result.getString(5));
				dtm.addRow(vec);
			
				S=S+result.getInt(4);
				i++;
				T=(S*10)/100;
				ST=S+T;
				
			}	
			llblT.setText(S.toString());
			llblT2.setText(T.toString());
			llblT4.setText(ST.toString());
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		JPanel pnBet = new JPanel();
		pnBet.setLayout(new BorderLayout());
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		con.add(pnTop, BorderLayout.NORTH);
		con.add(pnBet, BorderLayout.CENTER);
		con.add(pnBottom, BorderLayout.SOUTH);

		JPanel pnTop1 = new JPanel();
		pnTop1.setLayout(new BoxLayout(pnTop1, BoxLayout.Y_AXIS));
		JPanel pnTop2 = new JPanel();
		pnTop2.setPreferredSize(new Dimension(20, 0));
		JPanel pnTop3 = new JPanel();
		pnTop3.setLayout(new BorderLayout());
		pnTop.add(pnTop1, BorderLayout.CENTER);
		pnTop.add(pnTop2, BorderLayout.EAST);
		pnTop.add(pnTop3, BorderLayout.SOUTH);

		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JLabel lbl1 = new JLabel("NHÀ HÀNG UIT");
		lbl1.setFont(new Font("Arial", Font.BOLD, 13));
		lbl1.setForeground(Color.DARK_GRAY);
		pn1.add(lbl1);

		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pn2.setPreferredSize(new Dimension(0, 20));
		JLabel lbl2 = new JLabel("Trường đại học CNTT-ĐHQG Tp.HCM");
		lbl2.setForeground(Color.DARK_GRAY);
		pn2.add(lbl2);

		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel lbl3 = new JLabel("0968375943");
		lbl3.setForeground(Color.DARK_GRAY);
		pn3.add(lbl3);

		pnTop1.add(pn1);
		pnTop1.add(pn2);
		pnTop1.add(pn3);

		JPanel pn4 = new JPanel();
		pn4.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl4 = new JLabel("PHIẾU THANH TOÁN");
		lbl4.setFont(new Font("Arial", Font.BOLD, 18));
		pn4.add(lbl4);

		JPanel pn5 = new JPanel();
		pn4.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl5 = new JLabel("---------------------------------------------------------------------------------------------");
		pn5.add(lbl5);

		JPanel pn6 = new JPanel();
		pn6.setLayout(new BorderLayout());
		JPanel pn7 = new JPanel();
		pn7.setLayout(new BoxLayout(pn7, BoxLayout.Y_AXIS));
		JPanel pn8 = new JPanel();
		pn8.setLayout(new FlowLayout(FlowLayout.LEFT));
		lbl8 = new JLabel("");
		JLabel lbll = new JLabel("");
		lbll.setPreferredSize(new Dimension(20, 0));
		pn8.add(lbll);
		pn8.add(lbl8);
		pn7.add(pn8);

		JPanel pn9 = new JPanel();
		pn9.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbll1 = new JLabel("");
		lbll1.setPreferredSize(new Dimension(20, 0));
		lblNV = new JLabel("");
		pn9.add(lbll1);
		pn9.add(lblNV);
		pn7.add(pn9);

		pn6.add(pn7, BorderLayout.WEST);

		JPanel pn10 = new JPanel();
		pn10.setLayout(new FlowLayout(FlowLayout.CENTER));
		lblls = new JLabel("");
		pn10.add(lblls);
		pn6.add(pn10, BorderLayout.CENTER);

		JPanel pn11 = new JPanel();
		pn11.setLayout(new FlowLayout(FlowLayout.CENTER));
		lblr = new JLabel("");
		pn11.setPreferredSize(new Dimension(130, 0));
		pn11.add(lblr);
		pn6.add(pn11, BorderLayout.EAST);
		pnTop3.add(pn4, BorderLayout.NORTH);
		pnTop3.add(pn5, BorderLayout.CENTER);
		pnTop3.add(pn6, BorderLayout.SOUTH);

		JPanel pnrongtrai = new JPanel();
		pnrongtrai.setPreferredSize(new Dimension(20, 0));
		pnBet.add(pnrongtrai, BorderLayout.WEST);
		dtm = new DefaultTableModel();
		dtm.addColumn("STT");
		dtm.addColumn("Thực đơn");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Đơn giá");

		dtm.addColumn("Thành tiền");
		tbl = new JTable(dtm);
		JScrollPane sc11 = new JScrollPane(tbl,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnBet.add(sc11, BorderLayout.CENTER);

		JPanel pnphai = new JPanel();
		pnphai.setPreferredSize(new Dimension(20, 0));
		pnBet.add(pnphai, BorderLayout.EAST);

		JPanel pnBottom1 = new JPanel();
		pnBottom1.setLayout(new BorderLayout());
		pnBottom.add(pnBottom1, BorderLayout.NORTH);
		JPanel pnTong = new JPanel();
		pnTong.setLayout(new BoxLayout(pnTong, BoxLayout.Y_AXIS));
		JPanel pntong1 = new JPanel();
		pntong1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblT = new JLabel("Tổng cộng:");
		JLabel lblT1 = new JLabel("");
		lblT1.setPreferredSize(new Dimension(20, 0));
		pntong1.add(lblT1);
		pntong1.add(lblT);

		JPanel pntong2 = new JPanel();
		pntong2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblT2 = new JLabel("VAT(10%):");
		JLabel lblT3 = new JLabel("");
		lblT3.setPreferredSize(new Dimension(20, 0));
		pntong2.add(lblT3);
		pntong2.add(lblT2);

		JPanel pntong3 = new JPanel();
		pntong3.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblT4 = new JLabel("Tổng tiền thanh toán:");
		JLabel lblT5 = new JLabel("");
		lblT5.setPreferredSize(new Dimension(20, 0));
		pntong3.add(lblT5);
		pntong3.add(lblT4);
		pnTong.add(pntong1);
		pnTong.add(pntong2);
		pnTong.add(pntong3);
		pnBottom1.add(pnTong, BorderLayout.WEST);

		JPanel pnTTong = new JPanel();
		pnTTong.setLayout(new BoxLayout(pnTTong, BoxLayout.Y_AXIS));
		JPanel pnttong1 = new JPanel();
		pnttong1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		llblT = new JLabel("0");
		JLabel llblT1 = new JLabel("");
		llblT1.setPreferredSize(new Dimension(20, 0));
		pnttong1.add(llblT);
		pnttong1.add(llblT1);

		JPanel pnttong2 = new JPanel();
		pnttong2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		llblT2 = new JLabel("0");
		JLabel llblT3 = new JLabel("");
		llblT3.setPreferredSize(new Dimension(20, 0));
		pnttong2.add(llblT2);
		pnttong2.add(llblT3);

		JPanel pnttong3 = new JPanel();
		pnttong3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		llblT4 = new JLabel("0");
		JLabel llblT5 = new JLabel("");
		llblT5.setPreferredSize(new Dimension(20, 0));
		pnttong3.add(llblT4);
		pnttong3.add(llblT5);

		pnTTong.add(pnttong1);
		pnTTong.add(pnttong2);
		pnTTong.add(pnttong3);
		pnBottom1.add(pnTTong, BorderLayout.EAST);

		JPanel pnBottom2 = new JPanel();
		pnBottom2.setLayout(new BoxLayout(pnBottom2, BoxLayout.Y_AXIS));
		pnBottom.add(pnBottom2, BorderLayout.SOUTH);
		JPanel pncam = new JPanel();
		JLabel lblcam = new JLabel("Xin chân thành cảm ơn quý khách!");
		lblcam.setPreferredSize(new Dimension(200, 15));
		pncam.add(lblcam);
		pnBottom2.add(pncam);

		JPanel pncam1 = new JPanel();
		JLabel lblcam1 = new JLabel("---Hẹn gặp lại---");
		pncam1.add(lblcam1);
		pnBottom2.add(pncam1);

		JPanel pnBottom3 = new JPanel();
		pnBottom3.setPreferredSize(new Dimension(0, 30));
		pnBottom.add(pnBottom3, BorderLayout.CENTER);
	}
	public void showWindow() {
		this.setSize(450, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
}
