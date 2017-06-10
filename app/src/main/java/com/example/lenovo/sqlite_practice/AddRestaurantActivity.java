package com.example.lenovo.sqlite_practice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddRestaurantActivity extends AppCompatActivity {

    private Sqlite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        final EditText name = (EditText)findViewById(R.id.enter_name);
        final EditText address = (EditText)findViewById(R.id.enter_address);
        final EditText phone = (EditText)findViewById(R.id.enter_phone);
        final EditText introduction = (EditText)findViewById(R.id.enter_introduction);
        ImageView addPhoto = (ImageView)findViewById(R.id.enter_photo);
        Button addButton = (Button)findViewById(R.id.add_button);

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*open the album*/
            }
        });



        dbHelper = new Sqlite(this,"MeiTuan1.db",null,1);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                final String Rname = name.getText().toString();
                final String Raddress = address.getText().toString();
                final String Rphone = phone.getText().toString();
                final String Rintroduction = introduction.getText().toString();
                values.put("name",Rname);
                values.put("address",Raddress);
                values.put("phone",Rphone);
                values.put("introduction",Rintroduction);
                db.insert("Restaurant",null,values);
                Toast.makeText(AddRestaurantActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                /*跳转回显示餐馆列表*/
                Intent intent = new Intent(AddRestaurantActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
