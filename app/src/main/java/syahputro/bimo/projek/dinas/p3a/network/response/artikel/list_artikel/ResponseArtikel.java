package syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_artikel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseArtikel {

    @SerializedName("total")
    private int total;

    @SerializedName("articles")
    private List<Data> articles;

    @SerializedName("status")
    private boolean status;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setArticles(List<Data> articles) {
        this.articles = articles;
    }

    public List<Data> getArticles() {
        return articles;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}