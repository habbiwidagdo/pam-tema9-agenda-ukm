package com.agendaukm.model

// Extension function untuk menyaring daftar kegiatan berdasarkan status.
fun List<Kegiatan>.filterByStatus(
    status: StatusKegiatan
): List<Kegiatan> {

    // FILTER: Memilih hanya kegiatan yang statusnya sama dengan status pilihan.
    return filter { kegiatan ->
        kegiatan.status == status
    }
}
