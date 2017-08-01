package zc.zcdreaming.commonlibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by zhaochen on 2017/7/27.
 */

public class TimeUtils {

    static StringBuilder mFormatBuilder = new StringBuilder();
    static Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    static long SECONDS_OF_MINUTE = 60;
    static long SECONDS_OF_HOUR = 3600;
    static long SECONDS_OF_DAY = 86400;
    static long SECONDS_OF_MONTH = 2592000;

    public static SimpleDateFormat formatter_a = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat formatter_b = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat formatter_c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat formatter_d = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat formatter_e = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat formatter_f = new SimpleDateFormat("MM-dd");
    public static SimpleDateFormat formatter_g = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat formatter_h = new SimpleDateFormat("dd/MM");
    public static SimpleDateFormat formatter_l = new SimpleDateFormat("yyyy-MM-dd 	HH:mm");


    public static String getRelativeTimeString(long utcTime) {
        String result = "刚刚";
        long delta = getUTCtime() - utcTime;
        if (delta >= SECONDS_OF_MINUTE && delta < SECONDS_OF_HOUR) {
            result = delta / SECONDS_OF_MINUTE + "分钟前";
        } else if (delta >= SECONDS_OF_HOUR && delta < SECONDS_OF_DAY) {
            result = delta / SECONDS_OF_HOUR + "小时前";
        } else if (delta >= SECONDS_OF_DAY && delta < SECONDS_OF_MONTH) {
            result = delta / SECONDS_OF_DAY + "天前";
        } else if (delta >= SECONDS_OF_MONTH) {
            long t = utcTime * 1000;
            Date d = new Date(t);
            result = formatter_f.format(d);
        }
        return result;
    }

    public static String getCurrentTime_b() {
        Date now = new Date();
        return formatter_b.format(now);
    }

    /**
     * 把秒数转化为时分秒的形式
     * @param second
     * @return
     */
    public static String getHourFromSecond(long second) {
        long seconds = second % 60;
        long minutes = (second / 60) % 60;
        long hours = second / 3600;
        mFormatBuilder.setLength(0);
        return mFormatter.format("%02d:%02d:%02d", hours, minutes, seconds).toString();
    }

    /**
     * 把秒数转化为分秒毫秒的形式
     * @param msecond
     * @return
     */
    public static String getmssFromMSecond(long msecond) {
        long ms = msecond % 1000;
        long s = (msecond / 1000) % 60;
        long m = msecond / 60000;
        mFormatBuilder.setLength(0);
        return mFormatter.format("%02d:%02d:%02d", m, s, ms).toString();
    }

    /**
     * @param second second单位为秒,UTC时间
     * @return
     */
    public static String getDateString_g(long second) {
        long t = (long) second * 1000;
        Date d = new Date(t);
        return formatter_g.format(d);
    }

    public static long getCurrentYearUTC() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        currCal.clear();
        currCal.set(Calendar.YEAR, currentYear);
        return currCal.getTimeInMillis() / 1000;
    }

    public static long getUTCtime() {
        long time = System.currentTimeMillis();
        return time / 1000;
    }

    public static String getDateWithoutYearString_a(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, i);
        Date d = cal.getTime();
        return formatter_a.format(d);
    }

    public static Long getUTCMillisecond(int i) {
        Calendar cal = java.util.Calendar.getInstance();
        cal.add(Calendar.DATE, i);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    public static Long getUTCMilliSecondByDate(int year, int month, int day) {
        Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month - 1, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }
}
