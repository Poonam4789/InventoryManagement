package com.demo.example.inventoryapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.example.inventoryapplication.DbHelper.DBHelper;
import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.adapter.ProductAdapter;
import com.demo.example.inventoryapplication.appBase.InventoryMainActivity;
import com.demo.example.inventoryapplication.model.ProductsVO;

import java.util.ArrayList;

/**
 * Created by poonampatel on 09/05/18.
 */

public class ViewCartFragment extends Fragment
{
    RecyclerView _cartRecyclerView;
    DBHelper mydb;
    ProductAdapter _pageListViewAdapter;
    TextView _tv_text;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ((InventoryMainActivity) getActivity()).toggleDrawerIcon(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.cart_fragment_layout, container, false);
        _cartRecyclerView = view.findViewById(R.id.cart_view);
        _tv_text = view.findViewById(R.id.textView2);
        mydb = new DBHelper(getActivity().getApplicationContext());

        ArrayList<ProductsVO> _productsList = mydb.getAllProductsFromCart();
        _cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        _cartRecyclerView.setItemAnimator(null);
        if (_productsList.size() > 0)
        {
            _tv_text.setVisibility(View.GONE);
            _pageListViewAdapter = new ProductAdapter(getActivity().getSupportFragmentManager(), _productsList, "From_Cart");
        }
        else
        {
            _tv_text.setVisibility(View.VISIBLE);
        }

        _cartRecyclerView.setAdapter(_pageListViewAdapter);
        return view;
    }
}
