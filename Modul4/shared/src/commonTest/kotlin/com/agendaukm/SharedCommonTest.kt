package com.agendaukm

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import com.agendaukm.data.SumberKegiatanPalsu
import com.agendaukm.model.Kegiatan
import com.agendaukm.model.StatusKegiatan
import com.agendaukm.model.ringkas
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.agendaukm.model.filterByStatus
import com.agendaukm.ui.KegiatanFormUiState
import com.agendaukm.ui.KegiatanFormViewModel
import kotlin.test.assertFalse
import com.agendaukm.model.UKM
import com.agendaukm.model.label

class SharedCommonTest {

    @Test
    fun testVerifikasiTugasModul2ClassDanNullSafety() {
            // 1. Data UKM
            val ukmBasket = UKM(
                id = 1,
                namaUKM = "ITERA Basketball",
                penanggungJawab = "Ucup",
                kontakPerson = "081234567890"
            )

            // 2. Data kegiatan
            val kegiatanAwal = Kegiatan (
                id = 1,
                namaKegiatan = "Latihan rutin",
                deskripsiKegiatan = null,
                tanggalKegiatan = "20 Oktober 2026",
                waktuMulai = "15.00",
                waktuSelesai = "18.00",
                ruangan = "Lapangan basket",
                ukm = ukmBasket
            )

            // 3. Pengujian copy()
            val kegiatanBaru = kegiatanAwal.copy(
                deskripsiKegiatan = "Sparing antar divisi",
                status = StatusKegiatan.Berlangsung
            )

            // 4. Verfikasi pembacaan label
            println("\nUJI LABEL STATUS")
            println("Status Awal : ${kegiatanAwal.status.label()}")
            println("Status Baru: ${kegiatanBaru.status.label()}")

            // 5. Verifikasi ringkas kegiatan
            println("\nUJI RINGKAS KEGIATAN")
            println("Kegiatan Awal: ${kegiatanAwal.ringkas()}")
            println("\nKegiatan Baru: ${kegiatanBaru.ringkas()}")

            // 6. Verifikasi null safety
            println("\nUJI NULL SAFETY")
            println("Apakah kegiatan awal bernilai null? ${kegiatanAwal.deskripsiKegiatan == null}")
            println("Apakah kegiatan baru bernilai null? ${kegiatanBaru.deskripsiKegiatan == null}")

            // 7. Verifikasi fitur copy()
            println("\nUJI COPY")
            println("Apakah objek awal dan baru sama? ${kegiatanAwal === kegiatanBaru}")
        }

    @Test
    fun testPantauKegiatanDenganFlowOperator() = runBlocking {
        // 1. Inisialisasi sumber data
        val sumber = SumberKegiatanPalsu()
        val hasilDiterima = mutableListOf<String>()

        // 2. Eksekusi Flow dari pantau(id) dengan operator filter, map, dan collect
        sumber.pantau(id = 1)
            // FILTER: Hanya ambil kegiatan yang statusnya BUKAN Mendatang (misal: Berlangsung dan Selesai)
            .filter { kegiatan ->
                kegiatan.status != StatusKegiatan.Mendatang
            }
            // MAP: Ubah objek Kegiatan menjadi String ringkasan menggunakan extension function ringkas()
            .map { kegiatan ->
                kegiatan.ringkas()
            }
            // COLLECT: Kumpulkan hasil emisi ke dalam list hasilDiterima
            .collect { ringkasanString ->
                println("Diterima dari Flow: $ringkasanString")
                hasilDiterima.add(ringkasanString)
            }

        // 3. Verifikasi (Assertion)
        // Karena emisi pertama (Mendatang) difilter, maka data yang tersisa harus berjumlah 2 (Berlangsung & Selesai)
        assertEquals(2, hasilDiterima.size)

        // Memastikan isi string hasil transformasi mengandung nama kegiatan yang sesuai
        assertTrue(hasilDiterima[0].contains("Latihan Rutin Basket"))
    }

    @Test
    fun testFilterKegiatanBerdasarkanStatus() {
        // 1. Membuat sumber data kegiatan palsu.
        val sumber = SumberKegiatanPalsu()

        // 2. Mengambil satu contoh kegiatan.
        runBlocking {
            val kegiatan = sumber.ambil(id = 1)

            // 3. Menyaring kegiatan berdasarkan status Mendatang.
            val hasil = listOf(kegiatan)
                .filterByStatus(StatusKegiatan.Mendatang)

            // 4. Memastikan hasil filter sesuai.
            assertEquals(1, hasil.size)
            assertEquals(StatusKegiatan.Mendatang, hasil[0].status)
        }
    }

    // Menguji validasi form ketika semua kolom masih kosong.
    // Form yang belum lengkap seharusnya tidak bisa disimpan.
    @Test
    fun testFormBelumLengkapTidakBisaDisimpan() {
        val state = KegiatanFormUiState()

        assertFalse(state.bisaSimpan)
    }

    // Menguji validasi form ketika seluruh kolom wajib sudah diisi.
    // Deskripsi bersifat opsional sehingga boleh dibiarkan kosong.
    @Test
    fun testFormLengkapBisaDisimpan() {
        val state = KegiatanFormUiState(
            namaKegiatan = "Latihan Rutin",
            tanggalKegiatan = "30 Oktober 2026",
            waktuMulai = "19:00 WIB",
            waktuSelesai = "21:00 WIB",
            ruangan = "GOR ITERA",
            idUKM = 1
        )

        assertEquals(true, state.bisaSimpan)
    }

