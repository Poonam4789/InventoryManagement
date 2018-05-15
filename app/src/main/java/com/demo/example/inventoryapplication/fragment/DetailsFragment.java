package com.demo.example.inventoryapplication.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.example.inventoryapplication.R;
import com.demo.example.inventoryapplication.adapter.DetailsAdapter;
import com.demo.example.inventoryapplication.appBase.InventoryMainActivity;
import com.demo.example.inventoryapplication.model.DismissDialog;

import java.util.ArrayList;

/**
 * Created by poonampatel on 08/05/18.
 */

public class DetailsFragment extends DialogFragment implements DismissDialog
{

    private final String TAG = "DetailsFragment";
    public static final String PROCUCT_ID = "p_Id";
    public static final String POSITION = "position";
    public static final String Location = "p_location";
    View view;
    int _position = 0;
    public Toolbar _dialogToolbar;
    private TextView _toolbarTextView;
    private TextView _dialogDismissCross;
    private String _dialogTitle;
    private String _product_Id;
    private ListView _variantLiew;
    DetailsAdapter _detailsAdapter;
    private ArrayList<String> taskList;
    private String _location;

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
        view = inflater.inflate(R.layout.detail_fragment_layout, null, false);

        _dialogDismissCross = (TextView) view.findViewById(R.id.dialog_dismiss_cross);
        _dialogToolbar = (Toolbar) view.findViewById(R.id.dialog_toolbar);
        _toolbarTextView = (TextView) view.findViewById(R.id.toolbar_title);

        _variantLiew = view.findViewById(R.id.variantListView);
        taskList = new ArrayList<>();


        _product_Id = getArguments().getString(PROCUCT_ID);
        _position = getArguments().getInt(POSITION);
        _location = getArguments().getString(Location);
        if (_location.equalsIgnoreCase("From_Inventory"))
        {
            taskList.add("Add to Cart");
            taskList.add("Remove Details From Inventory");
            taskList.add("Update Details in Inventory");
        }
        else
        {
            taskList.add("Remove from Cart");
        }

        if (_dialogDismissCross != null)
        {
            _dialogDismissCross.setVisibility(View.VISIBLE);
            _dialogDismissCross.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dismiss();
                }
            });
        }
        setVariantData();
        return view;
    }

    private void setVariantData()
    {
        _detailsAdapter = new DetailsAdapter(getActivity().getSupportFragmentManager(), getContext(), R.layout.product_view_item, taskList, _product_Id, this);
        _variantLiew.setAdapter(_detailsAdapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_fragment_height);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_fragment_width);
        getDialog().getWindow().setLayout(width, height);
    }

    public void setToolbarTitle(String title)
    {
        if (_toolbarTextView != null)
        {
            _toolbarTextView.setText(title);
        }
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onViewCreated(view, savedInstanceState);

        _dialogTitle = "Choose Task !!";
        setToolbarTitle(_dialogTitle);
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
        if (getActivity() instanceof DialogInterface.OnDismissListener)
        {
            ((DialogInterface.OnDismissListener) getActivity()).onDismiss(dialog);
        }
    }

    @Override
    public void DismissDialog()
    {
        dismiss();
        getActivity().recreate();
    }
}
