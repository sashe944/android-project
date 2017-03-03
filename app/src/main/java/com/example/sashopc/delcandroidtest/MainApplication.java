package com.example.sashopc.delcandroidtest;

import android.app.Application;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.sashopc.delcandroidtest.Android.EndTest;

public class MainApplication extends Application {

    public static int TEST_TYPE;
    public static final long MINUTES = 720000;
    public static final long SECONDS = 1000;
    private boolean isDisplayed = false;

    private static long minutesLeft = MINUTES;

    private CountDownTimer timeLeft = null;

    private CountdownListener countdownListener;


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public void startTimer(){
        timeLeft =  new CountDownTimer(minutesLeft, SECONDS) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownListener.TimerTick(millisUntilFinished);

                minutesLeft = millisUntilFinished;

                if (!isDisplayed && millisUntilFinished <= 300000) {
                    isDisplayed = true;
                    Toast.makeText(MainApplication.this, "Остава по-малко от 5 минути", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish() {
                countdownListener.TimerTick(0);
                Toast.makeText(MainApplication.this, "Времето изтече", Toast.LENGTH_LONG).show();
                Intent moveToEndIntent = new Intent(MainApplication.this, EndTest.class);
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

