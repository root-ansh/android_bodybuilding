package in.curioustools.LCOHomeWorkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import in.curioustools.LCOHomeWorkout.screens.dashboard.DashboardActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Thread(() -> {

            try {
                Thread.sleep(500);
                runOnUiThread(() -> {
                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}