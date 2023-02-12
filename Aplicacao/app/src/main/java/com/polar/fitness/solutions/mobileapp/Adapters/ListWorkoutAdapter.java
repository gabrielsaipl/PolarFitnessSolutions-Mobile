package com.polar.fitness.solutions.mobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.Models.Workout;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class ListWorkoutAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    ArrayList<Workout> workouts;
    Context context;

    public ListWorkoutAdapter(Context context, ArrayList<Workout> workouts) {
        this.context = context;
        this.workouts = workouts;
    }

    @Override
    public int getCount() {
        return workouts.size();
    }

    @Override
    public Object getItem(int position) {
        return workouts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return workouts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_list_workout, null);
        }
        ViewHolderList viewHolder = (ViewHolderList) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new ViewHolderList(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.update(workouts.get(position));
        return convertView;
    }

    private static class ViewHolderList{
        private final TextView tvlistWorkoutsName;

        public ViewHolderList(View view){
            tvlistWorkoutsName = view.findViewById(R.id.tvListWorkoutsName);
        }

        public void update(Workout workout){
            tvlistWorkoutsName.setText(workout.getName());
        }
    }

}
