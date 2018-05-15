package com.demo.example.inventoryapplication.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

/**
 * Created by poonampatel on 09/05/18.
 */

public class StatusVO implements Parcelable
{
    public String productId;
    public String productName;
    public String productQuantity;
    public String productPrice;
    public String cartProductQuantity;

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
        dest.writeString(this.cartProductQuantity);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private StatusVO(Parcel in)
    {
        this.productId = in.readString();
        this.productName = in.readString();
        this.productQuantity = in.readString();
        this.productPrice = in.readString();
        this.cartProductQuantity = in.readString();

    }

    public StatusVO()
    {

    }

    public static final Parcelable.Creator<StatusVO> CREATOR = new Parcelable.Creator<StatusVO>()
    {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public StatusVO createFromParcel(Parcel source)
        {
            return new StatusVO(source);
        }

        public StatusVO[] newArray(int size)
        {
            return new StatusVO[size];
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

    public String getCartProductQuantity()
    {
        return cartProductQuantity;
    }

    public void setCartProductQuantity(String cartProductQuantity)
    {
        this.cartProductQuantity = cartProductQuantity;
    }

}
