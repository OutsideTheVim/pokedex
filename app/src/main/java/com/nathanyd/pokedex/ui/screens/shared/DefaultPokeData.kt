package com.nathanyd.pokedex.ui.screens.shared

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.nathanyd.pokedex.R
import com.nathanyd.pokedex.ui.PokeViewModel

//Image of the pokemon
@Composable
fun PokeImage(id: Int, modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png",
        contentDescription = stringResource(id = R.string.test_name),
        modifier = modifier
    )
}

//Gif of the pokemon
@Composable
fun PokeGif(name: String, modifier: Modifier = Modifier) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            "https://projectpokemon.org/images/normal-sprite/$name.gif",
            imageLoader
        ),
        contentDescription = null,
        modifier = modifier
    )
}

//Icon of the pokemon
@Composable
fun PokeIcon(id: Int, name: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
        contentDescription = name,
        modifier = modifier
    )
}

//Name of the pokemon
@Composable
fun PokeName(name: String, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Text(
        text = name,
        fontSize = fontSize,
        modifier = modifier
    )
}

// water, grass etc. change background color matching result
@Composable
fun PokeType(type: String, modifier: Modifier = Modifier) {

    //grass: 0xFF027820
    //poison: 0xFF4d0245

    val pokeViewModel: PokeViewModel = viewModel()

    val correctColor: Long = pokeViewModel.getTypeColor(type)

    Text(
        text = "",
        modifier
            .background(Color(correctColor))
            .size(20.dp)
    )
}