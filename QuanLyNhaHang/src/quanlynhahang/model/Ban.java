package quanlynhahang.model;

public class Ban {
	String maBan;
	String tenBan;
	String soCho;
	int status;
	DanhMucBan dmBan;
	int maDMB;
	public int getMaDMB() {
		return maDMB;
	}

	public void setMaDMB(int maDMB) {
		this.maDMB = maDMB;
	}

	public String getSoCho() {
		return soCho;
	}
	
	public void setSoCho(String soCho) {
		this.soCho = soCho;
	}

	public DanhMucBan getDmBan() {
		return dmBan;
	}

	public void setDmBan(DanhMucBan dmBan) {
		this.dmBan = dmBan;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMaBan() {
		return maBan;
	}

	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}

	public String getTenBan() {
		return tenBan;
	}

	public void setTenBan(String tenBan) {
		this.tenBan = tenBan;
	}

	public String toString() {
		return this.getTenBan();
	}
}
