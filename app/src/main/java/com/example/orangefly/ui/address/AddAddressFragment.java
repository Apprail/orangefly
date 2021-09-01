package com.example.orangefly.ui.address;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orangefly.R;


public class AddAddressFragment extends Fragment {
    Context context;
    EditText addressType, name, address1, address2, area, city, postal_code;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View root = inflater.inflate(R.layout.add_address, container, false);
        addressType = (EditText)root.findViewById(R.id.address_type);
        name = (EditText)root.findViewById(R.id.address_name);
        address1 = (EditText)root.findViewById(R.id.address_building);
        address2 = (EditText)root.findViewById(R.id.address_street);
        area = (EditText)root.findViewById(R.id.address_area);
        city = (EditText)root.findViewById(R.id.address_city);
        postal_code = (EditText)root.findViewById(R.id.address_postal);

        return root;
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onStop(){
        super.onStop();
    }
}
