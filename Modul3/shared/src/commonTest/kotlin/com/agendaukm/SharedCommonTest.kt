package com.agendaukm

import kotlin.test.assertEquals
import com.agendaukm.data.SumberKegiatanPalsu
import com.agendaukm.model.StatusKegiatan
import com.agendaukm.model.ringkas
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.agendaukm.model.filterByStatus

class SharedCommonTest {

    @Test
    fun testPantauKegiatanDenganFlowOperator() = runBlocking {
        // 1. Inisialisasi sumber data
        val sumber = SumberKegiatanPalsu()
        val hasilDiterima = mutableListOf<String>()

        // 2. Eksekusi Flow dari pantau(id) dengan operator filter, map, dan collect
        sumber.pantau(id = 1)
            // FILTER: Hanya ambil kegiatan yang statusnya BUKAN Mendatang (misal: Berlangsung dan Selesai)
            .filter { kegiatan ->
                kegiatan.status != StatusKegiatan.Mendatang
            }
            // MAP: Ubah objek Kegiatan menjadi String ringkasan menggunakan extension function ringkas()
            .map { kegiatan ->
                kegiatan.ringkas()
            }
            // COLLECT: Kumpulkan hasil emisi ke dalam list hasilDiterima
            .collect { ringkasanString ->
                println("Diterima dari Flow: $ringkasanString")
                hasilDiterima.add(ringkasanString)
            }

        // 3. Verifikasi (Assertion)
        // Karena emisi pertama (Mendatang) difilter, maka data yang tersisa harus berjumlah 2 (Berlangsung & Selesai)
        assertEquals(2, hasilDiterima.size)

        // Memastikan isi string hasil transformasi mengandung nama kegiatan yang sesuai
        assertTrue(hasilDiterima[0].contains("Latihan Rutin Basket"))
    }

    @Test
    fun testFilterKegiatanBerdasarkanStatus() {
        // 1. Membuat sumber data kegiatan palsu.
        val sumber = SumberKegiatanPalsu()

        // 2. Mengambil satu contoh kegiatan.
        runBlocking {
            val kegiatan = sumber.ambil(id = 1)

            // 3. Menyaring kegiatan berdasarkan status Mendatang.
            val hasil = listOf(kegiatan)
                .filterByStatus(StatusKegiatan.Mendatang)

            // 4. Memastikan hasil filter sesuai.
            assertEquals(1, hasil.size)
            assertEquals(StatusKegiatan.Mendatang, hasil[0].status)
        }
    }
}