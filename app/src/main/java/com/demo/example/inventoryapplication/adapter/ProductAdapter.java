package com.demo.example.inventoryapplication.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.fragment.DetailsFragment;
import com.demo.example.inventoryapplication.model.ProductsVO;

import java.util.ArrayList;

/**
 * Created by poonampatel on 08/05/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{
    private ArrayList<ProductsVO> _productsList;
    FragmentManager _fragmentManager;
    String _location;

    public ProductAdapter(FragmentManager supportFragmentManager, ArrayList<ProductsVO> list,String Location)
    {
        _productsList = list;
        _fragmentManager = supportFragmentManager;
        _location = Location;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position)
    {
        holder._productId.setText(_productsList.get(position).getProductId());
        holder._productName.setText("Product : "+_productsList.get(position).getProductName());
        holder._productQuantity.setText("Quantity : "+_productsList.get(position).getProductQuantity());
        holder._productPrice.setText("Price : "+_productsList.get(position).getProductPrice());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView _productId, _productName, _productQuantity, _productPrice;

        public ViewHolder(View view)
        {
            super(view);
            view.setOnClickListener(this);
            _productId = (TextView) view.findViewById(R.id.product_Id);
            _productName = (TextView) view.findViewById(R.id.product_name);
            _productQuantity = (TextView) view.findViewById(R.id.product_quantity);
            _productPrice = (TextView) view.findViewById(R.id.product_price);
        }

        @Override
        public void onClick(View v)
        {
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DetailsFragment.POSITION, getPosition());
            bundle.putString(DetailsFragment.PROCUCT_ID, _productsList.get(getPosition()).getProductId());
            bundle.putString(DetailsFragment.Location,_location);
            detailsFragment.setArguments(bundle);
            detailsFragment.show(_fragmentManager, "DIALOG WINDOW");
            Log.d("Clicked","CLICK to Add into CART");
        }
    }

    @Override
    public int getItemCount()
    {
        return _productsList.size();
    }

}
