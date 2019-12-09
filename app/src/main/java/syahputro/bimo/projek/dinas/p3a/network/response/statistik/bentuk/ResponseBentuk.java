package syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBentuk{

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("grafik")
	private List<GrafikItem> grafik;

	@SerializedName("status")
	private boolean status;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setGrafik(List<GrafikItem> grafik){
		this.grafik = grafik;
	}

	public List<GrafikItem> getGrafik(){
		return grafik;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}