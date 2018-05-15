package com.demo.example.inventoryapplication.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.demo.example.inventoryapplication.model.CartVO;
import com.demo.example.inventoryapplication.model.ProductsVO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by poonampatel on 08/05/18.
 */

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "MyInventoryDBName.db";


    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
        db.execSQL(SqlLIteCommandHelper.CREATE_PRODUCT);
        db.execSQL(SqlLIteCommandHelper.CREATE_CART);
        db.execSQL(SqlLIteCommandHelper.CREATE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }

    public boolean insertIProducts(String name, String quantity, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Products.COLUMN_NAME, name);
        contentValues.put(DBContract.Products.COLUMN_QUANTITY, quantity);
        contentValues.put(DBContract.Products.COLUMN_PRICE, price);
        db.insert(DBContract.Products.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertIProductsInCart(String productId, String quantity)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Cart.COLUMN_PRODUCT_ID, productId);
        contentValues.put(DBContract.Cart.COLUMN_PRODUCT_QUANTITY, quantity);
        db.insert(DBContract.Cart.TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DBContract.Products.TABLE_NAME + " where id=" + id + "", null);
        return res;
    }

    public int numberOfProcuctsRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DBContract.Products.TABLE_NAME);
        return numRows;
    }

    public boolean updateProducts(Integer id, String name, String quantity, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.Products.COLUMN_NAME, name);
        contentValues.put(DBContract.Products.COLUMN_QUANTITY, quantity);
        contentValues.put(DBContract.Products.COLUMN_PRICE, price);
        db.update(DBContract.Products.TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteProducts(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DBContract.Products.TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public Integer deleteProductFromCart(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DBContract.Cart.TABLE_NAME,
                "p_id = ? ",
                new String[]{Integer.toString(id)});
    }


    public ArrayList<ProductsVO> getAllProducts()
    {
        ArrayList<ProductsVO> array_list = new ArrayList<ProductsVO>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DBContract.Products.TABLE_NAME, null);
        res.moveToFirst();
        ProductsVO productsVO = new ProductsVO();
        productsVO.setProductId(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_ID)));
        productsVO.setProductName(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_NAME)));
        productsVO.setProductQuantity(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_QUANTITY)));
        productsVO.setProductPrice(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_PRICE)));
        array_list.add(productsVO);
        while (res.moveToNext())
        {
            ProductsVO productsVO1 = new ProductsVO();
            productsVO1.setProductId(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_ID)));
            productsVO1.setProductName(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_NAME)));
            productsVO1.setProductQuantity(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_QUANTITY)));
            productsVO1.setProductPrice(res.getString(res.getColumnIndex(DBContract.Products.COLUMN_PRICE)));
            array_list.add(productsVO1);
        }
        return array_list;
    }

    public ArrayList<ProductsVO> getAllProductsFromCart()
    {
        ArrayList<ProductsVO> array_list = new ArrayList<ProductsVO>();
        ArrayList<CartVO> cartVOArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DBContract.Cart.TABLE_NAME, null);
        res.moveToFirst();
        if (res.getCount() > 0)
        {
            CartVO cartVO = new CartVO();
            cartVO.setCartId(res.getString(res.getColumnIndex(DBContract.Cart.COLUMN_ID)));
            cartVO.setCartProductId(res.getString(res.getColumnIndex(DBContract.Cart.COLUMN_PRODUCT_ID)));
            cartVO.setCartProductQuantity(res.getString(res.getColumnIndex(DBContract.Cart.COLUMN_PRODUCT_QUANTITY)));
            cartVOArrayList.add(cartVO);

            while (res.moveToNext())
            {
                CartVO cartVO1 = new CartVO();
                cartVO1.setCartId(res.getString(res.getColumnIndex(DBContract.Cart.COLUMN_ID)));
                cartVO1.setCartProductId(res.getString(res.getColumnIndex(DBContract.Cart.COLUMN_PRODUCT_ID)));
                cartVO1.setCartProductQuantity(res.getString(res.getColumnIndex(DBContract.Cart.COLUMN_PRODUCT_QUANTITY)));
                cartVOArrayList.add(cartVO1);
            }

            for (CartVO cartVO1 : cartVOArrayList)
            {
                Cursor cursor = getData(Integer.parseInt(cartVO1.getCartProductId()));
                cursor.moveToFirst();
                if (cursor != null && cursor.getCount() > 0)
                {
                    ProductsVO productsVO = new ProductsVO();
                    productsVO.setProductId(cursor.getString(cursor.getColumnIndex(DBContract.Products.COLUMN_ID)));
                    productsVO.setProductName(cursor.getString(cursor.getColumnIndex(DBContract.Products.COLUMN_NAME)));
                    productsVO.setProductQuantity(cartVO1.cartProductQuantity);
                    productsVO.setProductPrice(cursor.getString(cursor.getColumnIndex(DBContract.Products.COLUMN_PRICE)));
                    array_list.add(productsVO);
                }
            }
            return array_list;
        }
        return array_list;
    }

}
