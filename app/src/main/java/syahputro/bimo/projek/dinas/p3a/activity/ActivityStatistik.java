package syahputro.bimo.projek.dinas.p3a.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
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
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk.GrafikItemBentuk;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk.ResponseBentuk;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.usia.GrafikItemUsia;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.usia.ResponseUsia;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.year.ResponseYear;

public class ActivityStatistik extends AppCompatActivity {
    private Spinner spinnerBerdasarkan, spinnerTahun;
    private ApiService service;
    LoadingButton loadingButton;
    ArrayList<String> array_tahun = new ArrayList<String>();
    List<DataEntry> data_chart = new ArrayList<>();
    String selected_tahun;
    AnyChartView anyChartView;
    TableLayout tableLayout;
    AnyChartView chart;

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
//                    anyChartView.clear();
                    getDataBerdasarkanBentuk(2018);
                } else {
//                    anyChartView.clear();
                    getDataBerdasarkanUsia(2018);
                }

            }
        });
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

    private void getDataBerdasarkanBentuk(int tahun) {
        final Call<ResponseBentuk> data = service.statistik_bentuk(2018);
        data.enqueue(new Callback<ResponseBentuk>() {
            @Override
            public void onResponse(Call<ResponseBentuk> call, Response<ResponseBentuk> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Pie pie = AnyChart.pie();
                        List<GrafikItemBentuk> grafikItemBentuks = response.body().getGrafik();

                        for (GrafikItemBentuk item : grafikItemBentuks) {
                            data_chart.add(new ValueDataEntry("Fisik", Integer.parseInt(item.getFisik())));
                            data_chart.add(new ValueDataEntry("Psikologi", Integer.parseInt(item.getPsikologi())));
                            data_chart.add(new ValueDataEntry("Seksual", Integer.parseInt(item.getSeksual())));
                            data_chart.add(new ValueDataEntry("Exploitasi", Integer.parseInt(item.getEksploitasi())));
                            data_chart.add(new ValueDataEntry("Penelantaran", Integer.parseInt(item.getPenelantaran())));
                            data_chart.add(new ValueDataEntry("Lain", Integer.parseInt(item.getLain())));
                        }

                        pie.data(data_chart);
                        anyChartView.setChart(pie);

                        pie.legend().position("top");
                        pie.legend().itemsLayout("horizontalExpandable");
                        pie.legend().padding(20);
                        loadingButton.hideLoading();

                        tableLayout.setVisibility(View.VISIBLE);
                        chart.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBentuk> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataBerdasarkanUsia(int tahun) {
        final Call<ResponseUsia> data = service.statistik_usia(2018);
        data.enqueue(new Callback<ResponseUsia>() {
            @Override
            public void onResponse(Call<ResponseUsia> call, Response<ResponseUsia> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Pie pie = AnyChart.pie();
                        List<GrafikItemUsia> grafikItemUsia = response.body().getGrafik();

                        for (GrafikItemUsia item : grafikItemUsia) {
                            data_chart.add(new ValueDataEntry("Usia 1", Integer.parseInt(item.getUsia1())));
                            data_chart.add(new ValueDataEntry("Usia 2", Integer.parseInt(item.getUsia2())));
                            data_chart.add(new ValueDataEntry("Usia 3", Integer.parseInt(item.getUsia3())));
                        }

                        pie.data(data_chart);
                        anyChartView.setChart(pie);
                        pie.legend().position("top");
                        pie.legend().itemsLayout("horizontalExpandable");
                        pie.legend().padding(20);
                        loadingButton.hideLoading();

                        tableLayout.setVisibility(View.VISIBLE);
                        chart.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUsia> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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
        anyChartView = findViewById(R.id.any_chart_view);
        chart = findViewById(R.id.any_chart_view);
        tableLayout = findViewById(R.id.table_statistik);
        loadingButton = findViewById(R.id.loadingButton);
        service = ApiClient.getClient().create(ApiService.class);
        spinnerBerdasarkan = findViewById(R.id.spinnerBerdasarkan);
        spinnerTahun = findViewById(R.id.spinnerTahun);
    }
}
