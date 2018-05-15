package com.demo.example.inventoryapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.example.inventoryapplication.DbHelper.DBHelper;
import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.adapter.StatusAdapter;
import com.demo.example.inventoryapplication.appBase.InventoryMainActivity;
import com.demo.example.inventoryapplication.model.ProductsVO;
import com.demo.example.inventoryapplication.model.StatusVO;

import java.util.ArrayList;

/**
 * Created by poonampatel on 09/05/18.
 */

public class InventoryStatusFrgament extends Fragment
{
    RecyclerView _inventoryStatusCartRecyclerView;
    DBHelper mydb;
    StatusAdapter _statusAdapter;
    TextView tv_status;
    int _actualItems = 0, _cartItems = 0;

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
        View view = inflater.inflate(R.layout.inventor_status_fragmet_layout, container, false);
        _inventoryStatusCartRecyclerView = view.findViewById(R.id.inventory_cart_view);

        tv_status = view.findViewById(R.id.status);
        mydb = new DBHelper(getActivity().getApplicationContext());
        ArrayList<ProductsVO> _AllProductList = mydb.getAllProducts();
        ArrayList<ProductsVO> _productsList = mydb.getAllProductsFromCart();

        _inventoryStatusCartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        _inventoryStatusCartRecyclerView.setItemAnimator(null);
        ArrayList<StatusVO> _statusList = new ArrayList<>();

        for (int i = 0; i < _productsList.size(); i++)
        {
            for (ProductsVO vo : _AllProductList)
            {
                StatusVO statusVO = new StatusVO();
                statusVO.setProductId(vo.getProductId());
                statusVO.setProductName(vo.getProductName());
                statusVO.setProductQuantity(vo.getProductQuantity());
                statusVO.setProductPrice(vo.getProductPrice());
                _actualItems = _actualItems + Integer.parseInt(vo.getProductQuantity());
                if (_productsList.get(i).getProductId().equalsIgnoreCase(vo.getProductId()))
                {
                    Log.d("status", "onCreateView: " + _productsList.get(i).getProductId().toString() + "  " + vo.getProductId());
                    statusVO.setCartProductQuantity(_productsList.get(i).getProductQuantity());
                    _cartItems = _cartItems + Integer.parseInt((_productsList.get(i).getProductQuantity()));
                }
                _statusList.add(statusVO);
            }
        }
        int size = _actualItems - _cartItems;
        tv_status.setText("All Items = " + _actualItems + " Items in Cart = " + _cartItems + " Total items in Inventory " + size);
        _statusAdapter = new StatusAdapter(_statusList);

        _inventoryStatusCartRecyclerView.setAdapter(_statusAdapter);
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        _actualItems = 0;
        _cartItems = 0;
    }
}
