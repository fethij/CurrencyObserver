package com.github.devjn.currencyobserver.utils


/**
 * Created by @author Jahongir on 30-Apr-17
 * devjn@jn-arts.com
 * Utils
 */
object Utils {

    val TAG = Utils::class.java.simpleName

    fun <T : Any, R : Any> whenAllNotNull(vararg options: T?, block: (List<T>) -> R) {
        if (options.all { it != null }) {
            block(options.filterNotNull())
        }
    }

    fun <T : Any, R : Any> whenAnyNotNull(vararg options: T?, block: (List<T>) -> R) {
        if (options.any { it != null }) {
            block(options.filterNotNull())
        }
    }

}