package com.example.android.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "com.example.android.testapp.extra.MESSAGE";

    public static final int TEXT_REQUEST = 1;

    private Context mContext;

    private TextView mReplyTitleTextView;
    private TextView mReplyMsgTextView;

    private TransportConfirm mTransConfirm;
    private TransportPerform mTransPerform;

    private EditText mMessageEditText;

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

        mContext = this;

        mReplyTitleTextView = findViewById(R.id.text_reply_title);
        mReplyMsgTextView = findViewById(R.id.text_reply_msg);

        mMessageEditText = findViewById(R.id.editText_main);

        mTransConfirm = new TransportConfirm();
        mTransPerform = new TransportPerform();

        Button button = findViewById(R.id.button_transport);
        button.setOnClickListener(mTransConfirm);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
            if (reply.equals("")){
                mReplyTitleTextView.setVisibility(View.INVISIBLE);
                mReplyMsgTextView.setVisibility(View.INVISIBLE);
            } else {
                mReplyTitleTextView.setVisibility(View.VISIBLE);
                mReplyMsgTextView.setText(reply);
                mReplyMsgTextView.setVisibility(View.VISIBLE);
            }
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

    class TransportPerform implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, SecondActivity.class);

            String message = mMessageEditText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);

            startActivityForResult(intent, TEXT_REQUEST);

            mMessageEditText.setText("");

            Button button = (Button)v;

            button.setText(R.string.button_trans_confirm);
            button.setOnClickListener(mTransConfirm);
        }
    }
}
