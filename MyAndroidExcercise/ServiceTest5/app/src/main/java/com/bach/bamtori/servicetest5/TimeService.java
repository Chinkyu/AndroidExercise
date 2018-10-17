package com.bach.bamtori.servicetest5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bach.bamtori.aidltest5.ITimeService;
import com.bach.bamtori.aidltest5.ITimeServiceCallback;

public class TimeService extends Service {
    final static String TAG = "TimeService";

    Thread m_ThrdUpdatTime = null;

    final RemoteCallbackList<ITimeServiceCallback> m_cbLists = new RemoteCallbackList<ITimeServiceCallback>();

    ITimeService.Stub m_binder = new ITimeService.Stub() {
        @Override
        public boolean registerTimeServiceCallback(ITimeServiceCallback callback) throws RemoteException {
            Log.d(TAG, "m_cbLists.register");
            if(callback != null)
                m_cbLists.register(callback);

            //Start Thread
            launchTimeUpdaterThread();
            return true;
        }

        @Override
        public boolean unregisterTimeServiceCallback(ITimeServiceCallback callback) throws RemoteException {
            Log.d(TAG, "m_cbLists.unregister");
            m_cbLists.unregister(callback);
            return false;
        }
    };

    private void launchTimeUpdaterThread() {
        m_ThrdUpdatTime = new Thread("Time Updator") {
            @Override
            public void run() {
                while(true) {
                    Log.d(TAG, "Update time... ");
                    int cnt = m_cbLists.beginBroadcast();
                    for( int idx = 0; idx < cnt; idx++) {
                        ITimeServiceCallback callback = m_cbLists.getBroadcastItem(idx);

                        long curTime = System.currentTimeMillis();
                        SimpleDateFormat tDateFormat = new SimpleDateFormat("YY/MM/dd hh:mm.ss");
                        try {
                            callback.onTimeChanged(tDateFormat.format(new Date(curTime)));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    m_cbLists.finishBroadcast();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        m_ThrdUpdatTime.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind()");

//        launchTimeUpdaterThread();
        return m_binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
