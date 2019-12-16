package cn.kelibra.demo.viewpagerdialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.kelibra.demo.viewpagerdialog.R;
import cn.kelibra.demo.viewpagerdialog.bean.DialogBean;
import cn.kelibra.demo.viewpagerdialog.dialog.adapter.NoticePagerAdapter;
import cn.kelibra.demo.viewpagerdialog.dialog.adapter.NoticePagerTransformer;

/**
 * @author: kezy
 * @create_time 2019/12/13
 * @description:
 */
public class ViewpagerDialog extends Dialog {

    private List<DialogBean> responseList;

    private ViewPager viewPager = null;

    public ViewpagerDialog(@NonNull Context context, List<DialogBean> responseList) {
        super(context, R.style.pagerDialog);
        this.responseList = responseList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (responseList == null || responseList.size() <= 0) {
            return;
        }
        //设置动画效果
        getWindow().setWindowAnimations(R.style.popupAnimation);
        //设置dialog的布局
        setContentView(R.layout.dialog_popup_ad);

        viewPager = (ViewPager) findViewById(R.id.dialog_viewpager);
        viewPager.setAdapter(createAdapter());
        viewPager.setPageTransformer(true, new NoticePagerTransformer());
        viewPager.setOffscreenPageLimit(responseList.size());
    }

    private String imgUrl = "";

    private DialogBean response;

    private PagerAdapter createAdapter() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < responseList.size(); i++) {

            response = responseList.get(i);
            ImageView[] mDots = new ImageView[responseList.size()];
            int dp1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7,
                    getContext().getResources().getDisplayMetrics());
            int dp2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                    getContext().getResources().getDisplayMetrics());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    dp1, dp1);

            View parent = LayoutInflater.from(getContext()).inflate(R.layout.item_pager_view, null);
            ImageView imageView = (ImageView) parent.findViewById(R.id.imageView);
            LinearLayout dotsLayout = (LinearLayout) parent.findViewById(R.id.dots_layout);
            imgUrl = response.imageUrl;
            final String title = response.title;
//            ImageLoadUtils.loadImage(getContext(), imgUrl, imageView);
            Glide.with(getContext())
                    .load(imgUrl)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "点击了： " + title, Toast.LENGTH_SHORT).show();
                }
            });

            //设置圆点
            if (responseList.size() > 0 && responseList.size() != 1) {
                for (int k = 0; k < responseList.size(); k++) {
                    mDots[k] = new ImageView(getContext());
                    mDots[k].setBackgroundResource(R.drawable.dot_gray_9);
                    params.leftMargin = dp2;// 设置圆点间隔
                    params.rightMargin = dp2;// 设置圆点间隔
                    if (k == i) {
                        mDots[k].setSelected(true);
                    } else {
                        mDots[k].setSelected(false);
                    }
                    mDots[k].setLayoutParams(params);
                    dotsLayout.addView(mDots[k], k);
                }
            }

            ImageView closeImage = parent.findViewById(R.id.imageview_dismiss_dialog);
            closeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            views.add(parent);
        }
        return new NoticePagerAdapter(views);
    }

    @Override
    public void show() {
        if (responseList.size() > 0) {
            super.show();
        }
    }
}
