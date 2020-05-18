package com.github.devjn.currencyobserver

import android.util.Log
import com.github.devjn.currencyobserver.rest.RestService
import com.github.devjn.currencyobserver.rest.data.CurrencyResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async


/**
 * Created by @author Jahongir on 02-Mar-18
 * devjn@jn-arts.com
 * CurrencyFragment
 */
class CurrencyFragment : BaseCurrencyFragment<Pair<String, Double>>() {

    override val viewType = 1

    override fun doRequest() = async(UI) {
        val apiService = RestService.getCurrencyService()
        val result = apiService.getCurrency().await()
        ad(result)
    }

    fun ad(result: CurrencyResponse) {

        println("CurrencyFragment " + "Result = " + result)
        Log.i("CurrencyFragment", "Result = " + result)

        val list = result.rates!!.entries.map { Pair(it.key, it.value) }

        updateData(list)
    }

}