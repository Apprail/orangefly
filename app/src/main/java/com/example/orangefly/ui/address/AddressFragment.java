package com.example.orangefly.ui.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orangefly.ActivitySecond;
import com.example.orangefly.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class AddressFragment extends Fragment {
    Context context;
    ListView addressListView;
    FloatingActionButton fab;
    ArrayList<AddressListItems> addressListItems;
    CustomAddressListView addressAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View root = inflater.inflate(R.layout.fragment_address, container, false);
        fab = (FloatingActionButton)root.findViewById(R.id.fab);
        addressListView = (ListView)root.findViewById(R.id.address_list);
        addressListItems = new ArrayList<AddressListItems>();
        addressListItems.add(new AddressListItems("Sample"));
        addressAdapter = new CustomAddressListView(context,addressListItems);
        addressListView.setAdapter(addressAdapter);

        addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                call_activity("Shipment Address");

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_activity("Add Address");
//                Intent intent = new Intent(getActivity(), ActivitySecond.class);
//                intent.putExtra("item","Add Address");
//                startActivity(intent);
            }
        });
        return root;
    }

    private void call_activity(String activityName){
        Intent intent = new Intent(getActivity(), ActivitySecond.class);
        intent.putExtra("item",activityName);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        addressAdapter = new CustomAddressListView(context,addressListItems);
        addressListView.setAdapter(addressAdapter);
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}
