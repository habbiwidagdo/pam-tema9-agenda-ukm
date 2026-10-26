package com.agendaukm.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agendaukm.model.Kegiatan

// @Composable fungsinya bertugas untuk menggambar ui di layar.
@Composable
fun KartuKegiatan(
    kegiatan: Kegiatan, // meminta data kegiatan apa yang mau digambar
    onKlik: () -> Unit  // membuat kartu ini siap menerima aksi "diklik"
) {
    // 1. Card ini bagian dari komponen ui tujuannya untuk membuat wadah berbentuk kartu yang punya bayangan tipis
    Card(
        // MODIFIER atau pengaturane tujuannya untuk mengatur ukuran, jarak, dan interaksi
        modifier = Modifier
            .fillMaxWidth() // mlebarkan kartu sampai mentok layar HP (kiri ke kanan)
            .padding(horizontal = 16.dp, vertical = 8.dp) // jarak luar supaya kartu tidak nempel ke pinggir layar
            .clickable { onKlik() } // Modifier agar kartu bisa merespons saat disentuh/diklik
    ) {

        // 2. LAYOUT ROW (Baris) untuk menyusun isi di dalamnya secara MENYAMPING (Kiri ke Kanan)
        // tujuannya untuk teks di sebelah kiri, Label Status (warna-warni) di sebelah kanan.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // jarak di dalam kartu agar teks tidak nabrak garis luar
            verticalAlignment = Alignment.CenterVertically // memastikan posisinya rata tengah (tidak terlalu atas/bawah)
        ) {

            // 3. LAYOUT COLUMN (Kolom): menyusun teks di dalamnya secara ke bawah (atas ke bawah)
            // tujuannya menumpuk Judul, Nama UKM, dan Waktu ke bawah.
            Column(
                // Modifier.weight(1f): Menyuruh kolom teks ini "mengambil semua sisa ruang kosong".
                // efeknya kolom teks akan melebar, dan otomatis mendorong LabelStatus mentok ke pojok kanan.
                modifier = Modifier.weight(1f)
            ) {
                // Teks Judul Kegiatan
                Text(
                    text = kegiatan.namaKegiatan,
                    fontWeight = FontWeight.Bold, // huruf ditebalkan
                    fontSize = 18.sp              // ukuran huruf dibesarkan
                )

                // spacer untuk memberi jarak spasi kosong ke teks di bawahnya (4.dp)
                Spacer(modifier = Modifier.height(4.dp))

                // teks Nama UKM  tujuannya untuk menarik data dari class UKM yang ada di modul 2
                Text(
                    text = "Penyelenggara: ${kegiatan.ukm.namaUKM}",
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // teks waktu tujuannya untuk menggabungkan tanggal dan jam dari class Kegiatan
                Text(
                    text = "${kegiatan.tanggalKegiatan} | ${kegiatan.waktuMulai} - ${kegiatan.waktuSelesai}",
                    fontSize = 12.sp
                )
            }

            // 4. memanggil komponen LabelStatus
            // karena dibungkus di dalam Row, labelnya muncul di sebelah kanan Column teks tadi.
            LabelStatus(status = kegiatan.status)
        }
    }
}