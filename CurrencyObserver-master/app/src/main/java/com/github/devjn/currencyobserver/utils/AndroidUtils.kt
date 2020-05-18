package com.github.devjn.currencyobserver.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import com.github.devjn.currencyobserver.App
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream


/**
 * Created by @author Jahongir on 30-Apr-17
 * devjn@jn-arts.com
 * Utils
 */
object AndroidUtils : NativeUtilsResolver {

    val TAG = AndroidUtils::class.java.simpleName

    var density = 1f

    fun setup(context: Context) {
        density = context.resources.displayMetrics.density;
    }

    fun dp(value: Float): Int {
        return Math.ceil((density * value).toDouble()).toInt()
    }


    fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
        val drawable: Drawable = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            DrawableCompat.wrap(ContextCompat.getDrawable(context, drawableId)!!).mutate() else
            ContextCompat.getDrawable(context, drawableId)!!

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

/*    fun startCustomTab(activity: Activity, url: String) {

        val uri = Uri.parse(url)

        // create an intent builder
        val intentBuilder = CustomTabsIntent.Builder()

        // Customizing
        intentBuilder.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary))
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark))

        // build custom tabs intent
        val customTabsIntent = intentBuilder.build()

        // launch the url
        customTabsIntent.launchUrl(activity, uri)
    }*/


    //  ------- NativeUtilsReolver


    override val cacheDir: File
        get() = App.applicationContext.cacheDir

    override fun isNetworkAvailable(): Boolean = checkIfHasNetwork()

    override fun getFileOutputStreamFor(filename: String): FileOutputStream = App.applicationContext.openFileOutput(filename, Context.MODE_PRIVATE)

    override fun openFileInputStreamFor(filename: String): FileInputStream = App.applicationContext.openFileInput(filename)

    override fun openRawResource(filename: String): InputStream = App.applicationContext.assets.open(filename)

    @SuppressLint("WrongConstant")
    fun checkIfHasNetwork(): Boolean {
        val cm = App.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}


/*
fun loadImage(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) return
    GlideApp.with(imageView.context).asBitmap().placeholder(R.drawable.default_specialization).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView)
}*/
