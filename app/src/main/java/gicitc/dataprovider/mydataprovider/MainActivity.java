package gicitc.dataprovider.mydataprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.s_user_ids);
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://edu.gicitc.dataprovider/users"),
                null,null,null,"ASC");
        List<Integer> ids = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                ids.add(cursor.getInt(0));
            }while (cursor.moveToNext());
        }
        SpinnerAdapter adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_dropdown_item_1line,android.R.id.text1,ids);
        spinner.setAdapter(adapter);
    }

    public void retrieveUser(View view) {
        Spinner spinner = findViewById(R.id.s_user_ids);
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://edu.gicitc.dataprovider/users/"+spinner.getSelectedItem()),
                null,null,null,"ASC");
        if(cursor != null && cursor.moveToFirst()){
            Toast.makeText(this, ""+cursor.getString(1), Toast.LENGTH_LONG).show();
            EditText etName = findViewById(R.id.et_name);
            EditText etAge = findViewById(R.id.et_age);
            etName.setText(cursor.getString(1));
            etAge.setText(cursor.getString(2));
        }

    }

    public void doInsert(View view) {
        EditText etName = findViewById(R.id.et_name);
        EditText etAge = findViewById(R.id.et_age);
        ContentValues values = new ContentValues();
        values.put("name",etName.getText().toString());
        values.put("age",etAge.getText().toString());
        Uri result = getContentResolver().insert(
                Uri.parse("content://edu.gicitc.dataprovider/users"), values);
        Toast.makeText(this,result.toString(),Toast.LENGTH_LONG).show();
    }

    public void doUpdate(View view) {
        Spinner spinner = findViewById(R.id.s_user_ids);
        EditText etName = findViewById(R.id.et_name);
        EditText etAge = findViewById(R.id.et_age);
        ContentValues values = new ContentValues();
        values.put("name",etName.getText().toString());
        values.put("age",etAge.getText().toString());
        int result = getContentResolver().update(
                Uri.parse("content://edu.gicitc.dataprovider/users"),
                values,"id=?",new String[]{""+spinner.getSelectedItem()}
        );
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }
}
