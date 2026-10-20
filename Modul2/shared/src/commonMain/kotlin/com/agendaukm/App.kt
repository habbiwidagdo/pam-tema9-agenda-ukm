package com.agendaukm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import com.agendaukm.model.*
import agendaukm.shared.generated.resources.Res
import agendaukm.shared.generated.resources.compose_multiplatform

@Composable
fun App() = MaterialTheme {

    // Pengujian tugas 2: LaunchedEfffect(Unit) berjalan otomatis di background saat UI muncul
    LaunchedEffect(Unit){
        println("Verifikasi Tugas 2")

        // 1. Data UKM
        val ukmBasket = UKM(
            id = 1,
            namaUKM = "ITERA Basketball",
            penanggungJawab = "Ucup",
            kontakPerson = "081234567890"
        )

        // 2. Data kegiatan
        val kegiatanAwal = Kegiatan (
            id = 1,
            namaKegiatan = "Latihan rutin",
            deskripsiKegiatan = null,
            tanggalKegiatan = "20 Oktober 2026",
            waktuMulai = "15.00",
            waktuSelesai = "18.00",
            ruangan = "Lapangan basket",
            ukm = ukmBasket
        )

        // 3. Pengujian copy()
        val kegiatanBaru = kegiatanAwal.copy(
            deskripsiKegiatan = "Sparing antar divisi",
            status = StatusKegiatan.Berlangsung
        )

        // 4. Verfikasi pembacaan label
        println("\nUJI LABEL STATUS")
        println("Status Awal : ${kegiatanAwal.status.label()}")
        println("Status Baru: ${kegiatanBaru.status.label()}")

        // 5. Verifikasi ringkas kegiatan
        println("\nUJI RINGKAS KEGIATAN")
        println("Kegiatan Awal: ${kegiatanAwal.ringkas()}")
        println("\nKegiatan Baru: ${kegiatanBaru.ringkas()}")

        // 6. Verifikasi null safety
        println("\nUJI NULL SAFETY")
        println("Apakah kegiatan awal bernilai null? ${kegiatanAwal.deskripsiKegiatan == null}")
        println("Apakah kegiatan baru bernilai null? ${kegiatanBaru.deskripsiKegiatan == null}")

        // 7. Verifikasi fitur copy()
        println("\nUJI COPY")
        println("Apakah objek awal dan baru sama? ${kegiatanAwal === kegiatanBaru}")
    }
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