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
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikelBanner;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikelMid;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItem;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItemDetail;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_artikel.ResponseArtikel;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_berita.ResponseBerita;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_kegiatan.ResponseKegiatan;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider.DataSlider;
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider.ResponseSlider;
import syahputro.bimo.projek.dinas.p3a.utils.SnapHelperOneByOne;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHalamanUtama extends Fragment {
    private RecyclerView recyclerView_top, recyclerView_mid;
    private AdapterArtikelBanner adapter_top;
    private List<ArticleItemDetail> beritaList = new ArrayList<>();
    private List<ArticleItemDetail> artikelList = new ArrayList<>();
    private List<ArticleItemDetail> kegiatanList = new ArrayList<>();
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
        init();

        //artikel banner
        loadDataBanner();
        linearSnapHelper.attachToRecyclerView(recyclerView_top);
        recyclerView_top.setNestedScrollingEnabled(false);
        recyclerView_top.setHasFixedSize(false);

        //artikel middle
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        AdapterArtikelMid adapterArtikelMid = new AdapterArtikelMid(articleList(), getActivity());
        recyclerView_mid.setAdapter(adapterArtikelMid);
        recyclerView_mid.setLayoutManager(layoutManager);
        Log.d("artikel", "artikel: " + loadArtikel());
    }

    private void loadDataBanner() {
        Call<ResponseSlider> riwayat = service.slider();
        riwayat.enqueue(new Callback<ResponseSlider>() {
            @Override
            public void onResponse(Call<ResponseSlider> call, Response<ResponseSlider> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<DataSlider> data = response.body().getArticles();
                        adapter_top = new AdapterArtikelBanner(data, getContext());
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

    private List<ArticleItem> articleList() {
        List<ArticleItem> itemList = new ArrayList<>();
        ArticleItem item = new ArticleItem("Berita", loadBerita());
        itemList.add(item);
        item = new ArticleItem("Artikel", loadArtikel());
        itemList.add(item);
        item = new ArticleItem("Kegiatan", loadKegiatan());
        itemList.add(item);
        return itemList;
    }

    private List<ArticleItemDetail> loadBerita() {
        Call<ResponseBerita> berita = service.berita(4);
        berita.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        beritaList = response.body().getArticles();
                        Log.d("berita", "berita error: " + beritaList);
                    }else {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return beritaList;
    }

    private List<ArticleItemDetail> loadArtikel() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(loggingInterceptor);

        Call<ResponseArtikel> berita = service.artikel(4);
        berita.enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        artikelList = response.body().getArticles();
                        Log.d("artikel", "artikel error: " + beritaList);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseArtikel> call, Throwable t) {
                Log.d("artikel", "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return artikelList;
    }

    private List<ArticleItemDetail> loadKegiatan() {
        Call<ResponseKegiatan> berita = service.kegiatan(4);
        berita.enqueue(new Callback<ResponseKegiatan>() {
            @Override
            public void onResponse(Call<ResponseKegiatan> call, Response<ResponseKegiatan> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        kegiatanList = response.body().getArticles();
                    }else {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKegiatan> call, Throwable t) {
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return kegiatanList;
    }


    private void init() {
        recyclerView_top = view.findViewById(R.id.rv_halaman_utama_artikel_top);
        recyclerView_mid = view.findViewById(R.id.rv_halaman_utama_artikel_mid);
        service = ApiClient.getClient().create(ApiService.class);
    }
}
