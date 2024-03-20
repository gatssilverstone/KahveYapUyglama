package com.silverstone.kahvedeneme3.ComposableFunctions

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silverstone.kahvedeneme3.R
import com.silverstone.kahvedeneme3.Views.EmailButton
import com.silverstone.kahvedeneme3.ui.theme.letter

@Composable
fun About(){
    Column(
        Modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(ScrollState(0))) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.icon) , contentDescription ="" )
            Text(text = "Kahve Yap", fontFamily = letter, fontSize = 60.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Kahve severler için, evdeki basit araçlarınız ile dışarda içtiğiniz lezzetleri yakalayabileceğiniz tarifleri, en basit haliyle adım adım anlatıyoruz.", fontSize = 30.sp, textAlign = TextAlign.Center,style = LocalTextStyle.current.copy(lineHeight = 34.sp))
        Spacer(modifier = Modifier.size(10.dp))
        Column(Modifier.fillMaxSize().align(Alignment.End).padding(bottom = 5.dp, top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Bu uygulama SilverStone Development tarafından geliştirilmiştir. Sorularınız, bildirimleriniz ve telif hakkı için bizimle iletişime geçin.")
            EmailButton()
        }
    }
}