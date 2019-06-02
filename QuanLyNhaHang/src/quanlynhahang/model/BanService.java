package quanlynhahang.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

public class BanService extends SQLService {
	PreparedStatement prepare=null;
	public  int LuuBan(Ban b)
	{
		try{
			String sql="Insert into Ban values(?,?,?,?,?)";
			 prepare = conn.prepareStatement(sql);
			prepare.setString(1, b.getMaBan());
			prepare.setString(2, b.getTenBan());
			prepare.setString(3, b.getSoCho());
			prepare.setInt(4, b.getMaDMB());
			prepare.setInt(5,0);
			return prepare.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return -1;
	}
	public int XoaBan(String ma)
	{
		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa bàn này","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		try{
			String sql="Delete from Ban where MaBan=?";
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
	public int UpdateB(Ban b)
	{
		int ret=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn cập nhật lại bàn này","Thông báo",JOptionPane.YES_NO_OPTION);
		if(ret ==JOptionPane.YES_OPTION)
		{
		try{
			String sql="update Ban set TenBan=?,SoCho=?,MaDMB=?,Status2=? where MaBan=?";
			prepare =conn.prepareStatement(sql);
			
			prepare.setString(1, b.getTenBan());
			prepare.setString(2, b.getSoCho());
			prepare.setInt(3, b.getMaDMB());
			prepare.setInt(4, 0);
			prepare.setString(5,b.getMaBan());
			return prepare.executeUpdate();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}
		return -1;
	}
	public Vector<Ban> DanhSachBan(int maDm)
	{
		Vector<Ban>dsBan = new Vector<Ban>();
		try{
			String sql=" select * from Ban where MaDMB=?";
			PreparedStatement prepare = conn.prepareStatement(sql);
			prepare.setInt(1,maDm );
			ResultSet result=prepare.executeQuery();
			while(result.next())
			{
				Ban b = new Ban();
				b.setMaBan(result.getString(1));
				b.setTenBan(result.getString(2));
				b.setSoCho(result.getString(3));
				b.setMaDMB(result.getInt(4));
				b.setStatus(result.getInt(5));
				dsBan.add(b);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsBan;
	}
}
