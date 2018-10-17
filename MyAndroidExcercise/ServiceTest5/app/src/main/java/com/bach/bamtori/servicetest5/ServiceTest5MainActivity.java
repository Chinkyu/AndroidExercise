package com.bach.bamtori.servicetest5;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bach.bamtori.aidltest5.ITimeService;
import com.bach.bamtori.aidltest5.ITimeServiceCallback;

public class ServiceTest5MainActivity extends AppCompatActivity {

    private static final String TAG = "ServiceTest5MainActivity";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    // AIDL Interface
    private ITimeService m_remoteTimeSrc = null;
    public ITimeServiceCallback m_remoteCallback = null;

    // for Binding Service
    private ServiceConnection m_TimeSvcConnection = null;

    // for UI
    private TextView m_tvTimeInfo = null;
    private TextView m_tvStatus = null;




    public void onBindButtonClick(View v) {

        // #3. Bind Service
        Log.d(TAG, "bindService...");
        Intent intent = new Intent("com.bach.bamtori.servicetest5.TimeService");

        //:: XXXX : 이게 업으면 intent 가 explicit 하지 않다고 Fail 남.
        intent.setPackage("com.bach.bamtori.servicetest5");  //:: ckkang : XXXX ?

        bindService( intent, m_TimeSvcConnection, Context.BIND_AUTO_CREATE);

    }

    public void onCallbackButtonClick(View v) {

        return ;
//       try {
//          if(m_remoteTimeSrc.registerTimeServiceCallback(m_remoteCallback)) {
//              Log.d( TAG,  "Callback was registered...");
//              m_tvStatus.setText("Callback was registered...");
//          } else {
//              Log.d( TAG, "Registering Callback was failed...");
//              m_tvStatus.setText("Registering Callback was failed...");
//          }
//       } catch (RemoteException e) {
//           e.printStackTrace();
//       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test5_main);

        m_tvTimeInfo = (TextView) findViewById(R.id.timeInfo);
        m_tvStatus = (TextView) findViewById(R.id.status);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        if (m_TimeSvcConnection == null) {
            m_TimeSvcConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    m_remoteTimeSrc = ITimeService.Stub.asInterface(service);
                    m_tvStatus.setText("Service is Connected...");
                    Log.d(TAG, "Service is Connected...");

                    try {
                        if(m_remoteTimeSrc.registerTimeServiceCallback(m_remoteCallback)) {
                            Log.d( TAG,  "Callback was registered...");
                            m_tvStatus.setText("Callback was registered...");
                        } else {
                            Log.d( TAG, "Registering Callback was failed...");
                            m_tvStatus.setText("Registering Callback was failed...");
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    m_remoteTimeSrc = null;
                    Log.d( TAG, "Service is Disconnected...");
                    m_tvStatus.setText("Service is Disconnected...");
                }
            };
        }

        // #2. Timer Callback
        //:: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Log.d( TAG, "m_remoteCallback create..");
        m_remoteCallback = new ITimeServiceCallback.Stub() {  //:: XXXX: 여기에 Stub가 없으면 계속죽는다. !!!!!
        //    m_remoteCallback = new ITimeServiceCallback() {  //:: XXXX: 여기에 Stub가 없으면 계속죽는다. !!!!!!
            String m_TimerInfo = null;

            @Override
            public void onTimeChanged(String timeinfo) throws RemoteException {
                Log.d(TAG, "onTimeChanged" + timeinfo);

                m_TimerInfo = timeinfo;
//                m_tvTimeInfo.setText(m_TimerInfo);

                Runnable updateUI = new Runnable() {
                    @Override
                    public void run() {
                        m_tvTimeInfo.setText(m_TimerInfo);
                    }
                };

                m_tvTimeInfo.postDelayed(updateUI, 10);

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_test5_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
