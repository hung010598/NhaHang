package quanlynhahang.model;

import java.util.Vector;

public class DanhMucBan {
	int maDMB;
	String  tenDMB;
	Vector<Ban> dsBan;
	public int getMaDMB() {
		return maDMB;
	}
	public void setMaDMB(int maDMB) {
		this.maDMB = maDMB;
	}
	public String getTenDMB() {
		return tenDMB;
	}
	public void setTenDMB(String tenDMB) {
		this.tenDMB = tenDMB;
	}
	public Vector<Ban> getDsBan() {
		return dsBan;
	}
	public void setDsBan(Vector<Ban> dsBan) {
		this.dsBan = dsBan;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tenDMB;
	}
}
