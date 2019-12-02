package syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataSlider implements Parcelable {

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


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.date);
		dest.writeString(this.image);
		dest.writeString(this.categoryId);
		dest.writeString(this.postCategory);
		dest.writeString(this.id);
		dest.writeString(this.title);
		dest.writeString(this.content);
	}

	public DataSlider() {
	}

	protected DataSlider(Parcel in) {
		this.date = in.readString();
		this.image = in.readString();
		this.categoryId = in.readString();
		this.postCategory = in.readString();
		this.id = in.readString();
		this.title = in.readString();
		this.content = in.readString();
	}

	public static final Parcelable.Creator<DataSlider> CREATOR = new Parcelable.Creator<DataSlider>() {
		@Override
		public DataSlider createFromParcel(Parcel source) {
			return new DataSlider(source);
		}

		@Override
		public DataSlider[] newArray(int size) {
			return new DataSlider[size];
		}
	};
}