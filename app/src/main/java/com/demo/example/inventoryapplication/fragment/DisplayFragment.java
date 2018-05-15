package com.demo.example.inventoryapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.demo.example.inventoryapplication.DbHelper.DBContract;
import com.demo.example.inventoryapplication.DbHelper.DBHelper;
import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.appBase.InventoryMainActivity;

/**
 * Created by poonampatel on 08/05/18.
 */

public class DisplayFragment extends Fragment
{
    EditText ed_name;
    EditText ed_quantity;
    EditText ed_price;
    Button btn_addProduct;
    private DBHelper mydb;
    private boolean isUpdate = false;
    String ProductId = "";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ((InventoryMainActivity) getActivity()).toggleDrawerIcon(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_display_items, container, false);

        mydb = new DBHelper(getContext());
        ed_name = view.findViewById(R.id.name_value);
        ed_quantity = view.findViewById(R.id.quantity_value);
        ed_price = view.findViewById(R.id.price_value);
        btn_addProduct = view.findViewById(R.id.add);

        if (getArguments() != null)
        {
            ed_name.setText(getArguments().getString(DBContract.Products.COLUMN_NAME));
            ed_quantity.setText(getArguments().getString(DBContract.Products.COLUMN_QUANTITY));
            ed_price.setText(getArguments().getString(DBContract.Products.COLUMN_PRICE));
            ProductId = getArguments().getString(DBContract.Products.COLUMN_ID);
            isUpdate = true;
        }

        btn_addProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isUpdate == true)
                {
                    mydb.updateProducts(Integer.parseInt(ProductId), ed_name.getText().toString(), ed_quantity.getText().toString(), ed_price.getText().toString());
                }
                else
                {
                    mydb.insertIProducts(ed_name.getText().toString(), ed_quantity.getText().toString(), ed_price.getText().toString());

                }
                getActivity().recreate();
                getActivity().onBackPressed();
            }
        });
        return view;
    }
}
