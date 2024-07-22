package com.example.androidwebpp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AdverDialog extends Dialog  {
    private Button btnClose;

    private TextView textView;
    private Activity m_activity;

    CountDownTimer countDownTimer;
    public interface AdverDialogListener {
        void onClose();
    }

    public AdverDialog(Context context, AdverDialogListener listener) {
        super(context, com.google.android.material.R.style.MaterialAlertDialog_Material3);
        this.m_activity = (Activity) context;

    }


    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = 2;
        lpWindow.dimAmount = 0.9f;
        getWindow().setAttributes(lpWindow);
        setContentView(R.layout.dialog_ad);
        textView = findViewById(R.id.popup_content);
        btnClose =  (Button) findViewById(R.id.btn_ok);
        btnClose.setEnabled(false);
        countDownTimer = new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                btnClose.setEnabled(true);
            }
        };
        countDownTimer.start();


        this.btnClose.setOnClickListener(v -> {
            Toast.makeText(m_activity, "AD CLOSE.", Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }
}
