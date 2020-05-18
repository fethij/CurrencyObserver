package com.github.devjn.currencyobserver.rest

import com.github.devjn.currencyobserver.rest.data.CurrencyResponse
import com.github.devjn.currencyobserver.rest.data.ResponseItem
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET


/**
 * Created by @author Jahongir on 01-Mar-18
 * devjn@jn-arts.com
 * RestApi
 */


interface RestApiCrypto {

    @GET("v1/ticker/")
    fun getCryptocurrency(): Deferred<List<ResponseItem>>

}

interface RestApiCurrency {

    @GET("latest")
    fun getCurrency(): Deferred<CurrencyResponse>

}