package com.silverstone.kahvedeneme3.AdMob

import android.content.Context
import com.silverstone.kahvedeneme3.MainActivity

fun showInterstitialAd(context: Context){
    (context as MainActivity).loadInterstitialAd {
        if (it){
            context.showInterstitialAd()
        }
    }
}

