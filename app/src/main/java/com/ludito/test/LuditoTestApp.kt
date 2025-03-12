package com.ludito.test

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 2:40 PM
 * Email: Kjuraev.001@gmail.com
 */

@HiltAndroidApp
class LuditoTestApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
    }
}