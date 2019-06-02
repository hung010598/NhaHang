package quanlynhahang.model;

import java.io.Serializable;
import java.util.Vector;

public class DanhMuc implements Serializable {
	int maDM;
	String tenDM;
	Vector<SanPham> SanPham;

	public int getMaDM() {
		return maDM;
	}

	public void setMaDM(int maDM) {
		this.maDM = maDM;
	}

	public String getTenDM() {
		return tenDM;
	}

	public void setTenDM(String tenDM) {
		this.tenDM = tenDM;
	}

	public String toString() {
		return this.tenDM;
	}

	public Vector<SanPham> getSanPham() {
		return SanPham;
	}

	public void setSanPham(Vector<SanPham> sanPham) {
		SanPham = sanPham;
	}
}
