package com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.silverstone.kahvedeneme3.Database.Asamalar
import com.silverstone.kahvedeneme3.ViewModel.ViewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.silverstone.kahvedeneme3.AdMob.showInterstitialAd
import com.silverstone.kahvedeneme3.R
import com.silverstone.kahvedeneme3.Views.GifLoader
import com.silverstone.kahvedeneme3.Views.SlideToTrigger
import com.silverstone.kahvedeneme3.ui.theme.Kahverengi


@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun AsamaScreen(
    viewModel: ViewModel,
    kahveId: Int,
    navController: NavController
) {
    val currentAsamaIndex = remember { mutableStateOf(0) }
    val asamalar = remember { mutableStateOf<List<Asamalar>>(emptyList()) }
    val currentAsama = asamalar.value.getOrNull(currentAsamaIndex.value)
    val description = currentAsama?.description ?: "Bilgi bulunamadı"
    val imageName = currentAsama?.image ?: "fkbas"
    val context= LocalContext.current




    LaunchedEffect(kahveId) {
        asamalar.value = viewModel.getAsamalarByKahveId(kahveId)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        AnimatedContent(targetState = currentAsama,
            transitionSpec = {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            }
        ) {
            Column() {
                Text(
                    text = description,
                    fontSize = 25.sp,
                    style = LocalTextStyle.current.copy(lineHeight = 34.sp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp), contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = getImageResourceId(imageName),
                        imageLoader = GifLoader(),
                        contentDescription = null, modifier = Modifier
                            .fillMaxWidth()
                    )
                }

            }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {

        IconButton(onClick = {
            if (currentAsamaIndex.value > 0) currentAsamaIndex.value--
            else navController.popBackStack()
        },Modifier.size(40.dp).padding(bottom = 10.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.backbutton),
                contentDescription = "",
                tint = Kahverengi
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp), verticalArrangement = Arrangement.Bottom
        ) {
            SlideToTrigger(
                onTrigger = {
                    if (currentAsamaIndex.value < asamalar.value.size - 1)
                        currentAsamaIndex.value++
                    else{
                        showInterstitialAd(context = context)
                        navController.navigate("bitis")

                    }
                }, GifLoader()
            )
        }
    }
    }
}
fun getImageResourceId(imageName:String):Int{
    return R.drawable::class.java.getField(imageName).getInt(null)
}

