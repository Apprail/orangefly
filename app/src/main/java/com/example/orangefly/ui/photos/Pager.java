package com.example.orangefly.ui.photos;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                AllPhotosTabFragment allPhotosTabFragment = new AllPhotosTabFragment();
                return allPhotosTabFragment;
            case 1:
                AlbumsTabFragment albumsTabFragment = new AlbumsTabFragment();
                return albumsTabFragment;
            case 2:
                FavouritesTabFragment favouritesTabFragment = new FavouritesTabFragment();
                return favouritesTabFragment;
            case 3:
                MemoriesTabFragment memoriesTabFragment = new MemoriesTabFragment();
                return memoriesTabFragment;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
