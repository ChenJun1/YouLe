package com.laiding.yl.youle.widget.cookie;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;

/**
 * Created by JunChen on 2018/1/22.
 */
public class ThemeResolver {

  public static int getColor(Context context, @AttrRes int attr) {
    return getColor(context, attr, 0);
  }

  public static int getColor(Context context, @AttrRes int attr, int defaultColor) {
    TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
    try {
      return a.getColor(0, defaultColor);
    } finally {
      a.recycle();
    }
  }

}
