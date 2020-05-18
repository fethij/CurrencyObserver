package com.github.devjn.currencyobserver.rest.data

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("rates")
	val rates: Map<String, Double>? = null,

	@field:SerializedName("base")
	val base: String? = null
)