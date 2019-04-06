package fi.tuni.gymdiary.mygymdiary.counter;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import fi.tuni.gymdiary.mygymdiary.R;

public class CounterActivity extends AppCompatActivity {

    TimePicker counterPicker;
    TextView counterTime;
    IntentFilter filter;
    MyBroadCastReceiver broadCastReceiver;
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;
    CounterService service;
    boolean receiverRegistered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        counterPicker = findViewById(R.id.tp);
        counterPicker.setIs24HourView(true);
        counterTime = findViewById(R.id.counterTextView);
        filter = new IntentFilter("fi.tuni.gymdiary.mygymdiary.ONTICK");
        broadCastReceiver = new MyBroadCastReceiver();
        Intent intent = new Intent(this, CounterService.class);

        setServiceConnection();
        setListeners();

        registerReceiver(broadCastReceiver,filter);
        receiverRegistered = true;
        bindService(intent, sConn, BIND_AUTO_CREATE);
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
            if(!service.isCounterOn()) {
                int seconds = counterPicker.getCurrentHour()*60 + counterPicker.getCurrentMinute();
                //     service.setCounterTime(seconds);
                service.startCounter(seconds);
          //      counterPicker.setEnabled(false);
            }

        } else if(view.getId() == R.id.resetbtn) {
            service.stopCounter();
            counterPicker.setCurrentHour(0);
            counterPicker.setCurrentMinute(0);
          //  counterPicker.setEnabled(true);
        }
    }
    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int countInSeconds = (Integer.parseInt(intent.getStringExtra("counterTime")) /1000);
            int minutes = countInSeconds / 60;
            int seconds = countInSeconds % 60;
            counterTime.setText(minutes+"min"+seconds+"sec");
        //    if(minutes==0 && seconds==0) {
         //       counterPicker.setEnabled(true);
        //    }
        }
    }

    @Override
    protected void onDestroy() {
        if(receiverRegistered) {
            unregisterReceiver(broadCastReceiver);
        }
        if(bound) {
            unbindService(sConn);
            bound = false;
        }
        super.onDestroy();
    }

    protected void setServiceConnection() {
        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.i("MyTag", "MainActivity onServiceConnected");
                service = ((CounterService.MyBinder) binder).getService();
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.i("MyTag", "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }
}
