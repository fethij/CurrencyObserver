package com.github.devjn.currencyobserver

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_currency
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_currency -> {
                supportActionBar?.title = getString(R.string.title_currency)
                navigate(CurrencyFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cryptocurrency -> {
                supportActionBar?.title = getString(R.string.title_crypto_currency)
                navigate(CryptoCurrencyFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                supportActionBar?.title = getString(R.string.title_settings)
                navigate(SettingsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun navigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment, "MAIN")
                .commit()
    }

}
