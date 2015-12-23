package com.example.jimit.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String MY_PREFS = "my_prefs";

    private EditText txtKey, txtValue;
    private Button btnInsert, btnShow, btnShowOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtKey = (EditText) findViewById(R.id.txt_key);
        txtValue = (EditText) findViewById(R.id.txt_value);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnShow = (Button) findViewById(R.id.btn_show);
        btnShowOne = (Button) findViewById(R.id.btn_show_one);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtKey.getText().toString())
                        && !TextUtils.isEmpty(txtValue.getText().toString())) {

                    SharedPreferences preferences = getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(txtKey.getText().toString(), txtValue.getText().toString());
                    editor.commit();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
                Map map = preferences.getAll();
                Iterator entries = map.entrySet().iterator();
                if (entries.hasNext()) {
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        Log.d(TAG, "onClick show: key=" + entry.getKey() + ", value=" + entry.getValue());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Nothing saved yet in Shared Preferences", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShowOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtKey.getText().toString())) {
                    SharedPreferences preferences = getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
                    String value = preferences.getString(txtKey.getText().toString(), "Key not found");
                    Toast.makeText(getApplicationContext(), "Key=" + txtKey.getText().toString() + ", value=" + value,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please provide key", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
