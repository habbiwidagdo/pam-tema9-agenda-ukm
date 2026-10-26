package com.agendaukm

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.agendaukm.model.Kegiatan
import com.agendaukm.model.StatusKegiatan
import com.agendaukm.model.UKM
import com.agendaukm.model.label
import com.agendaukm.model.ringkas
import com.agendaukm.ui.LayarUtama

@Composable
fun App() = MaterialTheme {

    // Pengujian tugas 2: LaunchedEffect(Unit) berjalan otomatis di background saat UI muncul
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

    LayarUtama()
}
