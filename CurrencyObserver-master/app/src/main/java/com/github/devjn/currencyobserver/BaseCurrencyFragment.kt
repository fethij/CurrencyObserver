package com.github.devjn.currencyobserver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.devjn.currencyobserver.adapters.RecyclerViewAdapter
import kotlinx.coroutines.experimental.Deferred


/**
 * Created by @author Jahongir on 02-Mar-18
 * devjn@jn-arts.com
 * BaseCurrencyFragment
 */
abstract class BaseCurrencyFragment<DATA> : Fragment() {

    protected open val viewType = 0

    protected lateinit var list: RecyclerView
    protected lateinit var adapter: RecyclerViewAdapter<DATA>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_responseitem_list, container, false)

        list = view.findViewById(R.id.list)
        adapter = RecyclerViewAdapter(emptyList(), viewType)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        doRequest()
    }

    abstract fun doRequest(): Deferred<*>

    protected fun updateData(result: List<DATA>) = adapter.setData(result)

}
