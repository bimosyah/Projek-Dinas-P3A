package syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_artikel;

import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("date")
	private String date;

	@SerializedName("image")
	private String image;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("post_category")
	private String postCategory;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("content")
	private String content;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setPostCategory(String postCategory){
		this.postCategory = postCategory;
	}

	public String getPostCategory(){
		return postCategory;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}
}