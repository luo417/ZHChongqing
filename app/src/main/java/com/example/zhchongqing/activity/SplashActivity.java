package com.example.zhchongqing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.zhchongqing.R;

public class SplashActivity extends Activity {

    private ImageView splash_cock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        splash_cock = (ImageView) findViewById(R.id.iv_splash_cock);

        //开启组合动画
        startAnimationSet();
    }

    public void startAnimationSet(){
        AnimationSet animationSet = new AnimationSet(false);

        //定义旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);

        //定义缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);

        //定义渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        animationSet.addAnimation(rotateAnimation);  //将旋转动画添加到组合动画中
        animationSet.addAnimation(scaleAnimation);  //将缩放动画添加到组合动画中
        animationSet.addAnimation(alphaAnimation);  //将缩放动画添加到组合动画中

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        splash_cock.startAnimation(animationSet);
    }

}
