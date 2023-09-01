package com.example.androidrepositories.utils

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.util.Log
import android.widget.ImageView
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat


object AppConstant {
    val VIEW_TYPE = 0
    val VIEW_TYPE1 = 1
    val VIEW_TYPE2 = 2

    @SuppressLint("SimpleDateFormat")
    fun getSortDataTime(strDate: String): String {
        return DateFormat.format(
            "MM-dd-yy hh:mm",
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(strDate)
        ).toString()
    }

    fun loadImage(imageUrl: String?, imageId: ImageView) {
        if (imageUrl != null) {
            try {
                Picasso.get().load(imageUrl)
                    .networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE).into(imageId)
            } catch (_: Exception) {
            }
        } else {
            Picasso.get().load("")
                .networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE).into(imageId)
        }
    }

    fun getJsonStringToObject(myObj: Any): String {
        val gson = Gson()
        return gson.toJson(myObj)
    }

    fun getObjectFromJson(s: String, anyObject: Any): Any? {
        try {
            return Gson().fromJson(s, anyObject::class.java)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return null
    }
}