package syahputro.bimo.projek.dinas.p3a.activity.layout_baru;

import android.Manifest;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.kusu.loadingbutton.LoadingButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.kategori.DataKategori;
import syahputro.bimo.projek.dinas.p3a.network.response.kategori.ResponseKategori;
import syahputro.bimo.projek.dinas.p3a.network.response.pengaduan.ResponsePengaduan;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;

public class ActivityPengaduan extends AppCompatActivity {
    private ApiService service;
    private Button btn_submit;
    private Spinner spinner_kategori;
    private final ArrayList<String> array_kategori = new ArrayList<String>();
    private final ArrayList<String> array_id_kategori = new ArrayList<String>();
    private EditText et_pengaduan;
    private LocationManager locationManager;
    private String longitude, latitude;
    private static final int REQUEST_LOCATION = 1;
    private LoadingButton loadingButton;
    private int id_kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaduan");

        init();
        loadKategori();

        spinner_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_kategori = Integer.parseInt(array_id_kategori.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingButton.showLoading();
                savePengaduan();
            }
        });

    }

    private void loadKategori() {
        Call<ResponseKategori> kategori = service.kategori();
        kategori.enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("0")) {
                            List<DataKategori> data_Kategori_kategori = response.body().getData();
                            for (DataKategori dataKategori : data_Kategori_kategori) {
                                array_id_kategori.add(dataKategori.getIdKategori());
                                array_kategori.add(dataKategori.getNamaKategori());
                            }
                            setSpinner();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        service = ApiClient.getClient().create(ApiService.class);
        spinner_kategori = findViewById(R.id.spinnerKategori);
        btn_submit = findViewById(R.id.buttonSubmit);
        et_pengaduan = findViewById(R.id.etPengaduan);
        loadingButton = findViewById(R.id.loadingButton);
    }

    private void savePengaduan() {
        String id_user = Preference.getIdUser(getApplicationContext());
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onGPS();
        } else {
            getLocation();
        }

        //id kategori belum dinamis
        Call<ResponsePengaduan> pengaduan = service.pengaduan(Integer.parseInt(id_user), id_kategori,
                et_pengaduan.getText().toString(), latitude, longitude);
        pengaduan.enqueue(new Callback<ResponsePengaduan>() {
            @Override
            public void onResponse(Call<ResponsePengaduan> call, Response<ResponsePengaduan> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("0")) {
                            loadingButton.hideLoading();
                            Toast.makeText(getApplicationContext(), "DataDetailArtikel Tersimpan", Toast.LENGTH_LONG).show();
                        } else if (response.body().getStatus().equals("1")) {
                            loadingButton.hideLoading();
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePengaduan> call, Throwable t) {
                loadingButton.hideLoading();
                Toast.makeText(getApplicationContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, array_kategori);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_kategori.setAdapter(adapter);
    }

    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
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

    private void getLocation() {
        //check permission again
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
