package com.chengfu.android.banner;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP_PREFIX;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.lang.annotation.Retention;

public class Banner extends FrameLayout {

    @RestrictTo(LIBRARY_GROUP_PREFIX)
    @Retention(SOURCE)
    @IntDef({HORIZONTAL, VERTICAL})
    public @interface Orientation {
    }

    public static final int HORIZONTAL = ViewPager2.ORIENTATION_HORIZONTAL;
    public static final int VERTICAL = ViewPager2.ORIENTATION_VERTICAL;

    private final ViewPager2 viewPager2;

    public Banner(@NonNull Context context) {
        this(context,null);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        viewPager2=new ViewPager2(context,attrs,defStyleAttr);
        viewPager2.setClipChildren(false);

        addView(viewPager2);
    }


    public void setAdapter(@Nullable @SuppressWarnings("rawtypes") RecyclerView.Adapter adapter) {
        viewPager2.setAdapter(adapter);
    }

    public void setCurrentItem(int item, boolean smoothScroll){
        viewPager2.setCurrentItem(item,smoothScroll);
    }

    public void setOrientation(@Orientation int orientation) {
        viewPager2.setOrientation(orientation);
    }

    public void setOffscreenPageLimit(@ViewPager2.OffscreenPageLimit int limit) {
        viewPager2.setOffscreenPageLimit(limit);
    }

    public void setPageTransformer(@Nullable ViewPager2.PageTransformer transformer) {
        viewPager2.setPageTransformer(transformer);
    }

    public void setOffsetRight(int offsetRight){
        RecyclerView recyclerView = (RecyclerView) viewPager2.getChildAt(0);
        recyclerView.setPadding(0, 0, offsetRight, 0);
        recyclerView.setClipToPadding(false);
    }
}
