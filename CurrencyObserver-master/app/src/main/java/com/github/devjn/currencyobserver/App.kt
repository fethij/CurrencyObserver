package com.github.devjn.currencyobserver

import android.app.Application
import android.content.Context
import com.github.devjn.currencyobserver.utils.AndroidUtils
import com.github.devjn.currencyobserver.utils.NativeUtils


/**
 * Created by @author Jahongir on 01-Mar-18
 * devjn@jn-arts.com
 * App
 */
class App : Application() {

    companion object {
        @Volatile
        lateinit var applicationContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Companion.applicationContext = applicationContext
        NativeUtils.registerResolver(AndroidUtils.apply { setup(applicationContext) })
    }

}