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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nathanyd.pokedex.R

//Image of the pokemon
@Composable
fun PokeImage(id: Int, modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png",
        contentDescription = stringResource(id = R.string.test_name),
        modifier = modifier
    )
}

@Composable
fun PokeIcon() {
    Image(
        painter = painterResource(id = R.drawable.test_icon),
        contentDescription = null,
        modifier = Modifier.size(64.dp)
    )
}

//Name of the pokemon
@Composable
fun PokeName(name: String, fontSize: TextUnit) {
    Text(
        text = name,
        fontSize = fontSize
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