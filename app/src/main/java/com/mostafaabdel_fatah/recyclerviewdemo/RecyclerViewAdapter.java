package com.mostafaabdel_fatah.recyclerviewdemo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
            implements View.OnClickListener {

    OnItemClickListener lisneter;
    Context context;
    ArrayList<Item> arrayList;
    GridLayoutManager gridLayoutManager;

    public  RecyclerViewAdapter(Context context , ArrayList<Item> list , GridLayoutManager gridLayoutManager){
        this.context = context;
        this.arrayList = list;
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (gridLayoutManager.getSpanCount() == 1) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2, parent, false);
        }
        view.setOnClickListener(this);
        RecyclerViewAdapter.MyViewHolder viewHolder = new RecyclerViewAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        Item item = arrayList.get(position);
        holder.textView.setText(item.getText());
        holder.itemView.setTag(arrayList.get(position));
        //holder.imageView.setImageResource(item.getImage());
       //holder.imageView.setImageBitmap(null);
        //Picasso.with(holder.imageView.getContext()).load(item.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    @Override
    public void onClick(View view) {
        if(lisneter != null){
            lisneter.onItemClick(view,(Item) view.getTag());
        }
    }


    public  static  class  MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public MyViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textView  = (TextView)  view.findViewById(R.id.textView);
        }
    }
    public interface OnItemClickListener{
        public void onItemClick(View view,Item item);
    }

}
