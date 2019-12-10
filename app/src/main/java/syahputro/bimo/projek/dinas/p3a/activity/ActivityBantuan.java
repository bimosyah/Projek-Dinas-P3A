package syahputro.bimo.projek.dinas.p3a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import syahputro.bimo.projek.dinas.p3a.R;

public class ActivityBantuan extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bantuan");

        init();
        webView.loadUrl("http://dp3akabupatenmalang.com/dp3a/api/info/bantuan");
    }

    private void init(){
        webView = findViewById(R.id.wvBantuan);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
