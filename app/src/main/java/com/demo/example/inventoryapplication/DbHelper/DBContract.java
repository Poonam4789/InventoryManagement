package com.demo.example.inventoryapplication.DbHelper;

import android.provider.BaseColumns;

/**
 * Created by poonampatel on 08/05/18.
 */

public interface DBContract
{
    class Products implements BaseColumns
    {
        public static final String TABLE_NAME = "Products";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
    }

    class Cart implements BaseColumns
    {
        public static final String TABLE_NAME = "Cart";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PRODUCT_ID = "p_id";
        public static final String COLUMN_PRODUCT_QUANTITY = "p_quantity";
    }

    class Order implements BaseColumns
    {
        public static final String TABLE_NAME = "Orders";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PRODUCT_ID = "p_id";
        public static final String COLUMN_PRODUCT_QUANTITY = "p_quantity";
        public static final String COLUMN_PRODUCT_SOLD_DATE = "p_date";

    }
}
