package com.example.orangefly.ui.store;

import android.content.Intent;
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

import com.example.orangefly.AnotherActivity;
import com.example.orangefly.R;
import com.example.orangefly.ui.login.LoginFragment;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class StoreFragment extends Fragment {

    //private StoreViewModel storeViewModel;
    GridView androidGridView;
    String[] gridViewString = {
            "Prints", "Photo Books", "Gifts", "Wall Art", "Phone Cases",
            "Calendars"
    } ;
    int[] gridViewImageId = {
            R.drawable.photo_prints_4_6, R.drawable.photo_books_soon, R.drawable.gifts_soon,
            R.drawable.wall_art_soon, R.drawable.phone_case_soon,
            R.drawable.calendar_soon
    };



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        storeViewModel =
//                new ViewModelProvider(this).get(StoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_store, container, false);
        final TextView textView = root.findViewById(R.id.text_store);
        textView.setText(R.string.title_home);
        // initializing the slider view.
        SliderView sliderView = root.findViewById(R.id.slider);
        // we are creating array list for storing our image urls.
        ArrayList<SliderBanner> sliderDataArrayList = new ArrayList<>();
        sliderDataArrayList.add(new SliderBanner("url",R.drawable.ofly_banner));
        // passing this array list inside our adapter class.
        SliderAdapter slider_adapter = new SliderAdapter(root.getContext(), sliderDataArrayList);
        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        androidGridView=(GridView)root.findViewById(R.id.store_grid);
        androidGridView.setAdapter(new CustomGridViewActivity(root.getContext(),gridViewString,gridViewImageId));
        // setadapter to sliderview.
        sliderView.setSliderAdapter(slider_adapter);
        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);
        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);
        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        sliderView.getPagerIndicator().setVisibility(View.VISIBLE);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dataFromGrid = ((TextView)view.findViewById(R.id.grid_text)).getText().toString();
                if (dataFromGrid.equals("Prints")){
                    Intent intent = new Intent(getActivity(), AnotherActivity.class);
                    intent.putExtra("item",dataFromGrid);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(),"It will launch soon",Toast.LENGTH_SHORT).show();
                }

            }
        });


//        storeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        return root;
    }

}