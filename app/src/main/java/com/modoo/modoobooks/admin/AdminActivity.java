package com.modoo.modoobooks.admin;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.modoo.modoobooks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminActivity extends AppCompatActivity {

    @BindView(R.id.vp_main_pager)
    ViewPager viewPager;
    @BindView(R.id.tl_main_tabs)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);

        Fragment[] arrFragments = new Fragment[3];
        arrFragments[0] = new UserManaFragment();
        arrFragments[1] = new BookManaFragment();
        arrFragments[2] = new StatManaFragment();

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), arrFragments);
        viewPager.setAdapter(adapter);
        //viewPager.setCurrentItem(page, true);
        tabLayout.setupWithViewPager(viewPager);
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

        Fragment[] arrFragments;

        public MainPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
            super(fm);
            this.arrFragments = arrFragments;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:
                    return "사용자 관리";
                case 1:
                    return "도서 관리";
                case 2:
                    return "통계 관리";
                default:
                    return "";
            }
        }


        @Override
        public Fragment getItem(int position) {

            return arrFragments[position];
        }

        @Override
        public int getCount() {
            return arrFragments.length;
        }
    }
}
