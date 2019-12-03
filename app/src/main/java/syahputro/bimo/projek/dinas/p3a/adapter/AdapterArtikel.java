package syahputro.bimo.projek.dinas.p3a.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.activity.ActivityArticleDetail;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItemDetail;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.Holder> {
    private List<ArticleItemDetail> list;
    private Context context;
    private int jumlah_tampil = 4;

    public AdapterArtikel(List<ArticleItemDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public AdapterArtikel(List<ArticleItemDetail> list, Context context, int jumlah_tampil) {
        this.list = list;
        this.context = context;
        this.jumlah_tampil = jumlah_tampil;
    }

    @NonNull
    @Override
    public AdapterArtikel.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_halaman_utama_artikel_vertical, parent, false);
        return new AdapterArtikel.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArtikel.Holder holder, int position) {
        final ArticleItemDetail itemDetail = list.get(position);
        for (ArticleItemDetail item1 : list) {
            Log.d("LIST", "onBindViewHolder: list" + item1.getTitle());
        }
        Glide.with(context)
                .load(itemDetail.getImage())
                .into(holder.imageView);

        holder.tvTitle.setText(itemDetail.getTitle());
        holder.tvTanggal.setText(itemDetail.getDate());
        holder.tvContent.setText(itemDetail.getContent());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityArticleDetail.class);
                intent.putExtra("id_artikel", itemDetail.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
//        if (list.size() > jumlah_tampil) {
//            return jumlah_tampil;
//        } else {
//            return list.size();
//        }
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvTanggal, tvContent;
        ConstraintLayout layout;
        LinearLayout linearLayout;

        Holder(@NonNull View itemView) {
            super(itemView);
//            layout = itemView.findViewById(R.id.layoutArtikel);
            linearLayout = itemView.findViewById(R.id.linearlayout);
            imageView = itemView.findViewById(R.id.iv_halaman_utama_artikel_mid);
            tvTitle = itemView.findViewById(R.id.tv_halaman_utama_artikel_mid_judul);
            tvTanggal = itemView.findViewById(R.id.tv_halaman_utama_artikel_mid_tanggal);
            tvContent = itemView.findViewById(R.id.tv_halaman_utama_artikel_mid_content);
        }
    }
}
