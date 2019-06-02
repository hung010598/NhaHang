package quanlynhahang.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ChucVuService extends SQLService{
	Statement statement;
	ResultSet result;
	public Vector<ChucVu> DanhSachChucVu()
	{
		Vector<ChucVu>vec = new Vector<ChucVu>();
		try{
			String sql="Select * from ChucVu";
			 statement = conn.createStatement();
			 result=statement.executeQuery(sql);
			while(result.next())
			{
				ChucVu cv= new ChucVu();
				cv.setMaCV(result.getString(1));
				cv.setTenCV(result.getString(2));
				cv.setLuong(result.getDouble(3));
				vec.add(cv);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vec;
	}
	public int LuuChucVu(ChucVu cv)
	{
		
		 try {
			 String sql ="Insert into ChucVu values(?,?,?)";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setString(1, cv.getMaCV());
			prepare.setString(2, cv.getTenCV());
			prepare.setDouble(3, cv.getLuong());
			return prepare.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return -1;
		
	}
	public int XoaChucVu(ChucVu cv)
	{
		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa chức vụ này","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		 try {
			String sql="delete from ChucVu where MaCV=?";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setString(1,cv.getMaCV() );
			return prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 return -1;
	}
	public int SuaChucVu(ChucVu cv)
	{

		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn Lưu lại thay đổi","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		 try {
			 String sql="Update ChucVu set TenCV=?,Luong=? where MaCV=?";
			PreparedStatement prepare =conn.prepareStatement(sql);
			prepare.setString(1,cv.getTenCV() );
			prepare.setDouble(2,cv.getLuong() );
			prepare.setString(3,cv.getMaCV() );
			return prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 return -1;
	}
}
