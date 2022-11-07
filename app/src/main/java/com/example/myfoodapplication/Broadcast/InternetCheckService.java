package com.example.myfoodapplication.Broadcast;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myfoodapplication.Activity.MainActivity;
import com.example.myfoodapplication.R;


public class InternetCheckService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getNetworkState(context);
        Dialog dialog = new Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.no_internet);

        Button retry = dialog.findViewById(R.id.retry);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                Intent intent1 = new Intent(context, MainActivity.class);
                context.startActivity(intent1);
            }
        });

        if (status.isEmpty() || status.equals("No internet")){
            dialog.show();
        }
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }
}
