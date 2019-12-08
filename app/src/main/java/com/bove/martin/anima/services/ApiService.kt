package com.bove.martin.anima.services

import com.bove.martin.anima.model.Slider
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Martín Bove on 07/12/2019.
 * E-mail: mbove77@gmail.com
 */
interface ApiService {

    @GET("apisliders/")
    fun getAllSliders(): Call<List <Slider>>
}