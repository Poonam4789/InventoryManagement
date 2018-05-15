package com.demo.example.inventoryapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.model.ProductsVO;

import java.util.ArrayList;

/**
 * Created by poonampatel on 09/05/18.
 */

public class ProfitGainAdapter extends RecyclerView.Adapter<ProfitGainAdapter.ViewHolder>
{
    private ArrayList<ProductsVO> _productsList;
    public float _totalRevenue = 0.0f, _totalProfit = 0.0f, _revenueOnItem = 0.0f;
    TextView _textView;

    public ProfitGainAdapter(ArrayList<ProductsVO> list, TextView tv_revenue_status)
    {
        _productsList = list;
        _textView = tv_revenue_status;
    }

    @Override
    public ProfitGainAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profit_gain_item, viewGroup, false);
        return new ProfitGainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfitGainAdapter.ViewHolder holder, int position)
    {
        holder._productName.setText("Product : " + _productsList.get(position).getProductName().toString());
        holder._productQuantity.setText("Quantity : " + _productsList.get(position).getProductQuantity().toString());
        holder._productPrice.setText("Price : " + _productsList.get(position).getProductPrice().toString());

        float output = Float.parseFloat(_productsList.get(position).getProductPrice()) * (float) 0.2;
        holder._productProfit.setText("Profit : " + String.valueOf(output));
        _totalProfit = _totalProfit + output;
        _revenueOnItem = output + Float.parseFloat(_productsList.get(position).getProductPrice().toString());
        holder._product_revenue.setText("Revenue : " + _revenueOnItem);
        _totalRevenue = _totalRevenue + output + Float.parseFloat(_productsList.get(position).getProductPrice().toString());
        _textView.setText("Total Revenue = " + _totalRevenue + " And Total Profit = " + _totalProfit);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _productName, _productQuantity, _productPrice, _productProfit, _product_revenue;

        public ViewHolder(View view)
        {
            super(view);
            _productName = (TextView) view.findViewById(R.id.product_name);
            _productQuantity = (TextView) view.findViewById(R.id.product_quantity);
            _productPrice = (TextView) view.findViewById(R.id.product_price);
            _product_revenue = (TextView) view.findViewById(R.id.product_revenue);
            _productProfit = (TextView) view.findViewById(R.id.product_profit);
        }
    }

    @Override
    public int getItemCount()
    {
        return _productsList.size();
    }

}
