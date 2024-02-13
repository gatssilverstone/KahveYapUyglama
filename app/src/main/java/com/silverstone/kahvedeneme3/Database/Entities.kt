package com.silverstone.kahvedeneme3.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asamalar")
data class Asamalar(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "kahveId") val kahveId: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String?
)


@Entity(tableName = "butun_araclar")
data class ButunAraclar(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "mevcut") val mevcut: Int = 0

)

@Entity(tableName = "butun_kahveler")
data class ButunKahveler(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "isFav") val isFav: Int = 0,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name =  "mevcut") val mevcut: Int = 0

)

@Entity(tableName = "malzemeler")
data class Malzemeler(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "kahveId") val kahveId: Int
)

@Entity(tableName = "kahve_araclari", primaryKeys = ["kahveId", "aracId"])
data class KahveAraclari(
    @ColumnInfo(name = "kahveId") val kahveId: Int,
    @ColumnInfo(name = "aracId") val aracId: Int
)