package com.example.xqtest.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;



/**
 * @author fangyang
 * @since 6/28/14.
 */
public class UIUtil {


    public static float dipToPix(Context context, float dip) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, metrics);
    }


    public static int dipResToPix(Context context, int dipRes) {
        return context.getResources().getDimensionPixelSize(dipRes);
    }

    public static float spToPix(Context context, float sp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }





    @SuppressLint("NewApi")
    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Point size = new Point();
            display.getSize(size);
            return size.x;
        } else {
            return display.getWidth();
        }
    }

    @SuppressLint("NewApi")
    public static int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        } else {
            return display.getHeight();
        }
    }


    // To animate view slide out from top to bottom
    public static void slideIn(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, view.getHeight(), 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    // To animate view slide out from bottom to top
    public static void slideOut(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    public static SpannableString createIndentedText(Spanned text, int marginFirstLine, int marginNextLines) {
        SpannableString result = new SpannableString(text);
        result.setSpan(new LeadingMarginSpan.Standard(marginFirstLine, marginNextLines), 0, text.length(), 0);
        return result;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int width;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        } else {
            width = display.getWidth();
        }
        return width;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int height;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        } else {
            height = display.getHeight();
        }
        return height;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getContentHeight(Context context) {
        int screenHeight = UIUtil.getScreenHeight(context);
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        int statusBarHeight = UIUtil.getStatusBarHeight(context);
        return screenHeight - statusBarHeight;
    }

    public static int getAttrColor(int attr, Context context) {
        return getAttrColor(attr, context, context.getTheme(), android.R.color.transparent);
    }

    public static int getAttrColor(int attr, Context context, int defaultColorId) {
        return getAttrColor(attr, context, context.getTheme(), defaultColorId);
    }

    public static int getAttrColor(int attr, Context context, Resources.Theme theme) {
        return getAttrColor(attr, context, theme, android.R.color.transparent);
    }

    public static int getAttrColor(int attr, Context context, Resources.Theme theme, int defaultColorId) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{
                attr
        });
        int color = typedArray.getColor(0, context.getResources().getColor(defaultColorId));
        typedArray.recycle();
        return color;
    }

    public static Drawable getAttrDrawable(int attr, Context context) {
        return getAttrDrawable(attr, context.getTheme());
    }

    public static Drawable getAttrDrawable(int attr, Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{
                attr
        });
        Drawable image = typedArray.getDrawable(0);
        typedArray.recycle();
        return image;
    }

    public static int getAttrResourceId(int attr, Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{
                attr
        });
        int id = typedArray.getResourceId(0, 0);
        typedArray.recycle();
        return id;
    }


    public static Spanned processHighlight(String original, String keyword) {
        if (TextUtils.isEmpty(original)) {
            return null;
        }

        Spanned result;
        String originalKeyword = getOriginalKeyword(original, keyword);
        if (!TextUtils.isEmpty(originalKeyword)) {
            result = Html.fromHtml(original.replace(originalKeyword, "<font color=#FF7700>" + originalKeyword + "</font>"));
        } else {
            result = Html.fromHtml(original);
        }
        return result;
    }

    public static String getOriginalKeyword(String str, String searchStr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(searchStr)) {
            return null;
        }

        final int length = searchStr.length();

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length)) {
                return str.substring(i, i + length);
            }
        }
        return null;
    }

    /**
     * 设置view 透明度 包括子view
     *
     * @param view
     * @param alpha 10进制
     */
    public static void setAlphaAllView(View view, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (view == null) {
            return;
        }
        if (view.getBackground() != null) {
            view.getBackground().mutate().setAlpha((int) (alpha * 255));
        }
        float alphaNum = alpha;
        view.setAlpha(alphaNum);
        //设置子view透明度
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewChild = vp.getChildAt(i);
                //调用本身（递归）
                setAlphaAllView(viewChild, alpha);
            }
        }
    }

    public static float getFontSize(Context context) {
        Configuration mCurConfig = new Configuration();
        try {
            mCurConfig.updateFrom(context.getResources().getConfiguration());
        } catch (Exception e) {
            Log.w("TAG", "Unable to retrieve font size");
        }

        return mCurConfig.fontScale;

    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    public static void setTint(ImageView iv, @ColorInt int color) {
        iv.setImageTintList(ColorStateList.valueOf(color));
    }

}
