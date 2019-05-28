package com.example.android.TestApp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int curToastId;
    private int nextToastId;

    private TransportConfirm mTransConfirm;
    private TransportPerform mTransPerform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getSupportActionBar().hide();
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );
        }
        setContentView(R.layout.activity_main);

        mTransConfirm = new TransportConfirm();
        mTransPerform = new TransportPerform();

        Button button = findViewById(R.id.button_transport);
        button.setOnClickListener(mTransConfirm);

        curToastId =  R.string.toast_message_1;
        nextToastId =  R.string.toast_message_2;
    }

    public void showToast (View view) {
        Toast toast = Toast.makeText(this, curToastId, Toast.LENGTH_SHORT);
        toast.show();

        int tmp = curToastId;
        curToastId = nextToastId;
        nextToastId = tmp;
    }

    class TransportPerform implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Button button = (Button)v;

            button.setText(R.string.button_trans_confirm);
            button.setOnClickListener(mTransConfirm);
        }
    }

    class TransportConfirm implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button)v;

            button.setText(R.string.button_trans_perform);
            button.setOnClickListener(mTransPerform);
        }
    }
}
