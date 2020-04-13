package in.curioustools.LCOHomeWorkout;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import in.curioustools.LCOHomeWorkout.modal.DailyLog;
import in.curioustools.LCOHomeWorkout.modal.Exercise;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        System.out.println(timeToString(30));
        System.out.println(timeToString(300));
        System.out.println(timeToString(3000));
        System.out.println(timeToString(30000));


        System.out.println(timeToString(60));
        System.out.println(timeToString(600));
        System.out.println(timeToString(6000));


        assertEquals(4, 2 + 2);
    }

    public static String timeToString(int timeInSeconds) {

        int sec = timeInSeconds % 60;
        int min = (timeInSeconds / 60) % 60;
        int hours = (timeInSeconds / 60) / 60;

        String s = "";
        if (hours != 0) {
            s += " " + hours + " HRS";
        }
        s += " " + min + "  MINS";
        s += " " + sec + " SECS";
        return s;
    }


    @Test
    public void x() {
        fun();
        assertEquals(1, 1);
    }

    public static void fun() {
        int max = 10;
        for (int i = max; i >= 0; i -= 1) {
            System.out.println(getProgress(i, max));
        }
        System.out.println("--------------------------------");

        max = 300;
        for (int i = max; i >= 0; i -= 17) {
            System.out.println(getProgress(i, max));
        }


    }

    private static int getProgress(int curr, int max) {
        return curr/max*100;
    }


    @Test
    public void test() {

        Object a = "Hello";
        Object b = new DailyLog("Hi",4);
        System.out.println(a.getClass()==String.class);
        System.out.println(b.getClass()==String.class);
        System.out.println(b.getClass()==DailyLog.class);



        assertEquals(4, 2 + 2);
    }

    public static String getRandomDate() {
        int date = Math.abs(new Random().nextInt()%31);
        int month = Math.abs(new Random().nextInt()%12);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(date+" "+month+" yyyy", Locale.ROOT);

        return df.format(c.getTime());
    }

}