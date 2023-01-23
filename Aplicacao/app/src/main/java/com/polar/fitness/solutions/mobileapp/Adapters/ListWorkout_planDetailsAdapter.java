package com.polar.fitness.solutions.mobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.Models.Exercise;
import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;
import com.polar.fitness.solutions.mobileapp.Models.Workout_Plan_Exercise_Relation;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class ListWorkout_planDetailsAdapter extends BaseAdapter {

    private ArrayList<Exercise> list1;
    private Context context;
    private LayoutInflater inflater;


    public ListWorkout_planDetailsAdapter(Context context, ArrayList<Exercise> list) {
        this.context = context;
        this.list1 = list;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list1.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //prenche cada quadrado
        if(convertView == null)
            convertView = inflater.inflate(R.layout.item_list_workout_plan_exercise, null);

        //preenchimento da view
        ViewHolderList viewHolder = (ListWorkout_planDetailsAdapter.ViewHolderList) convertView.getTag();
        if(viewHolder == null)
        {
            viewHolder = new ViewHolderList(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.update(list1.get(position));
        return convertView;
    }

    private class ViewHolderList
    {

        private TextView tvNome, tvMaxReps, tvMinReps, tvSets;

        public ViewHolderList(View view)
        {
            tvNome = view.findViewById(R.id.tv_nome);
            tvMaxReps = view.findViewById(R.id.tv_maxrep);
            tvMinReps = view.findViewById(R.id.tv_minrep);
            tvSets = view.findViewById(R.id.tv_sets);
        }


        public void update(Exercise exercise) {
            tvNome.setText(exercise.getExercise_name());
            String max_reps = String.valueOf(exercise.getMax_rep());
            tvMaxReps.setText("Max_repetições: " + max_reps);
            String min_reps = String.valueOf(exercise.getMin_rep());
            tvMinReps.setText("Min_repetições: " + min_reps);
            String sets = String.valueOf(exercise.getSets());
            tvSets.setText("Séries: " + sets);
        }
    }
}
