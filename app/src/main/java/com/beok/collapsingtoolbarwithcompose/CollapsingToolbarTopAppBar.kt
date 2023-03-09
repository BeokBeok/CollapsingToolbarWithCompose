package com.beok.collapsingtoolbarwithcompose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beok.collapsingtoolbarwithcompose.ui.theme.Purple700
import com.beok.collapsingtoolbarwithcompose.ui.theme.Teal200

@OptIn(ExperimentalMaterial3Api::class)
@Preview(heightDp = 500)
@Composable
fun CollapsingToolbarTopAppBar(books: List<BookModel> = BookModel.DEFAULT_BOOKS) {
    val listState = rememberLazyListState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val isCollapsed: Boolean by remember {
        derivedStateOf { scrollBehavior.state.collapsedFraction == 1f }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Library") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Purple700,
                    scrolledContainerColor = Teal200,
                    titleContentColor = if (isCollapsed) Color.Black else Color.White,
                ),
                scrollBehavior = scrollBehavior,
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            state = listState
        ) {
            items(items = books) { book ->
                Book(model = book)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
