package com.sorapp.sqlite_exmaple;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;

@RequiresApi(api = Build.VERSION_CODES.R)
public class MainActivity extends AppCompatActivity
{


    //Global Variables Start
    String []permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    String Main_Path= Environment.getStorageDirectory().getAbsolutePath()+"/DBTest";
    String DB_Path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/DBTest/db.sqlite";
    SQLiteDatabase DB;
    //Global Variables End


    //Main Function Start
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions,200);
        }
    }
    //Main Function End


    //Permission Request Start
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==200)
        {
            boolean permission_status=true;
            for (int a=0;a<grantResults.length;a++)
            {
                if(grantResults[a] == PackageManager.PERMISSION_DENIED)
                {
                    permission_status=false;
                    break;
                }
            }

            if(permission_status)
            {
                Get_Init_Database();
            }

        }
    }
    //Permission Request End


    //Get Init Database File Start
    public void Get_Init_Database()
    {
        File file=new File(Main_Path);
        if(!file.exists())
        {
            file.mkdirs();
            Log.i("Errt","Make Directory");
        }

        try
        {
            DB=SQLiteDatabase.openOrCreateDatabase(DB_Path,null,null);
            DB.execSQL("CREATE TABLE User (lastName TEXT,firstName TEXT);");
        }
        catch (Exception e)
        {
            Log.i("Errt",e.getMessage());
        }

    }
    //Get Init Database File End


    //Get On Click Submit Button Start
    public void Get_On_Click_Submit(View v)
    {
        EditText name=(EditText) findViewById(R.id.name);
        EditText family=(EditText) findViewById(R.id.family);

        DB.execSQL("INSERT INTO User Values('"+name.getText().toString()+"','"+family.getText().toString()+"')");

        name.setText("");
        family.setText("");

        Log.i("Errt","OK");
    }
    //Get On Click Submit Button End


}