package com.example.multilanguages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    currentLanguage: String,
    restartActivity: ()->Unit,
    onLanguageSelected: (String) -> Unit,

    ) {
    Scaffold(
        topBar = {
            topAppBar(currentLanguage, restartActivity,onLanguageSelected)
        },

        ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.title),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.desciption),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun topAppBar(
    selectedPosition: String,
    restartActivity:()->Unit,
    onLanguageSelected: (String) -> Unit,

    ) {


    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(Color.Cyan),
        title = { Text(
            text = stringResource(id = R.string.welcome),
            color = Color.Black,
            fontWeight = FontWeight.Bold

        ) },
        actions = {
            TranslateIcon(selectedPosition,onLanguageSelected,restartActivity)
        },

        )
}

@Composable
fun TranslateIcon(
    selectedPosition: String,
    onLanguageSelected: (String) -> Unit,
    restartActivity:()->Unit,

    ) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.translate),
            contentDescription = "Sort",
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = {
                    Text(text = "العربية", Modifier.padding(12.dp),
                        color = if(selectedPosition=="ar") Color.Red else Color.Black)
                },
                onClick = {
                    expanded = false;
                    onLanguageSelected("ar")
                    restartActivity()
                })

            DropdownMenuItem(
                text = {
                    Text(text = "English", Modifier.padding(12.dp),
                        color = if(selectedPosition=="en") Color.Red else Color.Black
                    )
                },
                onClick = {
                    expanded = false;
                    onLanguageSelected("en")
                    restartActivity()

                })

        }
    }
}
