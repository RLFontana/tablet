package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.PrePedido;
import br.com.easygo.cliente.model.Produto;

public class ItemSubPedidoAdapter extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(Produto produto);
    }

    private final OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final PrePedido prePedido;

    public ItemSubPedidoAdapter(Context context, PrePedido prePedido,
                                OnItemClickListener onClick) {
        this.context = context;
        this.prePedido = prePedido;
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
            final ItemPedido item = prePedido.getItemPedido(position);

            viewHolder.produtoName.setText(item.getProduto().getNome());
            viewHolder.produtoQuantidade.setText(String.valueOf(item.getQuantidade()));

            if(listener != null){
                viewHolder.btnProdutoRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(item.getProduto());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return prePedido != null && prePedido.getItensPedidos().size() > 0 ? prePedido.getItensPedidos().size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (prePedido.getItensPedidos() == null || prePedido.getItensPedidos().size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public void remove(int position) {
        prePedido.removeItemPedido(prePedido.getItemPedido(position).getProduto());
    }

    public Produto getItemByPosition(int position){
        return prePedido.getItensPedidos() == null || prePedido.getItensPedidos().isEmpty() ? null : prePedido.getItemPedido(position).getProduto();
    }

static class SubPedidoProdutoViewHolder extends RecyclerView.ViewHolder {
    TextView produtoName;
    TextView produtoQuantidade;
    ImageView btnProdutoRemove;

    public SubPedidoProdutoViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_subpedido_item, parent, false));
        produtoName = itemView.findViewById(R.id.item_subpedido_nome);
        produtoQuantidade = itemView.findViewById(R.id.item_subpedido_qtd);
        btnProdutoRemove = itemView.findViewById(R.id.btn_produto_remove);
    }
}

public class SubPedidoProdutoEmptyViewHolder extends RecyclerView.ViewHolder {
    public SubPedidoProdutoEmptyViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_produto_interacao, parent, false));

    }
}
}
