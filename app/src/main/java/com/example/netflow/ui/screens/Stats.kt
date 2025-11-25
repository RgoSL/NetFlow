package com.example.netflow.ui.screens

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.netflow.auth.data.models.Habit
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Composable
fun Stats(habits: List<Habit>) {

    val entries = listOf(
        PieEntry(
            habits.count { it.completed }.toFloat(),
            "Concluídos"
        ),
        PieEntry(
            habits.count { !it.completed }.toFloat(),
            "Pendentes"
        )
    )

    AndroidView(
        factory = { context ->

            PieChart(context).apply {

                val dataSet = PieDataSet(entries, "Hábitos").apply {
                    colors = listOf(
                        Color.parseColor("#A8E6CF"),
                        Color.parseColor("#A3D9F3")
                    )
                    valueTextColor = Color.BLACK
                    valueTextSize = 14f
                }

                data = PieData(dataSet)
                description.isEnabled = false
                centerText = "Progresso"
                setCenterTextSize(18f)
                animateY(800)
            }
        }
    )
}