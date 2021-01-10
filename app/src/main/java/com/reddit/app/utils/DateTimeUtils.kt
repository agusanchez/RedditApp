package com.reddit.app.utils

import android.content.Context
import com.reddit.app.R
import java.util.*

class DateTimeUtils {

    companion object {

        private const val SECOND_MILLIS = 1000
        private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
        private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
        private const val DAY_MILLIS = 24 * HOUR_MILLIS

        fun getTimeAgo(context: Context, postedTime: Long): String {
            val diff = Calendar.getInstance().timeInMillis - (postedTime * SECOND_MILLIS)
            return when {
                diff < MINUTE_MILLIS -> context.getString(R.string.post_moment_ago)
                diff < 2 * MINUTE_MILLIS -> context.getString(R.string.post_a_minute_ago)
                diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} ${context.getString(R.string.post_minutes_ago)}"
                diff < 2 * HOUR_MILLIS -> context.getString(R.string.post_an_hour_ago)
                diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} ${context.getString(R.string.post_hours_ago)}"
                diff < 48 * HOUR_MILLIS -> context.getString(R.string.post_yesterday)
                else -> "${diff / DAY_MILLIS} ${context.getString(R.string.post_days_ago)}"
            }
        }
    }
}