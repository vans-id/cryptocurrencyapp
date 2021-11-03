package com.plcoding.cryptocurrencyappyt.presentation.coin_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptocurrencyappyt.R
import com.plcoding.cryptocurrencyappyt.domain.model.Ticker
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.customGreen
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.customRed
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.textGray
import java.text.DecimalFormat

@Composable
fun CoinListItem(
    ticker: Ticker,
    onItemClick: (Ticker) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(ticker) }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(4f)) {
            Text(
                text = ticker.name,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = ticker.symbol,
                style = MaterialTheme.typography.body1,
                color = textGray
            )
        }

        Image(
            painter = painterResource(id = if (ticker.percentChange24h > 0) R.drawable.ic_stock2 else R.drawable.ic_stock),
            contentDescription = "price",
            modifier = Modifier.weight(3f)
        )

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = "$${DecimalFormat("#,###.##").format(ticker.price)}",
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (ticker.percentChange24h > 0) {
                Text(
                    text = "+${ticker.percentChange24h}%",
                    style = MaterialTheme.typography.body1,
                    color = customGreen,
                    textAlign = TextAlign.End
                )
            } else {
                Text(
                    text = "-${ticker.percentChange24h}%",
                    style = MaterialTheme.typography.body1,
                    color = customRed,
                    textAlign = TextAlign.End
                )
            }

        }
    }


//        Text(
//            text = if (ticker.is_active) "Active" else "Inactive",
//            color = if (ticker.is_active) Color.Green else Color.Red,
//            fontStyle = FontStyle.Italic,
//            textAlign = TextAlign.End,
//            style = MaterialTheme.typography.body2,
//            modifier = Modifier.align(CenterVertically)
//        )
}





















