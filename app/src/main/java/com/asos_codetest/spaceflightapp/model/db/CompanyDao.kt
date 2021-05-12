package com.asos_codetest.spaceflightapp.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asos_codetest.spaceflightapp.model.companyinfo.Company

/**
 * Interface to access data operation on Company Info
 * */
@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompanyInfo(companyInfo : Company)

    @Query("SELECT * FROM Company")
    fun getCompanyInfo() : LiveData<Company>?
}