package com.example.mywallet.ui.components.accountListItem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mywallet.models.Account
import com.example.mywallet.ui.components.cards.accountCard.AccountCard
import com.example.mywallet.ui.shimmerEffect

@Composable
fun AccountListItem(
    accounts : List<Account>
){
    LazyRow{
        items(accounts){
            account ->
            AccountCard(title = account.name, amount = account.balance, modifier = Modifier.width(200.dp))
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}


@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
){
    if(isLoading) {
        Row {
            for (i in 1..3){
                Card(
                    modifier = modifier,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 100.dp, height = 20.dp)
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .size(width = 200.dp, height = 60.dp)
                                .shimmerEffect()
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }

    } else {
        contentAfterLoading()
    }
}
@Preview
@Composable
fun AccountListItemPreview(){
    ShimmerListItem(isLoading = true, contentAfterLoading = {})
}