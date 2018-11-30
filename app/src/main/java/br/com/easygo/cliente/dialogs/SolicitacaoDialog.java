package br.com.easygo.cliente.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import br.com.easygo.cliente.R;
import br.com.easygo.cliente.firebase.FireBaseData;
import br.com.easygo.cliente.model.MainCardTipo;

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
        builder.setView(layoutInflater.inflate(isSolicitacao ? R.layout.dialog_solicitacao : R.layout.dialog_notificacao, null))
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
        return builder.create();
    }
}
