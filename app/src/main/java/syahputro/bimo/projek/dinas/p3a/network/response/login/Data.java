package syahputro.bimo.projek.dinas.p3a.network.response.login;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
