package com.silverstone.kahvedeneme3.ComposableFunctions.AvailableCoffees

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.silverstone.kahvedeneme3.AdMob.showInterstitialAd
import com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails.getImageResourceId
import com.silverstone.kahvedeneme3.Database.ButunAraclar
import com.silverstone.kahvedeneme3.R
import com.silverstone.kahvedeneme3.ViewModel.ViewModel
import com.silverstone.kahvedeneme3.ui.theme.Gold
import com.silverstone.kahvedeneme3.ui.theme.letter
import kotlinx.coroutines.launch

@Composable
fun ManageMevcut(navController: NavController,viewModel: ViewModel){

    val context= LocalContext.current
    val araclar=remember{ mutableStateOf<List<ButunAraclar>>(emptyList()) }

    LaunchedEffect(araclar) {
        araclar.value= viewModel.getAllAraclar()
    }

    val selectedCheckboxes = remember { mutableStateMapOf<Int, Boolean>() }


    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Sahip olduğunuz araçları seçin.", fontFamily = letter, fontSize = 35.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(20.dp))
        LazyColumn{
            itemsIndexed(araclar.value){index, arac ->
                ManageMevcutItem(arac = arac,
                    isChecked=selectedCheckboxes[index]?:(arac.mevcut==1),
                    onCheckboxChecked = {isChecked ->
                    selectedCheckboxes[index]=isChecked
                })
                Spacer(modifier = Modifier.size(10.dp))
            }

        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

        val buttonSize = (0.03f * context.resources.displayMetrics.widthPixels).dp
        val textSize=buttonSize.value.sp


        Button(onClick = {
            navController.navigate("Anasayfa")
        },
            colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.Transparent, disabledContentColor = Color.Transparent,
                contentColor = Color.Transparent, containerColor = Color.Transparent
            ),
            modifier = Modifier.weight(0.3f)
                .paint(painterResource( id =  R.drawable.buttonbg ))

        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.home), contentDescription ="",Modifier.size(buttonSize)
                    , colorFilter = ColorFilter.tint(Gold))
                Text(text = "Anasayfa", fontFamily = letter, fontSize = textSize, color = Gold)
            }
        }



        Button(onClick = {
            showInterstitialAd(context)
                viewModel.viewModelScope.launch {

            viewModel.updateMevcut(selectedCheckboxes)
            viewModel.updateMevcutKahveler()
            navController.navigate("Anasayfa")
            Toast.makeText(context,"Araçlarınız güncellenmiştir.",Toast.LENGTH_SHORT).show()

        }},
            colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.Transparent, disabledContentColor = Color.Transparent,
                contentColor = Color.Transparent, containerColor = Color.Transparent
            ),
            modifier = Modifier
                .weight(0.3f)
                .paint(painterResource( id =  R.drawable.buttonbg ))

        ) {


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.upgrade), contentDescription ="",Modifier.size(buttonSize)
                    , colorFilter = ColorFilter.tint(Gold))
                Text(text = "Kaydet", fontFamily = letter, fontSize = textSize, color = Gold)
            }
        }


    }
        Spacer(modifier = Modifier.size(10.dp))}}

}


@Composable
fun ManageMevcutItem(arac:ButunAraclar,isChecked:Boolean,onCheckboxChecked: (Boolean) -> Unit){
    val image= getImageResourceId(arac.image!!)

    Row(modifier = Modifier
        .clickable { onCheckboxChecked(!isChecked) }
        .fillMaxWidth()
        .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,     ) {

        Image(painter = painterResource(id = image), contentDescription = "", modifier=Modifier.size(60.dp))
        Spacer(modifier = Modifier.size(2.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = arac.name, fontFamily = letter, fontSize = 40.sp)
            Image(painter = painterResource(id = if (isChecked) R.drawable.check else R.drawable.uncheck), contentDescription = "", modifier = Modifier.size(40.dp))

        }

    }
}
