package com.example.kotlinpertama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etPanjang: EditText
    private lateinit var etLebar: EditText
    private lateinit var etTinggi: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPanjang = findViewById(R.id.et_panjang)
        etLebar = findViewById(R.id.et_lebar)
        etTinggi = findViewById(R.id.et_tinggi)
        btnHitung = findViewById(R.id.btn_hitung)
        tvHasil = findViewById(R.id.tv_hasil)

        btnHitung.setOnClickListener(this)

        //Implementasi perubahan orientation pada android
        if (savedInstanceState != null) {
            val hasil = savedInstanceState.getString(STATE_RESULT)
            tvHasil.setText(hasil)
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_hitung){
            val inputPanjang = etPanjang.text.toString().trim()
            val inputLebar = etLebar.text.toString().trim()
            val inputTinggi = etTinggi.text.toString().trim()

            //Cek null pada Edit Text
            var isEmptyFields = false
            when {
                inputPanjang.isEmpty() -> {
                    isEmptyFields = true
                    etPanjang.error = "Panjang harus diisi !"
                }
                inputLebar.isEmpty() -> {
                    isEmptyFields = true
                    etLebar.error = "Lebar harus diisi !"
                }
                inputTinggi.isEmpty() -> {
                    isEmptyFields = true
                    etTinggi.error = "Tinggi harus diisi !"
                }
            }

            //Implementasi jika tidak null
            if (!isEmptyFields) {
                val volume =
                    inputPanjang.toDouble() * inputLebar.toDouble() * inputTinggi.toDouble()
                tvHasil.text = volume.toString()
            }
        }
    }

    //Solusi ketika orientation berubah pada perangkat android
    companion object {
        private const val STATE_RESULT = "State Result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvHasil.text.toString())
    }

}
