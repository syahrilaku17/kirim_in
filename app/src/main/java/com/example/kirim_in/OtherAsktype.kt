package com.example.kirim_in

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.full_cost_type.*
import kotlinx.android.synthetic.main.other_ask_type.*

class OtherAsktype :  AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_ask_type)
        btn_upload_other.setOnClickListener {
            Toast.makeText(this, "Berhasil upload", Toast.LENGTH_SHORT).show()
        }
        btn_set_location_other.setOnClickListener {
            Toast.makeText(this, "Seting Lokasi Telah di Buat", Toast.LENGTH_SHORT).show()
        }
        btn_post_other.setOnClickListener {
            Toast.makeText(this, "Terima kasih Permintaan anda akan kami proses", Toast.LENGTH_SHORT).show()
        }
    }
}