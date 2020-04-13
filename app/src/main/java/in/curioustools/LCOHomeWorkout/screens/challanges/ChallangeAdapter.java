package in.curioustools.LCOHomeWorkout.screens.challanges;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.curioustools.LCOHomeWorkout.R;
import in.curioustools.LCOHomeWorkout.modal.Exercise;

public class ChallangeAdapter extends RecyclerView.Adapter<ChallangeAdapter.ChalHolder> {
    private List<Exercise> exerciseList = new ArrayList<>();
    @Nullable private ChalRvItemClickListener listener;

    @NonNull
    @Override
    public ChalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ll_eachrow_chal_exercise,parent,false);
        return new ChalHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChalHolder holder, int position) {
        holder.bindData(exerciseList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }


    //==========================================================================

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        if(exerciseList==null){exerciseList= new ArrayList<>();}
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }
    public void setListener(@NonNull ChalRvItemClickListener listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    //===========================================================================

    public static class ChalHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivExImg;
        public ChalHolder(@NonNull View v) {
            super(v);
            ivExImg =v.findViewById(R.id.iv_ex_image);
            tvTitle =v.findViewById(R.id.tv_ex_title);
        }

        public void bindData(Exercise e, @Nullable ChalRvItemClickListener listener){
            tvTitle.setText(e.geteName());
            ivExImg.setImageResource(e.getImageRes());
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(e);
                }
            });
        }

    }

    public interface ChalRvItemClickListener {
         void onClick(Exercise exercise);
    }
}
