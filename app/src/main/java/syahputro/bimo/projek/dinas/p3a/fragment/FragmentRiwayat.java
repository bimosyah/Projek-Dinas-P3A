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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.adapter.AdapterRiwayat;
import syahputro.bimo.projek.dinas.p3a.model_temp.DataRiwayat;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRiwayat extends Fragment {
    public List<DataRiwayat> list;
    private RecyclerView recyclerView;
    private AdapterRiwayat adapter;

    public FragmentRiwayat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Riwayat Pengaduan");
        return inflater.inflate(R.layout.fragment_riwayat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rvRiwayat);
        addData();
        adapter = new AdapterRiwayat(list, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void addData() {
        list.add(new DataRiwayat("2019-10-10", "0"));
        list.add(new DataRiwayat("2019-10-11", "1"));
        list.add(new DataRiwayat("2019-10-12", "2"));
        list.add(new DataRiwayat("2019-10-13", "3"));
    }
}
