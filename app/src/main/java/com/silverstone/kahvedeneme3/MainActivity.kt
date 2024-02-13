package com.silverstone.kahvedeneme3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails.getImageResourceId
import com.silverstone.kahvedeneme3.Database.ButunKahveler
import com.silverstone.kahvedeneme3.ComposableFunctions.Fragment.SwitchPage
import com.silverstone.kahvedeneme3.ViewModel.ViewModel
import com.silverstone.kahvedeneme3.ui.theme.Ahsap
import com.silverstone.kahvedeneme3.ui.theme.Bej
import com.silverstone.kahvedeneme3.ui.theme.Cikolata
import com.silverstone.kahvedeneme3.ui.theme.Gold
import com.silverstone.kahvedeneme3.ui.theme.Gri
import com.silverstone.kahvedeneme3.ui.theme.KahveDeneme3Theme
import com.silverstone.kahvedeneme3.ui.theme.Yesil
import com.silverstone.kahvedeneme3.ui.theme.letter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KahveDeneme3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Bej
                ) {
                    SwitchPage()
                }
            }
        }
    }
}

@Composable
fun Anasayfa(navController: NavController,viewModel: ViewModel) {


    val butunKahveler = remember { mutableStateOf(emptyList<ButunKahveler>()) }
    val mevcutKahveler = remember { mutableStateOf(emptyList<ButunKahveler>()) }
    val switchPosition= remember { mutableStateOf(0) }
    val gosterilenKahveler= remember {
        mutableStateOf(emptyList<ButunKahveler>())
    }

    LaunchedEffect(Unit) {
        butunKahveler.value = viewModel.getButunKahveler()
        mevcutKahveler.value=viewModel.getMevcutKahveler()
    }

    Column {

        Row(modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(Cikolata),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Row(modifier = Modifier.padding(10.dp)) {
                IconButton(onClick = { navController.navigate("aracMevcut")},Modifier.size(35.dp)) {
                    Icon(painter = painterResource(id =R.drawable.tools ) , contentDescription = "", tint = Gold)
                }
                Spacer(modifier = Modifier.size(20.dp))
                IconButton(onClick = {  },Modifier.size(35.dp)) {
                    Icon(painter = painterResource(id =R.drawable.about ) , contentDescription = "", tint = Gold)
                }
            }

        Row(verticalAlignment  = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)){
        Text(text = "Bütün Kahveler", fontFamily = letter, color = Gold, fontSize = 27.sp)
        Spacer(modifier = Modifier.size(5.dp))
            Switch(checked = switchPosition.value==1, onCheckedChange = {
                switchPosition.value=if (it) 1 else 0
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Ahsap,
            uncheckedThumbColor = Ahsap,
            checkedBorderColor = Gold,
            uncheckedBorderColor = Gold,
            checkedTrackColor = Yesil,
            uncheckedTrackColor = Gri))}}

        if (switchPosition.value==0) gosterilenKahveler.value=mevcutKahveler.value else gosterilenKahveler.value=butunKahveler.value
        if (gosterilenKahveler.value.isEmpty()){
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Yapabileceğiniz bir kahve yok...", fontFamily = letter, fontSize = 30.sp)
                Image(painter = painterResource(id = R.drawable.sad) , contentDescription ="" ,Modifier.size(300.dp).padding(horizontal = 10.dp))
                Text(text = "Sahip olduğunuz araçları düzenleyin.", fontSize = 15.sp)
            }

        }
        else
        LazyColumn(modifier = Modifier.padding(3.dp,1.dp)) {

            items(if (switchPosition.value==0) mevcutKahveler.value else butunKahveler.value) { a: ButunKahveler ->
                val image = getImageResourceId(a.image)
                val painter = painterResource(id = image)
                val description=a.description
                val outExpanded = remember { mutableStateOf(false) }
                val expanded = remember { mutableStateOf(false) }
                val bgColor= remember {
                    mutableStateOf(0.0f)
                }
                Row(
                    modifier = Modifier
                        .border(BorderStroke(2.dp, Gri), RoundedCornerShape(20.dp))
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Gri.copy(bgColor.value))
                        .padding(5.dp)
                        .clickable {
                            navController.navigate("malzemeCheck/${a.id}")
                        }

                ) {

                    if (!outExpanded.value) {
                        Image(
                            painter = painter,
                            contentDescription = "",
                            modifier = Modifier.size(70.dp)
                        )
                    }
                    Column(

                        modifier = Modifier

                            .fillMaxHeight(), verticalArrangement = Arrangement.Center
                    ) {

                        Row ( verticalAlignment = if (expanded.value) Alignment.CenterVertically else Alignment.Top) {
                            if (outExpanded.value)
                                Image(
                                    painter = painter,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(0.dp, 0.dp, 5.dp, 0.dp)
                                )
                           Spacer(modifier = Modifier.size(2.dp))
                            Text(
                                text = a.name,
                                style = TextStyle(fontFamily = letter , fontSize = 25.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                        val textSize = remember { mutableStateOf(1) }
                        val smallFont = 15
                        val bigFont = 25
                        val spacerSize= remember {
                            mutableStateOf(2)
                        }
                        if (expanded.value) {
                            textSize.value = 1
                            spacerSize.value=0
                            bgColor.value=0.4f
                        }
                        else {textSize.value = 70
                        spacerSize.value=2
                            bgColor.value=0f
                        }
                        outExpanded.value = expanded.value

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacerSize.value.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            Text(
                                text = description,
                                maxLines = if (expanded.value) Int.MAX_VALUE else 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .widthIn(max = LocalConfiguration.current.screenWidthDp.dp - textSize.value.dp - 40.dp),
                                style = TextStyle(fontSize = if (expanded.value) bigFont.sp else smallFont.sp)
                            )


                            if (!expanded.value) {
                                IconButton(
                                    onClick = { expanded.value = !expanded.value },
                                    modifier = Modifier.size(20.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.expand),
                                        contentDescription = "daha"
                                    )
                                }
                            }


                        }
                        if (expanded.value) {
                            IconButton(
                                onClick = { expanded.value = !expanded.value },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.nonexpand),
                                    contentDescription = "az"
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))

            }
        }


}
}
