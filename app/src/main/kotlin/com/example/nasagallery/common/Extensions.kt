package com.example.nasagallery.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
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