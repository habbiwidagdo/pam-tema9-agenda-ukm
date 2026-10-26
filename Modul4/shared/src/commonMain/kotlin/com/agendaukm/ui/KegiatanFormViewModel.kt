
package com.agendaukm.ui

import androidx.lifecycle.ViewModel
import com.agendaukm.model.Kegiatan
import com.agendaukm.model.StatusKegiatan
import com.agendaukm.model.UKM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KegiatanFormViewModel : ViewModel() {

    // State internal yang dapat diubah oleh ViewModel
    private val _uiState = MutableStateFlow(KegiatanFormUiState())

    // State yang dapat diamati UI, tetapi tidak dapat diubah langsung dari luar
    val uiState = _uiState.asStateFlow()

    // Memperbarui nama kegiatan ketika pengguna mengetik
    fun ubahNamaKegiatan(nilai: String) {
        _uiState.update {
            it.copy(namaKegiatan = nilai, pesan = null)
        }
    }

    // Memperbarui deskripsi kegiatan
    fun ubahDeskripsiKegiatan(nilai: String) {
        _uiState.update {
            it.copy(deskripsiKegiatan = nilai, pesan = null)
        }
    }

    // Memperbarui tanggal kegiatan
    fun ubahTanggalKegiatan(nilai: String) {
        _uiState.update {
            it.copy(tanggalKegiatan = nilai, pesan = null)
        }
    }

    // Memperbarui waktu mulai kegiatan
    fun ubahWaktuMulai(nilai: String) {
        _uiState.update {
            it.copy(waktuMulai = nilai, pesan = null)
        }
    }

    // Memperbarui waktu selesai kegiatan
    fun ubahWaktuSelesai(nilai: String) {
        _uiState.update {
            it.copy(waktuSelesai = nilai, pesan = null)
        }
    }

    // Memperbarui ruangan tempat kegiatan dilaksanakan
    fun ubahRuangan(nilai: String) {
        _uiState.update {
            it.copy(ruangan = nilai, pesan = null)
        }
    }

    // Memperbarui ID UKM yang dipilih pengguna
    fun ubahUKM(id: Int?) {
        _uiState.update {
            it.copy(idUKM = id, pesan = null)
        }
    }

    // Memvalidasi form sebelum proses penyimpanan dimulai
    fun mulaiMenyimpan(): Boolean {
        val state = _uiState.value

        // Menolak proses penyimpanan jika masih ada kolom wajib yang kosong
        if (!state.bisaSimpan) {
            _uiState.update {
                it.copy(
                    pesan = "Lengkapi semua kolom wajib terlebih dahulu."
                )
            }
            return false
        }

        // Menandai bahwa proses penyimpanan sedang berlangsung
        _uiState.update {
            it.copy(sedangMenyimpan = true, pesan = null)
        }

        return true
    }

    // Membentuk objek Kegiatan dan menyerahkannya ke fungsi penyimpanan
    // Fungsi simpan disediakan oleh pemanggil agar dapat dihubungkan
    // dengan mekanisme penyimpanan yang dibuat oleh anggota lain
    suspend fun kirim(
        idKegiatan: Int,
        ukm: UKM?,
        simpan: suspend (Kegiatan) -> Unit
    ): Boolean {

        // Menghentikan proses jika form belum memenuhi validasi
        if (!mulaiMenyimpan()) return false

        // Mengambil data form terbaru
        val state = _uiState.value

        // Memastikan objek UKM tersedia dan ID-nya sesuai pilihan pada form
        if (ukm == null || ukm.id != state.idUKM) {
            gagalMenyimpan("UKM yang dipilih tidak valid.")
            return false
        }

        // Mengubah data form menjadi objek Kegiatan
        val kegiatan = Kegiatan(
            id = idKegiatan,
            namaKegiatan = state.namaKegiatan.trim(),
            deskripsiKegiatan = state.deskripsiKegiatan.trim()
                .takeIf { it.isNotEmpty() },
            tanggalKegiatan = state.tanggalKegiatan.trim(),
            waktuMulai = state.waktuMulai.trim(),
            waktuSelesai = state.waktuSelesai.trim(),
            ruangan = state.ruangan.trim(),
            ukm = ukm,
            status = StatusKegiatan.Mendatang
        )

        // Menyerahkan objek kegiatan kepada fungsi penyimpanan
        return try {
            simpan(kegiatan)

            // Mengosongkan form setelah penyimpanan berhasil
            selesaiMenyimpan()
            true
        } catch (e: Exception) {
            // Menampilkan pesan jika penyimpanan mengalami kegagalan
            gagalMenyimpan(
                e.message ?: "Kegiatan gagal disimpan."
            )
            false
        }
    }

    // Mengakhiri proses penyimpanan dengan menampilkan pesan kegagalan
    fun gagalMenyimpan(pesan: String) {
        _uiState.update {
            it.copy(sedangMenyimpan = false, pesan = pesan)
        }
    }

    // Mengembalikan seluruh state form ke kondisi awal setelah berhasil disimpan
    fun selesaiMenyimpan() {
        _uiState.update {
            KegiatanFormUiState()
        }
    }
}
