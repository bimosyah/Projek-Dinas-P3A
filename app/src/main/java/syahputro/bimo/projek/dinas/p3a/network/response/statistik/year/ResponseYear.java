package syahputro.bimo.projek.dinas.p3a.network.response.statistik.year;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseYear {

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<String> data;

	@SerializedName("status")
	private String status;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setData(List<String> data){
		this.data = data;
	}

	public List<String> getData(){
		return data;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

}