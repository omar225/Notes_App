package com.example.notesapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.ui.viewmodels.NoteViewModel
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.database.Note

@Composable
fun EditingNoteScreen(
    id: Int,
    noteTitle: String,
    noteDetails: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        var details by remember { mutableStateOf(noteDetails) }
        var title by remember { mutableStateOf(noteTitle) }
        val context = LocalContext.current

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = {
                Text(text = "Note Title")
            },
        )

        OutlinedTextField(
            value = details,
            onValueChange = { details = it },
            label = {
                Text(text = "Note Details")
            },
            modifier = modifier.fillMaxWidth()
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                onClick = {
                    viewModel.upsert(Note(id, title ,details))
                    Toast.makeText(context, "Note Updated!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Update")
            }
            OutlinedButton(
                onClick = {
                    viewModel.delete(Note(id, title ,details))
                    Toast.makeText(context, "Note Deleted!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Delete")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EditingNoteScreenPreview() {
    EditingNoteScreen(2, "test title", "test details", rememberNavController())
}