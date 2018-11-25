package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.model.MainCardItem;

public class MainAdapter  extends RecyclerView.Adapter {

    private List<MainCardItem> itens;
    private Context context;

    public MainAdapter(Context context,  List<MainCardItem> itens) {
        this.context = context;
        this.itens = itens;
    }

    public List<MainCardItem> getItens() {
        return itens;
    }

    public void setItens(List<MainCardItem> itens) {
        this.itens = itens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        final MainViewHolder holder = (MainViewHolder) viewHolder;
        final MainCardItem item = itens.get(position);
        holder.titulo.setText(item.getTitulo());
        holder.cliente.setText(item.getCliente());
        holder.status.setText(item.getStatus());

        switch (item.getTipo()){
            case SOLICITACAO_ATENDIMENTO:
                holder.main_card.setCardBackgroundColor(context.getResources().getColor(R.color.solicitacao_atendimento));
                break;
            case PEDIDO_CLIENTE:
                holder.main_card.setCardBackgroundColor(context.getResources().getColor(R.color.pedido_cliente));
                break;
            case ITEM_PEDIDO_CONFIRMADO:
                holder.main_card.setCardBackgroundColor(context.getResources().getColor(R.color.item_pedido_confirmado));
                break;
            case ITEM_PEDIDO_ENTREGA:
                holder.main_card.setCardBackgroundColor(context.getResources().getColor(R.color.item_pedido_entrega));
                break;
        }

        if (item.getAcoes()!= null && item.getAcoes().size()>0){
            holder.btn0.setText(item.getAcoes().get(0).getNome());
            holder.btn0.setOnClickListener(item.getAcoes().get(0).getOnClickListener());

            if (item.getAcoes().size()>1) {
                holder.btn1.setText(item.getAcoes().get(1).getNome());
                holder.btn1.setOnClickListener(item.getAcoes().get(1).getOnClickListener());
            }
        }
    }



    @Override
    public int getItemCount() {
        return itens != null && itens.size() > 0 ? itens.size() : 0;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, cliente, status;
        Button btn0, btn1;
        CardView main_card;
        LinearLayout main_content;

        public MainViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_main, parent, false));
            titulo = itemView.findViewById(R.id.rv_item_main_titulo);
            cliente = itemView.findViewById(R.id.rv_item_main_cliente);
            status = itemView.findViewById(R.id.rv_item_main_status);
            btn0 = itemView.findViewById(R.id.rv_item_main_btn0);
            btn1 = itemView.findViewById(R.id.rv_item_main_btn1);
            main_card = itemView.findViewById(R.id.rv_item_main_main_card);
            main_content = itemView.findViewById(R.id.rv_item_main_main_content);
        }
    }
}
