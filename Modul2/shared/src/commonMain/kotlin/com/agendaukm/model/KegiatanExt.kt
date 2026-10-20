package com.agendaukm.model

// Extension func untuk melabeli status
fun StatusKegiatan.label(): String = when (this){
    StatusKegiatan.Mendatang -> "Mendatang"
    StatusKegiatan.Berlangsung -> "Sedang berlangsung"
    StatusKegiatan.Selesai -> "Selesai"
}

// Extension func untuk meringkas data kegiatan dan implementasi Null Safety
fun Kegiatan.ringkas(): String {
    // Memakai elvis operator (?:) untuk menangani null
    val detail = deskripsiKegiatan ?: "Tidak ada deskripsi"

    // Memakai Safe Call (?.) mengecek apakah deskripsi ada isinya, menghasilkan null jika deskripsiKegiatan null
    val adaDetail = deskripsiKegiatan?.isNotBlank()

    return "#$id $namaKegiatan | PJ: ${ukm.penanggungJawab}\n" +
            "Jadwal: $tanggalKegiatan ($waktuMulai - $waktuSelesai)\n | Lokasi: $ruangan\n" +
            "Info: $detail (Ada deskripsi? $adaDetail)"
}