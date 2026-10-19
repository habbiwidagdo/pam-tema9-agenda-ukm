package com.agendaukm.model

// data class entitas utama untuk menyimpan seluruh informasi agenda kegiatan
data class Kegiatan(
    val id: Int,                                       // identitas unik kegiatan
    val namaKegiatan: String,                             // nama acara
    val deskripsiKegiatan: String? = null,                // detail acara (opsional, boleh null)
    val tanggalKegiatan: String,                          // tanggal pelaksanaan acara
    val waktuMulai: String,                               // waktu mulai acara
    val waktuSelesai: String,                             // waktu selesai acara
    val ruangan: String,                                  // lokasi atau ruangan acara
    val ukm: UKM,                                         // relasi ke data UKM penyelenggara
    val status: StatusKegiatan = StatusKegiatan.Mendatang // status acara (default: Mendatang)
)