package com.agendaukm.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.agendaukm.model.Kegiatan
import kotlinx.coroutines.flow.asStateFlow
import com.agendaukm.model.UKM

object PenyimpananAgendaUKM {
    private val _daftar = MutableStateFlow(SumberKegiatanPalsu().semuaKegiatan)   // tiga laporan dari Modul 3
    val daftar: StateFlow<List<Kegiatan>> = _daftar.asStateFlow()

    fun tambah(namaKegiatan:String, deskripsiKegiatan:String?, tanggalKegiatan:String, waktuMulai:String, waktuSelesai:String, ruangan:String, ukm:UKM) = _daftar.update { lama ->
        lama + Kegiatan(id = lama.size + 1, namaKegiatan, deskripsiKegiatan, tanggalKegiatan, waktuMulai, waktuSelesai, ruangan, ukm)
    }
}