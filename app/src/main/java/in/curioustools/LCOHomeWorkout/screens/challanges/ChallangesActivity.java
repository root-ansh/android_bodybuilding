package in.curioustools.LCOHomeWorkout.screens.challanges;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import in.curioustools.LCOHomeWorkout.MyDialogs;
import in.curioustools.LCOHomeWorkout.R;
import in.curioustools.LCOHomeWorkout.modal.Exercise;

public class ChallangesActivity extends AppCompatActivity {
    TextView btRandomize,btStart;
    TextView tvExCount, tvTimeCount,tvToday;
    RecyclerView rv;

    ChallangeAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challanges);

        int dayNum = Exercise.getTodayNum();
        List<Exercise> eDayWise = Exercise.getDayWiseList(dayNum);


        tvToday =findViewById(R.id.tv_today);
        tvToday.setText(Exercise.getToday()+".\n Special Exercises for today are:");
        tvExCount=findViewById(R.id.tv_ex_count);
        tvTimeCount=findViewById(R.id.time_minut_count);

        rv=findViewById(R.id.rv_chal_list);

        GridLayoutManager gmanager = new GridLayoutManager(this,2);

        gmanager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) { return  position==0?2:1; }
        }
        );

        adp = new ChallangeAdapter();
        updateUiWithListData(eDayWise);
        adp.setListener(exercise -> MyDialogs.showItemDetailsDialog(this,this.getLayoutInflater(),exercise));
        rv.setLayoutManager(gmanager);
        rv.setAdapter(adp);


        btRandomize=findViewById(R.id.tv_randomize);
        btRandomize.setOnClickListener(v -> {
            List<Exercise> eRandom=Exercise.getRandom5Exercizes();
            updateUiWithListData(eRandom);

        });

        btStart =findViewById(R.id.tv_start);
        btStart.setOnClickListener(v -> {
            MyDialogs.showRepCountDialogue(this,this.getLayoutInflater(),eDayWise);

        });



    }

    private void updateUiWithListData(List<Exercise> exerciseList) {

        adp.setExerciseList(exerciseList);

        tvExCount.setText(""+exerciseList.size());
        int totalTime= 0;
        for (Exercise e:exerciseList) {
            totalTime+=e.getTimeInSeconds();
        }
        tvTimeCount.setText(""+totalTime);

    }


}