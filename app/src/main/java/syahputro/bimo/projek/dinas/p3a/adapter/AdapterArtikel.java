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
import syahputro.bimo.projek.dinas.p3a.network.response.artikel.list_slider.Data;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.Holder> {
    public List<Data> list;
    public Context context;

    public AdapterArtikel(List<Data> list, Context context) {
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
        Data data = list.get(position);
        holder.tv_halaman_utama_artikel_top.setText(data.getTitle());
        Glide.with(context).
                load(data.getImage()).
                into(holder.iv_halaman_utama_artikel_top);
    }

    @Override
    public int getItemCount() {
        int limit_data = 4;
        if (list.size() > limit_data) {
            return limit_data;
        } else {
            return list.size();
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView iv_halaman_utama_artikel_top;
        TextView tv_halaman_utama_artikel_top;

        Holder(@NonNull View itemView) {
            super(itemView);
            iv_halaman_utama_artikel_top = itemView.findViewById(R.id.iv_halaman_utama_artikel_top);
            tv_halaman_utama_artikel_top = itemView.findViewById(R.id.tv_halaman_utama_artikel_top);
        }
    }
}
