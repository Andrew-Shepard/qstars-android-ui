package com.example.androidui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.Icon

/*
Created: 2/2/23
Last Modified: 2/2/23

This file contains functions for the Header and the Body of the Navigation Drawer
 */

@Composable
fun DrawerHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical =10.dp),
        contentAlignment = Alignment.Center,
    ){
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
){
    LazyColumn(modifier){
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{ onItemClick(item) }
                    //padding between navigation drawers items
                    .padding(20.dp)
            ){
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
                //Space between icon and text
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}