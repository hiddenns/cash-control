package com.cashcontrol.domain.datasource

import com.cashcontrol.core.database.AppDatabase
import com.cashcontrol.domain.base.BaseDataSource

class LocalDatasource(
   private val database: AppDatabase
): BaseDataSource() {

    init {
        database.openHelper.writableDatabase
    }


}