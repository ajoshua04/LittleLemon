package com.littlelemon.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemRoom")
    fun getAllMenuItems(): LiveData<List<MenuItemRoom>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemRoom)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun isEmpty(): Boolean

    @Query("SELECT * FROM MenuItemRoom WHERE id == :id")
    fun getMenuItem(id: Int): MenuItemRoom
}

@Database(entities = [MenuItemRoom::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }

    }

}