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
import syahputro.bimo.projek.dinas.p3a.model.ArticleItemDetail;

public class AdapterArtikelVertical extends RecyclerView.Adapter<AdapterArtikelVertical.Holder> {
    private List<ArticleItemDetail> list;
    private Context context;

    public AdapterArtikelVertical(List<ArticleItemDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterArtikelVertical.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_halaman_utama_artikel_vertical, parent, false);
        return new AdapterArtikelVertical.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArtikelVertical.Holder holder, int position) {
        ArticleItemDetail itemDetail = list.get(position);
        Glide.with(context)
                .load(itemDetail.getImage())
                .into(holder.imageView);
        holder.tvTitle.setText(itemDetail.getTitle());
        holder.tvTanggal.setText(itemDetail.getDate());
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

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvTanggal;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_halaman_utama_artikel_mid);
            tvTitle = itemView.findViewById(R.id.tv_halaman_utama_artikel_mid_judul);
            tvTanggal = itemView.findViewById(R.id.tv_halaman_utama_artikel_mid_tanggal);
        }
    }
}
