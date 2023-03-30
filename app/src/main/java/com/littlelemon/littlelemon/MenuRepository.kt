package com.littlelemon.littlelemon

import androidx.lifecycle.MutableLiveData

class MenuRepository(private val menuItemDao: MenuItemDao) {

    val menuItems = MutableLiveData<List<MenuItemRoom>>()

    fun saveMenuToDatabase(menuItems: List<MenuItem>) {
        val menuItemsRoom = menuItems.map { it.toMenuItemRoom() }
        menuItemDao.insertAll(*menuItemsRoom.toTypedArray())
    }

    fun fetchMenuItem(id: Int): MenuItemRoom {
        return menuItemDao.getMenuItem(id)
    }

}