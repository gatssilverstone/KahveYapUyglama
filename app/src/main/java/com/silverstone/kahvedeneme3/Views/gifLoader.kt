package com.silverstone.kahvedeneme3.Views

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun GifLoader():ImageLoader{
    val imageLoader = ImageLoader.Builder(LocalContext.current)

        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    return imageLoader
}