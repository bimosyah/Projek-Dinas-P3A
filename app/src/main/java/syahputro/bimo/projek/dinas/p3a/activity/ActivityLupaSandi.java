package syahputro.bimo.projek.dinas.p3a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import syahputro.bimo.projek.dinas.p3a.R;

public class ActivityLupaSandi extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_sandi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lupa Kata Sandi");

        init();
        webView.loadUrl("http://dp3akabupatenmalang.com/dp3a/api/info/lupa");

    }

    private void init(){
        webView = findViewById(R.id.wvLupaPassword);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
