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

/**
 * Activity for using counter.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
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

    /**
     * Method is called when CounterActivity is created.
     * Intializes variables, sets layout and calls methods to create
     * needed actions.
     *
     * @param savedInstanceState Bundle for saved instance states
     */
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


    /**
     * Sets listener for TimePicker.
     */
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


    /**
     * Handles actions performed when buttons is clicked.
     *
     * @param view View containing the view clicked
     */
    public void onCLickHandler(View view) {
        if(view.getId() == R.id.selectbtn) {
            if(!service.isCounterOn()) {
                int seconds = counterPicker.getCurrentHour()*60 + counterPicker.getCurrentMinute();
                service.startCounter(seconds);
            }

        } else if(view.getId() == R.id.resetbtn) {
            service.stopCounter();
            counterPicker.setCurrentHour(0);
            counterPicker.setCurrentMinute(0);
        }
    }
    /**
     * Inner class for BroadcastReveicer.
     *
     * @author Ville-Veikko Nieminen
     * @version 1.8
     * @since 2019-04-21
     */
    class MyBroadCastReceiver extends BroadcastReceiver {

        /**
         * Method that is called when receives broadcast.
         *
         * @param context Context
         * @param intent Intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            int countInSeconds = (Integer.parseInt(intent.getStringExtra("counterTime")) /1000);
            int minutes = countInSeconds / 60;
            int seconds = countInSeconds % 60;
            counterTime.setText(minutes+"min"+seconds+"sec");
        }
    }

    /**
     * Method is called when Activity is destroyed.
     */
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

    /**
     * Sets Service connection.
     */
    protected void setServiceConnection() {
        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                service = ((CounterService.MyBinder) binder).getService();
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };
    }
}
