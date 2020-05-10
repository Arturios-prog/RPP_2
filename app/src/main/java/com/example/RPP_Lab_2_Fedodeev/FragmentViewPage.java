package com.example.RPP_Lab_2_Fedodeev;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laba_2_ver3.R;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentViewPage extends Fragment {

    private MyViewModel model;
    private ViewPager pager;
    private int page;

    public FragmentViewPage() {
    }

    public FragmentViewPage(MyViewModel model) {
        this.model = model;
    }

    public void  setPage(int page){
            this.page = page;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("MyTag", "**** Я создался и запустился *******");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_view_pager, container, false);
        pager=(ViewPager)result.findViewById(R.id.frag_pager);
        pager.setAdapter(new MyAdapterViewPage(getFragmentManager(), model));
        pager.setCurrentItem(page);
        Log.d("MyTag", "**** Я тоже ******* page:"+page);
        return result;
    }
}
