package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterRiwayat;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.riwayat.Data;
import syahputro.bimo.projek.dinas.p3a.network.response.riwayat.ResponseRiwayat;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;

public class ActivityRiwayat extends AppCompatActivity {
    private List<Data> list;
    private RecyclerView recyclerView;
    private AdapterRiwayat adapter;
    private ApiService service;
    private TextView tv_riwayat;
    private ProgressBar loadingRiwayat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Riwayat");

        init();
        loadData();
        loadingRiwayat.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        Call<ResponseRiwayat> riwayat = service.riwayat(Integer.parseInt(Preference.getIdUser(this)));
        riwayat.enqueue(new Callback<ResponseRiwayat>() {
            @Override
            public void onResponse(Call<ResponseRiwayat> call, Response<ResponseRiwayat> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("0")) {
                            List<Data> data_kategori = response.body().getData();
                            adapter = new AdapterRiwayat(data_kategori, getApplicationContext());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);
                            loadingRiwayat.setVisibility(View.GONE);
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            tv_riwayat.setVisibility(View.VISIBLE);
                            loadingRiwayat.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRiwayat> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
                loadingRiwayat.setVisibility(View.GONE);
            }
        });
    }

    private void init() {
        loadingRiwayat = findViewById(R.id.loadingRiwayat);
        recyclerView = findViewById(R.id.rvRiwayat);
        service = ApiClient.getClient().create(ApiService.class);
        list = new ArrayList<>();
        tv_riwayat = findViewById(R.id.tv_riwayat);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
