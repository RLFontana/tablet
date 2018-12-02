package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.Comanda;

public class ClienteViewAdapter  extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(Comanda item, List<Integer> selectedItems);
    }

    private final OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final List<Comanda> comandas;
    private List<Integer> selectedItems = new LinkedList<>();

    public ClienteViewAdapter(Context context, List<Comanda> comandas) {
        this.context = context;
        this.listener = null;
        this.comandas = comandas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            return new ClienteViewEmptyViewHolder(parent);
        }

        return new ClienteViewViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ClienteViewViewHolder) {
            final ClienteViewViewHolder viewHolder = (ClienteViewViewHolder) holder;
            final Comanda item = comandas.get(position);

            viewHolder.clienteName.setText(item.getCliente().getNome());

            Drawable drawable;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = context.getResources().getDrawable(item.getCliente().getImageResource(), context.getTheme());
            } else {
                drawable = context.getResources().getDrawable(item.getCliente().getImageResource());
            }
            viewHolder.clienteImage.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return comandas != null && comandas.size() > 0 ? comandas.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (comandas == null || comandas.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public void remove(int position) {
        Comanda item = comandas.get(position);
        if (comandas.contains(item)) {
            comandas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Comanda getItemByPosition(int position){
        return comandas == null ? null : comandas.get(position);
    }

    public int[] getSelecionados(){
        int[] selecionados = new int[selectedItems.size()];
        for(int i=0; i< selectedItems.size(); i++){
            selecionados[i] = selectedItems.get(i).intValue();
        }
        return selecionados;
    }

    static class ClienteViewViewHolder extends RecyclerView.ViewHolder {
        TextView clienteName;
        CardView clienteCard;
        LinearLayout clienteContent;
        ImageView clienteImage;
        ImageView clienteCheckedImage;

        public ClienteViewViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_view_cliente, parent, false));
            //clienteCard = itemView.findViewById(R.id.cliente_card);
            clienteName = itemView.findViewById(R.id.txt_cliente_name);
            //clienteContent = itemView.findViewById(R.id.cliente_content);
            clienteImage = itemView.findViewById(R.id.profile_image);
            clienteCheckedImage = itemView.findViewById(R.id.profile_checked);
        }
    }

    public class ClienteViewEmptyViewHolder extends RecyclerView.ViewHolder {
        public ClienteViewEmptyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_cliente, parent, false));

        }
    }
}