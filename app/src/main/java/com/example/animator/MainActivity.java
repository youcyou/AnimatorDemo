package com.example.animator;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private ImageView imageView;
    private Button button;

    private int[] res = {R.id.imageviewa,  R.id.imageviewb,  R.id.imageviewc,  R.id.imageviewd,
             R.id.imageviewe,  R.id.imageviewf,  R.id.imageviewg,  R.id.imageviewh};
    private ArrayList<ImageView> imageViewArrayList = new ArrayList();
    private boolean flag = true;

    private Button valueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        valueAnimator();
    }


    /**
     * value animator
     */
    private void valueAnimator(){
        valueButton = (Button) findViewById( R.id.value_button);
        valueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator animator = ValueAnimator.ofInt(
                        0,100);
                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        valueButton.setText("" +value);
                    }
                });
                animator.start();
            }
        });
    }

    /**
     * object animator exercise
     */
    private void objectAnimatorExercise() {

        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViewArrayList.add(imageView);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.imageviewa:
                if (flag) {
                    startAnim();
                    flag = false;
                } else {
                    closeAnim();
                    flag = true;
                }
                break;
            default:
                Toast.makeText(MainActivity.this, v.getId() + " clicked", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void startAnim() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewArrayList.get(i), "translationY", 0, i * 250);
            animator.setDuration(500);
            animator.setStartDelay(i * 350);
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
        }
    }

    private void closeAnim() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewArrayList.get(i), "translationY", i * 250, 0);
            animator.setDuration(500);
            animator.setStartDelay(i * 350);
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
        }
    }

    /**
     * object animator
     */
    private void animator() {
        imageView = (ImageView) findViewById( R.id.image);
        button = (Button) findViewById( R.id.button);

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

                /*
                //3 组合animator
                ObjectAnimator.ofFloat(imageView, "translationX",0,500).setDuration(1000).start();
                ObjectAnimator.ofFloat(imageView, "translationY",0,500).setDuration(1000).start();
                ObjectAnimator.ofFloat(imageView, "rotation",0,360).setDuration(500).start();

                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX",0,500) ;
                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY",0,500) ;
                PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation",0,360) ;
                ObjectAnimator.ofPropertyValuesHolder(imageView,p1,p2,p3).setDuration(1000).start();

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationX",0,500);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "translationY",0,500);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation",0,360);
                AnimatorSet set = new AnimatorSet();

                set.playTogether(animator1,animator2,animator3); //同时

                set.playSequentially(animator1,animator2,animator3);//顺序

                set.play(animator1).with(animator2); // with after before
                set.play(animator3).after(animator1);

                set.setDuration(1000);
                set.start();
                */

                /*
                //4 anmator listener
                Animator animator = ObjectAnimator.ofFloat(imageView, "alpha",0,1).setDuration(1000);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Toast.makeText(MainActivity.this, "animation end", Toast.LENGTH_SHORT).show();
                    }
                });
                animator.start();
                */
            }

        });
    }


}
