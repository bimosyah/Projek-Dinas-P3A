package syahputro.bimo.projek.dinas.p3a.network.response.riwayat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRiwayat {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("total")
    private Integer total;
    @SerializedName("data")
    private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
