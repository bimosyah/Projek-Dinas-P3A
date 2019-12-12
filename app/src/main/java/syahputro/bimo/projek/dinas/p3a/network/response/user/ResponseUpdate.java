package syahputro.bimo.projek.dinas.p3a.network.response.user;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdate{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}