package fi.tuni.gymdiary.mygymdiary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.concurrent.TimeUnit;

public class CounterActivity extends AppCompatActivity {

    TimePicker counterPicker;
    TextView counterTime;
    IntentFilter filter;
    MyBroadCastReceiver broadCastReceiver;
    boolean receiverRegistered = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        counterPicker = findViewById(R.id.tp);
        counterPicker.setIs24HourView(true);
        counterTime = findViewById(R.id.counterTextView);
        filter = new IntentFilter("fi.tuni.gymdiary.mygymdiary.ONTICK");
        broadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(broadCastReceiver,filter);
        receiverRegistered = true;

        setListeners();
    }

    protected void setListeners() {
        counterPicker.setCurrentHour(0);
        counterPicker.setCurrentMinute(0);
        counterPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                counterTime.setText(counterPicker.getCurrentHour()+"min"+" "+counterPicker.getCurrentMinute()+"sec");
            }
        });
    }

    public void onCLickHandler(View view) {
        if(view.getId() == R.id.selectbtn) {
            if(!receiverRegistered) {
                registerReceiver(broadCastReceiver, filter);
                receiverRegistered = true;
            }
            Intent intent = new Intent(this, CounterService.class);
            int seconds = counterPicker.getCurrentHour()*60 + counterPicker.getCurrentMinute();
            intent.putExtra("counterTime", seconds);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startService(intent);
            counterPicker.setEnabled(false);
        } else if(view.getId() == R.id.resetbtn) {
            counterPicker.setCurrentHour(0);
            counterPicker.setCurrentMinute(0);
            counterPicker.setEnabled(true);
            if(receiverRegistered) {
                stopService(new Intent(this, CounterService.class));
                unregisterReceiver(broadCastReceiver);
                receiverRegistered = false;
            }
        }
    }
    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int countInSeconds = (Integer.parseInt(intent.getStringExtra("counterTime")) /1000);
            int minutes = countInSeconds / 60;
            int seconds = countInSeconds % 60;
            counterTime.setText(minutes+"min"+seconds+"sec");
            if(minutes==0 && seconds==0) {
                counterPicker.setEnabled(true);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(receiverRegistered) {
            unregisterReceiver(broadCastReceiver);
        }
        super.onDestroy();
    }
}
