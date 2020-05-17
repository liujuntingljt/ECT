package com.hbsd.rjxy.ect.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbsd.rjxy.ect.Fragments.FanyiFragment;
import com.hbsd.rjxy.ect.Fragments.FuxiFragment;
import com.hbsd.rjxy.ect.Fragments.MiaojuFragment;
import com.hbsd.rjxy.ect.Fragments.WdFragment;
import android.support.v4.app.FragmentPagerAdapter;
import com.hbsd.rjxy.ect.R;
import com.hbsd.rjxy.ect.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout lin1, lin2, lin3, lin4;
    private ViewPager viewpager;
    private ImageView imgs[];
    private TextView tvs[];
    private int imgId[] = new int[]{R.drawable.juzi,R.drawable.fanyi,R.drawable.fuxi,R.drawable.wd ,R.drawable.juzioff,R.drawable.fanyioff,R.drawable.fuxioff,R.drawable.wdoff };
    private List<Fragment> fragments = new ArrayList<>();
    private SimplePageAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {
        lin1.setOnClickListener(this);
        lin2.setOnClickListener(this);
        lin3.setOnClickListener(this);
        lin4.setOnClickListener(this);
    }

    /*TODO
     *     初始化控件
     */
    @Override
    public void initView() {
        lin1 = findViewById(R.id.act_main_lin1);
        lin2 = findViewById(R.id.act_main_lin2);
        lin3 = findViewById(R.id.act_main_lin3);
        lin4 = findViewById(R.id.act_main_lin4);
        ImageView img1 = findViewById(R.id.act_main_img1);
        ImageView img2 = findViewById(R.id.act_main_img2);
        ImageView img3 = findViewById(R.id.act_main_img3);
        ImageView img4 = findViewById(R.id.act_main_img4);
        TextView tv1 = findViewById(R.id.act_main_tv1);
        TextView tv2 = findViewById(R.id.act_main_tv2);
        TextView tv3 = findViewById(R.id.act_main_tv3);
        TextView tv4 = findViewById(R.id.act_main_tv4);
        viewpager = (ViewPager) findViewById(R.id.act_main_vp);
        fragments.add(new MiaojuFragment());
        fragments.add(new FanyiFragment());
        fragments.add(new FuxiFragment());
        fragments.add(new WdFragment());
        imgs = new ImageView[]{img1, img2, img3,img4};
        tvs = new TextView[]{tv1, tv2, tv3,tv4};
        pagerAdapter = new SimplePageAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(4);
        /**
         * 设置ViewPager的滑动事件
         */
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /**
     * 设置显示的页面
     *
     * @param index 下标
     */
    private void setItem(int index) {
        for (int i = 0; i < 4; i++) {
            if (i == index) {
                imgs[i].setImageResource(imgId[i]);
                tvs[i].setTextColor(Color.parseColor("#D74B25"));
            } else {
                imgs[i].setImageResource(imgId[i + 4]);
                tvs[i].setTextColor(Color.parseColor("#515151"));
            }
        }
    }
    /**
     * 设置监听事件
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_main_lin1:
                setItem(0);
                viewpager.setCurrentItem(0);
                break;
            case R.id.act_main_lin2:
                setItem(1);
                viewpager.setCurrentItem(1);
                break;
            case R.id.act_main_lin3:
                setItem(2);
                viewpager.setCurrentItem(2);
                break;
            case R.id.act_main_lin4:
                setItem(3);
                viewpager.setCurrentItem(3);
                break;
        }
    }

    public class SimplePageAdapter extends FragmentPagerAdapter {
        public SimplePageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

}