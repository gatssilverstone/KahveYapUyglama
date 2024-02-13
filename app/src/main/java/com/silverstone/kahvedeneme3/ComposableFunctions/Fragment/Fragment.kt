package com.silverstone.kahvedeneme3.ComposableFunctions.Fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.silverstone.kahvedeneme3.Anasayfa
import com.silverstone.kahvedeneme3.ComposableFunctions.AvailableCoffees.ManageMevcut
import com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails.AsamaScreen
import com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails.BitisEkrani
import com.silverstone.kahvedeneme3.ComposableFunctions.CoffeeDetails.MalzemeCheckboxList
import com.silverstone.kahvedeneme3.ViewModel.ViewModel

@Composable
fun SwitchPage(){

    val viewModel:ViewModel= viewModel()

    val navController= rememberNavController()

    val baslangic= remember {
        mutableStateOf("anasayfa")
    }

    LaunchedEffect(key1 = true){
        val allAraclar=viewModel.getAllAraclar()
        if (allAraclar.all { it.mevcut==0 }){
            baslangic.value="aracMevcut"
        }
    }

    NavHost(navController = navController, startDestination = baslangic.value){
        composable("anasayfa"){
            Anasayfa(navController = navController, viewModel)
        }
        composable("malzemeCheck/{kahveId}"){backStackEntry ->
            val kahveId=backStackEntry.arguments?.getString("kahveId")?.toInt()
            MalzemeCheckboxList(viewModel = viewModel, kahveId = kahveId!!, navController = navController)
        }
        composable("asamalar/{kahveId}"){backStackEntry ->
            val kahveId=backStackEntry.arguments?.getString("kahveId")?.toInt()
            AsamaScreen(viewModel = viewModel, kahveId = kahveId!!, navController = navController)
        }
        composable("bitis"){
            BitisEkrani(navController = navController)
        }

        composable("aracMevcut"){
            ManageMevcut(navController = navController, viewModel = viewModel)
        }
    }
}