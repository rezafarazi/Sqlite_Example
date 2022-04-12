package com.sorapp.sqlite_exmaple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{


    //Global Variables Start
    String []permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
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

    }
    //Get Init Database File End



}