package syahputro.bimo.projek.dinas.p3a.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import syahputro.bimo.projek.dinas.p3a.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStatistik extends Fragment {


    public FragmentStatistik() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistik, container, false);
    }

}
