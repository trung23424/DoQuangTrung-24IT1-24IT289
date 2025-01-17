package quanlyvayvoncuasinhvientrongnganhang;

public class Information {
	private int maVay;
	private double soTien;
	private String kyHan;
	private double laiSuat;
	private String thoiGian;
	private String nhuCau;
	private String  id;
	
	public Information() {}
	
	public Information(String id,int maVay, double soTien, String kyHan, double laiXuat, String thoiGian, String nhuCau) {
		super();
		this.maVay = maVay;
		this.soTien = soTien;
		this.kyHan = kyHan;
		this.laiSuat = laiXuat;
		this.thoiGian = thoiGian;
		this.nhuCau = nhuCau;
		this.id = id;
	}
	public int getMaVay() {
		return maVay;
	}
	public void setMaVay(int maVay) {
		this.maVay = maVay;
	}
	public double getSoTien() {
		return soTien;
	}
	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}
	public String getKyHan() {
		return kyHan;
	}
	public void setKyHan(String kyHan) {
		this.kyHan = kyHan;
	}
	public double getLaiSuat() {
		return laiSuat;
	}
	public void setLaiXuat(double laiXuat) {
		this.laiSuat = laiXuat;
	}
	public String getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}
	public String getNhuCau() {
		return nhuCau;
	}
	public void setNhuCau(String nhuCau) {
		this.nhuCau = nhuCau;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
