package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import syahputro.bimo.projek.dinas.p3a.R;

public class ActivityStatistik extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Statistik");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
