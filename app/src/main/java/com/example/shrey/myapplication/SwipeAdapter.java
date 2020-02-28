package com.example.shrey.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SwipeAdapter extends FragmentPagerAdapter {
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int Position) {
     switch(Position)
     {
         case 0:
                PageFragment Page = new PageFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("current page", Position+1);
                Page.setArguments(bundle);
                return Page;
         case 1:
                return new PageFragment2();
         case 2:
                return new PageFragment3();
         case 3:
                return new PageFragment4();
         case 4:
                return new PageFragment5();
         default:
             break;

    }
    return null;
         }

    @Override
    public int getCount() {
        return 5;
    }
}
