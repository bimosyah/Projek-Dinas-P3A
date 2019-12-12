package syahputro.bimo.projek.dinas.p3a.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk.DataItemBentuk;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk.GrafikItemBentuk;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.bentuk.ResponseBentuk;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.usia.DataItemUsia;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.usia.GrafikItemUsia;
import syahputro.bimo.projek.dinas.p3a.network.response.statistik.usia.ResponseUsia;

public class ActivityStatistikDetail extends AppCompatActivity {
    private ApiService service;
    AnyChartView chart_usia;
    String tahun;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chart_usia = findViewById(R.id.any_chart_view);
        tableLayout = findViewById(R.id.table_statistik);
        service = ApiClient.getClient().create(ApiService.class);

        if (getIntent().getStringExtra("asal").equals("0")) {
            tahun = String.valueOf(getIntent().getIntExtra("tahun", 0));
            getDataBerdasarkanBentuk(tahun);
        } else {
            tahun = String.valueOf(getIntent().getIntExtra("tahun", 0));
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
                        List<GrafikItemBentuk> grafikItemBentuk = response.body().getGrafik();
                        List<DataItemBentuk> itemBentuk = response.body().getData();
                        List<DataEntry> data_chart = new ArrayList<>();

                        for (GrafikItemBentuk item : grafikItemBentuk) {
                            data_chart.add(new ValueDataEntry("Fisik", Integer.parseInt(item.getFisik())));
                            data_chart.add(new ValueDataEntry("Psikologi", Integer.parseInt(item.getPsikologi())));
                            data_chart.add(new ValueDataEntry("Seksual", Integer.parseInt(item.getSeksual())));
                            data_chart.add(new ValueDataEntry("Exploitasi", Integer.parseInt(item.getEksploitasi())));
                            data_chart.add(new ValueDataEntry("Penelantaran", Integer.parseInt(item.getPenelantaran())));
                            data_chart.add(new ValueDataEntry("Lain", Integer.parseInt(item.getLain())));
                        }

                        TableLayout stk = findViewById(R.id.table_statistik);
                        TableRow tbrow0 = new TableRow(getApplicationContext());
                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText(" Bulan ");
                        tv1.setTextColor(Color.BLACK);
                        tv1.setGravity(Gravity.CENTER);
                        tbrow0.addView(tv1);
                        TextView tv2 = new TextView(getApplicationContext());
                        tv2.setText(" Fisik ");
                        tv2.setTextColor(Color.BLACK);
                        tbrow0.addView(tv2);
                        TextView tv3 = new TextView(getApplicationContext());
                        tv3.setText(" Psikologi ");
                        tv3.setTextColor(Color.BLACK);
                        tbrow0.addView(tv3);
                        TextView tv4 = new TextView(getApplicationContext());
                        tv4.setText(" Seksual ");
                        tv4.setTextColor(Color.BLACK);
                        tbrow0.addView(tv4);
                        TextView tv5 = new TextView(getApplicationContext());
                        tv5.setText(" Eksploitasi ");
                        tv5.setTextColor(Color.BLACK);
                        tbrow0.addView(tv5);
                        TextView tv6 = new TextView(getApplicationContext());
                        tv6.setText(" Penelantaran ");
                        tv6.setTextColor(Color.BLACK);
                        tbrow0.addView(tv6);
                        TextView tv7 = new TextView(getApplicationContext());
                        tv7.setText(" Lain ");
                        tv7.setTextColor(Color.BLACK);
                        tbrow0.addView(tv7);

                        stk.addView(tbrow0);

                        for (DataItemBentuk item : itemBentuk) {
                            TableRow tbrow = new TableRow(getApplicationContext());
                            TextView t1v = new TextView(getApplicationContext());
                            t1v.setText("" + item.getBulan());
                            t1v.setTextColor(Color.BLACK);
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            TextView t2v = new TextView(getApplicationContext());
                            t2v.setText("" + item.getFisik());
                            t2v.setTextColor(Color.BLACK);
                            t2v.setGravity(Gravity.CENTER);
                            tbrow.addView(t2v);
                            TextView t3v = new TextView(getApplicationContext());
                            t3v.setText("" + item.getPsikologi());
                            t3v.setTextColor(Color.BLACK);
                            t3v.setGravity(Gravity.CENTER);
                            tbrow.addView(t3v);
                            TextView t4v = new TextView(getApplicationContext());
                            t4v.setText("" + item.getSeksual());
                            t4v.setTextColor(Color.BLACK);
                            t4v.setGravity(Gravity.CENTER);
                            tbrow.addView(t4v);
                            TextView t5v = new TextView(getApplicationContext());
                            t5v.setText("" + item.getEksploitasi());
                            t5v.setTextColor(Color.BLACK);
                            t5v.setGravity(Gravity.CENTER);
                            tbrow.addView(t5v);
                            TextView t6v = new TextView(getApplicationContext());
                            t6v.setText("" + item.getPenelantaran());
                            t6v.setTextColor(Color.BLACK);
                            t6v.setGravity(Gravity.CENTER);
                            tbrow.addView(t6v);
                            TextView t7v = new TextView(getApplicationContext());
                            t7v.setText("" + item.getLain());
                            t7v.setTextColor(Color.BLACK);
                            t7v.setGravity(Gravity.CENTER);
                            tbrow.addView(t7v);
                            stk.addView(tbrow);
                        }

                        pie.data(data_chart);
                        pie.legend().position("top");
                        pie.legend().itemsLayout("horizontalExpandable");
                        pie.legend().padding(20);
                        chart_usia.setChart(pie);
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
                        List<DataItemUsia> dataItem = response.body().getData();
                        List<DataEntry> data_chart = new ArrayList<>();
                        for (GrafikItemUsia item : grafikItemUsia) {
                            data_chart.add(new ValueDataEntry("Usia 19 - 24", Integer.parseInt(item.getUsia1())));
                            data_chart.add(new ValueDataEntry("Usia 25 - 44", Integer.parseInt(item.getUsia2())));
                            data_chart.add(new ValueDataEntry("Usia 45+", Integer.parseInt(item.getUsia3())));
                        }

                        TableLayout stk = findViewById(R.id.table_statistik);
                        TableRow tbrow0 = new TableRow(getApplicationContext());
                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText(" Bulan ");
                        tv1.setTextColor(Color.BLACK);
                        tv1.setGravity(Gravity.CENTER);
                        tbrow0.addView(tv1);
                        TextView tv2 = new TextView(getApplicationContext());
                        tv2.setText(" 19 - 24 ");
                        tv2.setTextColor(Color.BLACK);
                        tv2.setGravity(Gravity.CENTER);
                        tbrow0.addView(tv2);
                        TextView tv3 = new TextView(getApplicationContext());
                        tv3.setText(" 25 - 45");
                        tv3.setTextColor(Color.BLACK);
                        tv3.setGravity(Gravity.CENTER);
                        tbrow0.addView(tv3);
                        TextView tv4 = new TextView(getApplicationContext());
                        tv4.setText(" 45+ ");
                        tv4.setTextColor(Color.BLACK);
                        tv4.setGravity(Gravity.CENTER);
                        tbrow0.addView(tv4);
                        stk.addView(tbrow0);

                        for (DataItemUsia item : dataItem){
                            TableRow tbrow = new TableRow(getApplicationContext());
                            TextView t1v = new TextView(getApplicationContext());
                            t1v.setText("" + item.getBulan());
                            t1v.setTextColor(Color.BLACK);
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            TextView t2v = new TextView(getApplicationContext());
                            t2v.setText("" + item.getUsia1());
                            t2v.setTextColor(Color.BLACK);
                            t2v.setGravity(Gravity.CENTER);
                            tbrow.addView(t2v);
                            TextView t3v = new TextView(getApplicationContext());
                            t3v.setText("" + item.getUsia2());
                            t3v.setTextColor(Color.BLACK);
                            t3v.setGravity(Gravity.CENTER);
                            tbrow.addView(t3v);
                            TextView t4v = new TextView(getApplicationContext());
                            t4v.setText("" + item.getUsia3());
                            t4v.setTextColor(Color.BLACK);
                            t4v.setGravity(Gravity.CENTER);
                            tbrow.addView(t4v);
                            stk.addView(tbrow);
                        }

                        pie.data(data_chart);
                        pie.legend().position("top");
                        pie.legend().itemsLayout("horizontalExpandable");
                        pie.legend().padding(20);
                        chart_usia.setChart(pie);
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
