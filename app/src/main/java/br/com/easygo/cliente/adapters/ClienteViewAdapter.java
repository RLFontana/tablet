package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.objects.ClienteAdapterObject;
import br.com.easygo.cliente.model.Cliente;

public class ClienteViewAdapter  extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(Cliente item, List<Integer> selectedItems);
    }

    private final OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final List<Cliente> clientes;
    private List<Integer> selectedItems = new LinkedList<>();

    public ClienteViewAdapter(Context context, List<Cliente> clientes) {
        this.context = context;
        this.listener = null;
        this.clientes = clientes;
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
            final Cliente item = clientes.get(position);

            viewHolder.clienteName.setText(item.getNome());

            Drawable drawable;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = context.getResources().getDrawable(item.getImageResource(), context.getTheme());
            } else {
                drawable = context.getResources().getDrawable(item.getImageResource());
            }
            viewHolder.clienteImage.setImageDrawable(drawable);
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
        Cliente item = clientes.get(position);
        if (clientes.contains(item)) {
            clientes.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Cliente getItemByPosition(int position){
        return clientes == null ? null : clientes.get(position);
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