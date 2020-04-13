package in.curioustools.LCOHomeWorkout.screens.start_exercise;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ramijemli.percentagechartview.PercentageChartView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.curioustools.LCOHomeWorkout.R;
import in.curioustools.LCOHomeWorkout.modal.Exercise;

@SuppressLint("SetTextI18n")
public class StartExerciseActivity extends AppCompatActivity {
    private static final String TAG = "StartExerciseActivity>>";
    ImageView ivEx1, ivEx2, ivEx3, ivEx4, ivEx5;
    View brEx1, brEx2, brEx3, brEx4, brEx5;
    TextView tvTitle, tvDetails, tvDetailsTime;
    ImageView ivDetailsImg;
    TextView tvCurrExTimeLeft, tvRepCount;
    FloatingActionButton fabMusic;
    PercentageChartView pcv;
    FrameLayout cardBreak;
    TextView tvCardBreak;
    TextView tvStartFinish;

    public static final String KEY_ExList = "key_excer_list";
    public static final String KEY_REPCOUNT = "key_repcount";
    public static final int defaultRepcount = 10;

    @NonNull
    List<Exercise> exerciseList = new ArrayList<>();
    int REPCOUNT = 0, BREAK_COUNT = 20;

    Thread bgThread = null;
    MediaPlayer player;

