package com.github.devjn.currencyobserver.utils

import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream


/**
 * Created by @author Jahongir on 18-Apr-17
 * devjn@jn-arts.com
 * NativeUtils
 */
object NativeUtils {

    lateinit var resolver: NativeUtilsResolver
        private set

    fun registerResolver(resolver: NativeUtilsResolver) {
        println("registering Resolver")
        NativeUtils.resolver = resolver
        Log.d("NativeUtils", "registerResolver: " + resolver)
    }
}

interface NativeUtilsResolver {

    val cacheDir : File

//    fun mainThread(): Scheduler

    fun isNetworkAvailable(): Boolean

    fun getFileOutputStreamFor(filename: String): FileOutputStream;

    fun openFileInputStreamFor(filename: String): FileInputStream;

    fun openRawResource(filename: String): InputStream;

}