package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RowScope.TableCell(text: String, weight: Float)
{
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun TableScreen()
{
    val tableData = (1..100).mapIndexed { index, i -> index to "Item $index" }
    val column1Weight = .3f
    val column2Weight = .7f

    LazyColumn(Modifier.fillMaxSize().padding(16.dp))
    {
        item {
            Row(Modifier.background(Color.Gray))
            {
                TableCell(text = "Column1", weight = column1Weight)
                TableCell(text = "Column2", weight =  column2Weight)
            }
        }

        items(tableData){
            val (id, text) = it
            Row(Modifier.fillMaxWidth())
            {
                TableCell(text = id.toString(), weight = column1Weight)
                TableCell(text = text, weight = column2Weight)
            }

        }

    }
}