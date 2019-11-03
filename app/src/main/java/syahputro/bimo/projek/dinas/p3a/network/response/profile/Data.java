package syahputro.bimo.projek.dinas.p3a.network.response.profile;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("nama")
    private String nama;
    @SerializedName("nomor_telp")
    private String nomorTelp;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("tanggal_lahir")
    private String tanggalLahir;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelp() {
        return nomorTelp;
    }

    public void setNomorTelp(String nomorTelp) {
        this.nomorTelp = nomorTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}
