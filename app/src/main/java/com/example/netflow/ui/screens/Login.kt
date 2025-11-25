package com.example.netflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netflow.ui.theme.OffWhite
import com.example.netflow.ui.theme.PastelBlue
import com.example.netflow.ui.theme.SoftCoral
import com.example.netflow.viewModel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel, onLoginSuccess: () -> Unit) {

    val context = LocalContext.current
    val error = viewModel.errorMessage

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "NetFlow", fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = PastelBlue
        )

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email = it },
            label = { Text("Email") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.login(email, password, onLoginSuccess)
            },
            colors = ButtonDefaults.buttonColors(SoftCoral)
        ) {
            Text("Entrar")
        }

        if (error != null) {
            Text(error, color = Color.Red)
        }
    }
}