package com.example.anirudhs.proximitysensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView myText,timerText;
    SensorManager sm;
    Sensor proxSensor;
    Uri notification;
    Ringtone r=null;
    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.myText);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);


    }


    @Override
    protected void onPause() {
        super.onPause();
        r.stop();
        sm.unregisterListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        final float distance = event.values[0];

        if(distance==3.0){


            text="User Near";


        }
        else{
            text = "User Far";

        }
        myText.setText(text);



    }
}