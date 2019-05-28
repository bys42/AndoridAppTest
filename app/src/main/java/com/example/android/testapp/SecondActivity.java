package com.example.android.testapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.example.android.testapp.extra.REPLY";

    private EditText mReply;

    private int curToastId;
    private int nextToastId;

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
        setContentView(R.layout.activity_second);

        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        String prefix = getResources().getString(R.string.prefix_receive_msg);

        TextView textView = findViewById(R.id.text_receive_msg);
        textView.setText(prefix + message);

        mReply = findViewById(R.id.editText_second);

        curToastId =  R.string.toast_message_1;
        nextToastId =  R.string.toast_message_2;
    }

    public void returnMain(View view) {
        String reply = mReply.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void showToast (View view) {
        Toast toast = Toast.makeText(this, curToastId, Toast.LENGTH_SHORT);
        toast.show();

        int tmp = curToastId;
        curToastId = nextToastId;
        nextToastId = tmp;
    }

}
