package syahputro.bimo.projek.dinas.p3a.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kusu.loadingbutton.LoadingButton;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.register.ResponseRegister;

public class ActivityRegister extends AppCompatActivity {
    Button btn_register;
    String longitude = "", latitude = "";
    EditText etNama, etPassword, etNoTelp, etAlamat;
    EditText etTanggalLahir;
    ApiService service;
    LoadingButton loadingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        service = ApiClient.getClient().create(ApiService.class);

        etTanggalLahir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            String tanggal = year + "/" + (month + 1) + "/" + dayOfMonth;
                            etTanggalLahir.setText(tanggal);
                        }
                    };

                    // Get current year, month and day.
                    Calendar now = Calendar.getInstance();
                    int year = now.get(java.util.Calendar.YEAR);
                    int month = now.get(java.util.Calendar.MONTH);
                    int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                    // Create the new DatePickerDialog instance.
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityRegister.this, android.R.style.Theme_Holo_Dialog, onDateSetListener, year, month, day);

                    // Set dialog icon and title.
                    datePickerDialog.setTitle("Please select date.");

                    // Popup the dialog.
                    datePickerDialog.show();
                }
            }
        });
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingButton.showLoading();
                String nama = etNama.getText().toString();
                String password = etPassword.getText().toString();
                String notelp = etNoTelp.getText().toString();
                String alamat = etAlamat.getText().toString();
                String tglLahir = etTanggalLahir.getText().toString();
                if (nama.equals("") || password.equals("") || notelp.equals("") || alamat.equals("") || tglLahir.equals("")) {
                    Toast.makeText(getApplicationContext(), "Semua form harus diisi", Toast.LENGTH_LONG).show();
                    loadingButton.hideLoading();
                } else {
                    Call<ResponseRegister> registrasi = service.register(
                            nama,
                            password,
                            notelp,
                            alamat,
                            tglLahir,
                            latitude,
                            longitude
                    );
                    registrasi.enqueue(new Callback<ResponseRegister>() {
                        @Override
                        public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                            if (response.isSuccessful()) { // check response suksess or no
                                if (response.body() != null) {
                                    loadingButton.hideLoading();
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                    if (!response.body().getStatus().equals("1")) {
                                        startActivity(new Intent(ActivityRegister.this, ActivityLogin.class));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseRegister> call, Throwable t) {
                            loadingButton.hideLoading();
                            Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private void init() {
        btn_register = findViewById(R.id.btn_register);
        etAlamat = findViewById(R.id.etAlamat);
        etNama = findViewById(R.id.etNama);
        etNoTelp = findViewById(R.id.etNoTelp);
        etPassword = findViewById(R.id.etPassword);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        loadingButton = findViewById(R.id.loadingButton);

    }
}
