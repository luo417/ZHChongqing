package com.example.zhchongqing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zhchongqing.R;

public class GuideActivity extends Activity {

    private ViewPager guidePager;

    private static final int[] mImageIds = new int[]{R.drawable.guide_1,
            R.drawable.guide_2, R.drawable.guide_3};
    private LinearLayout dotGroup;
    private ImageView redDot;
    private Button enterHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        guidePager = (ViewPager) findViewById(R.id.vp_guide);
        dotGroup = (LinearLayout) findViewById(R.id.ll_guide_dots);
        redDot = (ImageView) findViewById(R.id.iv_dot_red);
        enterHomePage = (Button) findViewById(R.id.btn_guide_enter);
        enterHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
            }
        });

        initDots();

        MyPageAdapter adapter = new MyPageAdapter();
        guidePager.setAdapter(adapter);

        //监听ViewPager的滑动状态，根据其滑动状态动态更新红色dot的移动
        guidePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int dotDistance;

            @Override
            public void onPageScrolled(int i, float v, int i1) {
                dotGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        //获取两个小灰点之间的距离
                        dotDistance = dotGroup.getChildAt(1).getLeft() - dotGroup.getChildAt(0).getLeft();
                        dotGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

                //让小红点跟随页面的滑动而滑动
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) redDot.getLayoutParams();
                layoutParams.leftMargin = (int) (dotDistance * v + dotDistance * i);
                redDot.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int i) {
                //根据页面id判断显示或隐藏Button，1、2页隐藏，到第三页时显示
                if (i == 2) {
                    enterHomePage.setVisibility(View.VISIBLE);
                } else {
                    enterHomePage.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initDots() {
        for (int i = 0; i < mImageIds.length; i++) {
            View dot = new View(this);
            dot.setBackgroundResource(R.drawable.dot_grey);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i > 0) {
                params.leftMargin = 20;// 设置圆点间隔
            }
            dot.setLayoutParams(params);

            dotGroup.addView(dot);
        }
    }

    class MyPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(GuideActivity.this);

            imageView.setImageResource(mImageIds[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
