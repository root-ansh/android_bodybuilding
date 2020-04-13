package in.curioustools.LCOHomeWorkout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import in.curioustools.LCOHomeWorkout.modal.Exercise;
import in.curioustools.LCOHomeWorkout.screens.start_exercise.StartExerciseActivity;

public class MyDialogs {


    private static int curRepCount = 10;
    private static AlertDialog repCountdialog = null;

    public static void showRepCountDialogue(Context ctx, LayoutInflater inflater, List<Exercise> exerciseList) {

        View v = inflater.inflate(R.layout.ll_dialog_repcount, null, false);

        TextView tvRepCount = v.findViewById(R.id.tv_d_repcount);

        ImageButton ivAdd = v.findViewById(R.id.ibt_add);
        ImageButton ivminus = v.findViewById(R.id.ibt_minus);
        ivAdd.setOnClickListener(v1 -> {
            curRepCount = (curRepCount >= 30) ? 30 : curRepCount + 5;
            tvRepCount.setText("" + curRepCount);
        });
        ivminus.setOnClickListener(v1 -> {
            curRepCount = (curRepCount <= 0) ? 0 : curRepCount - 5;
            tvRepCount.setText("" + curRepCount);
        });

        TextView tvStart = v.findViewById(R.id.tv_d_start);
        tvStart.setOnClickListener(v12 -> {
            if (repCountdialog == null) return;
            Intent i = new Intent(ctx, StartExerciseActivity.class);
            i.putParcelableArrayListExtra(StartExerciseActivity.KEY_ExList, new ArrayList<>(exerciseList));
            i.putExtra(StartExerciseActivity.KEY_REPCOUNT, curRepCount);
            ctx.startActivity(i);

            repCountdialog.cancel();


        });

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setView(v);
        builder.setCancelable(true);

        repCountdialog = builder.create();
        repCountdialog.show();
    }

    private static AlertDialog itemDetailsDialogue = null;
    public static void showItemDetailsDialog(Context ctx, LayoutInflater inflater, Exercise exc) {
        View v = inflater.inflate(R.layout.ll_eachrow_exercise, null, false);
        ImageView ivInfoBubble = v.findViewById(R.id.iv_info_bubble);
        TextView tvtitle = v.findViewById(R.id.tv_infobub_title);
        TextView tvDetails = v.findViewById(R.id.tv_infobub_details);
        TextView tvRepCount = v.findViewById(R.id.tv_infobub_reptime);
        ImageView ivCancel = v.findViewById(R.id.iv_cancel);

        ivInfoBubble.setImageResource(exc.getImageRes());
        tvtitle.setText(exc.geteName());
        tvDetails.setText(exc.getDetails());
        tvRepCount.setText(Exercise.timeToString(exc.getTimeInSeconds()) + " Per Rep");
        ivCancel.setVisibility(View.VISIBLE);
        ivCancel.setOnClickListener(v1 -> {
            if (itemDetailsDialogue == null) {
                return;
            }
            itemDetailsDialogue.cancel();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setView(v);
        builder.setCancelable(true);

        itemDetailsDialogue = builder.create();
        itemDetailsDialogue.show();


    }

}
