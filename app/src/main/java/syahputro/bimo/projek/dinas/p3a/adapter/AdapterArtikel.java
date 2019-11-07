package syahputro.bimo.projek.dinas.p3a.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.model_temp.DataArtikel;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.Holder> {
    public List<DataArtikel> list;
    public Context context;

    public AdapterArtikel(List<DataArtikel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterArtikel.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_halaman_utama_artikel_top, parent, false);

        return new AdapterArtikel.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArtikel.Holder holder, int position) {
        DataArtikel data = list.get(position);
        holder.tv_halaman_utama_artikel_top.setText(data.getJudul());
        Glide.with(context).
                load(data.getImage()).
                into(holder.iv_halaman_utama_artikel_top);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView iv_halaman_utama_artikel_top;
        public TextView tv_halaman_utama_artikel_top;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_halaman_utama_artikel_top = itemView.findViewById(R.id.iv_halaman_utama_artikel_top);
            tv_halaman_utama_artikel_top = itemView.findViewById(R.id.tv_halaman_utama_artikel_top);
        }
    }
}
