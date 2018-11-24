package br.com.easygo.cliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.model.ItemPedido;

public class PedidoAdapter extends ArrayAdapter<ItemPedido> {

    private List<ItemPedido> dataSet;
    Context context;

    private static class ViewHolder{
        TextView itemName;
        TextView itemQuantity;
        ImageView clienteImage;
        ImageView cancelButton;
    }

    public PedidoAdapter(Context context, ArrayList<ItemPedido> dataSet) {
        super(context, R.layout.row_pedido, dataSet);
        this.context = context;
        this.dataSet = dataSet;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemPedido itemPedido = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.row_pedido, parent, false);
            viewHolder.itemName = convertView.findViewById(R.id.tv_order);
            viewHolder.itemQuantity = convertView.findViewById(R.id.tv_qtd);
            viewHolder.clienteImage = convertView.findViewById(R.id.iv_client);
            viewHolder.cancelButton = convertView.findViewById(R.id.ib_cancel);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.itemName.setText(itemPedido.getProduto().getNome());
        viewHolder.itemQuantity.setText(String.valueOf(itemPedido.getQuantidade()));
        viewHolder.cancelButton.setTag(position);

        return convertView;
    }
}
