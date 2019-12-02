package syahputro.bimo.projek.dinas.p3a.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.profile_dinas.ResponseProfileDinas;

public class FragmentProfileDinas extends Fragment {
    private TextView tvCall, tvKonsul, tvNamaDinas, tvAlamat;
    private ImageView imageView;
    private View view;
    private String no_wa, msg, telp, alamat, nama_dinas, logo;
    private ApiService service;

    public FragmentProfileDinas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Pengguna");
        view = inflater.inflate(R.layout.fragment_pengguna, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        loadDataDinas();

        tvKonsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + no_wa + "&text=" + msg)));
                } catch (Exception e) {
                }
            }
        });

        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL); // Action for what intent called for
                intent.setData(Uri.parse("tel: " + telp)); // DataDetailArtikel with intent respective action on intent
                startActivity(intent);
            }
        });
    }

    private void loadDataDinas() {
        Call<ResponseProfileDinas> riwayat = service.dinas();
        riwayat.enqueue(new Callback<ResponseProfileDinas>() {
            @Override
            public void onResponse(Call<ResponseProfileDinas> call, Response<ResponseProfileDinas> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("no_wa", "onResponse: " + response.body().getData().getNoWa());

                        no_wa = response.body().getData().getNoWa();
                        msg = response.body().getData().getTemplateWa();
                        telp = response.body().getData().getNoTelp();
                        alamat = response.body().getData().getAlamat();
                        nama_dinas = response.body().getData().getNamaDinas();
                        logo = response.body().getData().getLogo();

                        Glide.with(getActivity())
                                .load(logo)
                                .into(imageView);
                        tvNamaDinas.setText(nama_dinas);
                        tvAlamat.setText(alamat);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseProfileDinas> call, Throwable t) {
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        tvCall = view.findViewById(R.id.tvCall);
        tvKonsul = view.findViewById(R.id.tvKonsul);
        imageView = view.findViewById(R.id.ivDinas);
        tvNamaDinas = view.findViewById(R.id.textView9);
        tvAlamat = view.findViewById(R.id.textView12);
        service = ApiClient.getClient().create(ApiService.class);
    }
}
