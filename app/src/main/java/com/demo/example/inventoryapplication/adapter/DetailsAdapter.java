package com.demo.example.inventoryapplication.adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.demo.example.inventoryapplication.DbHelper.DBContract;
import com.demo.example.inventoryapplication.DbHelper.DBHelper;
import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.fragment.DisplayFragment;
import com.demo.example.inventoryapplication.model.DismissDialog;

import java.util.ArrayList;

/**
 * Created by poonampatel on 08/05/18.
 */

public class DetailsAdapter extends ArrayAdapter<String>
{
    private ArrayList<String> _taskList;
    LayoutInflater inflater;
    private TextView _task;
    private String _productId;
    DBHelper mydb;
    DismissDialog _dismissDialog;
    FragmentManager _manager;
    Context _context;

    public DetailsAdapter(FragmentManager fragmentManager, @NonNull Context context, int resource, ArrayList<String> taskVOsArrayList, String productID, DismissDialog dismissDialogListerner)
    {
        super(context, resource, taskVOsArrayList);
        _taskList = taskVOsArrayList;
        _productId = productID;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _dismissDialog = dismissDialogListerner;
        _manager = fragmentManager;
        mydb = new DBHelper(context);
        _context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.detail_item, parent, false);
        }

        _task = (TextView) convertView.findViewById(R.id.task);

        _task.setText(getItem(position));

        _task.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getItem(position).toString().equalsIgnoreCase("Add to Cart"))
                {
                    mydb.insertIProductsInCart(_productId, "1");
                    Log.d("CLicked", getItem(position).toString() + "cart");
                }
                else if (getItem(position).toString().equalsIgnoreCase("Remove Details From Inventory"))
                {
                    mydb.deleteProducts(Integer.parseInt(_productId));
                    Log.d("CLicked", getItem(position).toString() + "Remove");
                }
                else if (getItem(position).toString().equalsIgnoreCase("Update Details in Inventory"))
                {
                    DisplayFragment displayFragment = new DisplayFragment();
                    Bundle bundle = new Bundle();
                    Cursor cursor = mydb.getData(Integer.parseInt(_productId));
                    cursor.moveToFirst();
                    if (cursor != null)
                    {
                        bundle.putString(DBContract.Products.COLUMN_ID, cursor.getString(cursor.getColumnIndex(DBContract.Products.COLUMN_ID)));
                        bundle.putString(DBContract.Products.COLUMN_NAME, cursor.getString(cursor.getColumnIndex(DBContract.Products.COLUMN_NAME)));
                        bundle.putString(DBContract.Products.COLUMN_QUANTITY, cursor.getString(cursor.getColumnIndex(DBContract.Products.COLUMN_QUANTITY)));
                        bundle.putString(DBContract.Products.COLUMN_PRICE, cursor.getString(cursor.getColumnIndex(DBContract.Products.COLUMN_PRICE)));
                    }
                    displayFragment.setArguments(bundle);
                    _manager.beginTransaction().add(R.id.contentlayout, displayFragment, "DisplayFragment").addToBackStack("DisplayFragment").commit();

                    Log.d("CLicked", getItem(position).toString() + "update");
                }
                else
                {
                    mydb.deleteProductFromCart(Integer.parseInt(_productId));
                }
                _dismissDialog.DismissDialog();
            }
        });

        return convertView;
    }

    @Override
    public int getCount()
    {
        return _taskList.size();
    }
}
