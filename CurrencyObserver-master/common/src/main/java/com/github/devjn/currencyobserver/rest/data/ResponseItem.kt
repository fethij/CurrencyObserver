package com.github.devjn.currencyobserver.rest.data

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by @author vlad on 28.02.2018
 * devjn@jn-arts.com
 * ResponseItem
 */
data class ResponseItem(@SerializedName("id")
                        val id: String = "",
                        @SerializedName("name")
                        val name: String = "",
                        @SerializedName("symbol")
                        val symbol: String = "",
                        @SerializedName("rank")
                        val rank: Int = 0,
                        @SerializedName("price_usd")
                        val priceUsd: Float = 0.0f,
                        @SerializedName("price_btc")
                        val priceBtc: Float? = null,
                        @SerializedName("24h_volume_usd")
                        val _24hVolumeUsd: String? = null,
                        @SerializedName("market_cap_usd")
                        val marketCapUsd: String? = null,
                        @SerializedName("available_supply")
                        val availableSupply: String? = null,
                        @SerializedName("total_supply")
                        val totalSupply: String? = null,
                        @SerializedName("max_supply")
                        val maxSupply: String? = null,
                        @SerializedName("percent_change_1h")
                        val percentChange1h: Float = 0.0f,
                        @SerializedName("percent_change_24h")
                        val percentChange24h: Float = 0.0f,
                        @SerializedName("percent_change_7d")
                        val percentChange7d: Float = 0.0f,
                        @SerializedName("last_updated")
                        val lastUpdated: String? = null) {

    fun getImageUrl() = "https://raw.githubusercontent.com/cjdowner/cryptocurrency-icons/master/128/color/${symbol.toLowerCase(Locale.US)}.png"

}