package gicitc.dataprovider.mydataprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {
    private static final String DB_NAME="test.db";
    private static final int DB_VERSION=1;
    public DbManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create schema
        db.execSQL("CREATE TABLE `users` (" +
                "`ID`	INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`name`	TEXT," +
                "`age`	INTEGER NOT NULL DEFAULT 0);" );
        db.execSQL("insert into users(name,age) values('U1',12),('U2',13),('U3',15);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
