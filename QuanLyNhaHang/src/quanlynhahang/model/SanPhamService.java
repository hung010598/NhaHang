package quanlynhahang.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;


public class SanPhamService extends SQLService {
	PreparedStatement prepare=null;
	public  int LuuHangHoa(SanPham hh)
	{
		try{
			String sql="Insert into SanPham values(?,?,?,?,?,?)";
			 prepare = conn.prepareStatement(sql);
			prepare.setString(1, hh.getMaSP());
			prepare.setString(2, hh.getTenSP());
			prepare.setString(3, hh.getDVT());
			prepare.setInt(4, hh.getDonGia());
			prepare.setInt(5,hh.getMaDM());
			prepare.setString(6, hh.getImage());
			return prepare.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return -1;
	}
	public int XoaHangHoa(String ma)
	{
		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa mặt hàng này","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		try{
			String sql="Delete from SanPham where MaSP=?";
			prepare =conn.prepareStatement(sql);
			prepare.setString(1, ma);
			return prepare.executeUpdate();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
		return -1;
	}
	public int UpdateHH(SanPham hh)
	{
		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn cập nhật lại mặt hàng này","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		try{
			String sql="update SanPham set TenSP=?,DVT=?,DonGia=?, ImageSP=? where MaSP=?";
			prepare =conn.prepareStatement(sql);
			prepare.setString(1, hh.getTenSP());
			prepare.setString(2, hh.getDVT());
			prepare.setInt(3, hh.getDonGia());
			prepare.setString(4,hh.getImage());
			prepare.setString(5,hh.getMaSP());
			return prepare.executeUpdate();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
		return -1;
	}
	public Vector<SanPham> TimHangHoa(String Ten)
	{
		Vector<SanPham>dsHangHoa = new Vector<SanPham>();
		ResultSet result;
		try{
			
			String sql ="select * from SanPham where TenSP=?";
			prepare =conn.prepareStatement(sql);
			prepare.setString(1, Ten);
			result=prepare.executeQuery();
			while(result.next())
			{
				SanPham hh = new SanPham();
				hh.setMaSP(result.getString(1));
				hh.setTenSP(result.getString(2));
				hh.setDVT(result.getString(3));
				hh.setDonGia(result.getInt(4));
				hh.setImage(result.getString(6));
				dsHangHoa.add(hh);
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsHangHoa;
	}
	public Vector<SanPham> DanhSachHangHoa(int maDm)
	{
		Vector<SanPham>dsSanPham = new Vector<SanPham>();
		try{
			String sql=" select * from SanPham where MaDM=?";
			PreparedStatement prepare = conn.prepareStatement(sql);
			prepare.setInt(1,maDm );
			ResultSet result=prepare.executeQuery();
			while(result.next())
			{
				SanPham hh= new SanPham();
				hh.setMaSP(result.getString(1));
				hh.setTenSP(result.getString(2));
				hh.setDVT(result.getString(3));
				hh.setDonGia(Integer.parseInt(result.getString(4)));
				hh.setMaDM(result.getInt(5));
				hh.setImage(result.getString(6));
				dsSanPham.add(hh);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsSanPham;
	}
}
