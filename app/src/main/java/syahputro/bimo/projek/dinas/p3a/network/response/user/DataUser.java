package syahputro.bimo.projek.dinas.p3a.network.response.user;

import com.google.gson.annotations.SerializedName;

public class DataUser {

	@SerializedName("nomor_telp")
	private String nomorTelp;

	@SerializedName("nama")
	private String nama;

	@SerializedName("tanggal_lahir")
	private String tanggalLahir;

	@SerializedName("alamat")
	private String alamat;

	public void setNomorTelp(String nomorTelp){
		this.nomorTelp = nomorTelp;
	}

	public String getNomorTelp(){
		return nomorTelp;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setTanggalLahir(String tanggalLahir){
		this.tanggalLahir = tanggalLahir;
	}

	public String getTanggalLahir(){
		return tanggalLahir;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}