package syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSlider{

	@SerializedName("total")
	private int total;

	@SerializedName("articles")
	private List<DataSlider> articles;

	@SerializedName("status")
	private int status;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setArticles(List<DataSlider> articles){
		this.articles = articles;
	}

	public List<DataSlider> getArticles(){
		return articles;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int isStatus(){
		return status;
	}
}