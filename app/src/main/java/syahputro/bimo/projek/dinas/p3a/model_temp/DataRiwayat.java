package syahputro.bimo.projek.dinas.p3a.model_temp;

public class DataRiwayat {
    private String tanggal;
    private String id_status;

    public DataRiwayat(String tanggal, String id_status) {
        this.tanggal = tanggal;
        this.id_status = id_status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId_status() {
        return id_status;
    }

    public void setId_status(String id_status) {
        this.id_status = id_status;
    }
}
