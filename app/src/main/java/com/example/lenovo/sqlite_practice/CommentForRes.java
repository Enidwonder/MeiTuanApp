package com.example.lenovo.sqlite_practice;

/**
 * Created by lenovo on 2017/6/5.
 */

public class CommentForRes {
    private int id;
    private  int restaurantId;

    void setId(int id1){id = id1;}
    void setRestaurantId(int restaurantId1){restaurantId = restaurantId1;}

    int getId(){return id;}
    int getRestaurantId(){return restaurantId;}
}
