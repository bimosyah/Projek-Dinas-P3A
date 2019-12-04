package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import syahputro.bimo.projek.dinas.p3a.R;

public class ActivityBantuan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bantuan");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
