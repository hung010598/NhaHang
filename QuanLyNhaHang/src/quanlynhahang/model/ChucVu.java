package quanlynhahang.model;

import java.util.Vector;

public class ChucVu {
	String maCV;
	String tenCV;
	double luong=0.0;
	Vector<NhanVien> nhanVien;
	public Vector<NhanVien> getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(Vector<NhanVien> nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getMaCV() {
		return maCV;
	}
	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}
	public String getTenCV() {
		return tenCV;
	}
	public void setTenCV(String tenCV) {
		this.tenCV = tenCV;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tenCV;
	}
}
