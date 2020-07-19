package com.snake.shaadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.snake.shaadiproject.data.LocalData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static com.snake.shaadiproject.SQLiteDBHelper.TABLE_NAME;

public class AllMatchedUsers extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView imageViewNoData;
    MatchedAdapter matchedAdapter;
    List<LocalData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_matched_users);

        recyclerView = findViewById(R.id.recycleView);
        imageViewNoData = findViewById(R.id.noData);

        matchedAdapter = new MatchedAdapter(this, dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(matchedAdapter);



        readFromDB();

    }


    private void readFromDB() {
        SQLiteDatabase database = new SQLiteDBHelper(this).getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);

        dataList.clear();
        if (cursor.moveToFirst()) {
            do {
//                Log.d(this.getClass().getSimpleName(), "readFromDB: " + cursor.getColumnName(0) + cursor.getColumnName(2) + cursor.getColumnName(3) + cursor.getColumnName(4));
                try {
//                    Log.d(this.getClass().getSimpleName(), "writeIntoJsonFile: " + cursor.getCount());

                    dataList.add(new LocalData(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
                    matchedAdapter.notifyDataSetChanged();
                } catch (Exception e) {
//                    Log.d(this.getClass().getSimpleName(), "writeIntoJsonFile: " + e.getMessage());
                    matchedAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.GONE);
                    imageViewNoData.setVisibility(View.VISIBLE);
                }
            } while (cursor.moveToNext());

        } else {
            matchedAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.GONE);
            imageViewNoData.setVisibility(View.VISIBLE);
        }
        cursor.close();
        database.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
