package exercises.android.ronm.shoenotifications

import android.app.Application

private const val SP_NAME = "user_sp"
private const val SP_ONBOARDING_DONE_KEY = "sp_key_onboarding_done"

class ShoeNotificationsApp : Application() {

    var onboardingDone : Boolean = false
    set(value) {
        field = value
        // save to SP
        val sp = getSharedPreferences(SP_NAME , MODE_PRIVATE)
        sp.edit().putBoolean(SP_ONBOARDING_DONE_KEY, field).apply()
    }

    override fun onCreate() {
        super.onCreate()
        val sp = getSharedPreferences(SP_NAME , MODE_PRIVATE)
        onboardingDone = sp.getBoolean(SP_ONBOARDING_DONE_KEY, false)
    }
}