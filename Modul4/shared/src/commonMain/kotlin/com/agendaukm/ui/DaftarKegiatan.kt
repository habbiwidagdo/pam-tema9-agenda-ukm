package com.agendaukm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.agendaukm.model.Kegiatan

// Komponen UI untuk menampilkan daftar panjang kegiatan menggunakan LazyColumn
@Composable
fun DaftarKegiatan(
    semua: List<Kegiatan>,                        // Menerima daftar data kegiatan yang akan ditampilkan
    modifier: Modifier = Modifier,                // Modifier opsional dari pemanggil
    onPilih: (Kegiatan) -> Unit = {}              // Callback saat salah satu kartu diklik
){
    // LazyColumn hanya menggambar item yang sedang terlihat di layar.
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),  // Jarak sekeliling daftar (kiri, kanan, atas, bawah)
        verticalArrangement = Arrangement.spacedBy(12.dp)                      //  Jarak antara item dalam daftar
    ){
        // Menentukan item yang ingin ditampilkan ke layar berdasarkan ID
       items(
           items = semua,
           key = { kegiatan -> kegiatan.id }
       ){ item ->
           // Memanggil komponen KartuKegiatan untuk setiap item
           KartuKegiatan(
               kegiatan = item,
               onKlik = { onPilih(item)}
           )
       }
    }
}
