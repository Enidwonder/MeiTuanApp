package com.example.lenovo.sqlite_practice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HandInOrderSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_in_order_success);

        Intent intent = getIntent();
        int orderId = intent.getIntExtra("OrderId",0);

        TextView orderContent = (TextView)findViewById(R.id.OrderContent);
        TextView orderPrice = (TextView)findViewById(R.id.order_total_price) ;

        Sqlite dbHelper = new Sqlite(HandInOrderSuccessActivity.this,"MeiTuan1.db",null,1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("Orders",null,"id = ?",new String[]{String.valueOf(orderId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                orderContent.setText(cursor.getString(cursor.getColumnIndex("dishes")));
                orderPrice.setText(cursor.getString(cursor.getColumnIndex("total_price")));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
