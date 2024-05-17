package com.example.mywallet.pages.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mywallet.R
import com.example.mywallet.ui.components.accountListItem.AccountListItem
import com.example.mywallet.ui.components.accountListItem.ShimmerListItem
import com.example.mywallet.ui.components.animatedIcon.AnimatedIcon

@OptIn(ExperimentalLayoutApi::class)
@ExperimentalMaterial3Api
@Composable
fun Home(){
    val viewModel by remember { mutableStateOf(HomeViewModel()) }
    LaunchedEffect(Unit) {
        viewModel.fetch()
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Wallet", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) {
            values ->
        Column(modifier = Modifier
            .padding(values),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ShimmerListItem(isLoading = viewModel.accounts.isEmpty(), contentAfterLoading = {
                AccountListItem(accounts = viewModel.accounts)
            })
            AnimatedIcon(
                emptyIcon = painterResource(id = R.drawable.blood),
                fullIcon = painterResource(id = R.drawable.blood_completed)
            )
        }
    }
}