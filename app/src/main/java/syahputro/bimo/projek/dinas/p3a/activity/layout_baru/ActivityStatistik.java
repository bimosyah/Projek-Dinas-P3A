package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

import syahputro.bimo.projek.dinas.p3a.R;

public class ActivityStatistik extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Statistik");

        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
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
}
