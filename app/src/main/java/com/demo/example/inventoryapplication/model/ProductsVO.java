package com.demo.example.inventoryapplication.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

public class ProductsVO implements Parcelable
{
    public String productId;
    public String productName;
    public String productQuantity;
    public String productPrice;

    @Override
    public int describeContents()
    {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.productId);
        dest.writeString(this.productName);
        dest.writeString(this.productQuantity);
        dest.writeString(this.productPrice);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private ProductsVO(Parcel in)
    {
        this.productId = in.readString();
        this.productName = in.readString();
        this.productQuantity = in.readString();
        this.productPrice = in.readString();
    }

    public ProductsVO()
    {

    }

    public static final Creator<ProductsVO> CREATOR = new Creator<ProductsVO>()
    {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public ProductsVO createFromParcel(Parcel source)
        {
            return new ProductsVO(source);
        }

        public ProductsVO[] newArray(int size)
        {
            return new ProductsVO[size];
        }
    };

    public String getProductId()
    {
        return productId;
    }

    public String getProductName()
    {
        return productName;
    }
    public String getProductQuantity()
    {
        return productQuantity;
    }

    public String getProductPrice()
    {
        return productPrice;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setProductQuantity(String productQuantity)
    {
        this.productQuantity = productQuantity;
    }

    public void setProductPrice(String productPrice)
    {
        this.productPrice = productPrice;
    }
}
