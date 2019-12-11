package syahputro.bimo.projek.dinas.p3a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class ActivityStatistikDetail extends AppCompatActivity {
    private ApiService service;
    AnyChartView chart_usia;
    String tahun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chart_usia = findViewById(R.id.any_chart_view_usia);

        service = ApiClient.getClient().create(ApiService.class);
        if (getIntent().getStringExtra("asal").equals("0")){
            tahun = String.valueOf(getIntent().getIntExtra("tahun",0));
            getDataBerdasarkanBentuk(tahun);
        }else {
            tahun = String.valueOf(getIntent().getIntExtra("tahun",0));
            getDataBerdasarkanUsia(tahun);
        }
    }

    private void getDataBerdasarkanBentuk(String tahun) {
        final Call<ResponseBentuk> data = service.statistik_bentuk(Integer.parseInt(tahun));
        data.enqueue(new Callback<ResponseBentuk>() {
            @Override
            public void onResponse(Call<ResponseBentuk> call, Response<ResponseBentuk> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Pie pie = AnyChart.pie();
                        List<GrafikItemBentuk> grafikItemBentuk= response.body().getGrafik();
                        List<DataEntry> data_chart = new ArrayList<>();
                        for (GrafikItemBentuk item : grafikItemBentuk) {
                            data_chart.add(new ValueDataEntry("Fisik", Integer.parseInt(item.getFisik())));
                            data_chart.add(new ValueDataEntry("Psikologi", Integer.parseInt(item.getPsikologi())));
                            data_chart.add(new ValueDataEntry("Seksual", Integer.parseInt(item.getSeksual())));
                            data_chart.add(new ValueDataEntry("Exploitasi", Integer.parseInt(item.getEksploitasi())));
                            data_chart.add(new ValueDataEntry("Penelantaran", Integer.parseInt(item.getPenelantaran())));
                            data_chart.add(new ValueDataEntry("Lain", Integer.parseInt(item.getLain())));
                        }

                        pie.data(data_chart);
                        pie.legend().position("top");
                        pie.legend().itemsLayout("horizontalExpandable");
                        pie.legend().padding(20);
                        chart_usia.setChart(pie);

                        //tableLayout.setVisibility(View.VISIBLE);
                        chart_usia.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBentuk> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataBerdasarkanUsia(String tahun) {
        final Call<ResponseUsia> data = service.statistik_usia(Integer.parseInt(tahun));
        data.enqueue(new Callback<ResponseUsia>() {
            @Override
            public void onResponse(Call<ResponseUsia> call, Response<ResponseUsia> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Pie pie = AnyChart.pie();
                        List<GrafikItemUsia> grafikItemUsia = response.body().getGrafik();
                        List<DataEntry> data_chart = new ArrayList<>();
                        for (GrafikItemUsia item : grafikItemUsia) {
                            data_chart.add(new ValueDataEntry("Usia 19 - 24", Integer.parseInt(item.getUsia1())));
                            data_chart.add(new ValueDataEntry("Usia 25 - 44", Integer.parseInt(item.getUsia2())));
                            data_chart.add(new ValueDataEntry("Usia 45+", Integer.parseInt(item.getUsia3())));
                        }

                        pie.data(data_chart);
                        pie.legend().position("top");
                        pie.legend().itemsLayout("horizontalExpandable");
                        pie.legend().padding(20);
                        chart_usia.setChart(pie);

                        //tableLayout.setVisibility(View.VISIBLE);
                        chart_usia.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUsia> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