    // Menguji apakah ViewModel berhasil memperbarui nama kegiatan.
    @Test
    fun testViewModelMengubahNamaKegiatan() {
        val viewModel = KegiatanFormViewModel()

        viewModel.ubahNamaKegiatan("Latihan Rutin Basket")

        assertEquals(
            "Latihan Rutin Basket",
            viewModel.uiState.value.namaKegiatan
        )
    }

    // Menguji apakah proses penyimpanan ditolak ketika form kosong.
    // Pesan validasi harus ditampilkan dan status menyimpan tetap false.
    @Test
    fun testMulaiMenyimpanMenolakFormKosong() {
        val viewModel = KegiatanFormViewModel()

        val hasil = viewModel.mulaiMenyimpan()

        assertFalse(hasil)
        assertEquals(
            "Lengkapi semua kolom wajib terlebih dahulu.",
            viewModel.uiState.value.pesan
        )
        assertEquals(false, viewModel.uiState.value.sedangMenyimpan)
    }

    // Menguji apakah proses penyimpanan dapat dimulai ketika form valid.
    // Status menyimpan menjadi true dan pesan sebelumnya dibersihkan.
    @Test
    fun testMulaiMenyimpanMenerimaFormValid() {
        val viewModel = KegiatanFormViewModel()

        viewModel.ubahNamaKegiatan("Latihan Rutin")
        viewModel.ubahTanggalKegiatan("30 Oktober 2026")
        viewModel.ubahWaktuMulai("19:00 WIB")
        viewModel.ubahWaktuSelesai("21:00 WIB")
        viewModel.ubahRuangan("GOR ITERA")
        viewModel.ubahUKM(1)

        val hasil = viewModel.mulaiMenyimpan()

        assertEquals(true, hasil)
        assertEquals(true, viewModel.uiState.value.sedangMenyimpan)
        assertEquals(null, viewModel.uiState.value.pesan)
    }

    // Menguji pengiriman kegiatan ketika penyimpanan berhasil.
    @Test
    fun testKirimBerhasilMenyimpanKegiatan() = runBlocking {
        val viewModel = KegiatanFormViewModel()
        val ukm = UKM(
            id = 1,
            namaUKM = "UKM Basket",
            penanggungJawab = "Joko",
            kontakPerson = "12345678"
        )

        // Mengisi seluruh kolom wajib pada form.
        viewModel.ubahNamaKegiatan("Latihan Rutin")
        viewModel.ubahDeskripsiKegiatan("Latihan mingguan")
        viewModel.ubahTanggalKegiatan("2026-11-01")
        viewModel.ubahWaktuMulai("09:00")
        viewModel.ubahWaktuSelesai("11:00")
        viewModel.ubahRuangan("Aula")
        viewModel.ubahUKM(1)

        // Memeriksa data kegiatan yang diserahkan untuk disimpan.
        val hasil = viewModel.kirim(12, ukm)

        // Memastikan penyimpanan berhasil dan form kembali kosong.
        assertTrue(hasil)
        assertEquals("", viewModel.uiState.value.namaKegiatan)
        assertFalse(viewModel.uiState.value.sedangMenyimpan)
    }

    // Menguji penolakan penyimpanan jika ID UKM tidak sesuai.
    @Test
    fun testKirimMenolakUKMTidakValid() = runBlocking {
        val viewModel = KegiatanFormViewModel()

        viewModel.ubahNamaKegiatan("Latihan Rutin")
        viewModel.ubahTanggalKegiatan("2026-11-01")
        viewModel.ubahWaktuMulai("09:00")
        viewModel.ubahWaktuSelesai("11:00")
        viewModel.ubahRuangan("Aula")
        viewModel.ubahUKM(1)

        val ukm = UKM(
            id = 2,
            namaUKM = "UKM Musik",
            penanggungJawab = "Rina",
            kontakPerson = "87654321"
        )

        val hasil = viewModel.kirim(12, ukm)

        assertFalse(hasil)
        assertFalse(viewModel.uiState.value.sedangMenyimpan)
        assertEquals(
            "UKM yang dipilih tidak valid.",
            viewModel.uiState.value.pesan
        )
    }

    // Menguji penanganan error ketika penyimpanan gagal.
    @Test
    fun testKirimMenanganiKegagalanPenyimpanan() = runBlocking {
        val viewModel = KegiatanFormViewModel()

        viewModel.ubahNamaKegiatan("Latihan Rutin")
        viewModel.ubahTanggalKegiatan("2026-11-01")
        viewModel.ubahWaktuMulai("09:00")
        viewModel.ubahWaktuSelesai("11:00")
        viewModel.ubahRuangan("Aula")
        viewModel.ubahUKM(1)

        // Mensimulasikan kegagalan fungsi penyimpanan jika UKM null.
        val hasil = viewModel.kirim(12, null)

        assertFalse(hasil)
        assertFalse(viewModel.uiState.value.sedangMenyimpan)
        assertEquals(
            "UKM yang dipilih tidak valid.",
            viewModel.uiState.value.pesan
        )

        // Memastikan data form tetap tersedia untuk dicoba kembali.
        assertEquals(
            "Latihan Rutin",
            viewModel.uiState.value.namaKegiatan
        )
    }
}