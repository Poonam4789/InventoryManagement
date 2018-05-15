package com.demo.example.inventoryapplication.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

/**
 * Created by poonampatel on 09/05/18.
 */

public class CartVO implements Parcelable
{
    public String cartId;
    public String cartProductId;
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
        dest.writeString(this.cartId);
        dest.writeString(this.cartProductId);
        dest.writeString(this.cartProductQuantity);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private CartVO(Parcel in)
    {
        this.cartId = in.readString();
        this.cartProductId = in.readString();
        this.cartProductQuantity = in.readString();
    }

    public CartVO()
    {

    }

    public static final Creator<CartVO> CREATOR = new Creator<CartVO>()
    {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public CartVO createFromParcel(Parcel source)
        {
            return new CartVO(source);
        }

        public CartVO[] newArray(int size)
        {
            return new CartVO[size];
        }
    };
    public String getCartId()
    {
        return cartId;
    }

    public void setCartId(String cartId)
    {
        this.cartId = cartId;
    }

    public String getCartProductId()
    {
        return cartProductId;
    }

    public void setCartProductId(String cartProductId)
    {
        this.cartProductId = cartProductId;
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
