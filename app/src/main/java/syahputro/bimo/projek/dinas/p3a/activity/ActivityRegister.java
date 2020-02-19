package syahputro.bimo.projek.dinas.p3a.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.kusu.loadingbutton.LoadingButton;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.register.ResponseRegister;

public class ActivityRegister extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    Button btn_register;
    LocationManager locationManager;
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

        //add permission
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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
                gpsCheck();

                if (!latitude.equals("") && !longitude.equals("")){
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
                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            onGPS();
                        } else {
                            getLocation();
                        }

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
                }else {
                    gpsCheck();
                    loadingButton.hideLoading();
                }

            }
        });
    }

    private void gpsCheck() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (!Objects.requireNonNull(locationManager).isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onGPS();
        } else {
            getLocation();
        }
    }

    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    private void getLocation() {
        //check permission again
        if (ActivityCompat.checkSelfPermission(ActivityRegister.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityRegister.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location LocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps != null) {
                double lat = LocationGps.getLatitude();
                double longi = LocationGps.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
            } else if (LocationNetwork != null) {
                double lat = LocationNetwork.getLatitude();
                double longi = LocationNetwork.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
            } else if (LocationPassive != null) {
                double lat = LocationPassive.getLatitude();
                double longi = LocationPassive.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
            } else {
                Log.d("LOCATION", "getLocation: error");
            }
        }
    }
}
