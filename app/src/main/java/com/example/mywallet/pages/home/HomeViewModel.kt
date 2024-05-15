package com.example.mywallet.pages.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.mywallet.models.Account
import com.example.mywallet.services.AccountServiceFactory
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HomeViewModel : ViewModel() {
    private val service = AccountServiceFactory.makeAccountService()

    var accounts by mutableStateOf<List<Account>>(
        emptyList()
    )
    suspend fun fetch(){
        try {
            val response = service.listAccounts()
            if (response.status_code == 200){
                accounts = response.data
                Log.d("accounts",accounts.toString())
            }

        }catch (e : Exception){
            Log.d("Home view model =>",e.toString())
        }
    }
}