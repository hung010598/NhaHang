package quanlynhahang.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class KhachHangService extends SQLService{
	PreparedStatement prepare;

	SimpleDateFormat spf = new SimpleDateFormat("dd/mm/yyyy");

	public Vector<KhachHang> HienThiDanhSachKhachHang() {

		Vector<KhachHang> vec = new Vector<KhachHang>();
		try {
			String sql = "Select * from KhachHang";
			prepare = conn.prepareStatement(sql);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				KhachHang kh = new KhachHang();
				kh.setMaKH(result.getString(1));
				kh.setTenKH(result.getString(2));
				kh.setGioiTinh(result.getString(3));
				kh.setDiaChi(result.getString(4));
				kh.setSDT(result.getString(5));
				kh.setNgayDK(result.getString(6));
				vec.add(kh);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vec;
	}

	public int ThemKhachHang(KhachHang kh) {
		try {
			String sql = "Insert into KhachHang values(?,?,?,?,?,?)";
			prepare = conn.prepareStatement(sql);
			prepare.setString(1, kh.MaKH);
			prepare.setString(2, kh.TenKH);
			prepare.setString(3, kh.GioiTinh);
			prepare.setString(4, kh.DiaChi);
			prepare.setString(5, kh.SDT);
			prepare.setString(6, kh.getNgayDK());
			return prepare.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
