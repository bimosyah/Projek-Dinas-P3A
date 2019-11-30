package syahputro.bimo.projek.dinas.p3a.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikel;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikel2;
import syahputro.bimo.projek.dinas.p3a.model_temp.DataArtikel;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider.Data;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider.ResponseSlider;
import syahputro.bimo.projek.dinas.p3a.utils.SnapHelperOneByOne;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHalamanUtama extends Fragment {
    public List<DataArtikel> list;
    private RecyclerView recyclerView_top, recyclerView_mid;
    private AdapterArtikel adapter_top;
    private AdapterArtikel2 adapter_mid;
    private ApiService service;
    private View view;
    public FragmentHalamanUtama() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Halaman Utama");
        view = inflater.inflate(R.layout.fragment_halaman_utama, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        list = new ArrayList<>();

        init();

        addData();
        loadData();
//        //artikel atas
//        adapter_top = new AdapterArtikel(list, getContext());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
//                LinearLayoutManager.HORIZONTAL, false);
//        recyclerView_top.setLayoutManager(mLayoutManager);
//        recyclerView_top.setItemAnimator(new DefaultItemAnimator());
//        recyclerView_top.setAdapter(adapter_top);


        //artikel tengah
        adapter_mid = new AdapterArtikel2(list, getContext());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView_mid.setLayoutManager(mLayoutManager2);
        recyclerView_mid.setItemAnimator(new DefaultItemAnimator());
        recyclerView_mid.setAdapter(adapter_mid);


        linearSnapHelper.attachToRecyclerView(recyclerView_top);

        recyclerView_top.setNestedScrollingEnabled(false);
        recyclerView_top.setHasFixedSize(false);

        recyclerView_mid.setNestedScrollingEnabled(false);
        recyclerView_mid.setHasFixedSize(false);
    }

    private void addData() {
        list.add(new DataArtikel(R.drawable.artikel2, "Judul A"));
        list.add(new DataArtikel(R.drawable.artikel2, "Judul B"));
        list.add(new DataArtikel(R.drawable.artikel2, "Judul C"));
        list.add(new DataArtikel(R.drawable.artikel2, "Judul D"));
    }

    private void loadData() {
        Call<ResponseSlider> riwayat = service.slider();
        riwayat.enqueue(new Callback<ResponseSlider>() {
            @Override
            public void onResponse(Call<ResponseSlider> call, Response<ResponseSlider> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Data> data = response.body().getArticles();
                        adapter_top = new AdapterArtikel(data, getContext());
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                                LinearLayoutManager.HORIZONTAL, false);
                        recyclerView_top.setLayoutManager(mLayoutManager);
                        recyclerView_top.setItemAnimator(new DefaultItemAnimator());
                        recyclerView_top.setAdapter(adapter_top);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSlider> call, Throwable t) {
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        recyclerView_top = view.findViewById(R.id.rv_halaman_utama_artikel_top);
        recyclerView_mid = view.findViewById(R.id.rv_halaman_utama_artikel_mid);
        service = ApiClient.getClient().create(ApiService.class);
    }
}
