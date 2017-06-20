package com.example.sashopc.delcandroidtest;

import android.app.Application;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.sashopc.delcandroidtest.ui.EndTestActivity;

public class MainApplication extends Application {

    public static final int FIVE_MINUTES_IN_MILLIS = 300000;
    public static String TEST_TYPE;
    public static final long TEST_DURATION_IN_MILLIS = 720000;
    public static final long ONE_SECOND = 1000;
    private boolean isDisplayed = false;

    private CountDownTimer timeLeft = null;

    private CountdownListener countdownListener;


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public void startTimer(){
        timeLeft =  new CountDownTimer(TEST_DURATION_IN_MILLIS, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownListener.timerTick(millisUntilFinished);

                if (!isDisplayed && millisUntilFinished <= FIVE_MINUTES_IN_MILLIS) {
                    isDisplayed = true;
                    Toast.makeText(MainApplication.this, "Остава по-малко от 5 минути", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish() {
                countdownListener.timerTick(0);
                Toast.makeText(MainApplication.this, "Времето изтече", Toast.LENGTH_LONG).show();
                Intent moveToEndIntent = new Intent(MainApplication.this, EndTestActivity.class);
                moveToEndIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(moveToEndIntent);
            }
        };

        timeLeft.start();
    }

    public void pauseTimer(){
        timeLeft.cancel();
    }


     public void setCountdownListener(CountdownListener countdownListener) {
     this.countdownListener = countdownListener;
     }

}

