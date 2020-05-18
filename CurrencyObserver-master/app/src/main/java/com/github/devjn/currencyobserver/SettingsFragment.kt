package com.github.devjn.currencyobserver

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

/**
 * Created by @author Jahongir on 31-Mar-18
 * devjn@jn-arts.com
 * SettingsActivity
 */
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Indicate here the XML resource you created above that holds the preferences
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}
