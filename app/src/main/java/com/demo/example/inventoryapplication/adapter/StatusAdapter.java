package com.demo.example.inventoryapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.model.StatusVO;

import java.util.ArrayList;

/**
 * Created by poonampatel on 09/05/18.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>
{
    private ArrayList<StatusVO> _productsList;

    public StatusAdapter(ArrayList<StatusVO> list)
    {
        _productsList = list;
    }

    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.status_item, viewGroup, false);
        return new StatusAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusAdapter.ViewHolder holder, int position)
    {
        holder._productName.setText("Product : " + _productsList.get(position).getProductName());
        holder._productQuantityActual.setText("Actual Quantity : " + _productsList.get(position).getProductQuantity());
        if (_productsList.get(position).getCartProductQuantity() != null)
        {
            holder._productQuantityInCart.setText("Quantity in cart : " + _productsList.get(position).getCartProductQuantity());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _productName, _productQuantityActual, _productQuantityInCart;

        public ViewHolder(View view)
        {
            super(view);
            _productName = (TextView) view.findViewById(R.id.product_name);
            _productQuantityActual = (TextView) view.findViewById(R.id.product_quantity_actual);
            _productQuantityInCart = (TextView) view.findViewById(R.id.product_quantity_in_cart);
        }
    }

    @Override
    public int getItemCount()
    {
        return _productsList.size();
    }
}
