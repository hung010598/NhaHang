package quanlynhahang.model;

public class KhachHang {
	String MaKH;
	String TenKH;
	String SDT;
	String GioiTinh;
	String DiaChi;
	String NgayDK;
	public KhachHang() {
		super();
	}
	public KhachHang(String maKH, String tenKH, String sDT, String gioiTinh,
			String diaChi, String ngayDK) {
		super();
		MaKH = maKH;
		TenKH = tenKH;
		SDT = sDT;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
		NgayDK = ngayDK;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getTenKH() {
		return TenKH;
	}
	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getNgayDK() {
		return NgayDK;
	}
	public void setNgayDK(String ngayDK) {
		NgayDK = ngayDK;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTenKH();
	}
}	
