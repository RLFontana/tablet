package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.model.Produto;

public class ProdutoDetalheAdapter extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(Produto item);
    }

    private final OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final List<Produto> produtos;

    public ProdutoDetalheAdapter(Context context, List<Produto> produtos, OnItemClickListener onClick) {
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
            final Produto item = produtos.get(position);

            viewHolder.produtoName.setText(item.getNome());
            viewHolder.produtoValor.setText("R$ " + String.format("%.2f", item.getPrecoDouble()));
            if(listener != null) {
                viewHolder.addProduto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(item);
                    }
                });
            }
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
        Produto item = produtos.get(position);
        if (produtos.contains(item)) {
            produtos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Produto getItemByPosition(int position){
        return produtos == null ? null : produtos.get(position);
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView produtoName;
        TextView produtoValor;
        ImageView addProduto;

        public ProdutoViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_produto_interacao, parent, false));
            produtoName = itemView.findViewById(R.id.txt_produto_nome);
            produtoValor = itemView.findViewById(R.id.txt_produto_preco);
            addProduto = itemView.findViewById(R.id.btn_produto_add);
        }
    }

    public class ProdutoEmptyViewHolder extends RecyclerView.ViewHolder {
        public ProdutoEmptyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_produto_interacao, parent, false));

        }
    }
}
