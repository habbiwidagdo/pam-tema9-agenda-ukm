package com.agendaukm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agendaukm.model.UKM

// tujuannya untuk membuat halaman form yang tersambung langsung dengan ViewModel
@Composable
fun KegiatanFormScreen(
    // menyambungkan layar ini dengan ViewModel/otak pengelola data buatan tika
    vm: KegiatanFormViewModel = viewModel { KegiatanFormViewModel() },

    // titik sambung untuk tombol simpan
    onSimpanKlik: () -> Unit
) {
    // memantau kondisi data terbaru secara otomatis (langsung update kalau ada perubahan)
    val state by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
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

        KolomTeks(
            label = "Nama UKM",
            value = state.namaUKM,
            onValueChange = vm::ubahNamaUKM
        )

        KolomTeks(
            label = "Penanggung Jawab",
            value = state.penanggungJawab,
            onValueChange = vm::ubahPenanggungJawab
        )

        KolomTeks(
            label = "Kontak Person",
            value = state.kontakPerson,
            onValueChange = vm::ubahKontakPerson
        )

        // Tombol utama simpan kegiatan
        Button(
            onClick = {
                // Menyimpan data UKM dari form
                val ukmPilihan = UKM(
                    id = state.idUKM ?: 1,
                    namaUKM = state.namaUKM.ifBlank { "UKM" },
                    penanggungJawab = state.penanggungJawab.ifBlank { "Penanggung Jawab" },
                    kontakPerson = state.kontakPerson.ifBlank { "-" }
                )

                // Memanggil fungsi kirim ViewModel untuk menyimpan data kegiatan baru
                if (vm.kirim(idKegiatan = 0, ukm = ukmPilihan)) {
                    onSimpanKlik()
                }
            },
            // Tombol otomatis terkunci jika kolom wajib belum terisi
            enabled = state.bisaSimpan
        ) {
            // Tulisan di tombol berubah sendiri jadi "Menyimpan..." saat form sedang diproses
            Text(if (state.sedangMenyimpan) "Menyimpan..." else "Simpan Kegiatan")
        }

        // Tombol batal (kembali ke layar daftar tanpa menyimpan)
        OutlinedButton(
            onClick = onSimpanKlik
        ) {
            Text("Batal")
        }

        // kalau ada kendala atau form belum lengkap, munculkan pesan error berwarna merah
        state.pesan?.let { pesanError ->
            Text(text = pesanError, color = Color.Red)
        }
    }
}
