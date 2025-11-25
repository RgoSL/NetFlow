package com.example.netflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.netflow.auth.data.models.Habit
import com.example.netflow.ui.theme.OffWhite
import com.example.netflow.ui.theme.SoftCoral
import com.example.netflow.viewModel.HabitViewModel

@Composable
fun HabitItem(habit: Habit, onClick: () -> Unit) {
    Text(
        text = habit.title,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitList(
    viewModel: HabitViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Habit) -> Unit
) {
    val habits by viewModel.habits.collectAsState()

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(containerColor = SoftCoral, onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        },
        topBar = {
            TopAppBar(title = { Text("Meus Hábitos") })
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(OffWhite)
        ) {
            items(habits) { habit ->
                HabitItem(habit) {
                    onEditClick(habit)
                }
            }
        }
    }
}