package me.blog.korn123.easydiary.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import me.blog.korn123.easydiary.extensions.showAlarmNotification
import me.blog.korn123.easydiary.fragments.SettingsScheduleFragment
import me.blog.korn123.easydiary.helper.EasyDiaryDbHelper

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val alarmId = intent.getIntExtra(SettingsScheduleFragment.ALARM_ID, -1)
        EasyDiaryDbHelper.readAlarmBy(alarmId)?.let {
            context.showAlarmNotification(it)
        }
    }
}