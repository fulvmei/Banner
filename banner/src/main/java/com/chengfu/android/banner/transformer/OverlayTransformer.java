package com.chengfu.android.banner.transformer;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

/**
 * 叠加卡片效果
 */
public class OverlayTransformer implements ViewPager2.PageTransformer {
    private static final float DEFAULT_SCALE_STEP = 0.08f; // 默认缩放步长
    private static final int DEFAULT_OFFSET = 48; // 默认叠加偏移量
    private static final int DEFAULT_MAX_VISIBLE = 4;//最大可见数量

    private float scaleStep = DEFAULT_SCALE_STEP;
    private int offset = DEFAULT_OFFSET;
    private int maxVisible = DEFAULT_MAX_VISIBLE;

    public OverlayTransformer() {
    }

    public OverlayTransformer(int maxVisible,float scaleStep, int offset) {
        this.maxVisible = maxVisible;
        this.scaleStep = scaleStep;
        this.offset = offset;
    }

    public int getMaxVisible() {
        return maxVisible;
    }

    public void setMaxVisible(int maxVisible) {
        this.maxVisible = maxVisible;
    }

    public float getScaleStep() {
        return scaleStep;
    }

    public void setScaleStep(float scaleStep) {
        this.scaleStep = scaleStep;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void transformPage(View page, float position) {
        page.setTranslationZ(-position);
        page.setAlpha(1.0f);
        page.setScaleX(1.0f);
        page.setScaleY(1.0f);
        if (position <= 0.0f) {//当前页
            page.setTranslationX(0f);
            page.setClickable(true);
        } else {
            otherTrans(page, position);
            page.setClickable(false);
        }
    }

    private void otherTrans(View page, float position) {
        //缩放比例
//        float scale = (page.getWidth() - scaleOffset * position) / (float) (page.getWidth());
        float scale = 1 - scaleStep * position;
        float scaleOffset = page.getWidth() - page.getWidth() * (1 - scaleStep);
        float realOffset = offset + scaleOffset / 2;
        page.setScaleX(scale);
        page.setScaleY(scale);
        if (Math.floor(position) == 0.0) {
            page.setAlpha(1.0f - (position) * 0.5f);
        } else {
            page.setAlpha(0.5f - (position - 1) * 0.2f);
        }
        if (position > maxVisible - 1 && position < maxVisible) { //当前页向右滑动时,最右面第四个及以后页面应消失
            float curPositionOffset = realOffset * (float) Math.floor(position); //向下取整
            float lastPositionOffset = realOffset * (float) Math.floor(position - 1); //上一个卡片的偏移量
            float singleOffset = 1 - Math.abs(position % (int) position);
            float transX = (-page.getWidth() * position) + (lastPositionOffset + singleOffset * (curPositionOffset - lastPositionOffset));
            page.setTranslationX(transX);
        } else if (position <= maxVisible - 1) {
            float transX = (-page.getWidth() * position) + (realOffset * position);
            page.setTranslationX(transX);
        } else {
            page.setAlpha(0f);
        }
    }
}
