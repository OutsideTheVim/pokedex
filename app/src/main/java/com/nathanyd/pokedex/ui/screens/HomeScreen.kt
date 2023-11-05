package com.nathanyd.pokedex.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nathanyd.pokedex.ui.screens.shared.PokeImage
import com.nathanyd.pokedex.ui.screens.shared.PokeName
import com.nathanyd.pokedex.ui.theme.PokedexTheme

//Home Page
@Composable
fun DefaultHomeScreen(onPokeClicked: () -> Unit, modifier: Modifier = Modifier) {
    LazyColumn {
        items(5) {
            PokeCard(
                onPokeClicked = onPokeClicked,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

//Pokemon Card
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeCard(onPokeClicked: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onPokeClicked,
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 50.dp,
            bottomStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 50.dp
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PokeImage(modifier = Modifier.size(84.dp))
            PokeName()
        }
    }
}

//Pokemon Card Preview
@Preview(showBackground = true)
@Composable
fun PokeCardPreview() {
    PokedexTheme {
        //PokeCard()
    }
}