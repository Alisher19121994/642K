package com.example.a642k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var save_btn: Button
    lateinit var delete_btn: Button

    val isPersistent = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        editText = findViewById(R.id.et_btn)
        save_btn = findViewById(R.id.save_file_btn)
        delete_btn = findViewById(R.id.delete_file_btn)

        save_btn.setOnClickListener {
            val text = editText.text.toString().trim()
            createFile(text)
        }

        delete_btn.setOnClickListener {
            val text = editText.text.toString().trim()
            deleteThisFile(text)
        }
    }

    private fun createFile(text: String) {
        val fileName = "$text.txt"
        val file: File = if (isPersistent) {
            File(getExternalFilesDir(null), fileName)
        } else {
            File(externalCacheDir, fileName)
        }
        try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(text.toByteArray(Charset.forName("UTF-8")))

            Toast.makeText(this, "File created", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Toast.makeText(this, "File NOT created", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteThisFile(text: String) {
        val fileName = "$text.txt"
        val file = File(getExternalFilesDir(null),fileName)
        if (file.exists()){
            file.delete()
            Toast.makeText(this, "File deleted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "File NOT deleted", Toast.LENGTH_LONG).show()
        }
    }
}