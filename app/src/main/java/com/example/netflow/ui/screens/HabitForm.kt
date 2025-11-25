package com.example.netflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netflow.auth.data.models.Habit
import com.google.firebase.auth.FirebaseAuth

@Composable

fun HabitForm(
    habit: Habit? = null,
    onSave: (Habit) -> Unit
 ) {

    var title by remember { mutableStateOf(habit?.title ?:"") }
    var description by remember { mutableStateOf(habit?.description ?: "") }

    Column(
        Modifier.padding(24.dp)
    ) {

        Text("Novo Hábito", fontSize = 24.sp)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text("Título")}
        )

        OutlinedTextField(
            value = description,
            onValueChange = {description = it},
            label = { Text("Descrição")}
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val newHabit = Habit(
                    id = habit?.id ?: "",
                    title = title,
                    description = description,
                    userId = FirebaseAuth.getInstance().uid ?:""
                )
                onSave(newHabit)
            }
        ) {
            Text("Salvar")
        }
    }
}