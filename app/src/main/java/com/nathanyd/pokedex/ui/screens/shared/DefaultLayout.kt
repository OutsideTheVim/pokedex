package com.nathanyd.pokedex.ui.screens.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.nathanyd.pokedex.R

//App Layout
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeLayout() {
}

@Composable
fun TopAppBar() {
    Text(text = "Pokedex!")
}

@Composable
fun QuestionBox(
    clicked: () -> Unit,
    title: String,
    description: String
) {
    AlertDialog(
        icon = {
            Icon(painterResource(id = R.drawable.help_icon), contentDescription = "question mark")
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        onDismissRequest = clicked,
        confirmButton = {
            TextButton(onClick = clicked) {
                Text("Close")
            }
        }
    )
}
