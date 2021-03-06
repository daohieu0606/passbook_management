package com.example.passbook.customviews;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.passbook.R;

public class NotificationPopup extends Dialog {

    public NotificationPopup(@NonNull Context context, String title, String message,
                             com.example.passbook.intefaces.OnDismissListener onDismissListener) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.notification_popup);

        bindAndSetDataToView(title, message);

        this.setOnDismissListener(dialog -> {
            onDismissListener.onDismiss();
        });
    }

    private void bindAndSetDataToView(String title, String message) {
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtMessage = findViewById(R.id.txtMessage);
        TextView btnOK = findViewById(R.id.btnOK);

        txtTitle.setText(title);
        txtMessage.setText(message);
        btnOK.setOnClickListener(v -> {
            this.dismiss();
        });
    }
}
