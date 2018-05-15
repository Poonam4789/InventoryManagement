package com.demo.example.inventoryapplication.appBase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.demo.example.inventoryapplication.DbHelper.DBHelper;
import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.adapter.ProductAdapter;
import com.demo.example.inventoryapplication.fragment.DisplayFragment;
import com.demo.example.inventoryapplication.fragment.InventoryStatusFrgament;
import com.demo.example.inventoryapplication.fragment.ProfitGainFragment;
import com.demo.example.inventoryapplication.fragment.ViewCartFragment;
import com.demo.example.inventoryapplication.model.ProductsVO;

import java.util.ArrayList;

public class InventoryMainActivity extends AppCompatActivity
{

    public final static String EXTRA_MESSAGE = "MESSAGE";
    private RecyclerView _productCoversListView;
    DBHelper mydb;
    ProductAdapter _pageListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_main);

        mydb = new DBHelper(this);

        if (mydb.numberOfProcuctsRows() == 0)
        {
            mydb.insertIProducts("maggie", "25", "10");
            mydb.insertIProducts("cocacola", "40", "60");
            mydb.insertIProducts("mobiles", "10", "1500");
            mydb.insertIProducts("laptops", "10", "10000");
            mydb.insertIProducts("mouse", "15", "400");
        }

        ArrayList<ProductsVO> _productsList = mydb.getAllProducts();
        _productCoversListView = (RecyclerView) findViewById(R.id.product_view);
        _productCoversListView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        _productCoversListView.setItemAnimator(null);
        _pageListViewAdapter = new ProductAdapter(getSupportFragmentManager(), _productsList, "From_Inventory");

        _productCoversListView.setAdapter(_pageListViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.item1:
                DisplayFragment displayFragment = new DisplayFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.contentlayout, displayFragment, "DisplayFragment").addToBackStack("DisplayFragment").commit();
                return true;

            case R.id.cart:
                ViewCartFragment viewCartFragment = new ViewCartFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.contentlayout, viewCartFragment, "ViewCartFragment").addToBackStack("ViewCartFragment").commit();
                return true;

            case R.id.item2:
                InventoryStatusFrgament inventoryStatusFrgament = new InventoryStatusFrgament();
                getSupportFragmentManager().beginTransaction().add(R.id.contentlayout, inventoryStatusFrgament, "InventoryStatusFrgament").addToBackStack("InventoryStatusFrgament").commit();
                return true;

            case R.id.item3:
                ProfitGainFragment profitGainFragment = new ProfitGainFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.contentlayout, profitGainFragment, "ProfitGainFragment").addToBackStack("ProfitGainFragment").commit();
                return true;
            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }
    }


    public void toggleDrawerIcon(boolean showDrawer)
    {
        if (showDrawer)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed()
    {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("BackPress", "Fragmentcount = " + count);
        if (count == 0)
        {
            super.onBackPressed();
        }
        else if (count == 1)
        {
            super.onBackPressed();
            toggleDrawerIcon(true);
            settoolbarTitle(getApplicationContext().getResources().getString(R.string.app_name));
        }
        else if (count > 1)
        {
            String name = getSupportFragmentManager().getBackStackEntryAt(0).getName();
            Log.d("BackPress", "Fragment = " + name);
            if ("DisplayFragment".equalsIgnoreCase(name))
            {
                super.onBackPressed();
                toggleDrawerIcon(true);
                settoolbarTitle(getApplicationContext().getResources().getString(R.string.app_name));
            }
            else if ("ViewCartFragment".equalsIgnoreCase(name))
            {
                super.onBackPressed();
                toggleDrawerIcon(false);
                settoolbarTitle(getApplicationContext().getResources().getString(R.string.Cart));
            }
            else if ("InventoryStatusFrgament".equalsIgnoreCase(name))
            {
                super.onBackPressed();
                toggleDrawerIcon(false);
                settoolbarTitle(getApplicationContext().getResources().getString(R.string.inventory));
            }
            else if ("ProfitGainFragment".equalsIgnoreCase(name))
            {
                super.onBackPressed();
                toggleDrawerIcon(false);
                settoolbarTitle(getApplicationContext().getResources().getString(R.string.profit));
            }
        }
        invalidateMenu();
    }

    public void invalidateMenu()
    {
        invalidateOptionsMenu();
    }

    public void settoolbarTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }
}
