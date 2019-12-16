package cn.kelibra.demo.viewpagerdialog.dialog.adapter;

import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

/**
 * @author: kezy
 * @create_time 2019/12/13
 * @description:
 */
public class NoticePagerTransformer implements ViewPager.PageTransformer {
    private static final float ROX_MAX = 20.0f;
    private float mRox;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {    //页面不可见

            ViewCompat.setRotation(page, 0);
        } else if (position < 0) { //页面可见，页面的左边界已经画出屏幕

            mRox = (position * ROX_MAX);
            ViewCompat.setPivotX(page, page.getMeasuredWidth());
            ViewCompat.setPivotY(page, page.getMeasuredHeight());
            ViewCompat.setRotation(page, mRox);

        } else if (position < 1) {  //页面可见，页面的左边界已经进入屏幕

            mRox = (position * ROX_MAX);
            ViewCompat.setPivotX(page, 0);
            ViewCompat.setPivotY(page, page.getMeasuredHeight());
            ViewCompat.setRotation(page, mRox);

        } else {  //页面不可见

            ViewCompat.setRotation(page, 0);
        }
    }
}
