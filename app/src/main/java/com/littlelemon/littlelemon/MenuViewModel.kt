package com.littlelemon.littlelemon

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val menuRepository: MenuRepository) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    suspend fun fetchMenuItem(id: Int): MenuItemRoom = coroutineScope.async(Dispatchers.IO) {
        return@async menuRepository.fetchMenuItem(id)
    }.await()

}