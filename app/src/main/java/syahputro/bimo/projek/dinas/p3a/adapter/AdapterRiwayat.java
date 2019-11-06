package syahputro.bimo.projek.dinas.p3a.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.model_temp.DataRiwayat;

public class AdapterRiwayat extends RecyclerView.Adapter<AdapterRiwayat.Holder>{
    public List<DataRiwayat> list;
    public Context context;

    public AdapterRiwayat(List<DataRiwayat> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRiwayat.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_riwayat_pengaduan, parent, false);

        return new AdapterRiwayat.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRiwayat.Holder holder, int position) {
        DataRiwayat data = list.get(position);
        holder.tvTanggal.setText(data.getTanggal());
        String status = "";
        String color = context.getResources().getString(R.string.color_belum_direspon);

        switch (data.getId_status()) {
            case "0":
                status = context.getResources().getString(R.string.belum_direspon);
                color = context.getResources().getString(R.string.color_belum_direspon);
                break;
            case "1":
                status = context.getResources().getString(R.string.sudah_teratasi);
                color = context.getResources().getString(R.string.color_sudah_teratasi);
                break;
            case "2":
                status = context.getResources().getString(R.string.tidak_teratasi);
                color = context.getResources().getString(R.string.color_tidak_teratasi);
                break;
            case "3":
                status = context.getResources().getString(R.string.tidak_bisa_dihubungi);
                color = context.getResources().getString(R.string.color_tidak_bisa_dihubungi);
                break;
        }

        holder.tvStatus.setText(status);
        holder.tvStatus.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public TextView tvTanggal,tvStatus;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
