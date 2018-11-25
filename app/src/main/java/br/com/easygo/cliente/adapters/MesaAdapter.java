package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.adapters.objects.MesaAdapterObject;
import br.com.easygo.cliente.model.Mesa;
import br.com.easygo.cliente.model.SituacaoMesa;

public class MesaAdapter  extends RecyclerView.Adapter{

    public interface OnItemClickListener {
        void onItemClick(MesaAdapterObject item);
    }

    private final OnItemClickListener listener;

    private static final int EMPTY_VIEW = 10;

    private final Context context;
    private final List<MesaAdapterObject> mesas;

    public MesaAdapter(Context context, List<MesaAdapterObject> mesas, OnItemClickListener onClick) {
        this.context = context;
        this.mesas = mesas;
        this.listener = onClick;
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
            final MesaAdapterObject item = mesas.get(position);

            viewHolder.mesaName.setText(String.valueOf(item.getMesa().getNumero()));

            switch (item.getMesa().getSituacao()){
                case INDISPONIVEL:
                    viewHolder.mesaCard.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        viewHolder.mesaCard.setCardBackgroundColor(context.getResources().getColor(R.color.silver, context.getTheme()));
                    }else{
                        viewHolder.mesaCard.setCardBackgroundColor(context.getResources().getColor(R.color.silver));
                    }
                    break;
                default:
                    viewHolder.mesaCard.setEnabled(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        viewHolder.mesaCard.setCardBackgroundColor(context.getResources().getColor(R.color.indigo_300, context.getTheme()));
                    }else{
                        viewHolder.mesaCard.setCardBackgroundColor(context.getResources().getColor(R.color.indigo_300));
                    }
                    break;

            }

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
        return mesas != null && mesas.size() > 0 ? mesas.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mesas == null || mesas.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public void remove(int position) {
        MesaAdapterObject item = mesas.get(position);
        if (mesas.contains(item)) {
            mesas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public MesaAdapterObject getItemByPosition(int position){
        return mesas == null ? null : mesas.get(position);
    }

    static class MesaViewHolder extends RecyclerView.ViewHolder {
        TextView mesaName;
        CardView mesaCard;
        LinearLayout mesaContent;

        public MesaViewHolder(ViewGroup parent) {
            //super(LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_row_list_eventos, parent, false));
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_mesa, parent, false));
            mesaCard = itemView.findViewById(R.id.mesa_card);
            mesaName = itemView.findViewById(R.id.txt_mesa);
            mesaContent = itemView.findViewById(R.id.mesa_content);
        }
    }

    public class MesaEmptyViewHolder extends RecyclerView.ViewHolder {
        public MesaEmptyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_mesa, parent, false));

        }
    }
}
