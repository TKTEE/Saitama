package com.example.saitama

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var btnSms:Button
    lateinit var btnEmail:Button
    lateinit var btnCamera: Button
    lateinit var btnShare:Button
    lateinit var btnPesa:Button
    lateinit var btnCall:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         btnSms = findViewById<Button>(R.id.mBtnsms)
         btnEmail = findViewById<Button>(R.id.mBtnemail)
        btnCamera = findViewById<Button>(R.id.mBtncamera)
        btnShare = findViewById<Button>(R.id.mBtnshare)
        btnPesa = findViewById<Button>(R.id.mBtnpesa)
        btnCall = findViewById<Button>(R.id.mBtncall)

        btnSms.setOnClickListener {
            val uri = Uri.parse("smsto:YOUR_SMS_NUMBER")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello there......")
            startActivity(intent)
        }
        btnEmail.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir.....")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        btnCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)
        }
        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, watch any anime on zoro.to!")
            startActivity(shareIntent)
        }
        btnPesa.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }

        }
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254 798450329"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }
        }
    }
}