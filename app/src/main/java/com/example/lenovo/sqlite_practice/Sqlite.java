package com.example.lenovo.sqlite_practice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/6/3.
 */

public class Sqlite extends SQLiteOpenHelper {
    private Context mContext;

    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public static final String CREATE_RESTAURANT = "create table Restaurant("
            + "id integer primary key autoincrement not null,"
            + "name text,"
            + "address text,"
            + "introduction text,"
            + "phone text,"
            + "recount text,"
            + "priceAtLeast real,"
            + "time text,"
            + "photoPath text,"
            + "sendFee real)";

    public static final String CREATE_DISHES = "create table Dishes("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "ingredients text,"
            + "price real,"
            + "photoPath text,"
            + "restaurantId integer,"
            + "priceOfBox real,"
            + "orderedAmount integer,"
            + "foreign key (restaurantId) references Restaurant(id) on delete cascade)";

    public static final String CREATE_PERSONAL_ORDERS = "create table Orders("
            + "id integer primary key autoincrement,"
            + "restaurantId integer,"
            + "userId integer,"
            + "dishes text,"
            + "total_price real,"
            + "completedOrNot text,"
            + "foreign key (restaurantId) references Restaurant(id) on delete cascade)";

    public static final String CREATE_COMMENTforRESTAURANT = "create table CommentForRes("
            + "id integer primary key autoincrement,"
            + "restaurantId integer,"
            + "foreign key (restaurantId) references Restaurant(id) on delete cascade)";



    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("pragma foreign_keys = ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RESTAURANT);
        db.execSQL(CREATE_DISHES);
        db.execSQL(CREATE_PERSONAL_ORDERS);
        db.execSQL(CREATE_COMMENTforRESTAURANT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exits Restaurant");
        db.execSQL("drop table if exits Dishes");
        db.execSQL("drop table if exits Orders");
        db.execSQL("drop table if exits CommentForRes");
        onCreate(db);
    }
}
