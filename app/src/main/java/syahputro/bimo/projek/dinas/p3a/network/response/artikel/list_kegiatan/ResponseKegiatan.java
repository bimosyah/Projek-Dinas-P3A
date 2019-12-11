package syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_kegiatan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import syahputro.bimo.projek.dinas.p3a.model.ArticleItemDetail;

public class ResponseKegiatan {

	@SerializedName("total")
	private int total;

	@SerializedName("articles")
	private List<ArticleItemDetail> articles;

	@SerializedName("status")
	private int status;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setArticles(List<ArticleItemDetail> articles){
		this.articles = articles;
	}

	public List<ArticleItemDetail> getArticles(){
		return articles;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int isStatus(){
		return status;
	}
}