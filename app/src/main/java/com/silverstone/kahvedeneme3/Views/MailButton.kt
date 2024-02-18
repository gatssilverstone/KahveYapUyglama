package com.silverstone.kahvedeneme3.Views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.silverstone.kahvedeneme3.ui.theme.Gold
import com.silverstone.kahvedeneme3.ui.theme.Kahverengi

@Composable
fun EmailButton() {
    val context = LocalContext.current

    val emailAddress = "silverstone.dev@gmail.com"

    val emailBody = "Merhaba Silverstone, "

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
                    putExtra(Intent.EXTRA_TEXT, emailBody)
                }
                context.startActivity(intent)
            }, colors = ButtonDefaults.buttonColors(
                Kahverengi
            )
        ) {
            Text(text = "E-posta Gönder", color = Gold)
        }
    }
}