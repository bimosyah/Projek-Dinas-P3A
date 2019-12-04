package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikelMid;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItem;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItemDetail;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_artikel.ResponseArtikel;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_berita.ResponseBerita;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_kegiatan.ResponseKegiatan;

public class ActivityArtikelAll extends AppCompatActivity {
    private RecyclerView recyclerView_mid;
    private List<ArticleItemDetail> beritaList;
    private List<ArticleItemDetail> artikelList;
    private List<ArticleItemDetail> kegiatanList;
    private List<ArticleItem> itemList;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_all);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Berita");

        init();
        loadBerita();
        loadArtikel();
        loadKegiatan();
        articleList();

    }

    private void articleList() {
        ArticleItem item = new ArticleItem("Berita", beritaList);
        itemList.add(item);
        item = new ArticleItem("Artikel", artikelList);
        itemList.add(item);
        item = new ArticleItem("Kegiatan", kegiatanList);
        itemList.add(item);
    }

    private void loadBerita() {
        Call<ResponseBerita> berita = service.berita(4);
        berita.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()) {
                    Log.d("berita", "berita is successfull: ");
                    if (response.body() != null) {
                        Log.d("berita", "berita not null: ");
                        beritaList.addAll(response.body().getArticles());
                        Log.d("berita", "berita jumlah: " + beritaList.size());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        AdapterArtikelMid adapterArtikelMid = new AdapterArtikelMid(itemList, getApplication());
                        recyclerView_mid.setHasFixedSize(true);
                        recyclerView_mid.setAdapter(adapterArtikelMid);
                        recyclerView_mid.setLayoutManager(layoutManager);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadArtikel() {
        Call<ResponseArtikel> berita = service.artikel(4);
        berita.enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                if (response.isSuccessful()) {
                    Log.d("artikel", "artikel is successfull: ");
                    if (response.body() != null) {
                        Log.d("artikel", "artikel is not null: ");
                        artikelList.addAll(response.body().getArticles());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        AdapterArtikelMid adapterArtikelMid = new AdapterArtikelMid(itemList, getApplicationContext());
                        recyclerView_mid.setHasFixedSize(true);
                        recyclerView_mid.setAdapter(adapterArtikelMid);
                        recyclerView_mid.setLayoutManager(layoutManager);
                        Log.d("artikel", "artikel jumlah: " + artikelList.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseArtikel> call, Throwable t) {
                Log.d("artikel", "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadKegiatan() {
        Call<ResponseKegiatan> berita = service.kegiatan(4);
        berita.enqueue(new Callback<ResponseKegiatan>() {
            @Override
            public void onResponse(Call<ResponseKegiatan> call, Response<ResponseKegiatan> response) {
                if (response.isSuccessful()) {
                    Log.d("kegiatan", "kegiatan is successfull: ");
                    if (response.body() != null) {
                        Log.d("kegiatan", "kegiatan is not null: ");
                        kegiatanList.addAll(response.body().getArticles());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        AdapterArtikelMid adapterArtikelMid = new AdapterArtikelMid(itemList, getApplicationContext());
                        recyclerView_mid.setHasFixedSize(true);
                        recyclerView_mid.setAdapter(adapterArtikelMid);
                        recyclerView_mid.setLayoutManager(layoutManager);
                        Log.d("kegiatan", "kegiatan jumlah: " + kegiatanList.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKegiatan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void init() {
        itemList = new ArrayList<>();
        beritaList = new ArrayList<>();
        artikelList = new ArrayList<>();
        kegiatanList = new ArrayList<>();
        recyclerView_mid = findViewById(R.id.rv_halaman_utama_artikel_mid);
        service = ApiClient.getClient().create(ApiService.class);
    }
}
