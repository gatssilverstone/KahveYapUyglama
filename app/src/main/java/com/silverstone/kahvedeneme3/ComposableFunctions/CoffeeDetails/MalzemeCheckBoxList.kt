package com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.silverstone.kahvedeneme3.AdMob.AdMobBanner
import com.silverstone.kahvedeneme3.Database.Malzemeler
import com.silverstone.kahvedeneme3.R
import com.silverstone.kahvedeneme3.ViewModel.ViewModel
import com.silverstone.kahvedeneme3.ui.theme.Gold
import com.silverstone.kahvedeneme3.ui.theme.Gri
import com.silverstone.kahvedeneme3.ui.theme.letter

@Composable
fun MalzemeCheckboxList(
    viewModel: ViewModel,
    kahveId: Int,
    navController: NavController
) {

    val malzemeler = remember { mutableStateOf<List<Malzemeler>>(emptyList()) }

    val selectedMalzemeler = remember { mutableStateOf<List<Malzemeler>>(emptyList()) }

    val isIleriButtonEnabled = selectedMalzemeler.value.size == malzemeler.value.size

    LaunchedEffect(kahveId) {
        malzemeler.value = viewModel.getMalzemelerByKahveId(kahveId)
    }
    Column(Modifier.fillMaxSize()) {
    Column(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp-60.dp)) {
        LazyColumn {
            itemsIndexed(malzemeler.value){_,malzeme->
                MalzemeCheckbox(malzeme = malzeme, onMalzemeChecked = {isChecked ->
                    if (isChecked)
                        selectedMalzemeler.value=selectedMalzemeler.value + malzeme

                    else
                        selectedMalzemeler.value=selectedMalzemeler.value - malzeme


                })
            }

        }
        Spacer(modifier = Modifier.size(2.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        Button(onClick = { navController.navigate("asamalar/${kahveId}")},
            enabled = isIleriButtonEnabled,
            colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.Transparent, disabledContentColor = Color.Transparent,
            contentColor = Color.Transparent, containerColor = Color.Transparent
                ),
            modifier = Modifier.paint(painterResource( id = if (isIleriButtonEnabled) R.drawable.buttonbg else R.drawable.unbuttonbg))

        ) {
            Text(text = "Başla", fontFamily = letter, fontSize = 40.sp, color = if (isIleriButtonEnabled)Gold else Gri)
        }
        }

    }
        AdMobBanner()


}}

@Composable 
fun MalzemeCheckbox(
    malzeme: Malzemeler,
    onMalzemeChecked:(Boolean)->Unit
    ){
            val  isChecked= remember {
                mutableStateOf(false) }
    
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { isChecked.value = !isChecked.value },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
                ) {

                Image(painter = painterResource(id = if (isChecked.value) R.drawable.check else R.drawable.uncheck), contentDescription = "",
                    Modifier.size(40.dp)
                )
                
                Text(text = malzeme.name, fontFamily = letter, fontSize = 40.sp)
            }
    DisposableEffect(isChecked.value){
        onMalzemeChecked(isChecked.value)
        onDispose {  }
    }
}