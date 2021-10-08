package com.project.mangaloader.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;

import com.project.mangaloader.R;

public class NetworkErrorConnection {


    private Context context;

    public NetworkErrorConnection(Context context) {
        this.context = context;


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.networkconnectioncardview);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.TOP);
                dialog.show();
                android.os.Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                };
                handler.postDelayed(r, 4000);
            }
        };
        runnable.run();
    }
}
