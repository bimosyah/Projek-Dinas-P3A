package syahputro.bimo.projek.dinas.p3a.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.detail_artikel.ResponseDetailArtikel;

public class ActivityArticleDetail extends AppCompatActivity {
    ImageView imageView;
    ApiService service;
    TextView tvContent, tvTitle, tvTanggal, tvKategori;
    ProgressBar loadingArtikel;
    ConstraintLayout container_artikel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        init();

        String id_artikel = getIntent().getStringExtra("id_artikel");
        loadArtikel(id_artikel);

    }

    private void loadArtikel(String id_artikel) {
        Call<ResponseDetailArtikel> artikel = service.detail_artikel(Integer.parseInt(id_artikel));
        artikel.enqueue(new Callback<ResponseDetailArtikel>() {
            @Override
            public void onResponse(Call<ResponseDetailArtikel> call, Response<ResponseDetailArtikel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("Respone", "onResponse: " + response.body().getTotal());
                        Glide.with(getApplicationContext())
                                .load(response.body().getDataDetailArtikel().getImage())
                                .into(imageView);
                        tvTitle.setText(response.body().getDataDetailArtikel().getTitle());
                        tvTanggal.setText(response.body().getDataDetailArtikel().getDate());
                        tvKategori.setText(response.body().getDataDetailArtikel().getPostCategory());
                        tvContent.setText(response.body().getDataDetailArtikel().getContent());
                        container_artikel.setVisibility(View.VISIBLE);
                        loadingArtikel.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getApplicationContext(), "error body" + response.body(), Toast.LENGTH_LONG).show();
                        Log.d("error body", "onResponse: " + response.body());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error ga sukses" + response.body(), Toast.LENGTH_LONG).show();
                    Log.d("error body", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailArtikel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        container_artikel = findViewById(R.id.container_artikel);
        loadingArtikel = findViewById(R.id.loadingArtikel);
        service = ApiClient.getClient().create(ApiService.class);
        imageView = findViewById(R.id.ivArtikel);
        tvTitle = findViewById(R.id.tvTitle);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvKategori = findViewById(R.id.tvKategori);
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
