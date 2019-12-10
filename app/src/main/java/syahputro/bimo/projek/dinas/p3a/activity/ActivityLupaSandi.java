package syahputro.bimo.projek.dinas.p3a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import syahputro.bimo.projek.dinas.p3a.R;

public class ActivityLupaSandi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_sandi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lupa Kata Sandi");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
