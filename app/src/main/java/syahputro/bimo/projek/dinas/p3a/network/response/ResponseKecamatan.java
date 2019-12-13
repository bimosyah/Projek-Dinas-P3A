package syahputro.bimo.projek.dinas.p3a.network.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKecamatan{

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<DataItemKecamatan> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setData(List<DataItemKecamatan> data){
		this.data = data;
	}

	public List<DataItemKecamatan> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}