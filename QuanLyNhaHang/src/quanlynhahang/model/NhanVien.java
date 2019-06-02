package quanlynhahang.model;

public class NhanVien {
	String MaNV;
	String TenNV;
	String SDT;
	String GioiTinh;
	String DiaChi;
	String NgayVao;
	String Congviec;
	String Image;
	ChucVu chucVu;
	public NhanVien() {

	}

	public NhanVien(String Manv, String TenNV, String GT, String Dia,
			String SDT, String Ngay, String Cong,String Image) {
		this.MaNV = Manv;
		this.TenNV = TenNV;
		this.SDT = SDT;
		this.GioiTinh = GT;
		this.DiaChi = Dia;
		this.NgayVao = Ngay;
		this.Congviec = Cong;
		this.Image=Image;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public String getTenNV() {
		return TenNV;
	}

	public void setTenNV(String tenNV) {
		TenNV = tenNV;
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

	public String getNgayVao() {
		return NgayVao;
	}

	public void setNgayVao(String date) {
		NgayVao = date;
	}

	public String getCongviec() {
		return Congviec;
	}

	public void setCongviec(String congviec) {
		Congviec = congviec;
	}
	
	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String toString() {
		return this.getTenNV();
	}
}
