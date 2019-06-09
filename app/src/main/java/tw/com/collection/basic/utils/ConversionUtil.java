package tw.com.collection.basic.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ConversionUtil {

    /**
     * 轉字串去NULL
     * @param object object
     * @return String
     */
    public static String StrNoNull(Object object) {
        String value;

        if (object==null) {
            value="";
        }else {
            value=object.toString();
        }

        return value;
    }

    /**
     * 去NULL回傳Int
     * @param object object
     * @return int
     */
    public static int StrNoNullToInt(Object object) {
        int value;

        if (object==null) {
            value=0;
        }else {
            if ("".equals(object)) {object="0";}
            value=Integer.valueOf(object.toString());
        }

        return value;
    }

    /**
     * 去NULL回傳String
     * @param object object
     * @return String(date type 0000-00-00 00:00:00)
     */
    public static String StrNoNullToDate(Object object) {
        String value;

        if (object==null) {
            value="0000-00-00 00:00:00";
        }
        else {
            if ("".equals(object)) {
                value="0000-00-00 00:00:00";
            }
            else {
                value=object.toString();
            }
        }

        return value;
    }

    /**
     * int轉boolean 0= false 123= true
     * @param integer integer
     * @return boolean
     */
    public static boolean IntToBoolean(int integer) {
        boolean value;

        value = integer != 0;

        return value;
    }

    /**
     * boolean轉int false=0 true=1
     * @param parmboolean boolean
     * @return int
     */
    public static int BooleanToInt(boolean parmboolean) {
        int value = 0;

        if (parmboolean) { value = 1;}//true

        return value;
    }

    /**
     * Drawable轉Bitmap
     * @param drawable drawable
     * @return Bitmap
     */
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
