package com.example.lenovo.sqlite_practice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClickRestaurantActivity extends AppCompatActivity {

    private List<Dish> mList = new ArrayList<>();
    private Sqlite dbHelper;
    private int RestaurantId;
    private int orderedAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_restaurant);


        Intent intent = getIntent();
        RestaurantId = intent.getIntExtra("RestaurantId",0);


        dbHelper = new Sqlite(this,"MeiTuan1.db",null,1);
        initialDishList(RestaurantId);

        ListView listView = (ListView)findViewById(R.id.listview);
        ListViewAdapterforDishes adapterforDishes = new ListViewAdapterforDishes(this,R.layout.dishes_item,mList);
        adapterforDishes.setOnButtonClickListener(new AddDishClickListener() {
            @Override
            public void onClick(View view, int dishId) {
                /*addInOrder(dishId);*/
               /* TextView textView = view.findViewById(R.id.dish_amount);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Dishes",null,"id = dishId",null,null,null,null);

                textView.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("orderedAmount")) ));*/
            }
        });


    }

    /*public void addInOrder(int dishId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("")
    }*/

    public void initialDishList(int ResId){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("Dishes",null,"restaurantId = ResId",null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Dish dish = new Dish();
                dish.setId(cursor.getInt(cursor.getColumnIndex("id")));
                dish.setDish_name(cursor.getString(cursor.getColumnIndex("name")));
                dish.setPhotoPath(cursor.getString(cursor.getColumnIndex("photoPath")));
                dish.setIngredients(cursor.getString(cursor.getColumnIndex("ingredients")));
                dish.setRestaurantId(cursor.getInt(cursor.getColumnIndex("restaurantId")));
                dish.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                dish.setPriceOfBox(cursor.getColumnIndex("priceOfBox"));
                mList.add(dish);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}
