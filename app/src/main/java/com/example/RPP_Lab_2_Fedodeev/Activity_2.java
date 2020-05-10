package com.example.RPP_Lab_2_Fedodeev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.laba_2_ver3.R;


public class Activity_2 extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener {

    ListFragment frag;
    FragmentViewPage frag2;
    FragmentTransaction fragTrans;
    MyViewModel model;
    FrameLayout firstFrame;
    boolean needExit;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (needExit){
            finish();
        }
        else{
            fragTrans.remove(frag2);
            fragTrans = getSupportFragmentManager().beginTransaction();
            fragTrans.replace(R.id.fragCont, frag).addToBackStack("list_fragment");
            fragTrans.commit();
            needExit = true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        needExit = true;

        // Получаем json файл из первого активити
        Bundle arguments = getIntent().getExtras();
        String jsonString = arguments.get("jsonString").toString();

        Log.d("MyTag", "Здесь уже работает");

        // Создаем хранилище
        model = new ViewModelProvider(
                    this.getViewModelStore(),
                    new ViewModelProvider.NewInstanceFactory()
                ).get(MyViewModel.class);
        model.loadTechnologies(jsonString, this.getResources());

        Log.d("MyTag", "Модель создана");

        // Создаем список и добавляем на активити
        frag = new ListFragment(model);


        fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.fragCont, frag).addToBackStack("list_fragment");
        fragTrans.commit();
        /*ViewPager pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyAdapterViewPage(getSupportFragmentManager()));
        Log.d("MyTag", "Фрагмент создан");*/
    }

    @Override
    public void onListFragmentInteraction(Technologies.Technology item, int num) {
        Log.d("MyTag", "Я не знаю что он хочет, но я вызвался и получил число num:"+num);
        // Создаем ViewPager
        frag2 = new FragmentViewPage(model);
        frag2.setPage(num);
        fragTrans.remove(frag);
        fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragCont, frag2).addToBackStack("page_fragment");
        fragTrans.commit();
        needExit = false;

    }
}
