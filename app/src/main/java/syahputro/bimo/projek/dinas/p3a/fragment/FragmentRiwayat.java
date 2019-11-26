package syahputro.bimo.projek.dinas.p3a.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterRiwayat;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.riwayat.Data;
import syahputro.bimo.projek.dinas.p3a.network.response.riwayat.ResponseRiwayat;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;

public class FragmentRiwayat extends Fragment {
    private List<Data> list;
    private RecyclerView recyclerView;
    private AdapterRiwayat adapter;
    private ApiService service;
    private View view;

    public FragmentRiwayat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Riwayat Pengaduan");
        view = inflater.inflate(R.layout.fragment_riwayat, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        loadData();
    }

    private void loadData() {
        Call<ResponseRiwayat> riwayat = service.riwayat(Integer.parseInt(Preference.getIdUser(getActivity())));
        riwayat.enqueue(new Callback<ResponseRiwayat>() {
            @Override
            public void onResponse(Call<ResponseRiwayat> call, Response<ResponseRiwayat> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("0")) {
                            List<Data> data_kategori = response.body().getData();
                            Log.d("Retrofit Get", "Jumlah data Kontak: " +
                                    String.valueOf(data_kategori.size()));

                            adapter = new AdapterRiwayat(data_kategori, getContext());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRiwayat> call, Throwable t) {
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        recyclerView = view.findViewById(R.id.rvRiwayat);
        service = ApiClient.getClient().create(ApiService.class);
        list = new ArrayList<>();
    }
}
