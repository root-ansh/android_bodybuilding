package in.curioustools.LCOHomeWorkout.modal;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class DailyLog {

    @NonNull
    private String id;
    private String date;
    private int exerciseCount;

    public DailyLog(@NonNull String id, String date, int exerciseCount) {
        this.id = id;
        this.date = date;
        this.exerciseCount = exerciseCount;
    }
    public DailyLog(String date, int exerciseCount) {
        this.id = getDefaultID();
        this.date = date;
        this.exerciseCount = exerciseCount;
    }


    @NonNull
    public String getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public int getExerciseCount() {
        return exerciseCount;
    }


    public void setId(@NonNull String id) {
        this.id = id;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setExerciseCount(int exerciseCount) {
        this.exerciseCount = exerciseCount;
    }

    @Override @NonNull
    public String toString() {
        return "DailyLog{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", exerciseCount=" + exerciseCount +
                '}';
    }

    public static String getDefaultID() {
        return "tmp";
//        return UUID.randomUUID().toString();
    }




}


