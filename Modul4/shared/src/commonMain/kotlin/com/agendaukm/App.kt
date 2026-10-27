package com.agendaukm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.agendaukm.data.PenyimpananAgendaUKM
import com.agendaukm.model.Kegiatan
import com.agendaukm.ui.DetailKegiatanDialog
import com.agendaukm.ui.KegiatanFormScreen
import com.agendaukm.ui.LayarUtama

@Composable
fun App() = MaterialTheme {
    // ----------------------------------------------
    // Integrasi navigasi dan data reaktif stateFlow
    // ----------------------------------------------

    // Mengamati data daftar kegiatan terbaru secara real time dari PenyimpananAgendaUKM menggunakan collectAsState()
    val daftarKegiatan by PenyimpananAgendaUKM.daftar.collectAsState()

    // State navigasi untuk menentukan halaman mana yang aktif (F = layar daftar, T = layar form)
    var diForm by remember { mutableStateOf(false) }

    // State untuk menyimpan kegiatan yang dipilih user untuk menampilkan dialog detail
    var kegiatanTerpilih by remember { mutableStateOf<Kegiatan?>(null) }

    // Menyusun seluruh layar menggunakan column
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Pengecekan kondisi state diForm untuk mengatur rute navigasi layar
        if (diForm) {
            KegiatanFormScreen(
                onSimpanKlik = {
                    diForm = false  // Mengubah state diForm menjadi false untuk berpindah ke layar daftar
                }
            )
        } else {
            // Jika diForm bernilai false, tampilkan layar utama dengan daftar kegiatan
            LayarUtama(
                kegiatanList = daftarKegiatan,
                onKegiatanClick = { kegiatan ->
                    kegiatanTerpilih = kegiatan
                },
                onFabClick = {
                    diForm = true  // Mengubah state diForm menjadi true untuk berpindah ke Layar Form Tambah Kegiatan
                }
            )

            // Menampilkan dialog detail jika ada kegiatan yang diklik oleh user
            kegiatanTerpilih?.let { kegiatan ->
                DetailKegiatanDialog(
                    kegiatan = kegiatan,
                    onDismiss = { kegiatanTerpilih = null }
                )
            }
        }
    }
}
