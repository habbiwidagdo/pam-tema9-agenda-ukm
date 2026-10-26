package com.agendaukm.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.agendaukm.data.SumberKegiatanPalsu
import com.agendaukm.model.Kegiatan
import androidx.compose.ui.graphics.Color

// Komponen antarmuka utama layar yang menyusun header, daftar kegiatan, dan tombol FAB menggunakan Scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayarUtama(
    kegiatanList: List<Kegiatan> = SumberKegiatanPalsu().semuaKegiatan,
    onKegiatanClick: (Kegiatan) -> Unit = {},
    onFabClick: () -> Unit = {}
) {
    Scaffold(
        // Header Judul Layar Utama
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agenda UKM",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF9B682),
                    titleContentColor = Color(0xFF252642)
                )
            )
        },
        // FloatingActionButton sebagai pajangan di dalam kerangka Scaffold untuk menyempurnakan struktur antarmuka utama
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick
            ) {
                Text(
                    text = "+",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->
        // Konten utama menampilkan daftar kegiatan dengan padding dari Scaffold
        DaftarKegiatan(
            semua = kegiatanList,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onPilih = onKegiatanClick
        )
    }
}
