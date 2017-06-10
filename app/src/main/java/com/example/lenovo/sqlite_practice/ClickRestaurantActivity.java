package com.example.lenovo.sqlite_practice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ClickRestaurantActivity extends AppCompatActivity {

    private List<Dish> mList = new ArrayList<>();
    private Sqlite dbHelper;
    private int RestaurantId;
    private int orderedAmount;
    private TextView restaurant_name;
    private TextView restaurant_address;
    private TextView restaurant_phone;
    private TextView restaurant_introduction;
    private boolean FirstDish = true;
    private int OrderId;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_restaurant);



        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);

        restaurant_name = (TextView)findViewById(R.id.restaurant_name);
        restaurant_address = (TextView)findViewById(R.id.restaurant_address);
        restaurant_phone = (TextView)findViewById(R.id.restaurant_phone);
        restaurant_introduction = (TextView)findViewById(R.id.restaurant_introduction);

        Intent intent = getIntent();
        RestaurantId = intent.getIntExtra("RestaurantId",0);


        dbHelper = new Sqlite(this,"MeiTuan1.db",null,1);
        showResDetails(RestaurantId);
        //Toast.makeText(ClickRestaurantActivity.this,"begin",Toast.LENGTH_SHORT).show();
        for(int i = 0;i < 20;i++){
            addDishes(RestaurantId);
        }

        initialDishList(RestaurantId);

        ListView listView = (ListView)findViewById(R.id.listview);
        final ListViewAdapterforDishes adapterforDishes = new ListViewAdapterforDishes(this,R.layout.dishes_item,mList);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initialDishList(RestaurantId);
                adapterforDishes.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

        adapterforDishes.setOnAddButtonClickListener(new AddDishClickListener() {
            @Override
            public void onClick(View view, Dish dish) {

                if(FirstDish){
                    OrderId = addInOrder(dish);
                    FirstDish = false;

                }else{
                    updateOrder(dish,OrderId);
                }

               /* TextView textView = view.findViewById(R.id.dish_amount);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Dishes",null,"id = dishId",null,null,null,null);

                textView.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("orderedAmount")) ));*/
            }
        });

        /*查看订单按钮,点击跳转*/
        TextView theOrder = (TextView)findViewById(R.id.theOrder);
        theOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ClickRestaurantActivity.this,ThisOrderActivity.class);
                startActivity(in);
            }
        });

        /*提交订单按钮，点击跳转*/
        Button giveOrder = (Button)findViewById(R.id.order_pay);
        giveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ClickRestaurantActivity.this,HandInOrderSuccessActivity.class);
                startActivity(intent1);
            }
        });


    }

    /*新建订单*/
    public int addInOrder(Dish dish){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("restaurantId",dish.getRestaurantId());
        values.put("dishes",dish.getDish_name()+"* 1");
        values.put("total_price",dish.getPrice()+dish.getPriceOfBox());
        db.insert("Orders",null,values);
        int orderId = 0;
        Cursor cursor = db.query("Orders",null,"dishes = ? and restaurantId = ?",new String[]{dish.getDish_name()+"* 1",String.valueOf(dish.getRestaurantId())},null,null,null);
        if(cursor.moveToFirst()){
            do{
                orderId = cursor.getInt(cursor.getColumnIndex("id"));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return orderId;
    }

    /*添加菜品*/
    public void updateOrder(Dish dish,int orderId){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String exitedDishes = null;
        Cursor cursor = database.query("Orders",null,"id = ?",new String[]{String.valueOf(orderId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                exitedDishes = cursor.getString(cursor.getColumnIndex("dishes"));
            }while (cursor.moveToNext());
        }
        cursor.close();
        values.put("dishes",exitedDishes+dish.getDish_name()+"* 1");
        database.update("Orders",values,"id = ?",new String[]{String.valueOf(orderId)});
    }

    public void addDishes(int ResId){

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","KongpaoChicken");
        values.put("ingredients","Chicken");
        values.put("price",23.1);
        values.put("restaurantId",ResId);
        values.put("priceOfBox",1);
        values.put("orderedAmount",0);
        database.insert("Dishes",null,values);
        //Toast.makeText(ClickRestaurantActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
    }




    /*更新List内容*/
    public void initialDishList(int ResId){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("Dishes",null,"restaurantId=?",new String[]{String.valueOf(ResId)},null,null,null);
        //Toast.makeText(ClickRestaurantActivity.this,"initial",Toast.LENGTH_SHORT).show();
            if (cursor.moveToFirst()) {
                do {
                    Dish dish = new Dish();
                    dish.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    dish.setDish_name(cursor.getString(cursor.getColumnIndex("name")));
                    dish.setPhotoPath(cursor.getString(cursor.getColumnIndex("photoPath")));
                    dish.setIngredients(cursor.getString(cursor.getColumnIndex("ingredients")));
                    dish.setRestaurantId(cursor.getInt(cursor.getColumnIndex("restaurantId")));
                    dish.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                    dish.setPriceOfBox(cursor.getFloat(cursor.getColumnIndex("priceOfBox")));
                    mList.add(dish);
                } while (cursor.moveToNext());
            }
        cursor.close();

    }

    /*饭店详情*/
    public void showResDetails(int ResId){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("Restaurant",null,"id = ?",new String[]{String.valueOf(ResId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                restaurant_name.setText(cursor.getString(cursor.getColumnIndex("name")));
                restaurant_introduction.setText(cursor.getString(cursor.getColumnIndex("introduction")));
                restaurant_phone.setText(cursor.getString(cursor.getColumnIndex("phone")));
                restaurant_address.setText(cursor.getString(cursor.getColumnIndex("address")));
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}
