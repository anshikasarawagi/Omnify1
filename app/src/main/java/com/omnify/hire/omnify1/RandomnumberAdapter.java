package com.omnify.hire.omnify1;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by DELL on 27-07-2017.
 */

public class RandomnumberAdapter extends RecyclerView.Adapter<RandomnumberAdapter.RandomNoViewholder> {
    private AppCompatActivity activity;
    private int[] list;
    private LayoutInflater inflater;
    public RandomnumberAdapter(AppCompatActivity activity, int[] list)
    {
        this.activity=activity;
        this.list=list;
        inflater=activity.getLayoutInflater();
    }

    @Override
    public RandomNoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=(View)inflater.inflate(R.layout.itemview_randomnumber,parent,false);
        return new RandomnumberAdapter.RandomNoViewholder(view);
    }

    @Override
    public void onBindViewHolder(RandomNoViewholder holder, int position) {
holder.text.setText(String.valueOf(list[position]));
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class RandomNoViewholder extends RecyclerView.ViewHolder{
    TextView text;
        public RandomNoViewholder(View itemView) {
            super(itemView);
             text=(TextView)itemView.findViewById(R.id.text);
        }
    }
}
