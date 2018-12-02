package br.com.easygo.cliente.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.dao.InMemoryDB;
import br.com.easygo.cliente.firebase.FireBaseData;
import br.com.easygo.cliente.model.Cliente;
import br.com.easygo.cliente.model.ItemPedido;
import br.com.easygo.cliente.model.MainCardTipo;
import br.com.easygo.cliente.model.Mesa;

public class SolicitacaoDialog extends DialogFragment {

    private FireBaseData fireBaseData;

    public SolicitacaoDialog() {
        super();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        fireBaseData = getArguments() != null && getArguments().containsKey("FirebaseData") ? (FireBaseData)getArguments().getSerializable("FirebaseData") : null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        boolean isSolicitacao = fireBaseData.getType().equals(MainCardTipo.SOLICITACAO_ATENDIMENTO);
        View content = layoutInflater.inflate(isSolicitacao ? R.layout.dialog_solicitacao : R.layout.dialog_notificacao, null);
        builder.setView(content)
                .setPositiveButton(isSolicitacao ? R.string.atender : R.string.entregar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SolicitacaoDialog.this.getDialog().cancel();
                    }
                });
        TextView mensagem = content.findViewById(isSolicitacao ? R.id.tv_solicitacao : R.id.tv_notificacao);
        mensagem.setText(getText(fireBaseData));
        return builder.create();
    }

    private String getText(FireBaseData fireBaseData){
        StringBuilder stringBuilder = new StringBuilder();
        Mesa mesa = InMemoryDB.getMesa(fireBaseData);
        switch (fireBaseData.getType()){
            case SOLICITACAO_ATENDIMENTO:
                Cliente cliente = InMemoryDB.getCliente(fireBaseData);
                if (cliente != null) {
                    stringBuilder.append(cliente.getNome());
                    stringBuilder.append(" ");
                }
                if (mesa != null) {
                    stringBuilder.append("na mesa ");
                    stringBuilder.append(mesa.getNumero());
                    stringBuilder.append(" ");
                }
                stringBuilder.append("está solicitando atendimento");
                break;
            case ITEM_PEDIDO_ENTREGA:
                stringBuilder.append("O pedido de ");
                ItemPedido itemPedido = InMemoryDB.getItemPedido(fireBaseData);
                if (itemPedido != null) {
                    stringBuilder.append(itemPedido.getProduto().getNome());
                    stringBuilder.append(" ");
                    stringBuilder.append("de ");
                    stringBuilder.append(itemPedido.getListaComandas().get(0).getCliente().getNome());
                    stringBuilder.append(" ");
                }
                stringBuilder.append("está pronto para entrega na mesa ");
                if (mesa != null) {
                    stringBuilder.append(mesa.getNumero());
                }
                break;
        }
        return stringBuilder.toString();
    }
}
