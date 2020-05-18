package com.github.devjn.currencyobserver

import com.github.devjn.currencyobserver.rest.RestService
import com.github.devjn.currencyobserver.rest.data.ResponseItem
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class CryptoCurrencyFragment : BaseCurrencyFragment<ResponseItem>() {

    override fun doRequest() = async(UI) {
        val apiService = RestService.getCryptoCurrencyService()
        val result = apiService.getCryptocurrency().await()
        updateData(result)
    }

}
