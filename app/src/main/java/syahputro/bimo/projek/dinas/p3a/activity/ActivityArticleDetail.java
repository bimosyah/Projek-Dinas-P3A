package syahputro.bimo.projek.dinas.p3a.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
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
                                .load(response.body().getData().getImage())
                                .into(imageView);
                        tvTitle.setText(response.body().getData().getTitle());
                        tvTanggal.setText(response.body().getData().getDate());
                        tvKategori.setText(response.body().getData().getPostCategory());
                        tvContent.setText(response.body().getData().getContent());
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
        service = ApiClient.getClient().create(ApiService.class);
        imageView = findViewById(R.id.ivArtikel);
        tvTitle = findViewById(R.id.tvTitle);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvKategori = findViewById(R.id.tvKategori);
        tvContent = findViewById(R.id.tvContent);
    }
}
