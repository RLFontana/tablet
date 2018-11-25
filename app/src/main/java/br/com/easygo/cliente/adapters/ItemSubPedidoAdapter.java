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
import br.com.easygo.cliente.adapters.objects.SubPedidoAdapterObject;
import br.com.easygo.cliente.model.Produto;

public class ItemSubPedidoAdapter extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(Produto item);
    }

    private final OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final List<SubPedidoAdapterObject.SubPedidoProduto> produtos;

    public ItemSubPedidoAdapter(Context context, List<SubPedidoAdapterObject.SubPedidoProduto> produtos, OnItemClickListener onClick) {
        this.context = context;
        this.produtos = produtos;
        this.listener = onClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            return new SubPedidoProdutoEmptyViewHolder(parent);
        }

        return new SubPedidoProdutoViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SubPedidoProdutoViewHolder) {
            final SubPedidoProdutoViewHolder viewHolder = (SubPedidoProdutoViewHolder) holder;
            final SubPedidoAdapterObject.SubPedidoProduto item = produtos.get(position);

            viewHolder.produtoName.setText(item.getProduto().getNome());
            viewHolder.produtoQuantidade.setText(String.valueOf(item.getQuantidade()));
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
        SubPedidoAdapterObject.SubPedidoProduto item = produtos.get(position);
        if (produtos.contains(item)) {
            produtos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public SubPedidoAdapterObject.SubPedidoProduto getItemByPosition(int position){
        return produtos == null ? null : produtos.get(position);
    }

static class SubPedidoProdutoViewHolder extends RecyclerView.ViewHolder {
    TextView produtoName;
    TextView produtoQuantidade;

    public SubPedidoProdutoViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_subpedido_item, parent, false));
        produtoName = itemView.findViewById(R.id.item_subpedido_nome);
        produtoQuantidade = itemView.findViewById(R.id.item_subpedido_qtd);
    }
}

public class SubPedidoProdutoEmptyViewHolder extends RecyclerView.ViewHolder {
    public SubPedidoProdutoEmptyViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_produto_interacao, parent, false));

    }
}
}
