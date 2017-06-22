package com.bach.bamtori.servicetest3;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.Toast;

/**
 * Created by bamtori on 16. 11. 29.
 */

public class MessengerService extends Service {
    private final static String TAG = MessengerService.class.getSimpleName();
    static final int MSG_SAY_HELLO = 1;

    // handler for incoming message
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(),"heLocalBinderllo!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    // Messenger.....
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", ToaLocalBinderst.LENGTH_LONG).show();
        return mMessenger.getBinder();
    }
}
