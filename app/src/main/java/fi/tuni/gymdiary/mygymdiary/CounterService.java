package fi.tuni.gymdiary.mygymdiary;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

public class CounterService extends Service {

    private int counterTime;
    private Intent i;

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

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
