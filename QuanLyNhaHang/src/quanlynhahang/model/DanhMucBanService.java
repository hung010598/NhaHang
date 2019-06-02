package quanlynhahang.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DanhMucBanService extends SQLService{
	Statement statement;
	ResultSet result;
	public Vector<DanhMucBan> DanhSachDanhMuc()
	{
		Vector<DanhMucBan>vec = new Vector<DanhMucBan>();
		try{
			String sql="Select * from DanhMucBan";
			 statement = conn.createStatement();
			 result=statement.executeQuery(sql);
			while(result.next())
			{
				DanhMucBan dm= new DanhMucBan();
				dm.setMaDMB(result.getInt(1));
				dm.setTenDMB(result.getString(2));
				vec.add(dm);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vec;
	}
	public int LuuDanhMuc(DanhMucBan dm)
	{
		
		 try {
			 String sql ="Insert into DanhMucBan values(?,?)";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setInt(1, dm.getMaDMB());
			prepare.setString(2, dm.getTenDMB());
			return prepare.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return -1;
		
	}
	public int XoaDanhMuc(DanhMucBan dm)
	{
		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa Danh mục này","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		 try {
			 String sql="delete from DanhMuc where MaDM=?";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setInt(1,dm.getMaDMB() );
			return prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 return -1;
	}
	public int SuaDanhMuc(DanhMucBan dm)
	{

		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn Lưu lại thay đổi","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		 try {
			 String sql="Update DanhMuc set TenDM=? where MaDM=?";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setString(1,dm.getTenDMB() );
			prepare.setInt(2,dm.getMaDMB() );
			return prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 return -1;
	}
}
