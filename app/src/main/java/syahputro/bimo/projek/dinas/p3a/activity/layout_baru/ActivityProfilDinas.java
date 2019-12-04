package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import syahputro.bimo.projek.dinas.p3a.R;

public class ActivityProfilDinas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_dinas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil Dinas");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
