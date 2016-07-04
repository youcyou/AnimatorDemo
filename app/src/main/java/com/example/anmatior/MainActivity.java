package com.example.anmatior;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "image clicked", Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                //1. animation
                TranslateAnimation animation = new TranslateAnimation(0, 500, 0, 0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                imageView.startAnimation(animation);
                */

                /*
                //2 . animator 所有提供get set方法的属性都可以使用
                // translationX 偏移量
                // X 绝对值
                ObjectAnimator.ofFloat(imageView, "translationX",500).setDuration(1000).start();
                */

                //3 组合animator
//                ObjectAnimator.ofFloat(imageView, "translationX",0,500).setDuration(1000).start();
//                ObjectAnimator.ofFloat(imageView, "translationY",0,500).setDuration(1000).start();
//                ObjectAnimator.ofFloat(imageView, "rotation",0,360).setDuration(500).start();

//                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX",0,500) ;
//                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY",0,500) ;
//                PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation",0,360) ;
//                ObjectAnimator.ofPropertyValuesHolder(imageView,p1,p2,p3).setDuration(1000).start();

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationX",0,500);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "translationY",0,500);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation",0,360);
                AnimatorSet set = new AnimatorSet();

//                set.playTogether(animator1,animator2,animator3); //同时

//                set.playSequentially(animator1,animator2,animator3);//顺序

                set.play(animator1).with(animator2); // with after before
                set.play(animator3).after(animator1);

                set.setDuration(1000);
                set.start();
            }
        });

    }


}
