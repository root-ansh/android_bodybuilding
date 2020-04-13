package in.curioustools.LCOHomeWorkout.modal;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import in.curioustools.LCOHomeWorkout.R;

public class Exercise implements Parcelable {

    @NonNull
    private String id;
    private String eName;
    private String details;
    private int imageRes;
    private int timeInSeconds;

    public Exercise(String eName, String details, int imageRes, int timeInSeconds) {
        this.id = getDefaultID();
        this.eName = eName;
        this.details = details;
        this.imageRes = imageRes;
        this.timeInSeconds = timeInSeconds;
    }

    public Exercise(@NonNull String id, String eName, String details, int imageRes, int timeInSeconds) {
        this.id = id;
        this.eName = eName;
        this.details = details;
        this.imageRes = imageRes;
        this.timeInSeconds = timeInSeconds;
    }




    @NonNull
    public String getId() {
        return id;
    }

    public String geteName() {
        return eName;
    }

    public String getDetails() {
        return details;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    @NonNull
    public String toString() {
        return "Exercise{" +
                "id='" + id + '\'' +
                ", eName='" + eName + '\'' +
                ", details='" + details + '\'' +
                ", imageRes=" + imageRes +
                ", timeInSeconds=" + timeInSeconds +
                '}';
    }

    public static String getDefaultID() {
        return UUID.randomUUID().toString();
    }


    //---------------------------------------------------------------------------


    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };


    protected Exercise(Parcel in) {
        id = in.readString();
        eName = in.readString();
        details = in.readString();
        imageRes = in.readInt();
        timeInSeconds = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(eName);
        dest.writeString(details);
        dest.writeInt(imageRes);
        dest.writeInt(timeInSeconds);
    }

    //---------------------------------------------------------------------------

    public static String getRandomSong() {
        String[] songsList = {"s1.mp3", "s2.mp3", "s3.mp3", "s4.mp3"};
        int i = (int) Math.abs(new Random().nextInt() % songsList.length);
        return songsList[i];
    }

    public static String timeToString(int timeInSeconds) {

        int sec = timeInSeconds % 60;
        int min = (timeInSeconds / 60) % 60;
        int hours = (timeInSeconds / 60) / 60;

        String s = "";
        if (hours != 0) {
            s += " " + hours + " HRS";
        }
        if (min != 0) {
            s += " " + min + "  MINS";
        }
        s += " " + sec + " SECONDS";
        return s;
    }

