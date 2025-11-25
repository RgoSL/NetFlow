package com.example.netflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.netflow.navigation.NavFlow
import com.example.netflow.ui.theme.NetFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetFlowTheme {}
                NavFlow()
            }
        }
    }