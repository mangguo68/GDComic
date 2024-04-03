package com.graduation.design.domian.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Looper;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EdgeEffect;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Version 1.0
 */
public class MUtils {

    /**
     * 01
     * 方法：通过SharedPreferences储存文本
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putIn(Context context, String key, String value) {
        context.getSharedPreferences(key, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    /**
     * 02
     * 方法：通过SharedPreferences读取文本
     *
     * @param context 上下文
     * @param key     键
     * @return 获取到的文本
     */
    public static String putOut(Context context, String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE).getString(key, "");
    }

    /**
     * 03
     * 方法：简易对话框1
     *
     * @param thisContext MainActivity.this主界面的上下文
     * @param title       对话框标题
     * @param message     对话框内容
     * @param listener    点击确定按钮的点击事件
     */
    public static void easyDialog(Context thisContext, String title, String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(thisContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    /**
     * 04
     * 方法：简易对话框2
     *
     * @param thisContext MainActivity.this主界面的上下文
     * @param title       对话框标题
     * @param message     对话框内容
     * @param listenerYes 点击确定按钮的点击事件
     * @param listenerNo  点击取消按钮的点击事件
     */
    public static void easyDialog(Context thisContext, String title, String message, DialogInterface.OnClickListener listenerYes, DialogInterface.OnClickListener listenerNo) {
        new AlertDialog.Builder(thisContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", listenerYes)
                .setNegativeButton("取消", listenerNo)
                .create()
                .show();
    }

    /**
     * 05
     * 方法：简易提示1
     *
     * @param thisContext MainActivity.this主界面的上下文
     */
    public static void showTips(Context thisContext) {
        Toast.makeText(thisContext, "这是一个提示", Toast.LENGTH_SHORT).show();
    }

    /**
     * 06
     * 方法：简易提示2
     *
     * @param thisContext MainActivity.this主界面的上下文
     * @param content     要显示的信息
     */
    public static void showTips(Context thisContext, String content) {
        Toast.makeText(thisContext, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 07
     * 方法：这个方法将一个TextView的内容转化成图片，这个方法是一个完美的方法，解决一般方法不能用的情形, 但是有局限性，只能用textView，并且背景图片不能太大
     *
     * @param view 将视图转化为图片，你要将哪个视图转化为图片
     * @return 转化成的图片
     */
    public static Bitmap convertTextViewToBitmap(TextView view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 10
     * 方法：使图片圆角化
     *
     * @param bitmap  要圆角化的图片
     * @param roundPX 圆角半径
     * @return 处理后得到的圆角图片
     */
    public static Bitmap getRoundCornerBitmap(Bitmap bitmap, float roundPX) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        paint.setColor(color);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return bitmap2;
    }

    /**
     * 11
     * 方法：加载一张图片不放大
     *
     * @param context 上下文MainActivity.this
     * @param id      R.drawable.**
     * @return 加载后的图片
     */
    public static Bitmap loadBitmapWithoutScale(Context context, int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
        return bitmap;
    }

    /**
     * 12
     * 方法：模糊一张图片，返回模糊后的图片，缺点就是模糊度太小，最大只有25f
     *
     * @param context 上下文
     * @param bitmap  要模糊的图片
     * @param radius  模糊半径，最大25f
     * @return 模糊后的图片
     */
    public static Bitmap fastBlur(Context context, Bitmap bitmap, float radius) {
        //用需要创建高斯模糊bitmap创建一个空的bitmap
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // 初始化Renderscript，该类提供了RenderScript context，创建其他RS类之前必须先创建这个类，其控制RenderScript的初始化，资源管理及释放
        RenderScript rs = RenderScript.create(context);
        // 创建高斯模糊对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // 创建Allocations，此类是将数据传递给RenderScript内核的主要方 法，并制定一个后备类型存储给定类型
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //设定模糊度(注：Radius最大只能设置25.f)
        if (radius <= 25.f) {

            blurScript.setRadius(radius);
        } else {
            blurScript.setRadius(25.f);
        }
        // Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        // Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);
        // recycle the original bitmap
        // bitmap.recycle();
        // After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;
    }

    /**
     * 13
     * 方法：高度模糊一张图片，半径可以很大，但是比较消耗性能，想快速模糊一张图片，请使用上面的方法
     *
     * @param context   上下文
     * @param orgBitmap 要模糊的原始图片
     * @param radius    模糊半径
     * @return 模糊后的图片
     */
    public static Bitmap blurBitmap(Context context, Bitmap orgBitmap, int radius) {

        Bitmap bitmap = orgBitmap.copy(orgBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        //Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        //Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        return (bitmap);
    }

    /**
     * 14
     * 方法：截取屏幕截图，重要！！截图不包含状态栏！！
     *
     * @param activity 上下文，也就是MainActivity
     * @return 位图一张
     */
    public static Bitmap takeScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        //System.out.println(statusBarHeight);
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bitmap2;
    }

    /**
     * 15
     * 方法：获取状态栏高度，如果获取不到高度会返回0
     *
     * @param context 上下文
     * @return 状态栏高度，int值
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 16
     * 方法：获取控件在屏幕中的位置，0表示x，1表示y
     *
     * @param view 控件
     * @return 位置int型数组，0表式x，1表示y
     */
    public static int[] getViewLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

    /**
     * 17
     * 方法：获取控件背后的图像。
     *
     * @param context 上下文
     * @param view    要设置透明背景的控件，主要是ImageView
     */
    public static void letViewBecomeTranslucent(Context context, ImageView view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setImageBitmap(Bitmap.createBitmap(takeScreenShot((Activity) context), getViewLocationOnScreen(view)[0], getViewLocationOnScreen(view)[1] - getStatusBarHeight(context), view.getMeasuredWidth(), view.getMeasuredHeight()));
            }
        });
    }

    /**
     * 18
     * 方法：获取当前系统时间
     *
     * @return 返回当前系统时间并格式化
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    /**
     * 19
     * 方法：从xml里面加载布局，缺点是宽高不会生效
     *
     * @param context 上下文
     * @param res     xml的resId
     * @return xml的根布局
     */
    public static View getLayout(Context context, int res) {
        return LayoutInflater.from(context).inflate(res, null);
    }

    /**
     * 20
     * 方法：获取屏幕密度
     *
     * @param context 上下文
     * @return 返回屏幕密度
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 21
     * 方法：获取屏幕的宽度和高度
     *
     * @param context 上下文
     * @return 一个包含屏幕宽高的字符串
     */
    public static String printScreenHeightAndWidth(Context context) {
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        return "屏幕的宽高：" + widthPixels + "*" + heightPixels;
    }

    /**
     * 22
     * 方法：获取随机颜色值
     *
     * @return 一个随机颜色值，例如：#xxxxxx,使用时需要用Color.parseColor()来转换一下
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }

    /**
     * 23
     * 方法：向一个url地址发送get请求
     *
     * @param context 上下文，用于toast
     * @param url     要请求的地址
     * @return 请求后获得的结果(为字符串结果 ， 二进制流不适合)
     */
    public static String sendRequest(Context context, String url) {
        String result = "";
        //指定接口链接
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
            //发送请求并得到结果
            urlConnection.setConnectTimeout(6000);
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine = null;
            while ((inputLine = bufferedReader.readLine()) != null) {
                result = inputLine;
            }
            inputStream.close();
        } catch (IOException e) {
            Looper.prepare();
            MUtils.showTips(context, "网络错误！");
            e.printStackTrace();
            Looper.loop();
        }
        return result;
    }

    /**
     * 24
     * 方法：向一个url地址发送get请求，不过要自己捕获异常
     *
     * @param context 上下文，用于toast
     * @param url     要请求的地址
     * @return 请求后获得的结果(为字符串结果 ， 二进制流不适合)
     */
    public static String sendRequestWithException(Context context, String url) throws IOException {
        String result = "";
        //指定接口链接
        URLConnection urlConnection = new URL(url).openConnection();
        //发送请求并得到结果
        urlConnection.setConnectTimeout(6000);
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputLine = null;
        while ((inputLine = bufferedReader.readLine()) != null) {
            result = inputLine;
        }
        inputStream.close();
        return result;
    }

    /**
     * 25
     * 方法：设置沉浸式状态栏
     *
     * @param activity 要设置沉浸式状态栏的activity
     */
    public static void setImmersiveStatueBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);//状态栏颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//文字颜色为暗色
                }
            } else {
                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 26
     * 方法：找出最后一个与之匹配的项目，记住是最后一个哦
     *
     * @param regex 正则表达式
     * @param text  要查找的文本
     * @return 查找的内容
     */
    public static String find(String regex, CharSequence text) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        String result = "";
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    /**
     * 27
     * 方法：根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return 像素值
     */
    public static int dip2px(Context context, float dpValue) {
        // 获取当前手机的像素密度（1个dp对应几个px）
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f); // 四舍五入取整
    }

    /**
     * 28
     * 方法：根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context 上下文
     * @param pxValue 像素值
     * @return dp值
     */
    public static int px2dip(Context context, float pxValue) {
        // 获取当前手机的像素密度（1个dp对应几个px）
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f); // 四舍五入取整
    }

    /**
     * 29
     * 方法：从xml里面加载布局，但是附带布局的宽高
     *
     * @param context 上下文
     * @param res     xml的resId
     * @param parent  父布局
     * @return xml的根布局
     */
    public static View getLayoutWithWidthHeight(Context context, ViewGroup parent, int res) {
        return LayoutInflater.from(context).inflate(res, parent, false);
    }

    /**
     * 30
     * 方法：为scrollview设置阴影颜色
     *
     * @param scrollView 要设置阴影颜色的scrollview
     */
    public static void setShadowColor(ScrollView scrollView) {
        try {
            //修改头部阴影颜色
            @SuppressLint("SoonBlockedPrivateApi") Field topMethod = ScrollView.class.getDeclaredField("mEdgeGlowTop");
            topMethod.setAccessible(true);
            EdgeEffect top = (EdgeEffect) topMethod.get(scrollView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                top.setColor(Color.parseColor("#80FF0000"));
            }
            //修改底部阴影颜色
            @SuppressLint("SoonBlockedPrivateApi") Field bottomMethod = ScrollView.class.getDeclaredField("mEdgeGlowBottom");
            bottomMethod.setAccessible(true);
            EdgeEffect bottom = (EdgeEffect) bottomMethod.get(scrollView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                bottom.setColor(Color.parseColor("#80FF0000"));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 31
     * 快捷绑定viewPager2和tabLayout
     * @param viewPager2 vp
     * @param tabLayout tab
     * @param titleList tab要显示的标题，要按照顺序来传
     */
    public static void bindWithTabLayout(ViewPager2 viewPager2, TabLayout tabLayout, List<String> titleList) {
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position < titleList.size())
                tab.setText(titleList.get(position));
        }).attach();
    }
}
