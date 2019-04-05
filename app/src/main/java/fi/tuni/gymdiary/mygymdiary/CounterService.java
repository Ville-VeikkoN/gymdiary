package fi.tuni.gymdiary.mygymdiary;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service {

    MyBinder binder = new MyBinder();
    private int counterTime;
    private Intent i;
    CountDownTimer countDownTimer;

/*
    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        System.out.println("onstartcommand");                                                       //////////////
        counterTime = intent.getIntExtra("counterTime",1) *1000;
        i = new Intent("fi.tuni.gymdiary.mygymdiary.ONTICK");
        new CountDownTimer(counterTime, 1000) {

            public void onTick(long millisUntilFinished) {
                i.putExtra("counterTime",""+millisUntilFinished);
                System.out.println(millisUntilFinished);                                                                                            /////////////
                sendBroadcast(i);
            }

            public void onFinish() {


            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }*/

    public void startCounter(int seconds) {
        int counterTime = seconds*1000;
        i = new Intent("fi.tuni.gymdiary.mygymdiary.ONTICK");
        countDownTimer = new CountDownTimer(counterTime, 1000) {

            public void onTick(long millisUntilFinished) {
                i.putExtra("counterTime",""+millisUntilFinished);
                System.out.println(millisUntilFinished);                                                                                            /////////////
                sendBroadcast(i);
            }

            public void onFinish() {


            }
        }.start();
    }

    public void stopCounter() {
        countDownTimer.cancel();
    }

    public IBinder onBind(Intent intent) {
        Log.i("MyTag", "MyService onBind");
        return binder;
    }

    class MyBinder extends Binder {
        CounterService getService() {
            return CounterService.this;
        }
    }

    @Override
    public void onDestroy() {
        Log.d("MyTag", "OnDestroy");
        super.onDestroy();
    }


}
