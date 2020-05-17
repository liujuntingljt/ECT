package com.hbsd.rjxy.ect.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbsd.rjxy.ect.R;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private LinearLayout lin1, lin2, lin3,lin4;
    private ViewPager viewpager;
    private ImageView imgs[];
    private TextView tvs[];
  //  private int imgId[] = new int[]{R.mipmap.tuijianon, R.mipmap.faxianon, R.mipmap.wodeon, R.mipmap.tuijianoff, R.mipmap.faxianoff, R.mipmap.wodeoff};
    private List<Fragment> fragments = new ArrayList<>();
   // private MyPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    /*TODO
    *     初始化控件
    */
    private void initView() {
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
    }
}
