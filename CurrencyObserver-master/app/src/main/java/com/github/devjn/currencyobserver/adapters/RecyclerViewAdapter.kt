package com.github.devjn.currencyobserver.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.PictureDrawable
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.devjn.currencyobserver.R
import com.github.devjn.currencyobserver.glide.GlideApp
import com.github.devjn.currencyobserver.glide.SvgSoftwareLayerSetter
import com.github.devjn.currencyobserver.rest.data.ResponseItem
import kotlinx.android.synthetic.main.list_item_cryptocurrency.view.*
import kotlinx.android.synthetic.main.list_item_currency.view.*
import java.util.*


/**
 * Created by @author devjn on 28.02.2018
 * devjn@jn-arts.com
 * RecyclerViewAdapter
 */
class RecyclerViewAdapter<DATA>(private var data: List<DATA>, val viewType: Int = 0) : RecyclerView.Adapter<RecyclerViewAdapter.BindableHolder<DATA>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableHolder<DATA> = if (viewType == 0)
        CryptoCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_cryptocurrency, parent, false)) as BindableHolder<DATA> else
        CurrencyCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_currency, parent, false)) as BindableHolder<DATA>

    override fun onBindViewHolder(holder: BindableHolder<DATA>, position: Int) = holder.bind(data.get(position))

    override fun getItemViewType(position: Int) = viewType

    override fun getItemCount() = data.size

    fun setData(data: List<DATA>) {
        this.data = data
        notifyDataSetChanged()
    }

    abstract class BindableHolder<DATA>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: DATA)
    }

    class CryptoCardViewHolder(itemView: View) : BindableHolder<ResponseItem>(itemView) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: ResponseItem) {

            GlideApp.with(itemView).load(data.getImageUrl()).into(itemView.imageView)

            itemView.symbol.text = data.symbol
            itemView.name.text = data.name
            itemView.money.text = "$" + data.priceUsd.toString()

            itemView.hour.colorizeByValue(R.string.percentChangeInHour, data.percentChange1h)
            itemView.hours.colorizeByValue(R.string.percentChangeIn24Hours, data.percentChange24h)
            itemView.days.colorizeByValue(R.string.percentChangeIn7Days, data.percentChange7d)
        }
    }

    class CurrencyCardViewHolder(itemView: View) : BindableHolder<Pair<String, Double>>(itemView) {
        @SuppressLint("SetTextI18n")
        override fun bind(data: Pair<String, Double>) {
            itemView.text.text = "1 EUR = ${data.second} ${data.first}"

            GlideApp.with(itemView)
                    .`as`(PictureDrawable::class.java)
                    .listener(SvgSoftwareLayerSetter())
                    .load(getImageUrl(data.first))
                    .into(itemView.imageView2)

        }

        private fun getImageUrl(name: String) = "http://www.xe.com/themes/xe/images/flags/svg/${name.toLowerCase(Locale.US)}.svg"
    }
}

private fun TextView.colorizeByValue(@StringRes res: Int, value: Float, subtext: String = value.toString()) {
    val string = resources.getString(res, value)
    setText(string, TextView.BufferType.SPANNABLE)
    val str = text as Spannable
    val i = string.indexOf(subtext)
    val color = ContextCompat.getColor(context, if (value >= 0.0) R.color.stocks_green else R.color.stocks_red)
    str.setSpan(ForegroundColorSpan(color), i, i + subtext.length + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}
