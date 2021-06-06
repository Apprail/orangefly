package com.example.orangefly.ui.photos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.orangefly.AnotherActivity;
import com.example.orangefly.R;
import com.example.orangefly.keypreference.Preferences;
import com.google.android.material.tabs.TabLayout;

public class PhotoFragment extends Fragment{

    private PhotoViewModel photoViewModel;
    Context context;
    Button btn_signin;
    Boolean is_logged_in = false;
    //This is our tablayout
    private TabLayout tabLayout;
    //This is our viewPager
    private ViewPager viewPager;
    //Creating our pager adapter
    Pager adapter;
    LinearLayout viewPagerLayout;
    LinearLayout signinLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                new ViewModelProvider(this).get(PhotoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_photo, container, false);
        context = getActivity();

        viewPagerLayout = root.findViewById(R.id.viewpager_layout);
        signinLayout = root.findViewById(R.id.signin_layout);
        btn_signin = root.findViewById(R.id.btn_signin);

        tabLayout = root.findViewById(R.id.tabLayout);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("All Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Albums"));
        tabLayout.addTab(tabLayout.newTab().setText("Favourites"));
        tabLayout.addTab(tabLayout.newTab().setText("Memories"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = root.findViewById(R.id.pager);
        adapter = new Pager(getParentFragmentManager() , tabLayout.getTabCount());

        is_logged_in = Preferences.readBoolean(context,"logged_in");

        if (is_logged_in){
            signinLayout.setVisibility(View.GONE);
            viewPagerLayout.setVisibility(View.VISIBLE);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if(viewPager != null) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }else{

            viewPagerLayout.setVisibility(View.GONE);
            signinLayout.setVisibility(View.VISIBLE);
            btn_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Intent intent = new Intent(getActivity(), AnotherActivity.class);
                    intent.putExtra("item","Login");
                    startActivity(intent);
                }
            });
        }


        
        return root;
    }

}