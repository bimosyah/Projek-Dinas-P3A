package syahputro.bimo.projek.dinas.p3a.network.response.kategori;

import com.google.gson.annotations.SerializedName;

public class DataKategori {
    @SerializedName("id_kategori")
    private String idKategori;
    @SerializedName("nama_kategori")
    private String namaKategori;

    public DataKategori(String idKategori, String namaKategori) {
        this.idKategori = idKategori;
        this.namaKategori = namaKategori;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
}
