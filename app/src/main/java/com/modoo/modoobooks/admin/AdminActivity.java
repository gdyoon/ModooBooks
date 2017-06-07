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

        // ViewPager로 연동하기 위해 프래그먼트 배열 생성
        Fragment[] arrFragments = new Fragment[4];
        arrFragments[0] = new UserManaFragment();
        arrFragments[1] = new BookManaFragment();
        arrFragments[2] = new StatManaFragment();
        arrFragments[3] = new QueryTestFragment();

        // 어댑터에 프래그먼트 배열을 담아서
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), arrFragments);
        // ViewPager에 넣으면
        viewPager.setAdapter(adapter);
        // 탭에 4개의 메뉴가 생김
        tabLayout.setupWithViewPager(viewPager);
    }

    // ViewPager의 어댑터 클래스 정의
    class MainPagerAdapter extends FragmentPagerAdapter {

        Fragment[] arrFragments;

        public MainPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
            super(fm);
            this.arrFragments = arrFragments;
        }



        // 탭에 표시할 타이틀
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:
                    return "사용자 관리";
                case 1:
                    return "도서 관리";
                case 2:
                    return "통계 관리";

                case 3:
                    return "쿼리 테스트";
                default:
                    return "";
            }
        }


        // 탭 선택시 반환되는 프래그먼트
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
