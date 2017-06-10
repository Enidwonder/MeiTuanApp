package com.example.lenovo.sqlite_practice;

/**
 * Created by lenovo on 2017/6/4.
 */

public class Order {
    private int id;
    private int restaurantId;
    private int userId;
    private String dishes;
    private String total_price;
    private String completedOrNot;

    void setId(int id1){id = id1;}
    void setCompletedOrNot(String completedOrNot1){completedOrNot = completedOrNot1;}
    void setUserId(int userId1){userId = userId1;}
    void setRestaurantId(int restaurantId1){restaurantId = restaurantId1;};
    void setDishes(String dishes1){dishes = dishes1;};
    void setTotal_price(String total_price1){total_price = total_price1;};

    int getId(){return id;}
    String getCompletedOrNot(){return completedOrNot;}
    int getUserId(){return userId;}
    int getRestaurantId(){return restaurantId;};
    String getDishes(){return dishes;};
    String getTotal_price(){return total_price;};
}
