package com.silverstone.kahvedeneme3.AdMob

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdMobBanner() {
    Column() {
        Spacer(modifier = Modifier.size(2.dp))
        AndroidView(modifier = Modifier.fillMaxWidth(), factory ={

            AdView(it).apply {
                setAdSize(AdSize.BANNER)
                adUnitId="ca-app-pub-3940256099942544/6300978111"
                loadAd(AdRequest.Builder().build())
            }
        }

        )
    }
}