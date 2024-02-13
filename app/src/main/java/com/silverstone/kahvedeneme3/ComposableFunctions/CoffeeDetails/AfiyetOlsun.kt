package com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.silverstone.kahvedeneme3.R
import com.silverstone.kahvedeneme3.Views.GifLoader
import com.silverstone.kahvedeneme3.ui.theme.Gold
import com.silverstone.kahvedeneme3.ui.theme.letter

@Composable
fun BitisEkrani(navController: NavController){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 20.dp)) {
        Text(text = "Afiyet olsun", fontFamily = letter, fontSize = 50.sp)
        AsyncImage(model = R.drawable.afiyetolsun, imageLoader = GifLoader(), contentDescription = "", modifier = Modifier.fillMaxWidth() )
        Button(onClick = { navController.navigate("Anasayfa")},
            colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.Transparent, disabledContentColor = Color.Transparent,
                contentColor = Color.Transparent, containerColor = Color.Transparent
            ),
            modifier = Modifier.paint(painterResource( id =  R.drawable.buttonbg ))

        ) {
            Text(text = "Anasayfa", fontFamily = letter, fontSize = 40.sp, color = Gold )
        }
    }
}