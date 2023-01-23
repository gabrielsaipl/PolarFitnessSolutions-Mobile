package com.polar.fitness.solutions.mobileapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.polar.fitness.solutions.mobileapp.Models.Workout_Plan_Exercise_Relation;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;

import java.util.ArrayList;

public class Workout_Plan_Exercise_RelationAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Workout_Plan_Exercise_Relation> list1;

    public Workout_Plan_Exercise_RelationAdapter(Context context, ArrayList<Workout_Plan_Exercise_Relation> list) {
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
        return null;
    }
}
