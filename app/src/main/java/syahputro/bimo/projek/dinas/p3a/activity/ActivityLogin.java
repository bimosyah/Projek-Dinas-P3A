package syahputro.bimo.projek.dinas.p3a.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kusu.loadingbutton.LoadingButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.activity.layout_baru.ActivityHalamanUtama;
import syahputro.bimo.projek.dinas.p3a.activity.layout_baru.ActivityLupaSandi;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.login.ResponseLogin;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;


public class ActivityLogin extends AppCompatActivity {
    TextView tvDaftar,tvLupaKataSandi;
    Button btn_login;
    EditText etNoTelp, etPassword;
    ApiService service;
    LoadingButton loadingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        init();
        service = ApiClient.getClient().create(ApiService.class);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLogin.this, ActivityRegister.class));
            }
        });

        if (Preference.getLoggedInStatus(this)){
            if (getIntent().getBooleanExtra("EXIT", false)) {
                finish();
            }else{
                startActivity(new Intent(ActivityLogin.this, ActivityHalamanUtama.class));
            }
        }

        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingButton.showLoading();
                login(etNoTelp.getText().toString(),etPassword.getText().toString());
            }
        });

        tvLupaKataSandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLogin.this, ActivityLupaSandi.class));
            }
        });
    }

    private void init() {
        tvLupaKataSandi = findViewById(R.id.textView3);
        tvDaftar = findViewById(R.id.tvDaftar);
        btn_login = findViewById(R.id.button);
        etNoTelp = findViewById(R.id.etNoTelp);
        etPassword = findViewById(R.id.etPassword);
        loadingButton = findViewById(R.id.loadingButton);
    }

    private void login(String no_telp, String password) {
        if (no_telp.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "No Telp dan Password harus diisi", Toast.LENGTH_LONG).show();
            loadingButton.hideLoading();
        }else {
            Call<ResponseLogin> login = service.login(etNoTelp.getText().toString(), etPassword.getText().toString());
            login.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatus().equals("0")) {
                                Preference.setIdUser(getApplicationContext(), response.body().getData().getId());
                                Preference.setLoggedInStatus(getApplicationContext(), true);
                                loadingButton.hideLoading();
                                startActivity(new Intent(ActivityLogin.this, ActivityHalamanUtama.class));
                            } else if (response.body().getStatus().equals("1")) {
                                loadingButton.hideLoading();
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    loadingButton.hideLoading();
                    Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}
