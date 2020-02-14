package syahputro.bimo.projek.dinas.p3a.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.pengaduan.ResponsePengaduan;
import syahputro.bimo.projek.dinas.p3a.network.response.user.DataUser;
import syahputro.bimo.projek.dinas.p3a.network.response.user.ResponseUpdate;
import syahputro.bimo.projek.dinas.p3a.network.response.user.ResponseUser;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;

public class ActivityUser extends AppCompatActivity implements DialogPassword.DialogListener {
    ApiService service;
    TextView nama1, nama2, notelp, alamat, tgl_lahir;
    Button gantiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        init();
        loadData();
        gantiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog2();
            }
        });
    }

    private void init() {
        gantiPassword = findViewById(R.id.btn_ganti_password);
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

    public void openDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityUser.this);
        View view = getLayoutInflater().inflate(R.layout.layout_change_password, null);
        final EditText edtpassword1 = view.findViewById(R.id.etPassword);
        final EditText edtpassword2 = view.findViewById(R.id.etPasswordRetype);
        builder.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtpassword1.getText().toString().isEmpty() || edtpassword2.getText().toString().isEmpty()){
                    Toast.makeText(ActivityUser.this, "Password kosong", Toast.LENGTH_LONG).show();
                }else {
                    if (edtpassword1.getText().toString().matches(edtpassword2.getText().toString())){
                        update_password(edtpassword1.getText().toString());
                        dialog.dismiss();
                    }else {
                        Toast.makeText(ActivityUser.this, "Password salah", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void openDialog() {
        DialogPassword dialogPassword = new DialogPassword();
        dialogPassword.show(getSupportFragmentManager(), "dialog password");
    }

    private void update_password(String pass) {
        String id_user = Preference.getIdUser(getApplicationContext());
        service = ApiClient.getClient().create(ApiService.class);
        Call<ResponseUpdate> pengaduan = service.update_password(Integer.parseInt(id_user),pass);
        pengaduan.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 0) {
                            Toast.makeText(getApplicationContext(), "Berhasil di update", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getPassword(String password) {
        update_password(password);
        Toast.makeText(getApplicationContext(), password, Toast.LENGTH_LONG).show();
    }
}
