package syahputro.bimo.projek.dinas.p3a.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.kategori.Data;
import syahputro.bimo.projek.dinas.p3a.network.response.kategori.ResponseKategori;

public class FragmentPengaduan extends Fragment {

    ApiService service;

    public FragmentPengaduan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Pengaduan");
        return inflater.inflate(R.layout.fragment_pengaduan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        service = ApiClient.getClient().create(ApiService.class);
        final ArrayList<String> tes = new ArrayList<String>();

        Call<ResponseKategori> kategori = service.kategori();
        kategori.enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("0")) {
                            List<Data> data_kategori = response.body().getData();
                            for (Data data : data_kategori) {
                                tes.add(data.getNamaKategori());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        String[] arraySpinner = new String[]{
                "Pilih Kategori", "KDRT", "ANAK", "WANITA"
        };

        Spinner s = view.findViewById(R.id.spinnerKategori);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, tes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }
}
