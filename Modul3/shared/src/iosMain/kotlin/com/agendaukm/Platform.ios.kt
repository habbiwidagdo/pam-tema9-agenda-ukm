package com.agendaukm

import platform.UIKit.UIDevice

//class IOSPlatform: Platform {
//    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
//}

// Implementasi fungsi namaPlatform() untuk memenuhi expect pada platform iOS
actual fun namaPlatform(): String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion