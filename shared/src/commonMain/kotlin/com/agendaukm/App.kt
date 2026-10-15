package com.agendaukm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import agendaukm.shared.generated.resources.Res
import agendaukm.shared.generated.resources.compose_multiplatform

@Composable
fun App() = MaterialTheme {
    var tampil by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("AgendaUKM")
        Button(onClick = { tampil = !tampil }) {
            Text("Cek platform")
        }
        AnimatedVisibility(tampil) { Text(sapaan()) }
    }
}