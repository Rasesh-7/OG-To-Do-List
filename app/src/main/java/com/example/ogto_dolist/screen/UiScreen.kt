package com.example.ogto_dolist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ogto_dolist.room.ListEntity
import com.example.ogto_dolist.viewmodel.ListViewModel

@Composable
fun UiScreen(view: ListViewModel, navHostController: NavHostController){

    var input by rememberSaveable {
        mutableStateOf("")
    }

    Column(Modifier.padding(top = 52.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Text("OG To-Do List", fontSize = 44.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = input,
            onValueChange = {
                enteredInput -> input = enteredInput
            },
            label = {
                Text("Enter your task here")
            }
        )
        Spacer(Modifier.padding(8.dp))
        Button(onClick = {
            view.addList(ListEntity(0, input))
        }) {
            Text("Add Task")
        }
        Spacer(Modifier.padding(8.dp))
        ListsList(view, navHostController)
    }
}

@Composable
fun ListCard(view: ListViewModel, listEntity: ListEntity, navHostController: NavHostController){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("${listEntity.id}",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(end = 12.dp))

                Text(
                    listEntity.title,
                    fontSize = 24.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navHostController.navigate("UpdateScreen/${listEntity.id}")
                    }
                ) {
                    Icon(
                        Icons.Default.Edit, contentDescription = "Edit"
                    )
                }
                IconButton(
                    onClick = {
                        view.deleteList(listEntity)
                    }
                ) {
                    Icon(
                        Icons.Default.Delete, contentDescription = "Delete"
                    )
                }
            }
        }
    }
}

@Composable
fun ListsList(view: ListViewModel, navHostController: NavHostController){
    val lists by view.lists.collectAsState(emptyList())
    LazyColumn() {
        items(lists){
            item -> ListCard(view, item, navHostController)
        }
    }
}