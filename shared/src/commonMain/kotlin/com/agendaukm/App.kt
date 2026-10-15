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
//    Membuat variabel tampil yang menyimpan nilai perubahaan dari fungsi sapaan()
    var tampil by remember { mutableStateOf(false) }
//    Membuat kolom yang berisi teks dan tombol
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("AgendaUKM")
//        Membuat tombol yang ketika ditekan akan mengubah nilai tampil menjadi kebalikannya
        Button(onClick = { tampil = !tampil }) {
            Text("Cek platform")
        }
//        Menampilkan teks sapaan yang sesuai dengan nilai tampil
        AnimatedVisibility(tampil) { Text(sapaan()) }
    }
}