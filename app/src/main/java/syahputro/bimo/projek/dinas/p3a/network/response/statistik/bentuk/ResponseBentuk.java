package syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBentuk{

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<DataItemBentuk> data;

	@SerializedName("grafik")
	private List<GrafikItemBentuk> grafik;

	@SerializedName("status")
	private boolean status;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setData(List<DataItemBentuk> data){
		this.data = data;
	}

	public List<DataItemBentuk> getData(){
		return data;
	}

	public void setGrafik(List<GrafikItemBentuk> grafik){
		this.grafik = grafik;
	}

	public List<GrafikItemBentuk> getGrafik(){
		return grafik;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}