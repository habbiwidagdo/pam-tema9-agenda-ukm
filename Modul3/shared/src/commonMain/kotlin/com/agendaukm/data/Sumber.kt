// commonMain/data/Sumber.kt
package com.agendaukm.data

import kotlinx.coroutines.flow.Flow

interface Sumber<T>{
    suspend fun ambil(id: Int): T
    fun pantau(id: Int): Flow<T>
}