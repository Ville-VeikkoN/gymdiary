package fi.tuni.gymdiary.mygymdiary.counter;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import fi.tuni.gymdiary.mygymdiary.R;

public class CounterService extends Service {

    MyBinder binder = new MyBinder();
    private int counterTime;
    private Intent i;
    CountDownTimer countDownTimer = null;
    boolean counterOn;
    MediaPlayer mp = null;

    public void startCounter(int seconds) {
        counterOn = true;
        int counterTime = seconds*1000;
        i = new Intent("fi.tuni.gymdiary.mygymdiary.ONTICK");
        countDownTimer = new CountDownTimer(counterTime, 1000) {

            public void onTick(long millisUntilFinished) {
                i.putExtra("counterTime",""+millisUntilFinished);
                System.out.println(millisUntilFinished);                                                                                            /////////////
                sendBroadcast(i);
            }

            public void onFinish() {
                playSound();
                counterOn = false;
            }
        }.start();
    }

    public void stopCounter() {
        if(countDownTimer != null) {
            counterOn = false;
            countDownTimer.cancel();
        }
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
        counterOn = false;
        if(mp != null) {
            mp.release();
        }
        super.onDestroy();
    }

    public boolean isCounterOn() {
        return counterOn;
    }

    public void playSound() {
        try {
            if(mp != null) {
                mp.reset();
                mp.release();
            }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.beep);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
