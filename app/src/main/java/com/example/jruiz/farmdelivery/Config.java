package com.example.jruiz.farmdelivery;

public class Config{

    public static final String URL_ADD="http://192.168.1.12:81/Life/addUser.php";
    public static final String URL_GET_ALL = "http://192.168.1.12:81/Life/getAllUsers.php";
    public static final String URL_GET_USER = "http://192.168.1.12:81/Life/getUser.php?email=";
    public static final String URL_UPDATE_USER = "http://192.168.1.12:81/Life/updateUser.php";
    public static final String URL_DELETE_USER = "http://192.168.1.12:81/Life/deleteUser.php?email=";
    public static final String URL_GET_ALL_ITEMS = "http://192.168.1.12:81/Life/getAllItems.php";

    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_LASTNAME = "lastname";
    public static final String KEY_USER_POSITION = "position";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_PASSWORD = "password";

    public static final String KEY_ITEM_NAME = "name";
    public static final String KEY_ITEM_BRAND = "brand";
    public static final String KEY_ITEM_PRICE = "price";
    public static final String KEY_ITEM_DESCRIPTION = "description";

    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_NAME = "name";
    public static final String TAG_LASTNAME = "lastname";
    public static final String TAG_POSITION = "position";
    //item
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_BRAND = "brand";
    public static final String TAG_PRICE = "price";

    public static final String USR_EMAIL = "user_email";
}