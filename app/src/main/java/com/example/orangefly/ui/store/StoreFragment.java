package com.example.orangefly.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.orangefly.R;

public class StoreFragment extends Fragment {

    private StoreViewModel storeViewModel;
    GridView androidGridView;
    String[] gridViewString = {
            "Prints", "Photo Books", "Gifts", "Wall Art", "Phone Cases", "Calendars"
    } ;
    int[] gridViewImageId = {
            R.drawable.photo_prints_4_6, R.drawable.photo_books, R.drawable.gifts,
            R.drawable.wall_art, R.drawable.phone_case, R.drawable.calendar


    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storeViewModel =
                new ViewModelProvider(this).get(StoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_store, container, false);
        final TextView textView = root.findViewById(R.id.text_store);
        textView.setText("All Categories");

        androidGridView=(GridView)root.findViewById(R.id.store_grid);
        androidGridView.setAdapter(new CustomGridViewActivity(root.getContext(),gridViewString,gridViewImageId));
//        storeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        return root;
    }

}