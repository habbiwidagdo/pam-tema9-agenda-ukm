package com.agendaukm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.agendaukm.model.StatusKegiatan

// Komponen UI untuk menampilkan status kegiatan dalam bentuk label.
@Composable
fun LabelStatus(status: StatusKegiatan) {

    // Menentukan warna latar dan warna teks berdasarkan status kegiatan.
    val (warnaLatar, warnaTeks) = when (status) {
        // Biru untuk kegiatan yang belum dimulai.
        StatusKegiatan.Mendatang ->
            Color(0xFFDBEAFE) to Color(0xFF1D4ED8)

        // Hijau untuk kegiatan yang sedang berlangsung.
        StatusKegiatan.Berlangsung ->
            Color(0xFFDCFCE7) to Color(0xFF15803D)

        // Kuning untuk kegiatan yang telah selesai.
        StatusKegiatan.Selesai ->
            Color(0xFFFEF3C7) to Color(0xFFB45309)
    }

    // Menampilkan nama status dengan latar berwarna dan sudut membulat.
    Text(
        text = when (status) {
            StatusKegiatan.Mendatang -> "Mendatang"
            StatusKegiatan.Berlangsung -> "Berlangsung"
            StatusKegiatan.Selesai -> "Selesai"
        },
        modifier = Modifier
            .background(
                color = warnaLatar,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        color = warnaTeks
    )
}
