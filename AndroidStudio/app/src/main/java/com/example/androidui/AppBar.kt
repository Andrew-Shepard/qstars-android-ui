package com.example.androidui

import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor

/*
Created: 2/2/23
Last Modified: 2/2/23

This file contains the AppBar function that creates the app bar.
 */

@Composable
fun AppBar (onNavigationIconClick: () -> Unit)
{
    TopAppBar(
    title = {Text(text = stringResource(id = R.string.app_name))},
    backgroundColor = MaterialTheme.colors.onPrimary,
    contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick){
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawers"
                )
            }
        }
    )
}