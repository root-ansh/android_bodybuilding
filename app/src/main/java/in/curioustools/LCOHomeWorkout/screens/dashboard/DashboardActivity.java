package in.curioustools.LCOHomeWorkout.screens.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.curioustools.LCOHomeWorkout.R;
import in.curioustools.LCOHomeWorkout.modal.DailyLog;
import in.curioustools.LCOHomeWorkout.modal.Exercise;
import in.curioustools.LCOHomeWorkout.screens.aboutme.AboutMeActivity;
import in.curioustools.LCOHomeWorkout.screens.challanges.ChallangesActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        int randomNum1_100 = Math.abs(new Random().nextInt() % 100);
        String today = "Day #" + randomNum1_100 + ": " + Exercise.getToday();
        TextView tvTop = findViewById(R.id.tv_today);
        tvTop.setText(today);

        String exerciseType = Exercise.getExerciseTypeString();
        TextView tv = findViewById(R.id.tv_challange);
        tv.setVisibility(View.VISIBLE);
        tv.setText(exerciseType + " Day !!!");


        ConstraintLayout clComp = findViewById(R.id.cl_completed);
        clComp.setVisibility(View.GONE);

        tv.setOnClickListener(v -> {

            Intent i = new Intent(DashboardActivity.this, ChallangesActivity.class);
            startActivity(i);
            tv.setVisibility(View.GONE);
            clComp.setVisibility(View.VISIBLE);
        });


        List<Object> objectList = new ArrayList<>();

        objectList.add("Daily Logs");
        objectList.add(new DailyLog(Exercise.getRandomDate(), (int) (System.currentTimeMillis() % 5)));
        objectList.add(new DailyLog(Exercise.getRandomDate(), (int) (System.currentTimeMillis() % 5)));
        objectList.add(new DailyLog(Exercise.getRandomDate(), (int) (System.currentTimeMillis() % 5)));
        objectList.add(new DailyLog(Exercise.getRandomDate(), (int) (System.currentTimeMillis() % 5)));

        objectList.add("Excercise Details");
        objectList.addAll(Exercise.getDefaultExercizeList());

        RecyclerView rv = findViewById(R.id.rv_merged_dashboard);

        DashBoardMergedAdapter adp = new DashBoardMergedAdapter();
        rv.setAdapter(adp);
        adp.setObjectList(objectList);

        GridLayoutManager gmanager = new GridLayoutManager(this, 2);

        gmanager.setSpanSizeLookup(
                new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if(objectList.get(position).getClass()==DailyLog.class){
                            return 1;
                        }
                        return 2;
                    }
                }
        );
        rv.setLayoutManager(gmanager);


    }

    public void startinfoActivity(View view) {
        Intent i = new Intent(DashboardActivity.this, AboutMeActivity.class);
        startActivity(i);
    }
}