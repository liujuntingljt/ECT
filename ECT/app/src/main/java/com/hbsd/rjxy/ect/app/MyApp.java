package com.hbsd.rjxy.ect.app;

import android.app.Application;

import com.hbsd.rjxy.ect.dao.DaoMaster;
import com.hbsd.rjxy.ect.dao.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * APP入口
 */
public class MyApp extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "word_db" );
        Database db = helper.getReadableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){
        return  daoSession;
    }
}
