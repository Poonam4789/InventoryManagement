package com.demo.example.inventoryapplication.DbHelper;

/**
 * Created by poonampatel on 08/05/18.
 */

public final class SqlLIteCommandHelper
{
    static final String CREATE_PRODUCT = "create table " + DBContract.Products.TABLE_NAME + " ( " +
            DBContract.Products.COLUMN_ID + " integer primary key autoincrement, " +
            DBContract.Products.COLUMN_NAME + " text," +
            DBContract.Products.COLUMN_QUANTITY + " text, " +
            DBContract.Products.COLUMN_PRICE + " text )";

    static final String CREATE_CART = "create table " + DBContract.Cart.TABLE_NAME + " ( " +
            DBContract.Cart.COLUMN_ID + " integer primary key autoincrement," +
            DBContract.Cart.COLUMN_PRODUCT_ID + " text," +
            DBContract.Cart.COLUMN_PRODUCT_QUANTITY + " text)";

    static final String CREATE_ORDERS = "create table " + DBContract.Order.TABLE_NAME + " ( " +
            DBContract.Order.COLUMN_ID + " integer primary key autoincrement," +
            DBContract.Order.COLUMN_PRODUCT_ID + " text," +
            DBContract.Order.COLUMN_PRODUCT_QUANTITY + " text," +
            DBContract.Order.COLUMN_PRODUCT_SOLD_DATE + " text)";
}
