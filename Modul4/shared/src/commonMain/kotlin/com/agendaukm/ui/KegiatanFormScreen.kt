package com.agendaukm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// tujuannya untuk membuat halaman form yang tersambung langsung dengan ViewModel
@Composable
fun KegiatanFormScreen(
    // menyambungkan layar ini dengan ViewModel/otak pengelola data buatan tika
    vm: KegiatanFormViewModel = viewModel { KegiatanFormViewModel() },

    // titik sambung untuk tombol simpan, nanti fungsi aslinya diatur oleh habbi
    onSimpanKlik: () -> Unit
) {
    // memantau kondisi data terbaru secara otomatis (langsung update kalau ada perubahan)
    val state by vm.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp) // memberi jarak antar kotak isian agar tidak menempel
    ) {
        Text(text = "Form Tambah Kegiatan", modifier = Modifier.padding(bottom = 8.dp))

        // konsep "State turun, Event naik":
        // mengambil teks dari state (value), lalu mengirim ketikan baru pengguna kembali ke ViewModel (onValueChange).
        KolomTeks(
            label = "Nama Kegiatan",
            value = state.namaKegiatan,
            onValueChange = vm::ubahNamaKegiatan
        )

        KolomTeks(
            label = "Deskripsi",
            value = state.deskripsiKegiatan,
            onValueChange = vm::ubahDeskripsiKegiatan
        )

        KolomTeks(
            label = "Tanggal",
            value = state.tanggalKegiatan,
            onValueChange = vm::ubahTanggalKegiatan
        )

        KolomTeks(
            label = "Waktu Mulai",
            value = state.waktuMulai,
            onValueChange = vm::ubahWaktuMulai
        )

        KolomTeks(
            label = "Waktu Selesai",
            value = state.waktuSelesai,
            onValueChange = vm::ubahWaktuSelesai
        )

        KolomTeks(
            label = "Ruangan / Lokasi",
            value = state.ruangan,
            onValueChange = vm::ubahRuangan
        )

        Button(
            onClick = onSimpanKlik,
            // tombol otomatis terkunci (abu-abu) jika kolom wajib belum terisi semua
            enabled = state.bisaSimpan
        ) {
            // tulisan di tombol berubah sendiri jadi "Menyimpan..." saat form sedang diproses
            Text(if (state.sedangMenyimpan) "Menyimpan..." else "Simpan Kegiatan")
        }

        // kalau ada kendala atau form belum lengkap, munculkan pesan error berwarna merah
        state.pesan?.let { pesanError ->
            Text(text = pesanError, color = Color.Red)
        }
    }
}