    @Nullable
    View.OnClickListener startStopListener;
    View.OnClickListener fabMusicClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exercise);

        player = new MediaPlayer();

        initIntentData();
        initUi();
        updatUiInitialData();
        initListeners();

    }

    @Override
    protected void onStart() {
        super.onStart();

        tvStartFinish.setOnClickListener(startStopListener);
        fabMusic.setOnClickListener(fabMusicClickListener);


    }

    private void initIntentData() {

        exerciseList = getIntent().getParcelableArrayListExtra(KEY_ExList);
        if (exerciseList == null) exerciseList = Exercise.getRandom5Exercizes();

        REPCOUNT = getIntent().getIntExtra(KEY_REPCOUNT, defaultRepcount);

        Log.e(TAG, "onCreate: exercizelist=" + exerciseList);
        Log.e(TAG, "onCreate: repcount=" + REPCOUNT);

    }
    private void initUi() {
        ivEx1 = findViewById(R.id.iv_ex1);
        ivEx2 = findViewById(R.id.iv_ex2);
        ivEx3 = findViewById(R.id.iv_ex3);
        ivEx4 = findViewById(R.id.iv_ex4);
        ivEx5 = findViewById(R.id.iv_ex5);

        brEx1 = findViewById(R.id.view_break1);
        brEx2 = findViewById(R.id.view_break2);
        brEx3 = findViewById(R.id.view_break3);
        brEx4 = findViewById(R.id.view_break4);
        brEx5 = findViewById(R.id.view_break5);

        tvTitle = findViewById(R.id.tv_title);
        tvDetails = findViewById(R.id.tv_details);
        tvDetailsTime = findViewById(R.id.tv_details_time);
        ivDetailsImg = findViewById(R.id.iv_details_img);

        fabMusic = findViewById(R.id.fab_music);

        tvCurrExTimeLeft = findViewById(R.id.tv_current_ex_time);
        tvRepCount = findViewById(R.id.tv_repcount);
        pcv = findViewById(R.id.pcv);


        cardBreak = findViewById(R.id.card_break);
        tvCardBreak = findViewById(R.id.tv_card_breakcount);

        tvStartFinish = findViewById(R.id.tv_start);
    }
    private void updatUiInitialData() {
        //setting initial data;
        updateUiFabMusictoggle();
        tvStartFinish.setText("Start Exercise");

        updateUiToggleCardAndBreakTimer(false, 0);

        Exercise currEx = exerciseList.get(0);

        pcv.textSize(1);
        updateUiRepCountAndTimerUi(currEx.getTimeInSeconds()*REPCOUNT, 1, REPCOUNT);
        updateUiExDetailsCard(currEx);

        //init exercize list
        brEx1.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        brEx2.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        brEx3.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        brEx4.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        brEx5.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);

        ivEx1.setImageResource(exerciseList.get(0).getImageRes());
        ivEx2.setImageResource(exerciseList.get(1).getImageRes());
        ivEx3.setImageResource(exerciseList.get(2).getImageRes());
        ivEx4.setImageResource(exerciseList.get(3).getImageRes());
        ivEx5.setImageResource(exerciseList.get(4).getImageRes());

        ivEx1.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        ivEx2.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        ivEx3.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        ivEx4.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
        ivEx5.setBackgroundResource(R.drawable.bg_circle_gray_e5e5);
    }
    private void initListeners() {


        startStopListener = v -> {

            if (bgThread == null) {


                //actions
                initAndStartBgThread();
                startPlayingOrUpdateSong(Exercise.getRandomSong());

                //ui updates
                tvStartFinish.setText("FINISH EXERCISE ROUTINE");
                updateUiFabMusictoggle();

            } else {

                //actions
                bgThread.interrupt(); // stp thread
                bgThread = null;
                stopCurrentSong();

                //ui updates
                tvStartFinish.setText("START EXERCISE ROUTINE");
                updateUiFabMusictoggle();
            }

        };


        fabMusicClickListener = v -> {
            if(player.isPlaying()){
                stopCurrentSong();
            }
            else{
                startPlayingOrUpdateSong(Exercise.getRandomSong());
            }
            updateUiFabMusictoggle();

        };
    }

    private void initAndStartBgThread() {

        bgThread = new Thread(() -> {

            try {
//                Log.e(TAG, "initAndStartBgThread: Executing try block");

                int totalRunTime = 0; // == every list item.time*repcount
                for (Exercise e : exerciseList) {
                    totalRunTime += e.getTimeInSeconds();
//                    Log.e(TAG, "initAndStartBgThread: totalRepCount=" + totalRunTime);

                }
                totalRunTime *= REPCOUNT;
                int i = 0;

//                Log.e(TAG, "initAndStartBgThread: totalRepCount,i=" + totalRunTime + "," + i);


                while (totalRunTime > 0 && i < exerciseList.size()) {
//                    Log.e(TAG, "initAndStartBgThread: totalRepCount,i=" + totalRunTime + "," + i);


                    int uiRepCount = REPCOUNT;
                    Exercise currEx = exerciseList.get(i);
                    int currExTime = currEx.getTimeInSeconds() * REPCOUNT;
                    int currExTimeCopy = currExTime;//aka max Time for current exercize

//                    Log.e(TAG, "initAndStartBgThread: uirepcount,curEx,currExTime,currExTimeCopy=" + uiRepCount + ',' + currEx + ',' + currExTime + ',' + currExTimeCopy);

                    //update top bar current ex to pink and current card details
                    updateUiExListCurrentExercize(i);
                    updateUiExDetailsCard(currEx);


                    while (currExTime > 0) {
                        Thread.sleep(500);
                        currExTime--;

                        if (currExTime % currEx.getTimeInSeconds() == 0) {
                            uiRepCount--;

                        }
//                        Log.e(TAG, "initAndStartBgThread: uirepcount,curEx,currExTime,currExTimeCopy=" + uiRepCount + ',' + currEx + ',' + currExTime + ',' + currExTimeCopy);

                        updateUiRepCountAndTimerUi(currExTime, currExTimeCopy, uiRepCount); //update recount and times
                    }
//                    Log.e(TAG, "initAndStartBgThread: uirepcount,curEx,currExTime,currExTimeCopy=" + uiRepCount + ',' + currEx + ',' + currExTime + ',' + currExTimeCopy);

                    Log.e(TAG, "--------- ----1 ex finished---" );
                    updateUiExListFinishedExercize(i); //set current exercize to Star bg and Break to pink and Card to visible
                    totalRunTime = totalRunTime - currExTimeCopy;
                    i++;

//                    Log.e(TAG, "initAndStartBgThread: total runtime, i="+totalRunTime+","+i );

                    updateUiToggleCardAndBreakTimer(true, BREAK_COUNT);
                    int breakTimeCopy = BREAK_COUNT;
//                    Log.e(TAG, "initAndStartBgThread: breakTimecopy="+breakTimeCopy );

                    //takeABreak();
                    stopCurrentSong();
                    while (breakTimeCopy > 0) {
                        updateUiToggleCardAndBreakTimer(true, breakTimeCopy);
                        Thread.sleep(500);
                        breakTimeCopy--;
//                        Log.e(TAG, "initAndStartBgThread: breakTimecopy="+breakTimeCopy );
                    }
                    updateUiToggleCardAndBreakTimer(false, breakTimeCopy);
                    startPlayingOrUpdateSong(Exercise.getRandomSong());
                    updateUiFabMusictoggle();

                }
                closeActivity();


            } catch (Exception e) {
                e.printStackTrace();
            }


        });
        bgThread.start();
    }

    private void closeActivity() {
        runOnUiThread(() -> {
            player.stop();
            player.reset();
            finish(); //todo: show dialog
        });
    }


    public void startPlayingOrUpdateSong(String assetSongName){
        runOnUiThread(() -> {
            try {
                if(player.isPlaying()){
                    stopCurrentSong();
                }
                AssetFileDescriptor afd = getAssets().openFd(assetSongName);
                player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                player.setLooping(true);
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    public void  stopCurrentSong(){
        runOnUiThread(() -> {
            player.stop();
            player.reset();

        });

    }



    public  void updateUiFabMusictoggle(){
        runOnUiThread(() -> {
            if(player.isPlaying()) fabMusic.setImageResource(R.drawable.ic_music_off);
            else fabMusic.setImageResource(R.drawable.ic_music_on);

        });

    }
    private void updateUiToggleCardAndBreakTimer(boolean showCard, int breakTime) {
        Log.e(TAG, "updateUiToggleCardAndBreakTimer: called with params shocard,breaktime="+showCard+","+breakTime );
        runOnUiThread(() -> {
            if (showCard) {
                Log.e(TAG, "updateUiToggleCardAndBreakTimer: setting card visibility true, timer = breaktime" );
                cardBreak.setVisibility(View.VISIBLE);
                tvCardBreak.setText(Exercise.timeToString(breakTime));
            } else {
                Log.e(TAG, "updateUiToggleCardAndBreakTimer: setting carview visibility gone" );
                cardBreak.setVisibility(View.GONE);
            }
        });


    }
    private void updateUiExListCurrentExercize(int exPos) {
        Log.e(TAG, "updateUiExListCurrentExercize: called with exPosition="+exPos );
        runOnUiThread(() -> {
            switch (exPos) {
                case 0: {
                    ivEx1.setBackgroundResource(R.drawable.bg_circle_pink_ffebee);
                    break;
                }
                case 1: {
                    ivEx2.setBackgroundResource(R.drawable.bg_circle_pink_ffebee);
                    break;
                }
                case 2: {
                    ivEx3.setBackgroundResource(R.drawable.bg_circle_pink_ffebee);
                    break;
                }
                case 3: {
                    ivEx4.setBackgroundResource(R.drawable.bg_circle_pink_ffebee);
                    break;
                }
                case 4: {
                    ivEx5.setBackgroundResource(R.drawable.bg_circle_pink_ffebee);
                    break;
                }
            }
        });

    }
    private void updateUiExListFinishedExercize(int i) {
        Log.e(TAG, "updateUiExListFinishedExercize: called with param= i" );

        runOnUiThread(() -> {
            switch (i) {
                case 0: {
                    ivEx1.setBackgroundResource(R.drawable.ic_star);
                    brEx1.setBackgroundResource(R.drawable.ic_star);
                    break;
                }
                case 1: {
                    ivEx2.setBackgroundResource(R.drawable.ic_star);
                    brEx2.setBackgroundResource(R.drawable.ic_star);
                    break;
                }
                case 2: {
                    ivEx3.setBackgroundResource(R.drawable.ic_star);
                    brEx3.setBackgroundResource(R.drawable.ic_star);
                    break;
                }
                case 3: {
                    ivEx4.setBackgroundResource(R.drawable.ic_star);
                    brEx4.setBackgroundResource(R.drawable.ic_star);
                    break;
                }
                case 4: {
                    ivEx5.setBackgroundResource(R.drawable.ic_star);
                    brEx5.setBackgroundResource(R.drawable.ic_star);
                    break;
                }

            }
            Log.e(TAG, "updateUiExListFinishedExercize: setted background color of card exercize to star" );
        });

    }
    private void updateUiRepCountAndTimerUi(int currRentProgress, int maxProgress, int uiRepCount) {
        Log.e(TAG, "updateUiRepCountAndTimerUi: called with params currentprogress,maxprogress,uirepcount="+currRentProgress+','+maxProgress+','+uiRepCount );
        runOnUiThread(() -> {
            float progress = (currRentProgress *100f / maxProgress) ;

            String repsLeft=uiRepCount + " Reps Left";
            String currentExercizeTime=""+currRentProgress;

            Log.e(TAG, "updateUiRepCountAndTimerUi: setting timer ui with data: prgress,repsleft,currExTime="+progress+','+repsLeft+','+currentExercizeTime );

            pcv.setProgress(progress<100?progress:100, true);
            tvRepCount.setText(repsLeft);
            tvCurrExTimeLeft.setText(currentExercizeTime);


        });

    }
    private void updateUiExDetailsCard(Exercise currEx) {
        Log.e(TAG, "updateUiExDetailsCard: called with exercize="+currEx );
        runOnUiThread(() -> {
            ivDetailsImg.setImageResource(currEx.getImageRes());
            tvDetailsTime.setText(Exercise.timeToString(currEx.getTimeInSeconds()) + "/ Rep");
            tvDetails.setText(currEx.getDetails());
            tvTitle.setText(currEx.geteName());

        });
    }



}