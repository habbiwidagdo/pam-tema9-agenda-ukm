package com.agendaukm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agendaukm.model.Kegiatan

// Komponen pop-up interaktif untuk menampilkan rincian informasi kegiatan saat kartu diklik
@Composable
fun DetailKegiatanDialog(
    kegiatan: Kegiatan,   // Data objek kegiatan yang dipilih untuk ditampilkan rinciannya
    onDismiss: () -> Unit // Callback untuk menutup popup saat tombol Tutup atau area luar diklik
) {
    AlertDialog(
        // Menutup popup saat pengguna menekan tombol Kembali HP atau mengetuk area di luar dialog
        onDismissRequest = onDismiss,

        // Bagian judul (Judul kegiatan dan badge status)
        title = {
            Column {
                Text(
                    text = kegiatan.namaKegiatan,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Menampilkan label status kegiatan (misal: Mendatang, Berlangsung, Selesai)
                LabelStatus(status = kegiatan.status)
            }
        },

        // Bagian konten utama dialog yang berisi detail informasi kegiatan
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Garis pembatas horizontal untuk memisahkan judul dan rincian konten
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                // Informasi UKM Penyelenggara
                Text(text = "Penyelenggara:", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                Text(text = kegiatan.ukm.namaUKM, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)

                Spacer(modifier = Modifier.height(8.dp))

                // Informasi Penanggung Jawab & Kontak Person UKM
                Text(text = "Penanggung Jawab:", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                Text(text = "${kegiatan.ukm.penanggungJawab} (${kegiatan.ukm.kontakPerson})", fontSize = 14.sp)

                Spacer(modifier = Modifier.height(8.dp))

                // Informasi Tanggal dan Waktu Pelaksanaan
                Text(text = "Tanggal & Waktu:", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                Text(text = "${kegiatan.tanggalKegiatan} | ${kegiatan.waktuMulai} - ${kegiatan.waktuSelesai}", fontSize = 14.sp)

                Spacer(modifier = Modifier.height(8.dp))

                // Informasi Ruangan atau Lokasi Pelaksanaan
                Text(text = "Ruangan / Lokasi:", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                Text(text = kegiatan.ruangan, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(8.dp))

                // Informasi Deskripsi Tambahan
                Text(text = "Deskripsi:", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
                Text(
                    text = kegiatan.deskripsiKegiatan?.ifBlank { "Tidak ada deskripsi" } ?: "Tidak ada deskripsi",
                    fontSize = 14.sp
                )
            }
        },

        // Tombol konfirmasi di bagian bawah dialog untuk menutup pop-up
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Tutup")
            }
        }
    )
}
