package syahputro.bimo.projek.dinas.p3a.network.response.profile_dinas;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("template_wa")
	private String templateWa;

	@SerializedName("logo")
	private String logo;

	@SerializedName("no_wa")
	private String noWa;

	@SerializedName("nama_dinas")
	private String namaDinas;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("alamat")
	private String alamat;

	public void setTemplateWa(String templateWa){
		this.templateWa = templateWa;
	}

	public String getTemplateWa(){
		return templateWa;
	}

	public void setLogo(String logo){
		this.logo = logo;
	}

	public String getLogo(){
		return logo;
	}

	public void setNoWa(String noWa){
		this.noWa = noWa;
	}

	public String getNoWa(){
		return noWa;
	}

	public void setNamaDinas(String namaDinas){
		this.namaDinas = namaDinas;
	}

	public String getNamaDinas(){
		return namaDinas;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}