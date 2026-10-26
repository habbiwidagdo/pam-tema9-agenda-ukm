// commonMain/data/SumberKegiatanPalsu.kt
package com.agendaukm.data

import com.agendaukm.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Class penyedia dummy data
class SumberKegiatanPalsu:Sumber<Kegiatan> {

    // Kumpulan dummy data UKM penyelenggara
    private val ukmBadminton = UKM(2, "UKM Badminton Team", "Ucup", "081234567890")
    private val ukmFutsal = UKM(3, "UKM FUtsal Team", "Galih", "089612345678")
    private val ukmMusik = UKM(4, "UKM Musik", "Jaka", "085398765432")
    private val ukmTari = UKM(5, "UKM Tari", "Juun", "089912345678")
    private val ukmKarate = UKM(6, "UKM Karate Team", "Sopian", "081123456789")
    private val ukmPalsu = UKM(1, "UKM Basket", "Joko", "12345678")
//    override suspend fun ambil(id: Int): Kegiatan {
//        delay(1000)
//        return Kegiatan(
//            id = id,
//            namaKegiatan = "Latihan Rutin Basket",
//            deskripsiKegiatan = "Latihan mingguan persiapan rekrutmen",
//            tanggalKegiatan = "30 Oktober 2026",
//            waktuMulai = "19:00 WIB",
//            waktuSelesai = "21:00 WIB",
//            ruangan = "GOR ITERA",
//            ukm = ukmPalsu,
//            status = StatusKegiatan.Mendatang
//        )
//    }

    // Kumpulan dummy data kegiatan
    val semuaKegiatan: List<Kegiatan> = listOf(
        Kegiatan(
            id = 1,
            namaKegiatan = "Latihan Rutin Basket",
            deskripsiKegiatan = "Latihan mingguan persiapan rekrutmen",
            tanggalKegiatan = "30 Oktober 2026",
            waktuMulai = "19:00 WIB",
            waktuSelesai = "21:00 WIB",
            ruangan = "GOR ITERA",
            ukm = ukmPalsu,
            status = StatusKegiatan.Mendatang
        ),
        Kegiatan(
            id = 2,
            namaKegiatan = "Latihan Mingguan Badminton",
            deskripsiKegiatan = " ",
            tanggalKegiatan = "25 Oktober 2026",
            waktuMulai = "10:00 WIB",
            waktuSelesai = "12:00 WIB",
            ruangan = "Lapangan Badminton",
            ukm = ukmBadminton,
            status = StatusKegiatan.Mendatang
        ),
        Kegiatan(
            id = 3,
            namaKegiatan = "Turnamen Futsal Internal",
            deskripsiKegiatan = "Kompetisi futsal antar program studi",
            tanggalKegiatan =  "10 Oktober 2026",
            waktuMulai = "15:00 WIB",
            waktuSelesai = "17:00 WIB",
            ruangan = "Lapangan futsal",
            ukm = ukmFutsal,
            status = StatusKegiatan.Selesai
        ),
        Kegiatan(
            id = 4,
            namaKegiatan = "Jamming Session Musik",
            deskripsiKegiatan = "Latihan dan apresiasi musik bersama angggota baru",
            tanggalKegiatan = "15 Oktober 2026",
            waktuMulai = "14:00 WIB",
            waktuSelesai = "16:00 WIB",
            ruangan = "Ruang Musik",
            ukm = ukmMusik,
            status = StatusKegiatan.Selesai
        ),
        Kegiatan(
            id = 5,
            namaKegiatan = "Latihan Tari Tradisional",
            deskripsiKegiatan = null,
            tanggalKegiatan = "24 Oktober 2026",
            waktuMulai = "10:00 WIB",
            waktuSelesai = "12:00 WIB",
            ruangan = "Ruang Tari",
            ukm = ukmTari,
            status = StatusKegiatan.Berlangsung
        ),
        Kegiatan(
            id = 6,
            namaKegiatan = "Ujian Sabuk Karate",
            deskripsiKegiatan = "Ujian kenaikan tingkat sabuk karate",
            tanggalKegiatan = "20 Oktober 2026",
            waktuMulai = "13:00 WIB",
            waktuSelesai = "15:00 WIB",
            ruangan = "Lapangan Karate",
            ukm = ukmKarate,
            status = StatusKegiatan.Selesai
        ),
        Kegiatan(
            id = 7,
            namaKegiatan = "Seleksi Pemain Basket",
            deskripsiKegiatan = "Perekrutan atlet muda",
            tanggalKegiatan = "31 Oktober 2026",
            waktuMulai = "10:00 WIB",
            waktuSelesai = "12:00 WIB",
            ruangan = "GOR ITERA",
            ukm = ukmPalsu,
            status = StatusKegiatan.Mendatang
        ),
        Kegiatan(
            id = 8,
            namaKegiatan = "Sparring Futsal vs Unila",
            deskripsiKegiatan = "Pertandingan persahabatan persiapan laga",
            tanggalKegiatan = "25 Oktober 2026",
            waktuMulai = "15:00 WIB",
            waktuSelesai = "17:00 WIB",
            ruangan = "Lapangan futsal",
            ukm = ukmFutsal,
            status = StatusKegiatan.Mendatang
        ),
        Kegiatan(
            id = 9,
            namaKegiatan = "Workshop Musik Klasik",
            deskripsiKegiatan = "",
            tanggalKegiatan = "24 Oktober 2026",
            waktuMulai = "10:00 WIB",
            waktuSelesai = "12:00 WIB",
            ruangan = "Ruang Musik",
            ukm = ukmMusik,
            status = StatusKegiatan.Berlangsung
        ),
        Kegiatan(
            id = 10,
            namaKegiatan = "Pentas Seni Budaya",
            deskripsiKegiatan = "Pertunjukan tari daerah dalam rangka kebudayaan",
            tanggalKegiatan = "15 Oktober 2026",
            waktuMulai = "14:00 WIB",
            waktuSelesai = "16:00 WIB",
            ruangan = "Ruang Tari",
            ukm = ukmTari,
            status = StatusKegiatan.Selesai
        ),
        Kegiatan(
            id = 11,
            namaKegiatan = "Latihan Fisik Karate",
            deskripsiKegiatan = "Latihan ketahanan fisik",
            tanggalKegiatan = "26 Oktober 2026",
            waktuMulai = "13:00 WIB",
            waktuSelesai = "15:00 WIB",
            ruangan = "Lapangan Karate",
            ukm = ukmKarate,
            status = StatusKegiatan.Mendatang
        ),
    )

    // Mengambil satu data kegiatan berdasaran ID
    override suspend fun ambil(id: Int): Kegiatan {
        delay(1000)
        return semuaKegiatan.find { it.id == id } ?: throw NoSuchElementException("Kegiatan dengan ID $id tidak ditemukan!")
    }

    // Memantau perubahan status kegiatan secara realtime dengan flow
    override fun pantau(id: Int): Flow<Kegiatan> = flow {
        val dasar = ambil(id)
        emit(dasar.copy(status = StatusKegiatan.Mendatang))
        delay(1500)

        emit(dasar.copy(status = StatusKegiatan.Berlangsung))
        delay(2000)

        emit(dasar.copy(status = StatusKegiatan.Selesai))
    }
}