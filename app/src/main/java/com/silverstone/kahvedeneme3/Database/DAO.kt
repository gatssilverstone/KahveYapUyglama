package com.silverstone.kahvedeneme3.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AsamalarDao {

    @Query("SELECT * FROM asamalar WHERE kahveId = :kahveId")
    suspend fun getAsamalarByKahveId(kahveId: Int): List<Asamalar>

    @Insert
    suspend fun insertAsamalar(asamalar: Asamalar)

    @Update
    suspend fun updateAsamalar(asamalar: Asamalar)

    @Delete
    suspend fun deleteAsamalar(asamalar: Asamalar)
}

@Dao
interface ButunAraclarDao {
    @Query("SELECT * FROM butun_araclar")
    suspend fun getAllButunAraclar(): List<ButunAraclar>


    @Update
    suspend fun updateMevcut(araclar: List<ButunAraclar>)

    @Insert
    suspend fun insertButunAraclar(butunAraclar: ButunAraclar)

    @Update
    suspend fun updateButunAraclar(butunAraclar: ButunAraclar)

    @Delete
    suspend fun deleteButunAraclar(butunAraclar: ButunAraclar)
}

@Dao
interface ButunKahvelerDao {
    @Query("SELECT * FROM butun_kahveler")
    suspend fun getAllButunKahveler(): List<ButunKahveler>

    @Query("SELECT * FROM butun_kahveler WHERE mevcut = 1")
    suspend fun getMevcutKahveler(): List<ButunKahveler>

    @Query("UPDATE butun_kahveler SET mevcut = CASE WHEN (SELECT COUNT(*) FROM kahve_araclari ka INNER JOIN butun_araclar ba ON ka.aracId = ba.id WHERE ka.kahveId = butun_kahveler.id AND ba.mevcut = 1) = (SELECT COUNT(*) FROM kahve_araclari ka INNER JOIN butun_araclar ba ON ka.aracId = ba.id WHERE ka.kahveId = butun_kahveler.id) THEN 1 ELSE 0 END")
    suspend fun updateMevcutForKahveler()

    @Insert
    suspend fun insertButunKahveler(butunKahveler: ButunKahveler)

    @Update
    suspend fun updateButunKahveler(butunKahveler: ButunKahveler)

    @Delete
    suspend fun deleteButunKahveler(butunKahveler: ButunKahveler)
}

@Dao
interface MalzemelerDao {
    @Query("SELECT * FROM malzemeler WHERE kahveId = :kahveId")
    suspend fun getMalzemelerByKahveId(kahveId: Int): List<Malzemeler>

}

