package com.example.androidui

import android.media.Image
import androidx.compose.ui.graphics.vector.ImageVector

/*
Created: 2/2/23
Last Modified: 2/2/23

This file contains the MenuItem class that sets the required parameters for Menutem
 */
data class MenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)