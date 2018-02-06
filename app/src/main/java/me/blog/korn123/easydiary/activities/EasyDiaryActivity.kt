package me.blog.korn123.easydiary.activities

import android.content.Intent
import io.github.hanjoongcho.commons.activities.BaseSimpleActivity
import me.blog.korn123.easydiary.extensions.updateTextColors
import me.blog.korn123.commons.constants.Constants
import me.blog.korn123.commons.utils.CommonUtils

/**
 * Created by hanjoong on 2017-05-03.
 */

open class EasyDiaryActivity : BaseSimpleActivity() {
    override fun onPause() {
        super.onPause()
        val enableLock = CommonUtils.loadBooleanPreference(this@EasyDiaryActivity, Constants.APP_LOCK_ENABLE)
        if (enableLock) {
            val currentMillis = System.currentTimeMillis()
            CommonUtils.saveLongPreference(this@EasyDiaryActivity, Constants.SETTING_PAUSE_MILLIS, currentMillis)
        }
    }

    override fun onResume() {
        super.onResume()
        val enableLock = CommonUtils.loadBooleanPreference(this@EasyDiaryActivity, Constants.APP_LOCK_ENABLE)
        val pauseMillis = CommonUtils.loadLongPreference(this@EasyDiaryActivity, Constants.SETTING_PAUSE_MILLIS, 0)
        if (enableLock && pauseMillis != 0L) {
            if (System.currentTimeMillis() - pauseMillis > 1000) {
                // 잠금해제 화면
                val lockDiaryIntent = Intent(this@EasyDiaryActivity, DiaryLockActivity::class.java)
                startActivity(lockDiaryIntent)
            }
        }

        updateTextColors(findViewById(android.R.id.content))
    }
}