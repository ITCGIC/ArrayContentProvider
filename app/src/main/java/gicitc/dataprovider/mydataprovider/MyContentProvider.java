package gicitc.dataprovider.mydataprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    public static final String authorities="edu.gicitc.dataprovider";
    public static final String URL="content://"+authorities+"/users";
    public static final Uri URI = Uri.parse(URL);
    private static User[]users;
    @Override
    public boolean onCreate() {
        users = new User[3];
        users[0] = new User(1,"U1",10);
        users[1] = new User(2,"U r 2",20);
        users[2] = new User(3,"U3",30);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int index = Integer.parseInt(uri.getLastPathSegment());
        MatrixCursor cursor = new MatrixCursor(new String[]{"id","name","age"});
        cursor.addRow(new Object[]{users[index].id,users[index].name,users[index].age});
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.item/vnd.edu.gicitc.dataprovider.student";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