    public static List<Exercise> getDefaultExercizeList() {
        Exercise e1 = new Exercise(
                "Push Up",
                "Get down on all fours, placing your hands slightly wider than your shoulders." +
                        "Straighten your arms and legs.Lower your body until your chest nearly touches the " +
                        "floor.Pause, then push yourself back up. REPEAT",
                R.drawable.e1,
                4
        );
        Exercise e2 = new Exercise(
                "knee Crunches",
                "Lie down on your back. Plant your feet on the floor, hip-width apart. Bend " +
                        "your knees and place your arms across your chest. Contract your abs and inhale." +
                        "Exhale and lift your upper body, keeping your head and neck relaxed." +
                        "Inhale and return to the starting position.",
                R.drawable.e2,
                5
        );
        Exercise e3 = new Exercise(
                "Abdominal Crunches",
                "Lie down on your back. Plant your feet on the floor, hip-width apart. Bend " +
                        "your knees and place your arms across your chest. Contract your abs and inhale." +
                        "Exhale and lift your upper body, keeping your head and neck relaxed." +
                        "Inhale and return to the starting position.",
                R.drawable.e3,
                4
        );
        Exercise e4 = new Exercise(
                "Strech Cardio",
                "Stand straight. move your right arm from 90 degree to 180 degree while " +
                        "streching the stomach and keeping your left arm on it. Exhale and inhale likewise",
                R.drawable.e4,
                6
        );
        Exercise e5 = new Exercise(
                "Strech Cardio",
                "Lie on your back, legs straight and together. " +
                        "Keep your legs straight and lift them all the way up to the ceiling until your butt comes off the floor. " +
                        "Slowly lower your legs back down till they're just above the floor. Hold for a moment." +
                        " Raise your legs back up. Repeat.",
                R.drawable.e5,
                5
        );
        Exercise e6 = new Exercise(
                "Dumbel Pushups",
                "Position your dumbbells vertically underneath your upper chest. The outer" +
                        " edges of the dumbbells should line up with the outer edges of your chest. " +
                        "Get into a pushup position with your hands on each dumbbell. Lower yourself" +
                        " down as far as you can go, keeping your elbows tucked, then return to start",
                R.drawable.e6,
                5
        );

        Exercise e7 = new Exercise(
                "Dumbel Pushups",
                "Stand with a dumbbell in each hand with palms facing forward. Curl the " +
                        "weights as you turn your wrists so that your palms face away at the top.",
                R.drawable.e7,
                6
        );
        Exercise e8 = new Exercise(
                "Swiss Ball Pushups",
                "Lay with your chest on the stability ball. Place your hands on the ball at " +
                        "the sides of your chest. They will be shoulder-width apart. Place your toes" +
                        " on the floor, legs straight. Push your body up until your arms are almost " +
                        "straight (do not lock your elbows)",
                R.drawable.e8,
                4
        );
        Exercise e9 = new Exercise(
                "One Leg Balance",
                "stand on one leg without support of the upper extremities or bracing of the " +
                        "unweighted leg against the stance leg",
                R.drawable.e9,
                3
        );


        Exercise e11 = new Exercise(
                "Dumbel Press",
                "Lie back on a bench holding a dumbbell in each hand just to the sides of " +
                        "your shoulders.Press the weights above your chest by extending your elbows" +
                        "until your arms are straight, then bring the weights back down slowly.",
                R.drawable.e11,
                4
        );
        Exercise e13 = new Exercise(
                "Shoulder Press",
                "Stand tall with your feet hip-width apart, and hold a pair of dumbbells in " +
                        "front of your shoulders with your elbows tucked and palms facing each other." +
                        "Press the weights directly above your shoulders until your arms are straight" +
                        " and your biceps are next to your ears.Pause, and then lower the weights back " +
                        "to the starting position.",
                R.drawable.e13,
                6
        );
        Exercise e14 = new Exercise(
                "Crunches",
                "Lie on your back on an exercise mat.Bend your knees so your feet are " +
                        "flat on the floor.Cross your arms in front of your chest.Lift your shoulder" +
                        "blades off of the mat with a smooth, controlled motion.REPEAT",
                R.drawable.e14,
                2
        );


        return new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e11, e13, e14));

    }

    public static List<Exercise> getRandom5Exercizes() {
        List<Exercise> exercises = getDefaultExercizeList();

        while (exercises.size() > 5) {
            int rIdx = (int) ((System.currentTimeMillis() + Math.random()) % exercises.size());
            exercises.remove(rIdx);
        }

        return exercises;

    }

    public static List<Exercise> getDayWiseList(int dayNum) {
        return getRandom5Exercizes();//todo change it to today log
    }


    public static List<Exercise> getRandom5Exercizes(int day) {
        // day=0,1,2,3,4,5

        List<Exercise> exercises = getDefaultExercizeList();

        while (exercises.size() > 5) {
            int rIdx = (int) ((System.currentTimeMillis() + Math.random()) % exercises.size());
            exercises.remove(rIdx);
        }

        return exercises;

    }



    public static String getToday() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current dateTime => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ROOT);

        return df.format(c.getTime());

    }

    public static String getRandomDate() {
        int date = Math.abs(new Random().nextInt()%31);
        int month = Math.abs(new Random().nextInt()%12);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, "+date+" "+month+" yyyy", Locale.ROOT);

        return df.format(c.getTime());
    }


    public static int getTodayNum() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current dateTime => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("EEE", Locale.ROOT);

        String day = df.format(c.getTime());

        if (day.equals("Mon")) {
            return 1;
        } else if (day.equals("Tue")) {
            return 2;
        } else if (day.equals("Wed")) {
            return 3;
        } else if (day.equals("Thu")) {
            return 4;
        } else if (day.equals("Fri")) {
            return 5;
        } else if (day.equals("Sat")) {
            return 6;
        } else {
            return 7;
        }
    }

    public static String getExerciseTypeString() {
        int dayNum = getTodayNum();
        switch (dayNum) {
            case 1:
                return "Shoulders";
            case 2:
                return "Biceps";
            case 3:
                return "Triceps";
            case 4:
                return "Chest";
            case 5:
                return "Legs";
            case 6:
                return "Cardio";
            default:
                return "Mix Exercise";

        }
    }



}
