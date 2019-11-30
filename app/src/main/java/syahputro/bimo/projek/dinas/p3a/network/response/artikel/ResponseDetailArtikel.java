package syahputro.bimo.projek.dinas.p3a.network.response.artikel;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailArtikel{

	@SerializedName("total")
	private int total;

	@SerializedName("articles")
	private Articles articles;

	@SerializedName("status")
	private boolean status;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setArticles(Articles articles){
		this.articles = articles;
	}

	public Articles getArticles(){
		return articles;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}