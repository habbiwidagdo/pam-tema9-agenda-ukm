package com.agendaukm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun namaPlatform(): String

/**
 * Menampilkan sapaan.
 * Menggunakan fungsi getPlatform() yang dideklarasikan.
 */
fun sapaan(): String =
    "Agenda UKM berjalan di ${namaPlatform()}"
