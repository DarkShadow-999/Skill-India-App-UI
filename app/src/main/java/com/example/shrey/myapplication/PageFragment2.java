package com.example.shrey.myapplication;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment2 extends android.support.v4.app.Fragment
{


    public PageFragment2() {
        // Required empty public constructor

    }
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.page_fragment_layout2, container, false);


            return view;
        }

}
