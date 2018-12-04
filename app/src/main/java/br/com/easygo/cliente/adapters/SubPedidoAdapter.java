package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.model.BundlePedidos;
import br.com.easygo.cliente.model.PrePedido;

public class SubPedidoAdapter extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(PrePedido item);
    }

    private final OnItemClickListener listener;
    private final ItemSubPedidoAdapter.OnItemClickListener listenerSubItem;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    BundlePedidos pedidos;

    public SubPedidoAdapter(Context context, BundlePedidos pedidos,
                            OnItemClickListener onClick, ItemSubPedidoAdapter.OnItemClickListener onClickSubItem) {
        this.context = context;
        this.pedidos = pedidos;
        this.listener = onClick;
        this.listenerSubItem = onClickSubItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            return new SubPedidoEmptyViewHolder(parent);
        }

        return new SubPedidoViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SubPedidoViewHolder) {
            final SubPedidoViewHolder viewHolder = (SubPedidoViewHolder) holder;
            final PrePedido item = pedidos.getPrePedido(position);

            viewHolder.mesaName.setText("Mesa " + item.getMesa().getNumero());
            viewHolder.valorTotal.setText("R$ " + String.format("%.2f", item.getValorTotal()));

            if(item.getValorTotal() > 0){
                viewHolder.totalContainer.setVisibility(View.VISIBLE);
            }else{
                viewHolder.totalContainer.setVisibility(View.GONE);
            }

            viewHolder.clienteLista.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            ClienteViewAdapter adapter = new ClienteViewAdapter(context, item.getComandas());
            viewHolder.clienteLista.setAdapter(adapter);

            if(item.getItensPedidos().isEmpty()){
                viewHolder.subpedidoLista.setVisibility(View.GONE);
            }else{
                viewHolder.subpedidoLista.setVisibility(View.VISIBLE);

            }

            viewHolder.subpedidoLista.setLayoutManager(new LinearLayoutManager(context));
            ItemSubPedidoAdapter itemsPedido = new ItemSubPedidoAdapter(context, item, listenerSubItem);
            viewHolder.subpedidoLista.setAdapter(itemsPedido);

            if(listener != null){
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(item);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return pedidos.getPrePedidos() != null && pedidos.getPrePedidos().size() > 0 ? pedidos.getPrePedidos().size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (pedidos.getPrePedidos() == null || pedidos.getPrePedidos().size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public void remove(int position) {
        pedidos.getPrePedidos().remove(position);
        notifyItemRemoved(position);

    }

    public PrePedido getItemByPosition(int position){
        return pedidos.getPrePedidos() == null || pedidos.getPrePedidos().isEmpty() ? null : pedidos.getPrePedido(position);
    }

    static class SubPedidoViewHolder extends RecyclerView.ViewHolder {
        TextView mesaName;
        RecyclerView clienteLista;
        RecyclerView subpedidoLista;
        RelativeLayout totalContainer;
        TextView valorTotal;


        public SubPedidoViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_subpedido, parent, false));
            mesaName = itemView.findViewById(R.id.txt_mesa_subpedido);
            clienteLista = itemView.findViewById(R.id.rv_clientes_subpedido);
            subpedidoLista = itemView.findViewById(R.id.rv_produtos_subpedido);
            totalContainer = itemView.findViewById(R.id.subpedido_total_container);
            valorTotal = itemView.findViewById(R.id.subpedido_valor_total);
        }
    }

    public class SubPedidoEmptyViewHolder extends RecyclerView.ViewHolder {
        public SubPedidoEmptyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_mesa, parent, false));

        }
    }
}
