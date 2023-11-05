package com.nathanyd.pokedex.ui.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nathanyd.pokedex.R

//small Image of the pokemon
@Composable
fun PokeImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.test_image),
        contentDescription = stringResource(id = R.string.test_name),
        modifier = modifier
    )
}

//Name of the pokemon
@Composable
fun PokeName() {
    Text(
        text = stringResource(id = R.string.test_name),
        fontSize = 24.sp
    )
}

// water, grass etc. change background color matching result
@Composable
fun PokeType(color: Long) {
    Text(
        text = "",
        Modifier
            .background(Color(color))
            .size(20.dp)
    )
}