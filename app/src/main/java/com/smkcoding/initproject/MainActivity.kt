package com.smkcoding.initproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var namaInput: String = ""
    private var emailInput: String = ""
    private var telpInput: String = ""
    private var alamatInput: String = ""
    private var genderInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener { validasiInput() }
        setDataSpinnerGender()
    }

    //    fungsi untuk pindah ke halaman profil
    private fun gotoProfilActivity() {
        val intent = Intent(this, ProfilActivity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)

        intent.putExtras(bundle)

        startActivity(intent)
    }

    //    fungsi untuk melakukan validasi inputan
    private fun validasiInput() {
        namaInput = editName.text.toString()
        emailInput = editEMail.text.toString()
        telpInput = editTelp.text.toString()
        alamatInput = editAddress.text.toString()
        genderInput = spinnerGender.selectedItem.toString()

        when {
//            check di tiap inputan apakah kosong atau tidak, jika kosong maka tampil error
            namaInput.isEmpty() -> editName.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih Jenis Kelamin", ignoreCase = true) ->
                tampilToast("Jenis kelamin harus dipilih")
            emailInput.isEmpty() -> editEMail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> editTelp.error = "Telpon tidak boleh kosong"
            alamatInput.isEmpty() -> editAddress.error = "Alamat tidak boleh kosong"

            else -> {
//                jika semua inputan diisi, jalankan intent
                tampilToast("Data berhasil disimpan")
                gotoProfilActivity()
            }
        }
    }

    //    fungsi menampilkan toast
    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //    fungsi spinner gender
    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.jenis_kelamin, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter
    }
}
