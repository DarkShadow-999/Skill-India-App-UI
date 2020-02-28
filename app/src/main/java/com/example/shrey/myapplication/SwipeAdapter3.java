package com.example.shrey.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shrey.myapplication.PageFragment2;
import com.example.shrey.myapplication.PageFragment7;
import com.example.shrey.myapplication.PageFragment9;

public class SwipeAdapter3 extends FragmentPagerAdapter {
    public SwipeAdapter3(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int Position) {
        switch(Position)
        {
            case 0:
                PageFragment9 Page = new PageFragment9();
                Bundle bundle = new Bundle();
                bundle.putInt("current page", Position+1);
                Page.setArguments(bundle);
                return Page;
            case 1:
                return new PageFragment7();
            case 2:
                return new PageFragment2();
            default:
                break;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
