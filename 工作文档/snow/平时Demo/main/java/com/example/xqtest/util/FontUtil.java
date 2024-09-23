package com.example.xqtest.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by lyj on 29/08/2017.
 */

public class FontUtil {
    public static final int FONT_SIZE_LEVEL_SMALL = 0;
    public static final int FONT_SIZE_LEVEL_NORMAL = 1;
    public static final int FONT_SIZE_LEVEL_MEDIUM = 2;
    public static final int FONT_SIZE_LEVEL_BIG = 3;

    public static final int FONT_SIZE_12 = 12;
    public static final int FONT_SIZE_13 = 13;
    public static final int FONT_SIZE_14 = 14;
    public static final int FONT_SIZE_15 = 15;
    public static final int FONT_SIZE_16 = 16;
    public static final int FONT_SIZE_17 = 17;
    public static final int FONT_SIZE_18 = 18;
    public static final int FONT_SIZE_19 = 19;
    public static final int FONT_SIZE_20 = 20;
    public static final int FONT_SIZE_21 = 21;
    public static final int FONT_SIZE_22 = 22;
    public static final int FONT_SIZE_23 = 23;
    public static final int FONT_SIZE_24 = 24;
    public static final int FONT_SIZE_26 = 26;
    public static final int FONT_SIZE_28 = 28;
    public static final int FONT_SIZE_30 = 30;

    public static Typeface BOLD_TYPE;

    public static Typeface MEDIUM_TYPE;

    public static Typeface REGULAR_TYPE;

    public static void setNumberBoldFont(Context context, TextView textView) {
        if (BOLD_TYPE == null) {
            BOLD_TYPE = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/DIN-Bold.otf");
        }
        textView.setTypeface(BOLD_TYPE);
    }

    public static void setNumberMediumFont(Context context, TextView textView) {
        if (MEDIUM_TYPE == null) {
            MEDIUM_TYPE = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/DIN-Medium.otf");
        }
        textView.setTypeface(MEDIUM_TYPE);
    }

    public static void setNumberRegularFont(Context context, TextView textView) {
        if (REGULAR_TYPE == null) {
            REGULAR_TYPE = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/DIN-Regular.otf");
        }
        textView.setTypeface(REGULAR_TYPE);
    }

    public static Typeface getNumberMediumFont(Context context) {
        if (MEDIUM_TYPE == null) {
            MEDIUM_TYPE = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/DIN-Medium.otf");
        }
        return MEDIUM_TYPE;
    }
    public static Typeface getNumberBoldFont(Context context) {
        if (BOLD_TYPE == null) {
            BOLD_TYPE = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/DIN-Bold.otf");
        }
        return BOLD_TYPE;
    }

    public static Typeface getNumberRegularFont(Context context) {
        if (REGULAR_TYPE == null) {
            REGULAR_TYPE = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/DIN-Regular.otf");
        }
        return REGULAR_TYPE;
    }

    public static int getFontSizeFromTheme(int level) {
        switch (level) {
            case FONT_SIZE_LEVEL_SMALL:
                return FONT_SIZE_15;
            case FONT_SIZE_LEVEL_NORMAL:
                return FONT_SIZE_16;
            case FONT_SIZE_LEVEL_MEDIUM:
                return FONT_SIZE_18;
            case FONT_SIZE_LEVEL_BIG:
                return FONT_SIZE_20;
            default:
                return FONT_SIZE_16;
        }
    }

    public static int getStatusDetailTitleFontSize(int level) {
        switch (level) {
            case FONT_SIZE_LEVEL_SMALL:
                return FONT_SIZE_22;
            case FONT_SIZE_LEVEL_NORMAL:
                return FONT_SIZE_24;
            case FONT_SIZE_LEVEL_MEDIUM:
                return FONT_SIZE_26;
            case FONT_SIZE_LEVEL_BIG:
                return FONT_SIZE_28;
            default:
                return FONT_SIZE_24;
        }
    }

    public static int getReTweetedFontSize(int level) {
        switch (level) {
            case FONT_SIZE_LEVEL_SMALL:
                return FONT_SIZE_14;
            case FONT_SIZE_LEVEL_NORMAL:
                return FONT_SIZE_16;
            case FONT_SIZE_LEVEL_MEDIUM:
                return FONT_SIZE_18;
            case FONT_SIZE_LEVEL_BIG:
                return FONT_SIZE_20;
            default:
                return FONT_SIZE_16;
        }
    }
}
