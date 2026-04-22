package com.example.ogto_dolist.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ogto_dolist.room.ListEntity
import com.example.ogto_dolist.viewmodel.ListViewModel

@Composable
fun UpdateScreen(view: ListViewModel, bookId: String?, navHostController: NavHostController){

    var input by rememberSaveable {
        mutableStateOf("");
    }

    Column(
        Modifier.padding(top = 52.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = {
                enteredInput -> input = enteredInput
            }
        )
        Spacer(Modifier.padding(8.dp))
        Button(
            onClick = {
                var newList = ListEntity(bookId!!.toInt(), input)
                view.updateList(newList)
            }
        ) {
            Text("Update List")
        }
    }
}