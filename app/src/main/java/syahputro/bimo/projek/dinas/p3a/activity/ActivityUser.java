package syahputro.bimo.projek.dinas.p3a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;

public class ActivityUser extends AppCompatActivity {
    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        init();

    }

    private void init(){
        service = ApiClient.getClient().create(ApiService.class);
    }

    private void loadData(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
