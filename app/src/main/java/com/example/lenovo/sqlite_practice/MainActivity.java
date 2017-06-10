package com.example.lenovo.sqlite_practice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Restaurant> mList = new ArrayList<>();
    private Sqlite dbHelper;
    private View emptyView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_restaurant :{
                Intent intent = new Intent(MainActivity.this,AddRestaurantActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new Sqlite(this,"MeiTuan1.db",null,1);
        initialList();


        ListView listView = (ListView)findViewById(R.id.res_listview);
        TextView restaurant_name = (TextView)findViewById(R.id.restaurant_name);
        TextView introduction = (TextView)findViewById(R.id.introduction);
        TextView address = (TextView)findViewById(R.id.address);

        emptyView = findViewById(R.id.empty);
        listView.setEmptyView(emptyView);

        final ListViewAdapter adapter = new ListViewAdapter(MainActivity.this,R.layout.list_item,mList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = mList.get(position);

                /* give the id of restaurant to get the details */
                Intent intent = new Intent(MainActivity.this,ClickRestaurantActivity.class);
                intent.putExtra("RestaurantId",restaurant.getId());
                startActivity(intent);
            }
        });

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initialList();
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });


    }

    /*initial the List*/
    public void initialList(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Restaurant restaurant = new Restaurant();

        Cursor cursor = database.query("Restaurant",null,null,null,null,null,null);
        /* to search all the datas in the table Restaurant*/
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                restaurant.setName(name);
                String address = cursor.getString(cursor.getColumnIndex("address"));
                restaurant.setAddress(address);
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                restaurant.setPhone(phone);
                String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
                restaurant.setIntroduction(introduction);
                String recount = cursor.getString(cursor.getColumnIndex("recount"));
                restaurant.setRecount(recount);
                String photoPath = cursor.getString(cursor.getColumnIndex("photoPath"));
                restaurant.setPhotoPath(photoPath);
                String time = cursor.getString(cursor.getColumnIndex("time"));
                restaurant.setTime(time);
                float priceAtLeast = cursor.getFloat(cursor.getColumnIndex("priceAtLeast"));
                restaurant.setPriceAtLeast(priceAtLeast);
                float sendFee = cursor.getFloat(cursor.getColumnIndex("sendFee"));
                restaurant.setSendFee(sendFee);
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                restaurant.setId(id);

                mList.add(restaurant);
            }while(cursor.moveToNext());
            cursor.close();
        }

    }


}
