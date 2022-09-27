package com.example.nasagallery.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.view.WindowCompat
import java.io.IOException
import java.nio.charset.Charset

fun Context.getJSONFromAssets(name: String): String? {
    var json: String? = null
    val charset: Charset = Charsets.UTF_8
    try {
        val myUsersJSONFile = this.assets.open(name)
        val size = myUsersJSONFile.available()
        val buffer = ByteArray(size)
        myUsersJSONFile.read(buffer)
        myUsersJSONFile.close()
        json = String(buffer, charset)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
    return json
}

fun Context.getActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}

fun Activity.hideSystemUI() {

    //Hides the ugly action bar at the top
    actionBar?.hide()

    //Hide the status bars

    WindowCompat.setDecorFitsSystemWindows(window, false)

    @Suppress("DEPRECATION")
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    } else {
        window.insetsController?.apply {
            hide(WindowInsets.Type.statusBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}