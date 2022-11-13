package com.example.mainlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Game> {
    Context context;
    List<Game> objects;
    public GameAdapter(Context context, int resource, int textViewResourceId, List<Game> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context=context;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.lv_row_layout,parent,false);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvSubTitle = view.findViewById(R.id.tvSubTitle);
        Game temp = objects.get(position);
        tvTitle.setText(temp.getTitle());
        tvSubTitle.setText(temp.getSubTitle());

        return view;
    }
}
