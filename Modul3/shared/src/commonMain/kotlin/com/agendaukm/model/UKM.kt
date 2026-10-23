package com.agendaukm.model

// data class entitas pendukung untuk menyimpan data UKM penyelenggaranya
data class UKM(
    val id: Int,              // identitas unik UKM tersebut
    val namaUKM: String,         // nama UKM
    val penanggungJawab: String, // nama penanggung jawab kegiatan
    val kontakPerson: String     // nomor kontak yang bisa dihubungi
)