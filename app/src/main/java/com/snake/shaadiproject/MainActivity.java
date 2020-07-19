package com.snake.shaadiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.snake.shaadiproject.data.Dob;
import com.snake.shaadiproject.data.LocalData;
import com.snake.shaadiproject.data.Location;
import com.snake.shaadiproject.data.Name;
import com.snake.shaadiproject.data.Picture;
import com.snake.shaadiproject.data.Results;
import com.snake.shaadiproject.data.Root;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

import static com.snake.shaadiproject.SQLiteDBHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    List<Results> resultsList = new ArrayList<>();
    SwipeStackAdapter stackAdapter;
    SwipeStack swipeStack;
    ImageView imageViewNoData;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        imageViewNoData = findViewById(R.id.noData);
        layout=findViewById(R.id.layout);

        stackAdapter = new SwipeStackAdapter(MainActivity.this, resultsList);

        swipeStack.setAdapter(stackAdapter);
        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

            }

            @Override
            public void onViewSwipedToRight(int position) {
                if(isNetworkAvailable()) {
                    stackAdapter.saveToDb(stackAdapter.mPosition);
                }
            }

            @Override
            public void onStackEmpty() {
                loadHeroList();
                swipeStack.resetStack();
            }
        });

        ImageView imageViewRight = findViewById(R.id.acceptBtn);
        imageViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeStack.swipeTopViewToRight();
            }
        });

        ImageView imageViewLeft = findViewById(R.id.rejectBtn);
        imageViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeStack.swipeTopViewToLeft();
            }
        });

        if (isNetworkAvailable()) {
            loadHeroList();
        } else {
            Toast.makeText(this, "No Internet Available", Toast.LENGTH_SHORT).show();
            readFromDB();
        }
    }


    private void loadHeroList() {
        AndroidNetworking.get("https://randomuser.me/api/?results=10")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultsList.clear();
                        Gson gson = new Gson();
                        Root root = gson.fromJson(response.toString(), Root.class);
                        resultsList.addAll(root.getResults());
                        stackAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        swipeStack.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                        imageViewNoData.setVisibility(View.VISIBLE);
                    }
                });

    }


    private void readFromDB() {
        SQLiteDatabase database = new SQLiteDBHelper(this).getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);

        resultsList.clear();
        if (cursor.moveToFirst()) {
            do {
//                Log.d(this.getClass().getSimpleName(), "readFromDB: " + cursor.getColumnName(0) + cursor.getColumnName(2) + cursor.getColumnName(3) + cursor.getColumnName(4));
                try {
//                    Log.d(this.getClass().getSimpleName(), "writeIntoJsonFile: " + cursor.getCount());

                    resultsList.add(new Results(cursor.getString(2), new Name(cursor.getString(1)), new Location(cursor.getString(5)), new Dob(cursor.getString(4)), new Picture(cursor.getString(3))));
                    stackAdapter.notifyDataSetChanged();
                } catch (Exception e) {
//                    Log.d(this.getClass().getSimpleName(), "writeIntoJsonFile: " + e.getMessage());
                    stackAdapter.notifyDataSetChanged();
                    swipeStack.setVisibility(View.GONE);
                    layout.setVisibility(View.GONE);
                    imageViewNoData.setVisibility(View.VISIBLE);

                }
            } while (cursor.moveToNext());

        } else {
            stackAdapter.notifyDataSetChanged();
            swipeStack.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
            imageViewNoData.setVisibility(View.VISIBLE);
        }
        cursor.close();
        database.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu) {
            startActivity(new Intent(this, AllMatchedUsers.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

