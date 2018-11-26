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

public class SolicitacaoDialog extends DialogFragment {

    private boolean isSolicitacao;

    public SolicitacaoDialog() {
        super();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        isSolicitacao = getArguments() != null ? getArguments().getBoolean("isSolicitacao") : false;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

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
