package com.polar.fitness.solutions.mobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.Volley;
import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class ListNutrition_planAdapter extends BaseAdapter {
    private ArrayList<Nutrition_plan> listNutrition_plan;
    private Context contexto;
    private LayoutInflater inflater;

    public ListNutrition_planAdapter(Context context, ArrayList<Nutrition_plan> list)
    {
        this.contexto = context;
        this.listNutrition_plan = list;
    }

    @Override
    public int getCount() {
        return listNutrition_plan.size();
    }

    @Override
    public Object getItem(int position) {
        return listNutrition_plan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listNutrition_plan.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //prenche cada quadrado
        if(convertView == null)
            convertView = inflater.inflate(R.layout.item_list_nutrition_plan, null);

            //preenchimento da view
            ViewHolderList viewHolder = (ViewHolderList) convertView.getTag();
            if(viewHolder == null)
            {
                viewHolder = new ViewHolderList(convertView);
                convertView.setTag(viewHolder);
            }
        viewHolder.update(listNutrition_plan.get(position));
        return convertView;
    }

    private class ViewHolderList
    {

        private TextView tvTitulo, tvContent;

        public ViewHolderList(View view)
        {
            tvTitulo = view.findViewById(R.id.tvNutrition_plan_title);
            tvContent = view.findViewById(R.id.tvNutrition_planContent);
        }

        public void update(Nutrition_plan nutrition_plan)
        {
            tvTitulo.setText(nutrition_plan.getNutritionname());
            tvContent.setText(nutrition_plan.getContent());
        }

    }
}
