package syahputro.bimo.projek.dinas.p3a.fragment;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

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

public class FragmentPengaduan extends Fragment {

    private ApiService service;
    private Button btn_submit;
    private Spinner spinner_kategori;
    private View view;
    private final ArrayList<String> array_kategori = new ArrayList<String>();
    private final ArrayList<String> array_id_kategori = new ArrayList<String>();
    private EditText et_pengaduan;
    private LocationManager locationManager;
    private String longitude, latitude;
    private static final int REQUEST_LOCATION = 1;
    private LoadingButton loadingButton;
    private int id_kategori;

    public FragmentPengaduan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Pengaduan");
        view = inflater.inflate(R.layout.fragment_pengaduan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        service = ApiClient.getClient().create(ApiService.class);
        spinner_kategori = view.findViewById(R.id.spinnerKategori);
        btn_submit = view.findViewById(R.id.buttonSubmit);
        et_pengaduan = view.findViewById(R.id.etPengaduan);
        loadingButton = view.findViewById(R.id.loadingButton);
    }

    private void savePengaduan() {
        String id_user = Preference.getIdUser(getActivity());
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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
                            Toast.makeText(getActivity(), "DataDetailArtikel Tersimpan", Toast.LENGTH_LONG).show();
                        } else if (response.body().getStatus().equals("1")) {
                            loadingButton.hideLoading();
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePengaduan> call, Throwable t) {
                loadingButton.hideLoading();
                Toast.makeText(getActivity(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, array_kategori);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_kategori.setAdapter(adapter);
    }

    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
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
