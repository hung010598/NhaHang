package quanlynhahang.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DanhMucService extends SQLService {
	Statement statement;
	ResultSet result;
	public Vector<DanhMuc> DanhSachDanhMuc()
	{
		Vector<DanhMuc>vec = new Vector<DanhMuc>();
		try{
			String sql="Select * from DanhMuc";
			 statement = conn.createStatement();
			 result=statement.executeQuery(sql);
			while(result.next())
			{
				DanhMuc dm= new DanhMuc();
				dm.setMaDM(result.getInt(1));
				dm.setTenDM(result.getString(2));
				vec.add(dm);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vec;
	}
	public int LuuDanhMuc(DanhMuc dm)
	{
		
		 try {
			 String sql ="Insert into DanhMuc values(?,?)";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setInt(1, dm.getMaDM());
			prepare.setString(2, dm.getTenDM());
			return prepare.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return -1;
		
	}
	public int XoaDanhMuc(DanhMuc dm)
	{
		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa Danh mục này","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		 try {
			String sql="delete from DanhMuc where MaDM=?";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setInt(1,dm.getMaDM() );
			return prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 return -1;
	}
	public int SuaDanhMuc(DanhMuc dm)
	{

		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn Lưu lại thay đổi","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		 try {
			 String sql="Update DanhMuc set TenDM=? where MaDM=?";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setString(1,dm.getTenDM() );
			prepare.setInt(2,dm.getMaDM() );
			return prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 return -1;
	}
}
