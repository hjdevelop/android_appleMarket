package com.example.android_applemarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val productNum: Int,
    val productImage: Int,
    val productTitle: String,
    val productWrite: String,
    val seller: String,
    val price: Int,
    val address: String,
    var likeCount: Int,
    val chattingCount: Int,
    var isLike: Boolean
): Parcelable