package com.github.devjn.currencyobserver.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

object IOSUtils : NativeUtilsResolver {

    val TAG = IOSUtils::class.java.simpleName


    //  ------- NativeUtilsReolver


    override val cacheDir: File
    get() = File("cacheDir")


    override fun isNetworkAvailable(): Boolean = checkIfHasNetwork()

    override fun getFileOutputStreamFor(filename: String): FileOutputStream = FileOutputStream(filename)

    override fun openFileInputStreamFor(filename: String): FileInputStream = FileInputStream(filename)

    override fun openRawResource(filename: String): InputStream = FileInputStream(filename)


    fun checkIfHasNetwork(): Boolean {

        return true
    }

}