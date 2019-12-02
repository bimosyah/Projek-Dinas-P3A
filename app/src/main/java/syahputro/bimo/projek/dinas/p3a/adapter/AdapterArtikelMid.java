package syahputro.bimo.projek.dinas.p3a.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import syahputro.bimo.projek.dinas.p3a.R;
import syahputro.bimo.projek.dinas.p3a.activity.ActivityArticle;
import syahputro.bimo.projek.dinas.p3a.model.ArticleItem;

public class AdapterArtikelMid extends RecyclerView.Adapter<AdapterArtikelMid.Holder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    public List<ArticleItem> list;
    public Context context;

    public AdapterArtikelMid(List<ArticleItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterArtikelMid.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_halaman_utama_artikel_middle, parent, false);

        return new AdapterArtikelMid.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArtikelMid.Holder holder, int position) {
        final ArticleItem item = list.get(position);
        holder.tvArticleTitle.setText(item.getItemTitle());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rv.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(item.getDetailList().size());

        AdapterArtikel artikelVertical = new AdapterArtikel(item.getDetailList(), context);
        holder.rv.setLayoutManager(layoutManager);
        holder.rv.setAdapter(artikelVertical);
        holder.rv.setRecycledViewPool(viewPool);

        holder.tvArticleTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityArticle.class);
                intent.putExtra("jenis",item.getItemTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvArticleTitle;
        private RecyclerView rv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvArticleTitle = itemView.findViewById(R.id.tvArticleTitle);
            rv = itemView.findViewById(R.id.rvArtikel);
        }
    }
}
