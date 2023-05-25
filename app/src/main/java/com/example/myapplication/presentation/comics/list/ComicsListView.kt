package com.example.myapplication.presentation.film.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger


@Composable
fun ComicsListView(
    navController: NavController,
    vm: ComicsListViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit, block = {
        vm.getComics()
    })


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Comics")
                }
            )
        })
    {
        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(vm.comics) { item ->
                    Row(modifier = Modifier.padding(4.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.thumbnail?.path + "." + item.thumbnail?.extension)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(4.dp)
                        )
                        Text(item.title.orEmpty())
                    }
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(item.description.orEmpty())
                    }
                    Divider()
                }
            }
        }
    }

}