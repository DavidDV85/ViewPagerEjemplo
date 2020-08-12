package com.xdrake85.viewpagerejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;
    /** The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps. */
    private ViewPager mPager;
    /** The pager adapter, which provides the pages to the view pager widget.*/
    private PagerAdapter pagerAdapter;
    private ArrayList<Integer>ListaPosicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aleatorio(5);
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    /*** A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in sequence.*/
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment retorno = new ScreenSlidePageFragment();
            int eleccion=ListaPosicion.get(position);
            if ( eleccion == 0)
                retorno= new ScreenSlidePageFragment();
            if (eleccion == 1)
                retorno= new ScreenSlidePageFragment2();
            if (eleccion == 2)
                retorno= new ScreenSlidePageFragment3();
            if (eleccion == 3)
                retorno= new ScreenSlidePageFragment4();
            if (eleccion == 4)
                retorno= new ScreenSlidePageFragment5();
            return retorno;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void aleatorio(int total){
        int random = 0;
        int avance=0;
        int min=1;
        int max= total;
        boolean encontro=false;
        ListaPosicion = new ArrayList<Integer>();
        while(avance < total){
            random= new Random(max).nextInt(min) + max;
            encontro=false;
            for (int registro : ListaPosicion){
                if((random - 1) == registro ){
                    encontro=true;
                    break;
                }
            }
            if (!encontro){
                ListaPosicion.add(random  - 1);
                avance++;
                if(min == random){
                    min++;
                }
                if(max == random){
                    max--;
                }
            }
        }
        Log.d("AsignaValores",ListaPosicion.toString());
    }
}