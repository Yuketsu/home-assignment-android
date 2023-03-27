package com.example.myapplication.presentation.film.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking


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
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(item.id)
                        Spacer(Modifier.width(5.dp))
                        Text(item.title)
                    }
                    Divider()
                }
            }
        }
    }

}