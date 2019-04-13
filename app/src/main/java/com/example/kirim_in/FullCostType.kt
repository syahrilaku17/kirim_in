package com.example.kirim_in

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.full_cost_type.*

class FullCostType :  AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_cost_type)
        btn_upload_full.setOnClickListener {
            Toast.makeText(this, "Berhasil upload", Toast.LENGTH_SHORT).show()
        }
        btn_set_location_full.setOnClickListener {
            val intent = Intent(this,MapsActivity::class.java)
            startActivity(intent)        }
        btn_post_full.setOnClickListener {
            Toast.makeText(this, "Terima kasih Permintaan anda akan kami proses", Toast.LENGTH_SHORT).show()
        }
    }
}