package com.example.lenovo.sqlite_practice;

/**
 * Created by lenovo on 2017/6/4.
 */

public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String introduction;
    private String recount;
    private String photoPath;
    private float priceAtLeast;
    private String time;
    private float sendFee;


    void setPhotoPath(String photoPath1){photoPath = photoPath1;}
    void setRecount(String recount1){recount = recount1;}
    void  setPriceAtLeast(float priceAtLeast1){priceAtLeast = priceAtLeast1;}
    void  setTime(String time1){time = time1;}
    void  setSendFee(float sendFee1){sendFee = sendFee1;}
    void setPhone(String phone1){phone = phone1;}
    void setId(int id1){id = id1;}
    void setName(String name1){name = name1;};
    void setAddress(String address1){address = address1;};
    void setIntroduction(String introduction1){introduction = introduction1;};


    String getPhotoPath(){return photoPath;}
    String getRecount(){return recount;}
    String getTime(){return time;}
    float getPriceAtLeast(){return priceAtLeast;}
    float getSendFee(){return sendFee;}
    int getId(){return id;}
    String getPhone(){return phone;}
    String getName(){return name;};
    String getAddress(){return address;};
    String getIntroduction(){return introduction;};
}
