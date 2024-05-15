package com.example.mywallet.models

data class RemoteResponse(
    val `data`: List<Account>,
    val status_code: Int
)