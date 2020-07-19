package com.snake.shaadiproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.snake.shaadiproject.data.LocalData;

import java.util.ArrayList;
import java.util.List;

public class MatchedAdapter extends RecyclerView.Adapter<MatchedAdapter.Holder> {

    private Context context;
    private List<LocalData> dataList = new ArrayList<>();

    public MatchedAdapter(Context context, List<LocalData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewLocation;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textViewName = itemView.findViewById(R.id.name);
            textViewLocation = itemView.findViewById(R.id.city);



        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.textViewName.setText(dataList.get(position).getName());
        holder.textViewLocation.setText(dataList.get(position).getLocation());

        Glide.with(context).load(dataList.get(position).getImage()).into(holder.imageView);

//        Log.d("Adapter", "onBindViewHolder: "+dataList.get(position).getImage()+dataList.get(position).getName()+dataList.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
