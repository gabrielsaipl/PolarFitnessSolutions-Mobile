package com.polar.fitness.solutions.mobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class ListWorkout_planAdapter extends BaseAdapter {
    private ArrayList<Workout_plan> listWorkout_plan;
    private Context context;
    private LayoutInflater inflater;

    public ListWorkout_planAdapter(Context context , ArrayList<Workout_plan> list){
        this.context = context;
        this.listWorkout_plan = list;
    }

    @Override
    public int getCount(){
        return listWorkout_plan.size();
    }

    @Override
    public Object getItem(int position){
        return listWorkout_plan.get(position);
    }

    @Override
    public long getItemId(int position){
        return listWorkout_plan.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_list_workout_plan, null);
        }
        ViewHolderList viewHolder = (ViewHolderList) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new ViewHolderList(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.update(listWorkout_plan.get(position));
        return convertView;
    }

    public class ViewHolderList{
        private TextView tvWorkoutName;

        public ViewHolderList(View view){
            tvWorkoutName = view.findViewById(R.id.tvWorkoutName);
        }

        public void update(Workout_plan workout_plan){
            tvWorkoutName.setText(workout_plan.getWorkout_name());
        }
    }
}
