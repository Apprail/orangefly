package com.example.orangefly.ui.prints;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;
import com.example.orangefly.R;
import com.google.android.material.tabs.TabLayout;

public class PrintFragment extends Fragment {
    Context context;
    //This is our viewPager
    private ViewPager viewPager;
    //Creating our pager adapter
    Prints_Pager adapter;
    LinearLayout viewPagerLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_prints, container, false);
        context = getActivity();

        viewPagerLayout = root.findViewById(R.id.prints_viewpager_layout);

        //This is our tablayout
        TabLayout tabLayout = root.findViewById(R.id.tabLayout_prints);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Prints"));
//        tabLayout.addTab(tabLayout.newTab().setText("Albums"));
//        tabLayout.addTab(tabLayout.newTab().setText("Favourites"));
//        tabLayout.addTab(tabLayout.newTab().setText("Memories"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = root.findViewById(R.id.prints_pager);
        adapter = new Prints_Pager(getParentFragmentManager() , tabLayout.getTabCount());


        viewPagerLayout.setVisibility(View.VISIBLE);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //If your tab layout has more than 2 tabs then tab will scroll other wise they will take whole width of the screen
        if (tabLayout.getTabCount() <= 2) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabIndicatorFullWidth(true);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setTabIndicatorFullWidth(false);
        }

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
        return root;
    }
}
