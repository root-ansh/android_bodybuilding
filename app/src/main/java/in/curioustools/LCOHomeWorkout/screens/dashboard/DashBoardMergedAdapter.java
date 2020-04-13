package in.curioustools.LCOHomeWorkout.screens.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.curioustools.LCOHomeWorkout.R;
import in.curioustools.LCOHomeWorkout.modal.DailyLog;
import in.curioustools.LCOHomeWorkout.modal.Exercise;

public class DashBoardMergedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> objectList = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(viewType==0){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ll_eachrow_header, parent, false);
            return new DBHolderHeader(v);
        }
        else if(viewType==1){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ll_eachrow_dailylog, parent, false);
            return new DBHolderDailyLog(v);

        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ll_eachrow_exercise, parent, false);
            return new DBHolderExercise(v);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            ((DBHolderHeader)holder).bindData((String) objectList.get(position));
        }
        else if(getItemViewType(position)==1){
            ((DBHolderDailyLog)holder).bindData((DailyLog) objectList.get(position));
        }
        else {
            ((DBHolderExercise)holder).bindData((Exercise) objectList.get(position));

        }


    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(objectList.get(position).getClass()==String.class){
            return 0;
        }
        else if(objectList.get(position).getClass()==DailyLog.class){
            return 1;
        }
        else if(objectList.get(position).getClass()==Exercise.class){
            return 2;
        }

        return -1;
    }

    //==========================================================================

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        if (objectList == null) {
            objectList = new ArrayList<>();
        }
        this.objectList = objectList;
        notifyDataSetChanged();
    }
    //===========================================================================

    public static class DBHolderExercise extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDetails, tvTimer;
        ImageView ivExPic;

        public DBHolderExercise(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_infobub_title);
            tvDetails = itemView.findViewById(R.id.tv_infobub_details);
            tvTimer =itemView.findViewById(R.id.tv_infobub_reptime);
            ivExPic = itemView.findViewById(R.id.iv_info_bubble);

        }

        public void bindData(Exercise e) {
            tvTitle.setText(e.geteName());
            tvDetails.setText(e.getDetails());
            tvTimer.setText(Exercise.timeToString(e.getTimeInSeconds()));
            ivExPic.setImageResource(e.getImageRes());

        }
    }
    public static class DBHolderDailyLog extends RecyclerView.ViewHolder {
        TextView tvDate;
        ImageView ivStar1, ivStar2, ivStar3, ivStar4, ivMus5;

        public DBHolderDailyLog(@NonNull View itemView) {
            super(itemView);

            tvDate =itemView.findViewById(R.id.tv_dl_date);
            ivStar1 =itemView.findViewById(R.id.ic_star1);
            ivStar2 =itemView.findViewById(R.id.ic_star2);
            ivStar3 =itemView.findViewById(R.id.ic_star3);
            ivStar4 =itemView.findViewById(R.id.ic_star4);
            ivMus5 =itemView.findViewById(R.id.ic_star5);


        }

        public void bindData(DailyLog d) {
            tvDate.setText(""+d.getDate());
            switch (d.getExerciseCount()) {
                case 0: {
                    ivStar1.setImageResource(R.drawable.ic_star);
                    ivStar2.setImageResource(R.drawable.ic_star_grey);
                    ivStar3.setImageResource(R.drawable.ic_star_grey);
                    ivStar4.setImageResource(R.drawable.ic_star_grey);
                    ivMus5.setImageResource(R.drawable.ic_muscle_flat_gray);

                    return;
                }
                case 1: {
                    ivStar1.setImageResource(R.drawable.ic_star);
                    ivStar2.setImageResource(R.drawable.ic_star);
                    ivStar3.setImageResource(R.drawable.ic_star_grey);
                    ivStar4.setImageResource(R.drawable.ic_star_grey);
                    ivMus5.setImageResource(R.drawable.ic_muscle_flat_gray);
                    return;
                }
                case 2: {
                    ivStar1.setImageResource(R.drawable.ic_star);
                    ivStar2.setImageResource(R.drawable.ic_star);
                    ivStar3.setImageResource(R.drawable.ic_star);
                    ivStar4.setImageResource(R.drawable.ic_star_grey);
                    ivMus5.setImageResource(R.drawable.ic_muscle_flat_gray);
                    return;
                }
                case 3: {
                    ivStar1.setImageResource(R.drawable.ic_star);
                    ivStar2.setImageResource(R.drawable.ic_star);
                    ivStar3.setImageResource(R.drawable.ic_star);
                    ivStar4.setImageResource(R.drawable.ic_star);
                    ivMus5.setImageResource(R.drawable.ic_muscle_flat_gray);

                    return;
                }
                default: {
                    ivStar1.setImageResource(R.drawable.ic_star);
                    ivStar2.setImageResource(R.drawable.ic_star);
                    ivStar3.setImageResource(R.drawable.ic_star);
                    ivStar4.setImageResource(R.drawable.ic_star);
                    ivMus5.setImageResource(R.drawable.ic_muscle_flat_yellow);
                }


            }

        }


    }
    public static class DBHolderHeader extends RecyclerView.ViewHolder {
        TextView tvHeader;

        public DBHolderHeader(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tv_header);
        }

        public void bindData(String s) {
            tvHeader.setText(s);

        }


    }


}
