package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.activity.ActivityLogin;
import syahputro.bimo.projek.dinas.p3a.activity.ActivityUser;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikelBanner;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikelMid;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterHome;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItem;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItemDetail;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_kegiatan.ResponseKegiatan;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider.DataSlider;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider.ResponseSlider;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;
import syahputro.bimo.projek.dinas.p3a.utils.SnapHelperOneByOne;

public class ActivityHalamanUtama extends AppCompatActivity {
    private RecyclerView recyclerView_top;
    private RecyclerView recyclerView_bottom;
    private List<ArticleItemDetail> kegiatanList;
    private List<ArticleItem> itemList;
    private AdapterArtikelBanner adapter_top;
    private ApiService service;
    private RelativeLayout menu1, menu2, menu3, menu4, menu5, menu6;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);
        init();
        loadDataBanner();

        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        linearSnapHelper.attachToRecyclerView(recyclerView_top);
        recyclerView_top.setNestedScrollingEnabled(false);
        recyclerView_top.setHasFixedSize(false);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHalamanUtama.this, ActivityPengaduan.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHalamanUtama.this, ActivityStatistik.class);
                startActivity(intent);
            }
        });
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHalamanUtama.this, ActivityArtikelAll.class);
                startActivity(intent);
            }
        });
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHalamanUtama.this, ActivityRiwayat.class);
                startActivity(intent);
            }
        });
        menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHalamanUtama.this, ActivityProfilDinas.class);
                startActivity(intent);
            }
        });
        menu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHalamanUtama.this, ActivityBantuan.class);
                startActivity(intent);
            }
        });

        loadKegiatan();
    }

    private void loadDataBanner() {
        Call<ResponseSlider> riwayat = service.slider(4);
        riwayat.enqueue(new Callback<ResponseSlider>() {
            @Override
            public void onResponse(Call<ResponseSlider> call, Response<ResponseSlider> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<DataSlider> data = response.body().getArticles();
                        adapter_top = new AdapterArtikelBanner(data, getApplicationContext());
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),
                                LinearLayoutManager.HORIZONTAL, false);
                        recyclerView_top.setLayoutManager(mLayoutManager);
                        recyclerView_top.setItemAnimator(new DefaultItemAnimator());
                        recyclerView_top.setAdapter(adapter_top);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSlider> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        recyclerView_top = findViewById(R.id.rv_halaman_utama_artikel_top);
        service = ApiClient.getClient().create(ApiService.class);
        menu1 = findViewById(R.id.menu1);
        menu2 = findViewById(R.id.menu2);
        menu3 = findViewById(R.id.menu3);
        menu4 = findViewById(R.id.menu4);
        menu5 = findViewById(R.id.menu5);
        menu6 = findViewById(R.id.menu6);
        recyclerView_bottom = findViewById(R.id.recyclerView_bottom);
        kegiatanList = new ArrayList<>();
        itemList = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Preference.clearLoggedInUser(getApplicationContext());
            finish();
        } else if (item.getItemId() == R.id.action_profile) {
            startActivity(new Intent(ActivityHalamanUtama.this, ActivityUser.class));
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            Intent intent = new Intent(ActivityHalamanUtama.this, ActivityLogin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
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
                        kegiatanList = response.body().getArticles();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        AdapterHome adapterHome= new AdapterHome(kegiatanList, getApplicationContext());
                        recyclerView_bottom.setHasFixedSize(true);
                        recyclerView_bottom.setAdapter(adapterHome);
                        recyclerView_bottom.setLayoutManager(layoutManager);
//                        Log.d("kegiatan", "kegiatan jumlah: " + kegiatanList.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKegiatan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
