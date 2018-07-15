package me.portfolio.aungkhanthtoo.healthcare.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo

@Dao
interface HealthDao : BaseDao<HealthcareInfo> {

    @Query("SELECT * FROM health_info")
    fun getHealthInfoLiveData(): LiveData<List<HealthcareInfo>>

    @Query("SELECT * FROM health_info")
    fun getHealthInfo(): List<HealthcareInfo>

}