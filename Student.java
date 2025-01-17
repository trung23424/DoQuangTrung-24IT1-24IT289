package quanlyvayvoncuasinhvientrongnganhang;

public class Student {
	private int id;
	private String ho_ten;
	private String sdt;
	private String gioi_tinh;
	private String ngay_sinh;
	private String cccd;
	private String dia_chi;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(int id, String ho_ten, String sdt, String gioi_tinh, String ngay_sinh, String cccd, String dia_chi) {
		super();
		this.id = id;
		this.ho_ten = ho_ten;
		this.sdt = sdt;
		this.gioi_tinh = gioi_tinh;
		this.ngay_sinh = ngay_sinh;
		this.cccd = cccd;
		this.dia_chi = dia_chi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHo_ten() {
		return ho_ten;
	}
	public void setHo_ten(String ho_ten) {
		this.ho_ten = ho_ten;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getGioi_tinh() {
		return gioi_tinh;
	}
	public void setGioi_tinh(String gioi_tinh) {
		this.gioi_tinh = gioi_tinh;
	}
	public String getNgay_sinh() {
		return ngay_sinh;
	}
	public void setNgay_sinh(String ngay_sinh) {
		this.ngay_sinh = ngay_sinh;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	public String getDia_chi() {
		return dia_chi;
	}
	public void setDia_chi(String dia_chi) {
		this.dia_chi = dia_chi;
	}
	
}
