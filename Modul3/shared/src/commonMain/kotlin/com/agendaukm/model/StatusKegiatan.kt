package com.agendaukm.model

// sealed class untuk membatasi status kegiatan untuk pilihan yang ditentukan saja
sealed class StatusKegiatan {
    data object Mendatang : StatusKegiatan()   // status untuk acara yang belum mulai
    data object Berlangsung : StatusKegiatan() // status untuk acara yang sedang berjalan
    data object Selesai : StatusKegiatan()     // status untuk acara yang sudah selesai
}