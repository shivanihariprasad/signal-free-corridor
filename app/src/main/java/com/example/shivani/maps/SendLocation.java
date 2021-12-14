package com.example.harish.maps;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Shreyas on 06-01-2018.
 */

public class SendLocation extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        DbHelper helper=new DbHelper(context);

        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Contract.ContractEntry.TABLE_NAME, null);
        int latColumnIndex=cursor.getColumnIndex(Contract.ContractEntry.COLUMN_LATITIUDE);
        int lngColumnIndex=cursor.getColumnIndex(Contract.ContractEntry.COLUMN_LONGITUDE);
        cursor.moveToFirst();
        double lat=cursor.getDouble(latColumnIndex);
        double lng=cursor.getDouble(lngColumnIndex);
        Socket s = null;
        try {
            s = new Socket("192.168.1.100", 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(s.getOutputStream());
           dos.writeUTF(lat+","+lng);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        long when = System.currentTimeMillis();

        Intent intent1 = new Intent(context,MapsActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //if we want ring on notifcation then uncomment below line//
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setContentIntent(pendingIntent).
                setContentText("Time to take your pills").
                setContentTitle("Pill Reminder")
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setLights(Color.BLUE, 500, 500)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        ;
//                setSound(alarmSound).

        notificationManager.notify(100,builder.build());
    }
}
