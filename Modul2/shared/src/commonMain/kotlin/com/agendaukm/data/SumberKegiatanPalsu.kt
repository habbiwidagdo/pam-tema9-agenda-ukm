// commonMain/data/SumberKegiatanPalsu.kt
package com.agendaukm.data

import com.agendaukm.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SumberKegiatanPalsu : Sumber<Kegiatan> {
    private val ukmPalsu = UKM(1, "UKM Basket", "Joko", "12345678")

    override suspend fun ambil(id: Int): Kegiatan {
        delay(1000)
        return Kegiatan(
            id = id,
            namaKegiatan = "Latihan Rutin Basket",
            deskripsiKegiatan = "Latihan mingguan persiapan rekrutmen",
            tanggalKegiatan = "10 Oktober 2026",
            waktuMulai = "19:00 WIB",
            waktuSelesai = "21:00 WIB",
            ruangan = "GOR ITERA",
            ukm = ukmPalsu,
            status = StatusKegiatan.Mendatang
        )
    }

    override fun pantau(id: Int): Flow<Kegiatan> = flow {
        val dasar = ambil(id)
        emit(dasar.copy(status = StatusKegiatan.Mendatang))
        delay(1500)

        emit(dasar.copy(status = StatusKegiatan.Berlangsung))
        delay(2000)

        emit(dasar.copy(status = StatusKegiatan.Selesai))
    }
}