
package com.agendaukm.ui

// Menyimpan seluruh state atau kondisi pada form kegiatan
data class KegiatanFormUiState(
    val namaKegiatan: String = "",
    val deskripsiKegiatan: String = "",
    val tanggalKegiatan: String = "",
    val waktuMulai: String = "",
    val waktuSelesai: String = "",
    val ruangan: String = "",
    val idUKM: Int? = null,
    val sedangMenyimpan: Boolean = false,
    val pesan: String? = null
) {
    // Memeriksa apakah seluruh kolom wajib sudah diisi
    // Tombol simpan tidak dapat digunakan saat proses penyimpanan berlangsung
    val bisaSimpan: Boolean
        get() = namaKegiatan.isNotBlank() &&
                tanggalKegiatan.isNotBlank() &&
                waktuMulai.isNotBlank() &&
                waktuSelesai.isNotBlank() &&
                ruangan.isNotBlank() &&
                idUKM != null &&
                !sedangMenyimpan
}
