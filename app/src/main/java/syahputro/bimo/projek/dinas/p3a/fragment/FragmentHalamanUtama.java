package syahputro.bimo.projek.dinas.p3a.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikel;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterArtikel2;
import syahputro.bimo.projek.dinas.p3a.model_temp.DataArtikel;
import syahputro.bimo.projek.dinas.p3a.utils.SnapHelperOneByOne;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHalamanUtama extends Fragment {
    public List<DataArtikel> list;
    private RecyclerView recyclerView_top, recyclerView_mid;
    private AdapterArtikel adapter_top;
    private AdapterArtikel2 adapter_mid;

    public FragmentHalamanUtama() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Halaman Utama");
        return inflater.inflate(R.layout.fragment_halaman_utama, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        list = new ArrayList<>();

        recyclerView_top = view.findViewById(R.id.rv_halaman_utama_artikel_top);
        recyclerView_mid = view.findViewById(R.id.rv_halaman_utama_artikel_mid);

        addData();
        //artikel atas
        adapter_top = new AdapterArtikel(list, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView_top.setLayoutManager(mLayoutManager);
        recyclerView_top.setItemAnimator(new DefaultItemAnimator());
        recyclerView_top.setAdapter(adapter_top);
        linearSnapHelper.attachToRecyclerView(recyclerView_top);

        //artikel tengah
        adapter_mid = new AdapterArtikel2(list, getContext());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView_mid.setLayoutManager(mLayoutManager2);
        recyclerView_mid.setItemAnimator(new DefaultItemAnimator());
        recyclerView_mid.setAdapter(adapter_mid);

        recyclerView_top.setNestedScrollingEnabled(false);
        recyclerView_mid.setNestedScrollingEnabled(false);
    }

    private void addData() {
        list.add(new DataArtikel(R.drawable.artikel2, "Judul A"));
        list.add(new DataArtikel(R.drawable.artikel2, "Judul B"));
        list.add(new DataArtikel(R.drawable.artikel2, "Judul C"));
        list.add(new DataArtikel(R.drawable.artikel2, "Judul D"));
    }
}
