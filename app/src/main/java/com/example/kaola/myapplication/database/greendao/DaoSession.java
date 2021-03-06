package com.example.kaola.myapplication.database.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.kaola.myapplication.database.DataReportData;

import com.example.kaola.myapplication.database.greendao.DataReportDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dataReportDataDaoConfig;

    private final DataReportDataDao dataReportDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dataReportDataDaoConfig = daoConfigMap.get(DataReportDataDao.class).clone();
        dataReportDataDaoConfig.initIdentityScope(type);

        dataReportDataDao = new DataReportDataDao(dataReportDataDaoConfig, this);

        registerDao(DataReportData.class, dataReportDataDao);
    }
    
    public void clear() {
        dataReportDataDaoConfig.clearIdentityScope();
    }

    public DataReportDataDao getDataReportDataDao() {
        return dataReportDataDao;
    }

}
