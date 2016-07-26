package com.zhiyicx.zhibo.zbsmartlivesdkdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.BaseApplication;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;

import java.security.MessageDigest;

/**
 * Created by jess on 2015/11/23.
 */
public class UiUtils {
    static public Toast mToast;


    /**
     * dip转pix
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获得资源
     */
    public static Resources getResources() {
        return BaseApplication.getContext().getResources();
    }

    /**
     * 得到字符数组
     */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    /**
     * pix转dip
     */
    public static int pix2dip(int pix) {
        final float densityDpi = getResources().getDisplayMetrics().density;
        return (int) (pix / densityDpi + 0.5f);
    }

    /**
     * 获得上下文
     *
     * @return
     */
    public static Context getContext() {
        return BaseApplication.getContext();
    }


    /**
     * 从dimens中获得尺寸
     *
     * @param homePicHeight
     * @return
     */

    public static int getDimens(int homePicHeight) {
        return (int) getResources().getDimension(homePicHeight);
    }

    /**
     * 从dimens中获得尺寸
     *
     * @param
     * @return
     */

    public static float getDimens(String dimenNmae) {
        return getResources().getDimension(getResources().getIdentifier(dimenNmae, "dimen", getContext().getPackageName()));
    }

    /**
     * 从String 中获得字符
     *
     * @return
     */

    public static String getString(int stringID) {
        return getResources().getString(stringID);
    }

    /**
     * 从String 中获得字符
     *
     * @return
     */

    public static String getString(String strName) {
        return getString(getResources().getIdentifier(strName, "string", getContext().getPackageName()));
    }

    /**
     * findview
     *
     * @param view
     * @param viewName
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewByName(View view, String viewName) {
        int id = getResources().getIdentifier(viewName, "id", getContext().getPackageName());
        T v = (T) view.findViewById(id);
        return v;
    }

    /**
     * findview
     *
     * @param activity
     * @param viewName
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewByName(Activity activity, String viewName) {
        int id = getResources().getIdentifier(viewName, "id", getContext().getPackageName());
        T v = (T) activity.findViewById(id);
        return v;
    }

    /**
     * 根据lauout名字获得id
     *
     * @param layoutName
     * @return
     */
    public static int findLayout(String layoutName) {
        int id = getResources().getIdentifier(layoutName, "layout", getContext().getPackageName());
        return id;
    }

    /**
     * 填充view
     *
     * @param detailScreen
     * @return
     */
    public static View inflate(int detailScreen) {
        return View.inflate(getContext(), detailScreen, null);
    }

    /**
     * 单列toast
     *
     * @param string
     */

    public static void makeText(String string) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), string, Toast.LENGTH_SHORT);
        }
        mToast.setText(string);
        mToast.show();
    }


    /**
     * 通过资源id获得drawable
     *
     * @param rID
     * @return
     */
    public static Drawable getDrawablebyResource(int rID) {
        return getResources().getDrawable(rID);
    }



    public static int getLayoutId(String layoutName) {
        return getResources().getIdentifier(layoutName, "layout", getContext().getPackageName());
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕的高度
     *
     * @return
     */
    public static int getScreenHeidth() {
        return getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 显示对话框提示
     *
     * @param text
     */

    public static void showDialog(String text, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提示");
        builder.setNegativeButton("确定", null);
        builder.setMessage(text);
        builder.show();
    }

    /**
     * 显示对话框提示
     *
     * @param text
     */

    public static void showDialogWithMethod(String text, Activity activity, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提示");
        builder.setNegativeButton("确定", listener);
        builder.setMessage(text);
        builder.show();
    }

    /**
     * 获得颜色
     */
    public static int getColor(int rid) {
        return getResources().getColor(rid);
    }

    /**
     * 获得颜色
     */
    public static int getColor(String colorName) {
        return getColor(getResources().getIdentifier(colorName, "color", getContext().getPackageName()));
    }

    /**
     * 移除孩子
     *
     * @param view
     */
    public static void removeChild(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(view);
        }
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    private static int mCount;


    /**
     * MD5
     *
     * @param string
     * @return
     *
     * @throws Exception
     */
    public static String MD5encode(String string) {
        byte[] hash = new byte[0];
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 使用glide显示图片
     *
     * @param url
     * @param imageView
     */
    public static void glideDisplay(String url, ImageView imageView) {
        Glide.with(getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.pic_touxiang_150)
                .into(imageView);
    }

    /**
     * 使用glide显示图片不使用默认图片
     */
    public static void glideDisplayNotPlaceholder(String url, ImageView imageView) {
        Glide.with(getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    /**
     * 使用glide显示，并裁剪图片
     *
     * @param url
     * @param imageView
     * @param transformation
     */
    public static void glideDisplayWithTrasform(String url, ImageView imageView, BitmapTransformation transformation) {
        Glide.with(getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .transform(transformation)
                .placeholder(R.mipmap.pic_touxiang_150)
                .into(imageView);
    }

    public static DrawableRequestBuilder glideWrap(String url) {
        return Glide.with(getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop();
    }



    /**
     * 全屏，并且沉侵式状态栏
     *
     * @param activity
     */
    public static void statuInScreen(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    /**
     * 退出提示dialog
     */
    public static AlertDialog.Builder getExitDialog(AlertDialog.Builder builder,
                                                    DialogInterface.OnClickListener listener) {

        return getDialog(builder, listener,"退出","是否退出", "取消","确定");

    }

    public static AlertDialog.Builder getDialog(AlertDialog.Builder builder,
                                                DialogInterface.OnClickListener listener, String title, String message, String nagativeMessage, String positiveMessage) {
        builder.setTitle(title);
        builder.setMessage(message);
        if(!TextUtils.isEmpty(nagativeMessage))
        builder.setNegativeButton(nagativeMessage, null);
        builder.setPositiveButton(positiveMessage, listener);
        return builder;
    }


}
