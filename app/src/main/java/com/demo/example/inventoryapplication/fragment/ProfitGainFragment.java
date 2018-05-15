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
import com.demo.example.inventoryapplication.adapter.ProductAdapter;
import com.demo.example.inventoryapplication.adapter.ProfitGainAdapter;
import com.demo.example.inventoryapplication.adapter.StatusAdapter;
import com.demo.example.inventoryapplication.appBase.InventoryMainActivity;
import com.demo.example.inventoryapplication.model.ProductsVO;
import com.demo.example.inventoryapplication.model.StatusVO;

import java.util.ArrayList;

/**
 * Created by poonampatel on 09/05/18.
 */

public class ProfitGainFragment extends Fragment
{
    private RecyclerView _profitView;
    DBHelper mydb;
    TextView tv_msg,tv_revenue_status;
    ProfitGainAdapter _profitGainAdapter;
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
        View view = inflater.inflate(R.layout.profitgain_fragment, container, false);
        _profitView = view.findViewById(R.id.profit_view);

        tv_msg = view.findViewById(R.id.noPurchase);
        tv_revenue_status = view.findViewById(R.id.revenue_status);
        mydb = new DBHelper(getActivity().getApplicationContext());
        ArrayList<ProductsVO> _productsList = mydb.getAllProductsFromCart();

        _profitView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        _profitView.setItemAnimator(null);

        if (_productsList.size() > 0)
        {
            tv_msg.setVisibility(View.GONE);
            _profitGainAdapter = new ProfitGainAdapter(_productsList,tv_revenue_status);
            _profitView.setAdapter(_profitGainAdapter);
        }
        else
        {
            tv_msg.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
