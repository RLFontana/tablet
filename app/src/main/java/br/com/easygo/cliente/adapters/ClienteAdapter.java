package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.objects.ClienteAdapterObject;

public class ClienteAdapter  extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(ClienteAdapterObject item);
    }

    private final OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final List<ClienteAdapterObject> clientes;

    public ClienteAdapter(Context context, List<ClienteAdapterObject> mesas) {
        this.context = context;
        this.listener = null;
        this.clientes = mesas;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            return new MesaEmptyViewHolder(parent);
        }

        return new MesaViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MesaViewHolder) {
            final MesaViewHolder viewHolder = (MesaViewHolder) holder;
            final ClienteAdapterObject item = clientes.get(position);

            viewHolder.clienteName.setText(item.getCliente().getNome() + "");
            //viewHolder.clienteImage.setImageDrawable(res);

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
        return clientes != null && clientes.size() > 0 ? clientes.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (clientes == null || clientes.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public void remove(int position) {
        ClienteAdapterObject item = clientes.get(position);
        if (clientes.contains(item)) {
            clientes.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ClienteAdapterObject getItemByPosition(int position){
        return clientes == null ? null : clientes.get(position);
    }

    static class MesaViewHolder extends RecyclerView.ViewHolder {
        TextView clienteName;
        CardView clienteCard;
        LinearLayout clienteContent;
        ImageView clienteImage;

        public MesaViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_cliente, parent, false));
            clienteCard = itemView.findViewById(R.id.cliente_card);
            clienteName = itemView.findViewById(R.id.txt_cliente_name);
            clienteContent = itemView.findViewById(R.id.cliente_content);
            clienteImage = itemView.findViewById(R.id.img_cliente_foto);
        }
    }

    public class MesaEmptyViewHolder extends RecyclerView.ViewHolder {
        public MesaEmptyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_cliente, parent, false));

        }
    }
}