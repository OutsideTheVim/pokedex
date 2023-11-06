package com.nathanyd.pokedex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nathanyd.pokedex.R
import com.nathanyd.pokedex.ui.screens.shared.PokeImage
import com.nathanyd.pokedex.ui.screens.shared.PokeType
import com.nathanyd.pokedex.ui.screens.shared.QuestionBox

//Screen for Pokemon info page
@Composable
fun DefaultPokeScreen(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            PokeBackground()
            PokeImage(
                modifier = Modifier
                    .size(184.dp)
                    .align(Alignment.Center)
            )
        }
        PokeStatus()
    }
}

//Pokemon information card
@Composable
fun PokeStatus() {

    var questionBox: Boolean by remember {
        mutableStateOf(false)
    }

    if(questionBox) QuestionBox(
        clicked = { questionBox = false },
        title = "What does #1 mean?",
        description = stringResource(id = R.string.number_question_desc, "#1")
    )

    Column {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.test_name),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(14.dp))
            Card {
                Row {
                    PokeType(color = 0xFF027820)
                    PokeType(color = 0xFF4d0245)
                }
            }
        }
        StatsBar("HP", 0.45f)
        StatsBar("Attack", 0.49f)
        StatsBar("Defense", 0.49f)
        StatsBar("Speed", 0.45f)
        StatsBar("Special Attack", 0.65f)
        StatsBar("Special Defense", 0.65f)
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Text(
                text = "#1",
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
