package syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk;

import com.google.gson.annotations.SerializedName;

public class GrafikItemBentuk {

	@SerializedName("eksploitasi")
	private String eksploitasi;

	@SerializedName("psikologi")
	private String psikologi;

	@SerializedName("penelantaran")
	private String penelantaran;

	@SerializedName("lain")
	private String lain;

	@SerializedName("seksual")
	private String seksual;

	@SerializedName("fisik")
	private String fisik;

	public void setEksploitasi(String eksploitasi){
		this.eksploitasi = eksploitasi;
	}

	public String getEksploitasi(){
		return eksploitasi;
	}

	public void setPsikologi(String psikologi){
		this.psikologi = psikologi;
	}

	public String getPsikologi(){
		return psikologi;
	}

	public void setPenelantaran(String penelantaran){
		this.penelantaran = penelantaran;
	}

	public String getPenelantaran(){
		return penelantaran;
	}

	public void setLain(String lain){
		this.lain = lain;
	}

	public String getLain(){
		return lain;
	}

	public void setSeksual(String seksual){
		this.seksual = seksual;
	}

	public String getSeksual(){
		return seksual;
	}

	public void setFisik(String fisik){
		this.fisik = fisik;
	}

	public String getFisik(){
		return fisik;
	}
}