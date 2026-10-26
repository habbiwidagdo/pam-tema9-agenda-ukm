package com.agendaukm.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// tujuannya untuk membuat kolom input yang bisa dipakai berulang kali (Stateless)
// kodenya sengaja tidak menyimpan data sendiri supaya isinya murni dikendalikan oleh ViewModel
@Composable
fun KolomTeks(
    label: String,                  // teks petunjuk judul kolom misal: "nama Kegiatan"
    value: String,                  // teks yang sedang ditampilkan di dalam kotak
    onValueChange: (String) -> Unit // tujuannya untuk melaporkan ketikan baru ke atas (ViewModel) untuk diproses
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth() // buat kotak memanjang penuh ke samping
    )
}