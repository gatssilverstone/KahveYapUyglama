package com.silverstone.kahvedeneme3.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.silverstone.kahvedeneme3.Database.Asamalar
import com.silverstone.kahvedeneme3.Database.ButunAraclar
import com.silverstone.kahvedeneme3.Database.ButunKahveler
import com.silverstone.kahvedeneme3.Database.CoffeeDatabase
import com.silverstone.kahvedeneme3.Database.Malzemeler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ViewModel(application: Application) : AndroidViewModel(application) {
    private val coffeeDatabase = CoffeeDatabase.getInstance(application)
    private val malzemelerDao = coffeeDatabase.malzemelerDao()
    private val asamalarDao= coffeeDatabase.asamalarDao()
    private val araclarDao=coffeeDatabase.butunAraclarDao()
    private val kahvelerDao=coffeeDatabase.butunKahvelerDao()


    suspend fun getMalzemelerByKahveId(kahveId: Int): List<Malzemeler> {
        return withContext(Dispatchers.IO) {
            malzemelerDao.getMalzemelerByKahveId(kahveId)
        }
    }

    suspend fun getAsamalarByKahveId(kahveId: Int): List<Asamalar>{
        return withContext(Dispatchers.IO){
            asamalarDao.getAsamalarByKahveId(kahveId)
        }
    }

    suspend fun getAllAraclar():List<ButunAraclar> {
        return withContext(Dispatchers.IO){
            araclarDao.getAllButunAraclar()
        }
    }

    suspend fun getButunKahveler():List<ButunKahveler>{
        return withContext(Dispatchers.IO){
            kahvelerDao.getAllButunKahveler()
        }
    }

    suspend fun getMevcutKahveler():List<ButunKahveler>{
        return withContext(Dispatchers.IO){
            kahvelerDao.getMevcutKahveler()
        }
    }

    suspend fun updateMevcutKahveler(){
        kahvelerDao.updateMevcutForKahveler()
    }

    suspend fun updateMevcut(selectedCheckboxes: Map<Int, Boolean>){
        val araclar=getAllAraclar()
        val updatedAraclar=araclar.toMutableList()
        for ((index,isChecked) in selectedCheckboxes){
            if (isChecked){updatedAraclar[index]= araclar[index].copy(mevcut=1) }
            else{updatedAraclar[index]= araclar[index].copy(mevcut=0)}
        }
        araclarDao.updateMevcut(updatedAraclar)
    }



}


