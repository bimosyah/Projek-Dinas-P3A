package syahputro.bimo.projek.dinas.p3a.network.response.statistik.usia;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("tahun")
	private String tahun;

	@SerializedName("usia_1")
	private String usia1;

	@SerializedName("usia_2")
	private String usia2;

	@SerializedName("usia_3")
	private String usia3;

	@SerializedName("bulan")
	private String bulan;

	public void setTahun(String tahun){
		this.tahun = tahun;
	}

	public String getTahun(){
		return tahun;
	}

	public void setUsia1(String usia1){
		this.usia1 = usia1;
	}

	public String getUsia1(){
		return usia1;
	}

	public void setUsia2(String usia2){
		this.usia2 = usia2;
	}

	public String getUsia2(){
		return usia2;
	}

	public void setUsia3(String usia3){
		this.usia3 = usia3;
	}

	public String getUsia3(){
		return usia3;
	}

	public void setBulan(String bulan){
		this.bulan = bulan;
	}

	public String getBulan(){
		return bulan;
	}
}