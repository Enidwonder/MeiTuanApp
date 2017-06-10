package com.example.lenovo.sqlite_practice;

/**
 * Created by lenovo on 2017/6/4.
 */

public class Dish {
    private int id;
    private String dish_name;
    private String ingredients;
    private int restaurantId;
    private float price;
    private float priceOfBox;
    private String photoPath;
    private int orderedAmount;

    void setId(int id1){id = id1;}
    void setOrderedAmount(int amount){orderedAmount = amount;}
    void setPhotoPath(String photoPath1){photoPath = photoPath1;}
    void setPriceOfBox(float priceOfBox1){priceOfBox = priceOfBox1;}
    void setPrice(float price1){price = price1;}
    void setDish_name(String dish_name1){dish_name = dish_name1;}
    void setIngredients(String ingredients1){ingredients = ingredients1;}
    void setRestaurantId(int restaurant1){restaurantId = restaurant1;}

    int getId(){return id;}
    int getOrderedAmount(){return orderedAmount;}
    String getPhotoPath(){return photoPath;}
    float getPriceOfBox(){return priceOfBox;}
    float getPrice(){return price;}
    String getDish_name(){return dish_name;}
    String getIngredients(){return ingredients;}
    int getRestaurantId(){return restaurantId;}
}
