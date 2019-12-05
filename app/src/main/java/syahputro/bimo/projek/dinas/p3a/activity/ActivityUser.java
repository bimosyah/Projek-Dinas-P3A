package syahputro.bimo.projek.dinas.p3a.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.user.DataUser;
import syahputro.bimo.projek.dinas.p3a.network.response.user.ResponseUser;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;

public class ActivityUser extends AppCompatActivity {
    ApiService service;
    TextView nama1, nama2, notelp, alamat, tgl_lahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        init();
        loadData();
    }

    private void init() {
        service = ApiClient.getClient().create(ApiService.class);
        nama1 = findViewById(R.id.tvNama1);
        nama2 = findViewById(R.id.tvNama2);
        notelp = findViewById(R.id.tvNoTelp);
        alamat = findViewById(R.id.tvAlamat);
        tgl_lahir = findViewById(R.id.tvTanggalLahir);
    }

    private void loadData() {
        Call<ResponseUser> artikel = service.user(Integer.parseInt(Preference.getIdUser(getApplicationContext())));
        artikel.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<DataUser> user = response.body().getData();
                        nama1.setText(user.get(0).getNama());
                        nama2.setText(user.get(0).getNama());
                        notelp.setText(user.get(0).getNomorTelp());
                        alamat.setText(user.get(0).getAlamat());
                        tgl_lahir.setText(user.get(0).getTanggalLahir());
//                        for (DataUser n : user){
//                            nama1.setText(n.getNama());
//                            nama2.setText(n.getNama());
//                            notelp.setText(n.getNomorTelp());
//                            alamat.setText(n.getAlamat());
//                            tgl_lahir.setText(n.getTanggalLahir());
//                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
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
