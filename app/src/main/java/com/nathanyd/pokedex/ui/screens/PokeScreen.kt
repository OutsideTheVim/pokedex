package com.nathanyd.pokedex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nathanyd.pokedex.R
import com.nathanyd.pokedex.data.PokeData
import com.nathanyd.pokedex.ui.PokeScreenViewModel
import com.nathanyd.pokedex.ui.PokeUiState
import com.nathanyd.pokedex.ui.PokeViewModel
import com.nathanyd.pokedex.ui.screens.shared.ButtonSwitch
import com.nathanyd.pokedex.ui.screens.shared.ErrorScreen
import com.nathanyd.pokedex.ui.screens.shared.LoadingScreen
import com.nathanyd.pokedex.ui.screens.shared.PokeGif
import com.nathanyd.pokedex.ui.screens.shared.PokeImage
import com.nathanyd.pokedex.ui.screens.shared.PokeType
import com.nathanyd.pokedex.ui.screens.shared.QuestionBox

@Composable
fun VerifyPokeScreen(
    pokeUiState: PokeUiState,
    pokeViewModel: PokeScreenViewModel = viewModel(),
    pokeId: Int?
) {

    pokeViewModel.getSinglePokeData(pokeId ?: 1)

    when (pokeUiState) {
        is PokeUiState.Success -> {
            pokeUiState.getNames.forEach {
                DefaultPokeScreen(
                    data = it
                )
            }
        }

        is PokeUiState.Error -> ErrorScreen(onClick = {
            pokeViewModel.getSinglePokeData(5)
        }) // onclick nog fixxen
        is PokeUiState.Loading -> LoadingScreen(
            count = pokeUiState.count,
            amount = pokeUiState.amount
        )
    }
}

//Screen for Pokemon info page
@Composable
fun DefaultPokeScreen(data: PokeData, modifier: Modifier = Modifier) {

    var isSwitched by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            PokeBackground()
            if (isSwitched) PokeGif(
                name = data.name,
                modifier = Modifier
                    .size(184.dp)
                    .align(Alignment.Center)
            )
            if (!isSwitched) PokeImage(
                id = data.id,
                modifier = Modifier
                    .size(184.dp)
                    .align(Alignment.Center)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ButtonSwitch(
                    isSwitched = isSwitched,
                    onCheckChanged = { isSwitched = !isSwitched },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        PokeStatus(data)
    }
}

//Pokemon information card
@Composable
fun PokeStatus(data: PokeData) {

    var questionBox: Boolean by remember {
        mutableStateOf(false)
    }

    if (questionBox) QuestionBox(
        clicked = { questionBox = false },
        title = stringResource(id = R.string.number_question_title, "#${data.id}"),
        description = stringResource(id = R.string.number_question_desc, "#${data.id}")
    )

    Column {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = data.name,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(14.dp))
            Card {
                Row {
                    data.types.forEach {
                        PokeType(type = it.type.name)
                    }
                }
            }
        }

        var stat: Float
        var increase: Int

        data.stats?.forEach {
            increase = it.base_stat * 5

            if (increase.toString().length < 3) {
                stat = "0.0${it.base_stat * 5}f".toFloat()
            } else {
                stat = "0.${it.base_stat * 5}f".toFloat()
            }

            StatsBar(nameStat = it.stat.name, stat = stat)
        }

        Spacer(modifier = Modifier.weight(1f))
        Row {
            Text(
                text = "#${data.id}",
                modifier = Modifier.padding(bottom = 48.dp),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = R.drawable.help_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        questionBox = true
                    }
            )
        }
    }
}

//Progressbar of the called stat
@Composable
fun StatsBar(nameStat: String, stat: Float) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = nameStat
        )
        LinearProgressIndicator(progress = stat)
    }
}

//pokemon background image
@Composable
fun PokeBackground() {
    Image(
        painter = painterResource(id = R.drawable.sky_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(264.dp)
            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
    )
}
