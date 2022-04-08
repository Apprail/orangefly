package com.example.orangefly.ui.prints;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.orangefly.R;
import com.example.orangefly.ui.prints.PrintsListItems;
import com.example.orangefly.ui.prints.CustomPrintsListView;

import java.util.ArrayList;

public class PrintTabFragment  extends Fragment {
    Context context;
    ListView listView;
    ArrayList<PrintsListItems> listItems;
    CustomPrintsListView adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_prints_tab, container, false);
        context = getActivity();

        listView = (ListView)root.findViewById(R.id.prints_list);
        listItems = new ArrayList<PrintsListItems>();
        listItems.add(new PrintsListItems("4X4", R.mipmap.square));
        listItems.add(new PrintsListItems("4X6", R.mipmap.rectangle));
        adapter = new CustomPrintsListView(context,listItems);
        listView.setAdapter(adapter);
        return root;
    }
    @Override
    public void onResume(){
        super.onResume();
        adapter = new CustomPrintsListView(context,listItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}