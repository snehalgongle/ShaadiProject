package com.snake.shaadiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.snake.shaadiproject.data.Results;

import java.util.ArrayList;
import java.util.List;

public class SwipeStackAdapter extends BaseAdapter {


    private Context context;
    private List<Results> resultsList = new ArrayList<>();
    public int mPosition;

    public SwipeStackAdapter(Context context, List<Results> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public int getCount() {
        return resultsList.size();
    }

    @Override
    public Results getItem(int position) {
        return resultsList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        Log.d("positions", "getView: "+position);
        this.mPosition = position;
        convertView = LayoutInflater.from(context).inflate(R.layout.card, parent, false);


        TextView textViewCard = convertView.findViewById(R.id.textViewCard);
        TextView textViewCity = convertView.findViewById(R.id.locationNameTxt);
        ImageView imageView = convertView.findViewById(R.id.profileImageView);

        Glide.with(context).load(resultsList.get(position).getPicture().getLarge()).into(imageView);
        if(((MainActivity)context).isNetworkAvailable()){
            textViewCard.setText(resultsList.get(position).getName().getTitle() + " " + resultsList.get(position).getName().getFirst() + " " + resultsList.get(position).getName().getLast() + ",  " + resultsList.get(position).getDob().getAge());
            textViewCity.setText(resultsList.get(position).getLocation().getCity() + ", " + resultsList.get(position).getLocation().getCountry());

        }else{
            textViewCard.setText( resultsList.get(position).getName().getFirst());
            textViewCity.setText(resultsList.get(position).getLocation().getCity());
        }


        return convertView;
    }

    public void saveToDb(int position) {
        SQLiteDatabase database = new SQLiteDBHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_NAME, resultsList.get(position).getName().getFirst()+" "+resultsList.get(position).getName().getLast());
        values.put(SQLiteDBHelper.COLUMN_GENDER, resultsList.get(position).getGender());
        values.put(SQLiteDBHelper.COLUMN_AGE,  resultsList.get(position).getDob().getAge());
        values.put(SQLiteDBHelper.COLUMN_IMAGE,  resultsList.get(position).getPicture().getLarge());
        values.put(SQLiteDBHelper.COLUMN_LOC,  resultsList.get(position).getLocation().getCity()+", "+ resultsList.get(position).getLocation().getCountry());
        database.insert(SQLiteDBHelper.TABLE_NAME, null, values);

        database.close();

    }



}