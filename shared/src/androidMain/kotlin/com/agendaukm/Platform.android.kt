package com.agendaukm

import android.os.Build

//class AndroidPlatform : Platform {
//    override val name: String = "Android ${Build.VERSION.SDK_INT}"
//}
//
// Implementasi fungsi namaPlatform() untuk memenuhi expect pada platform Android
actual fun namaPlatform(): String = "Android ${Build.VERSION.SDK_INT}"