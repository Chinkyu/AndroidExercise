package com.example.bamtori.myevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        View view = findViewById(R.id.view1);
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                float curX = event.getX();
                float curY = event.getY();

//                println("Action " + action);

                if (action == MotionEvent.ACTION_DOWN) {
                    println("Action down : " + curX + " , " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("Action move : " + curX + " , " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("Action up : " + curX + " , " + curY);
                }
                return true;
            }
        });


        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown()");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress()");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp()");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll()" + distanceX + ", " + distanceY);
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress()");

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling()" + velocityX + ", " + velocityY);
                return false;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText( this, "System BACK Key Pressed", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;

//        return super.onKeyDown(keyCode, event);
    }

    public void println(String s) {
        textView.append(s + "\n");
    }
}
