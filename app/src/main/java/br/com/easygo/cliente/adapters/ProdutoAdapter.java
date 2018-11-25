package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.objects.ProdutoAdapterObject;
import br.com.easygo.cliente.model.Produto;

public class ProdutoAdapter  extends RecyclerView.Adapter{


    private final ProdutoDetalheAdapter.OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final List<ProdutoAdapterObject> produtos;

    public ProdutoAdapter(Context context, List<ProdutoAdapterObject> produtos, ProdutoDetalheAdapter.OnItemClickListener onClick) {
        this.context = context;
        this.produtos = produtos;
        this.listener = onClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            return new ProdutoEmptyViewHolder(parent);
        }

        return new ProdutoViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProdutoViewHolder) {
            final ProdutoViewHolder viewHolder = (ProdutoViewHolder) holder;
            final ProdutoAdapterObject item = produtos.get(position);

            viewHolder.produtoTipo.setText(item.getTipo());

            viewHolder.produtoLista.setLayoutManager(new LinearLayoutManager(context));
            ProdutoDetalheAdapter adapter = new ProdutoDetalheAdapter(context, item.getProdutos(), listener);
            viewHolder.produtoLista.setAdapter(adapter);

            if(item.isExpaded()){
                viewHolder.produtoLista.setVisibility(View.VISIBLE);
            }else{
                viewHolder.produtoLista.setVisibility(View.GONE);
            }

            viewHolder.produtoExpandirTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i=0;i<produtos.size();i++){
                        ProdutoAdapterObject prd = produtos.get(i);
                        if(item == prd){
                            prd.setExpaded(!item.isExpaded());
                            notifyItemChanged(i);
                        }else if(prd.isExpaded()){
                            prd.setExpaded(false);
                            notifyItemChanged(i);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return produtos != null && produtos.size() > 0 ? produtos.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (produtos == null || produtos.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public void remove(int position) {
        ProdutoAdapterObject item = produtos.get(position);
        if (produtos.contains(item)) {
            produtos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ProdutoAdapterObject getItemByPosition(int position){
        return produtos == null ? null : produtos.get(position);
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView produtoTipo;
        RecyclerView produtoLista;
        ImageView produtoExpandir;
        LinearLayout produtoExpandirTitle;

        public ProdutoViewHolder(ViewGroup parent) {
            //super(LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_row_list_eventos, parent, false));
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_produto, parent, false));
            produtoTipo = itemView.findViewById(R.id.txt_produto_tipo);
            produtoLista = itemView.findViewById(R.id.rv_produtos_detalhe);
            produtoExpandir = itemView.findViewById(R.id.btn_produto_expandir);
            produtoExpandirTitle = itemView.findViewById(R.id.btn_produto_expandir_title);
        }
    }

    public class ProdutoEmptyViewHolder extends RecyclerView.ViewHolder {
        public ProdutoEmptyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_mesa, parent, false));

        }
    }
}
