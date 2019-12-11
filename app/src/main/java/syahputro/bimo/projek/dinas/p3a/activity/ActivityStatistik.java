package syahputro.bimo.projek.dinas.p3a.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChartView;
import com.kusu.loadingbutton.LoadingButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.year.ResponseYear;

public class ActivityStatistik extends AppCompatActivity {
    private Spinner spinnerBerdasarkan, spinnerTahun;
    private ApiService service;
    LoadingButton loadingButton;
    ArrayList<String> array_tahun = new ArrayList<String>();
    String selected_tahun;
    //    TableLayout tableLayout;
    AnyChartView chart_bentuk, chart_usia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Statistik");

        init();
        setSpinnerBerdasarkan();
        setSpinnerTahun();

        Log.d("tag", "onResponse: array_tahun " + array_tahun);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingButton.showLoading();
                if (spinnerBerdasarkan.getSelectedItemId() == 0) {
//                    getDataBerdasarkanBentuk(2018);
                    Intent intent = new Intent(ActivityStatistik.this, ActivityStatistikDetail.class);
                    intent.putExtra("asal", "0");
                    startActivity(intent);
                }
                if (spinnerBerdasarkan.getSelectedItemId() == 1) {
//                    getDataBerdasarkanUsia(2018);
                    Intent intent = new Intent(ActivityStatistik.this, ActivityStatistikDetail.class);
                    intent.putExtra("asal", "1");
                    startActivity(intent);
                }
                loadingButton.hideLoading();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setSpinnerBerdasarkan() {
        String[] bentuk = {"Bentuk Kekerasan", "Usia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, bentuk);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBerdasarkan.setAdapter(adapter);
    }

    private void setSpinnerTahun() {
        Call<ResponseYear> tahun = service.statistik_tahun();
        tahun.enqueue(new Callback<ResponseYear>() {
            @Override
            public void onResponse(Call<ResponseYear> call, Response<ResponseYear> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("0")) {
//                            List<String> tahun = response.body().getData();
                            array_tahun.addAll(response.body().getData());
//                            Log.d("tag", "onResponse: array_tahun " + array_tahun);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, array_tahun);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerTahun.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseYear> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        chart_bentuk = findViewById(R.id.any_chart_view);
        chart_usia = findViewById(R.id.any_chart_view_usia);
//        tableLayout = findViewById(R.id.table_statistik);
        loadingButton = findViewById(R.id.loadingButton);
        service = ApiClient.getClient().create(ApiService.class);
        spinnerBerdasarkan = findViewById(R.id.spinnerBerdasarkan);
        spinnerTahun = findViewById(R.id.spinnerTahun);
    }
}
