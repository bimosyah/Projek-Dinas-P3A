package syahputro.bimo.projek.dinas.p3a.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
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

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.Holder> {
    public List<ArticleItemDetail> list;
    public Context context;

    public AdapterHome(List<ArticleItemDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterHome.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_halaman_utama_artikel_vertical, parent, false);
        return new AdapterHome.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHome.Holder holder, int position) {
        final ArticleItemDetail itemDetail = list.get(position);
        Glide.with(context)
                .load(itemDetail.getImage())
                .into(holder.imageView);

        holder.tvTitle.setText(itemDetail.getTitle());
        holder.tvTanggal.setText(itemDetail.getDate());
        holder.tvContent.setText(Html.fromHtml(itemDetail.getContent()).toString());

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
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
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
