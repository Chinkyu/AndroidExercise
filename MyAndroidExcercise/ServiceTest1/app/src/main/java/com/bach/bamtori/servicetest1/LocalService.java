package com.bach.bamtori.servicetest1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by bamtori on 16. 12. 6.
 */

public class LocalService extends Service {
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();


    // Class Binder
    public class LocalBinder extends Binder {
        LocalService getService() {
            // return the instance of LocalService
            return LocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // method for client
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
