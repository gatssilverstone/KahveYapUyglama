package com.silverstone.kahvedeneme3.Views


import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.silverstone.kahvedeneme3.R
import com.silverstone.kahvedeneme3.ui.theme.Gold
import com.silverstone.kahvedeneme3.ui.theme.letter


@Composable
fun SlideToTrigger(onTrigger: () -> Unit, img: ImageLoader) {
    val offsetX = remember { mutableStateOf(0f) }
    val isTrig = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .paint(painterResource(id = R.drawable.swipebg), contentScale = ContentScale.FillBounds)
            // .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 30.dp)
            .clip(RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectHorizontalDragGestures(onDragEnd = {
                    isTrig.value = false
                }) { change, dragAmount, ->
                    change.consume()
                    offsetX.value += dragAmount
                    if (!isTrig.value && offsetX.value > 100.dp.toPx()) {
                        onTrigger()
                        offsetX.value = 0f
                        isTrig.value = true
                    }
                }
            }
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.fillMaxHeight().width(40.dp).align(Alignment.CenterVertically)) {
                    AsyncImage(model = R.drawable.swipe, imageLoader = img, contentDescription = null, modifier = Modifier.fillMaxSize())
                }
                Text(
                    text = "Sıradaki", fontFamily = letter, color = Gold, fontSize = 32.sp,
                    modifier = Modifier.padding(1.dp)
                )
                Box(modifier = Modifier.fillMaxHeight().width(45.dp).align(Alignment.CenterVertically)) {
                    AsyncImage(model = R.drawable.swipe, imageLoader = img, contentDescription = null, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
