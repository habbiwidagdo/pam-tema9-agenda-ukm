package com.agendaukm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

/**
 * Menampilkan sapaan.
 * Menggunakan fungsi getPlatform() yang dideklarasikan.
 */
fun sapaan(): String =
    "Agenda UKM berjalan di ${getPlatform().name}"
