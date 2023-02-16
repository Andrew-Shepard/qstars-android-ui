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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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

    LazyColumn(Modifier.size(width = 400.dp, height = 200.dp).padding(16.dp))
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

@Composable
fun Table(tableTitle: String)
{
    Column()
    {
        Text(text = tableTitle, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.offset(x = 16.dp).offset(y = 10.dp))
        TableScreen()
    }

}

@Preview
@Composable
fun TablePrev()
{
    Table("Assets!")
}
