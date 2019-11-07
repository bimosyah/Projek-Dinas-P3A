package syahputro.bimo.projek.dinas.p3a.model_temp;

public class DataArtikel {
    private int image;
    private String judul;

    public DataArtikel(int image, String judul) {
        this.image = image;
        this.judul = judul;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
