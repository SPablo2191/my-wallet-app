package com.example.mywallet.services

import com.example.mywallet.models.RemoteResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AccountService {
    @GET("account")
    suspend fun listAccounts() : RemoteResponse
}

object AccountServiceFactory{
    fun makeAccountService() : AccountService{
        return Retrofit.Builder()
            .baseUrl("https://my-wallet-api-iley.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AccountService::class.java)
    }
}