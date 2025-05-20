package com.example.permissionapp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataGridExample() {
    val data = listOf(
        "Row 1 Col 1", "Row 1 Col 2", "Row 1 Col 3",
        "Row 2 Col 1", "Row 2 Col 2", "Row 2 Col 3"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3 columns
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) { item ->
            Text(
                text = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
