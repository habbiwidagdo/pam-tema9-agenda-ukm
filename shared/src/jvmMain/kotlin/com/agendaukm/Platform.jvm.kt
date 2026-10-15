package com.agendaukm

//class JVMPlatform: Platform {
//    override val name: String = "Java ${System.getProperty("java.version")}"
//}

// Implementasi fungsi namaPlatform() untuk memenuhi expect pada platform Desktop
actual fun namaPlatform(): String = "Desktop JVM"
