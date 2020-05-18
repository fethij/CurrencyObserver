package com.github.devjn.currencyobserver.utils

import android.content.Context
import apple.foundation.NSURL

fun String.toNSURL() = NSURL.URLWithString(this)