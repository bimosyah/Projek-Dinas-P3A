package syahputro.bimo.projek.dinas.p3a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.profile_dinas.ResponseProfileDinas;

public class ActivityProfilDinas extends AppCompatActivity {
    private TextView tvCall, tvKonsul, tvNamaDinas, tvAlamat;
    private ImageView imageView;
    private String no_wa, msg, telp, alamat, nama_dinas, logo;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_dinas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil Dinas");

        init();
        loadDataDinas();

        tvKonsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + no_wa + "&text=" + msg)));
                } catch (Exception e) {
                }
            }
        });

        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL); // Action for what intent called for
                intent.setData(Uri.parse("tel: " + telp)); // DataDetailArtikel with intent respective action on intent
                startActivity(intent);
            }
        });

    }

    private void loadDataDinas() {
        Call<ResponseProfileDinas> riwayat = service.dinas();
        riwayat.enqueue(new Callback<ResponseProfileDinas>() {
            @Override
            public void onResponse(Call<ResponseProfileDinas> call, Response<ResponseProfileDinas> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("no_wa", "onResponse: " + response.body().getData().getNoWa());

                        no_wa = response.body().getData().getNoWa();
                        msg = response.body().getData().getTemplateWa();
                        telp = response.body().getData().getNoTelp();
                        alamat = response.body().getData().getAlamat();
                        nama_dinas = response.body().getData().getNamaDinas();
                        logo = response.body().getData().getLogo();

                        Glide.with(getApplicationContext())
                                .load(logo)
                                .into(imageView);
                        tvNamaDinas.setText(nama_dinas);
                        tvAlamat.setText(alamat);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseProfileDinas> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        tvCall = findViewById(R.id.tvCall);
        tvKonsul = findViewById(R.id.tvKonsul);
        imageView = findViewById(R.id.ivDinas);
        tvNamaDinas = findViewById(R.id.textView9);
        tvAlamat = findViewById(R.id.textView12);
        service = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
