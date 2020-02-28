package com.example.shrey.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shrey.myapplication.PageFragment2;
import com.example.shrey.myapplication.PageFragment3;
import com.example.shrey.myapplication.PageFragment6;
import com.example.shrey.myapplication.PageFragment8;

public class SwipeAdapter2 extends FragmentPagerAdapter {
    public SwipeAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int Position) {
        switch(Position)
        {
            case 0:
                PageFragment8 Page = new PageFragment8();
                Bundle bundle = new Bundle();
                bundle.putInt("current page", Position+1);
                Page.setArguments(bundle);
                return Page;
            case 1:
                return new PageFragment2();
            case 2:
                return new PageFragment3();
            case 3:
                return new PageFragment6();
            default:
                break;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
