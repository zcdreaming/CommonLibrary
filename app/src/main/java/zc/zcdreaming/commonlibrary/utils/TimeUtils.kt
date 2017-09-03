package zc.zcdreaming.commonlibrary.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Formatter
import java.util.Locale

/**
 * Created by zcdreaming on 2017/7/27.
 */

object TimeUtils {

    internal var mFormatBuilder = StringBuilder()
    internal var mFormatter = Formatter(mFormatBuilder, Locale.getDefault())
    internal var SECONDS_OF_MINUTE: Long = 60
    internal var SECONDS_OF_HOUR: Long = 3600
    internal var SECONDS_OF_DAY: Long = 86400
    internal var SECONDS_OF_MONTH: Long = 2592000

    var formatter_a = SimpleDateFormat("yyyy-MM-dd")
    var formatter_b = SimpleDateFormat("HH:mm:ss")
    var formatter_c = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var formatter_d = SimpleDateFormat("yyyyMMddHHmmss")
    var formatter_e = SimpleDateFormat("HH:mm")
    var formatter_f = SimpleDateFormat("MM-dd")
    var formatter_g = SimpleDateFormat("yyyyMMdd")
    var formatter_h = SimpleDateFormat("dd/MM")
    var formatter_l = SimpleDateFormat("yyyy-MM-dd 	HH:mm")


    fun getRelativeTimeString(utcTime: Long): String {
        var result = "刚刚"
        val delta = utCtime - utcTime
        if (delta >= SECONDS_OF_MINUTE && delta < SECONDS_OF_HOUR) {
            result = (delta / SECONDS_OF_MINUTE).toString() + "分钟前"
        } else if (delta >= SECONDS_OF_HOUR && delta < SECONDS_OF_DAY) {
            result = (delta / SECONDS_OF_HOUR).toString() + "小时前"
        } else if (delta >= SECONDS_OF_DAY && delta < SECONDS_OF_MONTH) {
            result = (delta / SECONDS_OF_DAY).toString() + "天前"
        } else if (delta >= SECONDS_OF_MONTH) {
            val t = utcTime * 1000
            val d = Date(t)
            result = formatter_f.format(d)
        }
        return result
    }

    val currentTime_b: String
        get() {
            val now = Date()
            return formatter_b.format(now)
        }

    /**
     * 把秒数转化为时分秒的形式
     * @param second
     * @return
     */
    fun getHourFromSecond(second: Long): String {
        val seconds = second % 60
        val minutes = second / 60 % 60
        val hours = second / 3600
        mFormatBuilder.setLength(0)
        return mFormatter.format("%02d:%02d:%02d", hours, minutes, seconds).toString()
    }

    /**
     * 把秒数转化为分秒毫秒的形式
     * @param msecond
     * @return
     */
    fun getmssFromMSecond(msecond: Long): String {
        val ms = msecond % 1000
        val s = msecond / 1000 % 60
        val m = msecond / 60000
        mFormatBuilder.setLength(0)
        return mFormatter.format("%02d:%02d:%02d", m, s, ms).toString()
    }

    /**
     * @param second second单位为秒,UTC时间
     * @return
     */
    fun getDateString_g(second: Long): String {
        val t = second.toLong() * 1000
        val d = Date(t)
        return formatter_g.format(d)
    }

    val currentYearUTC: Long
        get() {
            val currCal = Calendar.getInstance()
            val currentYear = currCal.get(Calendar.YEAR)
            currCal.clear()
            currCal.set(Calendar.YEAR, currentYear)
            return currCal.timeInMillis / 1000
        }

    val utCtime: Long
        get() {
            val time = System.currentTimeMillis()
            return time / 1000
        }

    fun getDateWithoutYearString_a(i: Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, i)
        val d = cal.time
        return formatter_a.format(d)
    }

    fun getUTCMillisecond(i: Int): Long {
        val cal = java.util.Calendar.getInstance()
        cal.add(Calendar.DATE, i)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis / 1000
    }

    fun getUTCMilliSecondByDate(year: Int, month: Int, day: Int): Long {
        val cal = java.util.Calendar.getInstance()
        cal.set(year, month - 1, day)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis / 1000
    }
}
