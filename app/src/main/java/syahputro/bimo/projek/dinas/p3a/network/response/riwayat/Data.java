package syahputro.bimo.projek.dinas.p3a.network.response.riwayat;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id_pengaduan")
    private String idPengaduan;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("waktu_lapor")
    private String waktuLapor;
    @SerializedName("id_status")
    private String id_status;
    @SerializedName("status")
    private String status;

    public String getId_status() {
        return id_status;
    }

    public void setId_status(String id_status) {
        this.id_status = id_status;
    }

    public String getIdPengaduan() {
        return idPengaduan;
    }

    public void setIdPengaduan(String idPengaduan) {
        this.idPengaduan = idPengaduan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getWaktuLapor() {
        return waktuLapor;
    }

    public void setWaktuLapor(String waktuLapor) {
        this.waktuLapor = waktuLapor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
