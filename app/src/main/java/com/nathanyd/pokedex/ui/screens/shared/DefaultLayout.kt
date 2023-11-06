@file:OptIn(ExperimentalMaterial3Api::class)

package com.nathanyd.pokedex.ui.screens.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nathanyd.pokedex.R
import com.nathanyd.pokedex.ui.Pages

//App Layout
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeLayout() {
}

//Layout for Top Bar
@Composable
fun TopAppBarLayout(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            modifier = modifier
        )
    }
}

//layout for searchbar
@Composable
fun SearchBarLayout(
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        placeholder = { Text(placeholder) },
        modifier = modifier
    ) {
        Column {
            repeat(3) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PokeIcon()
                    PokeName("bulbasaur", fontSize = 18.sp)
                }
            }
        }
    }
}

//Layout for alert/question box
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

@Composable
fun ErrorScreen() {
    Text("Error")
}

@Composable
fun LoadingScreen() {
    Text("Loading")
}
