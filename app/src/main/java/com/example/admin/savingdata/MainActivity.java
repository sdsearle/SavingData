package com.example.admin.savingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {

    private static final String MY_PREF_FILE = "com.example.admin.savingdata.mypreffile";
    EditText etSaveData, etName, etPhone;
    TextView tvRetrieveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSaveData = (EditText) findViewById(R.id.etText);
        tvRetrieveData = (TextView) findViewById(R.id.tvRetriveData);
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);

    }

    public void usingSharedPref(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);

        switch (view.getId()) {
            case R.id.btnSaveData:

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("keyData",etSaveData.getText().toString());
                boolean isSaved = editor.commit();
                if(isSaved) {
                    Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(this, "Data NOT saved", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnRetriveData:

                String retrievedData = sharedPreferences.getString("keyData", "default");
                tvRetrieveData.setText(retrievedData);

                break;
        }
    }

    public void usingSQLite(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        switch (view.getId()){
            case R.id.btnSaveDataSQL:
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                long rownum = databaseHelper.saveNewContact(name,phone);
                Toast.makeText(this, "Data Saved at Row " + rownum, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRetriveDataSQL:

                databaseHelper.getContacts();
                break;
        }
    }
}
