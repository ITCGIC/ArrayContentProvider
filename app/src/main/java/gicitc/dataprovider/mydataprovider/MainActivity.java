package gicitc.dataprovider.mydataprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void retrieveUser(View view) {
        Cursor cursor = getContentResolver().query(Uri.parse("content://edu.gicitc.dataprovider/users/1"),
                null,null,null,"ASC");
        if(cursor != null && cursor.moveToFirst()){
            Toast.makeText(this, ""+cursor.getString(1), Toast.LENGTH_LONG).show();
        }

    }
}
