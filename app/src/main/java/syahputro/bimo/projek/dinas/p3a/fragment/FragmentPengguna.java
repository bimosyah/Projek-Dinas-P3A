package syahputro.bimo.projek.dinas.p3a.fragment;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import syahputro.bimo.projek.dinas.p3a.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPengguna extends Fragment {
    TextView tvCall, tvKonsul;

    public FragmentPengguna() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Pengguna");
        return inflater.inflate(R.layout.fragment_pengguna, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCall = view.findViewById(R.id.tvCall);
        tvKonsul = view.findViewById(R.id.tvKonsul);

        tvKonsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String mobile = "+6281233045596";
                    String msg = "Its Working";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + msg)));
                }catch (Exception e){
                    //whatsapp app not install
                }
            }
        });

        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNumber = "081233045596";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL); // Action for what intent called for
                intent.setData(Uri.parse("tel: " + mobileNumber)); // Data with intent respective action on intent
                startActivity(intent);
            }
        });
    }
}
