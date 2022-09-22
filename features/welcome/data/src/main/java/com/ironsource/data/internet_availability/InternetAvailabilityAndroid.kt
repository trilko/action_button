package com.ironsource.data.internet_availability

import android.content.Context
import android.net.ConnectivityManager

class InternetAvailabilityAndroid(
    private val context: Context
) : InternetAvailable {

    override fun check(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}