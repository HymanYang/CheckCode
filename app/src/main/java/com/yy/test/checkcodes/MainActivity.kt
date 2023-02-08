package com.yy.test.checkcodes

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

const val REQUEST_WRITE_EXTERNAL_STORAGE = 1


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.content).setOnClickListener {
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_WRITE_EXTERNAL_STORAGE)
//            }else{
//                Toast.makeText(this,"已申请权限",Toast.LENGTH_SHORT).show()
//            }
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"已申请权限",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"申请权限失败",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}