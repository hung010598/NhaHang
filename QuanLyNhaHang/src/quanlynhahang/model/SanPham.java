package quanlynhahang.model;

public class SanPham {
	String maSP;
	String tenSP;
	String DVT;
	DanhMuc danhMuc;
	String image;
	int donGia;
	private int maDM;
	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhmuc(DanhMuc danhmuc) {
		this.danhMuc = danhmuc;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	public int getMaDM() {
		return maDM;
	}

	public void setMaDM(int maDM) {
		this.maDM = maDM;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getDVT() {
		return DVT;
	}

	public void setDVT(String dVT) {
		DVT = dVT;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String toString() {
		return this.tenSP;
	}
}
