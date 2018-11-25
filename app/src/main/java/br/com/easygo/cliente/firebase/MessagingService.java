package br.com.easygo.cliente.firebase;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    final String TAG = "MessagingService";


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        // Get updated InstanceID token.
        String refreshedToken = s;
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "ID: " + remoteMessage.getCollapseKey());
        if(remoteMessage != null && remoteMessage.getData().containsKey("cardTipo")){
            FireBaseData firebaseData = FireBaseData.parse(remoteMessage.getData());

        }
    }
}
