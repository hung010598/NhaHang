package quanlynhahang.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class NhanVienService extends SQLService {
	
	PreparedStatement prepare;
	
	SimpleDateFormat spf = new SimpleDateFormat("dd/mm/yyyy");
public Vector<NhanVien> HienThiDanhSachNhanVien(String maCV)
{
	
	Vector<NhanVien>vec = new Vector<NhanVien>();
	try{
		String sql="Select * from NhanVien where CongViec=?";
		 prepare = conn.prepareStatement(sql);
		 prepare.setString(1,maCV );
		ResultSet result = prepare.executeQuery();
		
		while(result.next())
		{
			NhanVien nv = new NhanVien();
			nv.setMaNV(result.getString(1));
			nv.setTenNV(result.getString(2));
			nv.setGioiTinh(result.getString(3));
			nv.setDiaChi(result.getString(4));
			nv.setSDT(result.getString(5));
			nv.setNgayVao(result.getString(6));
			nv.setCongviec(result.getString(7));
			nv.setImage(result.getString(8));
			vec.add(nv);
		}
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return vec;
}
public int ThemNhanVien(NhanVien nv)
{
	try{
		String sql="Insert into NhanVien values(?,?,?,?,?,?,?,?)";
		prepare = conn.prepareStatement(sql);
		prepare.setString(1, nv.MaNV);
		prepare.setString(2, nv.TenNV);
		prepare.setString(3, nv.GioiTinh);
		prepare.setString(4, nv.DiaChi);
		prepare.setString(5, nv.SDT);
		prepare.setString(6,  nv.getNgayVao());
		prepare.setString(7, nv.Congviec);
		prepare.setString(8,nv.Image );
		return prepare.executeUpdate();
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return -1;
}
}
