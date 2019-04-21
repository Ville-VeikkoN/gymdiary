package fi.tuni.gymdiary.mygymdiary.counter;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import fi.tuni.gymdiary.mygymdiary.R;

/**
 * Service for counter.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class CounterService extends Service {

    MyBinder binder = new MyBinder();
    private int counterTime;
    private Intent i;
    CountDownTimer countDownTimer = null;
    boolean counterOn;
    MediaPlayer mp = null;

    /**
     * Starts the counter.
     *
     * @param seconds Integer representing amount of seconds in counter.
     */
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

    /**
     * Stops the counter.
     */
    public void stopCounter() {
        if(countDownTimer != null) {
            counterOn = false;
            countDownTimer.cancel();
        }
    }

    /**
     * Method is called when binded
     *
     * @param intent Intent
     * @return MyBinder binder
     */
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * Inner class for Binder
     *
     * @author Ville-Veikko Nieminen
     * @version 1.8
     * @since 2019-04-21
     */
    class MyBinder extends Binder {
        CounterService getService() {
            return CounterService.this;
        }
    }

    /**
     * Method is called when Activity is destroyed.
     */
    @Override
    public void onDestroy() {
        Log.d("MyTag", "OnDestroy");
        counterOn = false;
        if(mp != null) {
            mp.release();
        }
        super.onDestroy();
    }

    /**
     * Returns boolean which tells if counter is running.
     *
     * @return boolean counterOn representing the fact if counter is running.
     */
    public boolean isCounterOn() {
        return counterOn;
    }

    /**
     * Method plays the sound when counter is ready.
     */
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
