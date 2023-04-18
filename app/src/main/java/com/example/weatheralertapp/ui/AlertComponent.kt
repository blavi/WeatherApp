package com.example.weatheralertapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.weatheralertapp.data.AlertDetails
import com.example.weatheralertapp.data.WeatherAlert

@Composable
fun WeatherAlertComponent(
    item: WeatherAlert
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            AsyncImage(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://picsum.photos/250/250/?temp=" + item.properties.imageRandomKey)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                contentDescription = "product image",
                contentScale = ContentScale.Fit
            )

        }
        Column(modifier = Modifier
            .wrapContentSize()
            .padding(start = 16.dp)
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                color = androidx.compose.ui.graphics.Color.Black,
                text = item.properties.event,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.wrapContentSize(),
                color = androidx.compose.ui.graphics.Color.Black,
                text = stringResource(id = R.string.start, item.properties.effective)
            )
            Text(
                modifier = Modifier.wrapContentSize(),
                color = androidx.compose.ui.graphics.Color.Black,
                text = stringResource(id = R.string.end,item.properties.ends ?: "")
            )
            Text(
                modifier = Modifier.wrapContentSize(),
                color = androidx.compose.ui.graphics.Color.Black,
                text = stringResource(id = R.string.sender, item.properties.senderName)
            )
            Text(
                modifier = Modifier.wrapContentSize(),
                color = androidx.compose.ui.graphics.Color.Black,
                text = stringResource(id = R.string.duration, item.properties.duration)
            )
        }
    }
}

@Preview
@Composable
fun WeatherAlertComponentPreview() {
    WeatherAlertComponent(
        item = WeatherAlert(
            AlertDetails(
            "23",
            "233",
            "sdfsd",
            "ekwjew",
            "12:00",
                33
            )
        )
    )
}