package syahputro.bimo.projek.dinas.p3a.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.network.ApiClient;
import syahputro.bimo.projek.dinas.p3a.network.ApiService;
import syahputro.bimo.projek.dinas.p3a.network.response.login.ResponseLogin;
import syahputro.bimo.projek.dinas.p3a.network.response.user.ResponseUpdate;
import syahputro.bimo.projek.dinas.p3a.utils.Preference;

public class DialogPassword extends AppCompatDialogFragment {
    EditText etPassword1,etPassword2;
    ApiService service;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_change_password, null);

        etPassword1 = view.findViewById(R.id.etPassword);
        etPassword2 = view.findViewById(R.id.etPasswordRetype);
        service = ApiClient.getClient().create(ApiService.class);

        builder.setView(view)
                .setTitle("Ubah Password")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String password1 = etPassword1.getText().toString();
                        String password2 = etPassword2.getText().toString();
                        if (password1.equals(password2)){
                            update_password(password1);
                        }else {
                            Toast.makeText(getContext(), "Password tidak cocok ", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        return builder.create();
    }

    private void update_password(String pass) {
        Call<ResponseUpdate> update = service.update_password(Integer.parseInt(Preference.getIdUser(getActivity())),pass);
        update.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 0){
                            Toast.makeText(getContext(), "Password berhasil diubah", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
