package com.marvel.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.marvel.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    CenterAlignedTopAppBar(
        colors = toolbarColors(),
        title = {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Search,
                    tint = Color.Red.copy(alpha = 0.7f),
                    contentDescription = "search"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchToolbar(
    navController: NavHostController? = null,
    textFieldChangesCallback: ((String) -> Unit)? = null
) {
    var textFieldValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val callback = rememberUpdatedState(textFieldChangesCallback)

    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    CenterAlignedTopAppBar(
        colors = toolbarColors(containerColor = Color.Black),
        title = {
            SearchTextField(
                value = textFieldValue,
                onValueChange = {
                    callback.value?.invoke(it)
                    textFieldValue = it
                },
                focusRequester = focusRequester
            )
        },
        actions = {
            CancelButton { navController?.popBackStack() }
        }
    )
}

@Composable
private fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        label = { Text(stringResource(R.string.search), color = Color.Black.copy(alpha = 0.5f)) },
        textStyle = TextStyle(color = Color.Black),
        leadingIcon = { Icon(Icons.Filled.Search, "", tint = Color.Black.copy(alpha = 0.5f)) },
        colors = textFieldColors(),
        singleLine = true
    )
}

@Composable
private fun CancelButton(onClick: () -> Unit) {
    Text(
        modifier = Modifier

            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = onClick
            )
            .padding(8.dp),
        text = stringResource(R.string.cancel),
        color = Color.Red.copy(alpha = 0.7f),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsedToolbar(navController: NavHostController? = null) {
    TopAppBar(
        colors = toolbarColors(containerColor = Color.Transparent),
        title = {},
        navigationIcon = {
            IconButton(onClick = { navController?.popBackStack() }) {
                BackIcon()
            }
        }
    )
}

@Composable
private fun BackIcon() {
    Icon(
        modifier = Modifier.offset(x = 0.1.dp, y = (-0.2).dp),
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        tint = Color.Black.copy(alpha = 0.4f),
        contentDescription = ""
    )
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        tint = Color.White,
        contentDescription = ""
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun toolbarColors(containerColor: Color = Color.Black.copy(alpha = 0.6f)) =
    TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = containerColor,
        scrolledContainerColor = containerColor
    )

@Composable
private fun textFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    disabledContainerColor = Color.White,
    cursorColor = Color.Black,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)
