package com.example.android.scrollingtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toast mToastMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToastMsg = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        mToastMsg.setGravity(Gravity.CENTER, 0, 0);
    }

    public void showToast(View view) {
        mToastMsg.show();
    }
}
