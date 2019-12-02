package syahputro.bimo.projek.dinas.p3a.model;

import java.util.List;

public class ArticleItem {
    private String itemTitle;
    private List<ArticleItemDetail> detailList;

    public ArticleItem(String itemTitle, List<ArticleItemDetail> detailList) {
        this.itemTitle = itemTitle;
        this.detailList = detailList;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public List<ArticleItemDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ArticleItemDetail> detailList) {
        this.detailList = detailList;
    }
}
