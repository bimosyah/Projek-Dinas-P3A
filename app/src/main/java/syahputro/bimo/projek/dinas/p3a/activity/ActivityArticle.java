package syahputro.bimo.projek.dinas.p3a.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikel;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItemDetail;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_artikel.ResponseArtikel;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_berita.ResponseBerita;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_kegiatan.ResponseKegiatan;

public class ActivityArticle extends AppCompatActivity {
    private String jenis;
    private RecyclerView rv;
    private ApiService service;
    private AdapterArtikel adapter;
    List<ArticleItemDetail> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        jenis = getIntent().getStringExtra("jenis");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(jenis);

        init();
        loadArtikel();
        Log.d("TAG", "onCreate: jumlah list = " + list.size());
    }

    private void init() {
        list = new ArrayList<>();
        rv = findViewById(R.id.rvArtikel);
        service = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void loadArtikel() {
        switch (jenis) {
            case "Berita":
                Call<ResponseBerita> berita = service.berita(10);
                berita.enqueue(new Callback<ResponseBerita>() {
                    @Override
                    public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                list = response.body().getArticles();
                                adapter = new AdapterArtikel(list, getApplicationContext());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                rv.setLayoutManager(mLayoutManager);
                                rv.setItemAnimator(new DefaultItemAnimator());
                                rv.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBerita> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Server Error " + t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "Artikel":
                Call<ResponseArtikel> artikel = service.artikel(10);
                artikel.enqueue(new Callback<ResponseArtikel>() {
                    @Override
                    public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                List<ArticleItemDetail> list = response.body().getArticles();
                                adapter = new AdapterArtikel(list, getApplicationContext());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                rv.setLayoutManager(mLayoutManager);
                                rv.setItemAnimator(new DefaultItemAnimator());
                                rv.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseArtikel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Server Error " + t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "Kegiatan":
                Call<ResponseKegiatan> kegiatan = service.kegiatan(10);
                kegiatan.enqueue(new Callback<ResponseKegiatan>() {
                    @Override
                    public void onResponse(Call<ResponseKegiatan> call, Response<ResponseKegiatan> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                List<ArticleItemDetail> list = response.body().getArticles();
                                adapter = new AdapterArtikel(list, getApplicationContext());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                rv.setLayoutManager(mLayoutManager);
                                rv.setItemAnimator(new DefaultItemAnimator());
                                rv.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKegiatan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Server Error " + t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }
}
