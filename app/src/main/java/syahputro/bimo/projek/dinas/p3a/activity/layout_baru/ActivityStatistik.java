package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.kusu.loadingbutton.LoadingButton;

import java.util.ArrayList;
import java.util.List;

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
    List<DataEntry> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Statistik");

        init();
        setSpinnerBerdasarkan();
        setSpinnerTahun();

        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingButton.showLoading();

            }
        });




        Pie pie = AnyChart.pie();

        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        pie.data(data);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setSpinnerBerdasarkan() {
        String[] bentuk = {"Bentuk", "Usia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, bentuk);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBerdasarkan.setAdapter(adapter);
    }

    private void setSpinnerTahun() {
        Call<ResponseYear> kategori = service.statistik_tahun();
        kategori.enqueue(new Callback<ResponseYear>() {
            @Override
            public void onResponse(Call<ResponseYear> call, Response<ResponseYear> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("0")) {
                            List<String> tahun = response.body().getData();
                            array_tahun.addAll(tahun);
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
        loadingButton = findViewById(R.id.loadingButton);
        service = ApiClient.getClient().create(ApiService.class);
        spinnerBerdasarkan = findViewById(R.id.spinnerBerdasarkan);
        spinnerTahun = findViewById(R.id.spinnerTahun);
    }
}
