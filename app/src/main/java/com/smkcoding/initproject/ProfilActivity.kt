package com.smkcoding.initproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {


    companion object {
        val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        ambilData()

        btnEditName.setOnClickListener { navigasiKeEditProfil() }

        btnEditCall.setOnClickListener { dialPhoneNumber(txtTelp.text.toString()) }
    }

    private fun ambilData() {
        val bundle = intent.extras

        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val alamat = bundle?.getString("alamat")

        txtName.text = nama
        txtGender.text = gender
        txtEmail.text = email
        txtTelp.text = telp
        txtAddress.text = alamat

    }

    //  fungsi untuk berpindah ke EditProfilActivity
    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)

        val namaUser = txtName.text.toString()
        intent.putExtra("nama", namaUser)

        startActivityForResult(intent, REQUEST_CODE)
        btnEditCall.setOnClickListener { dialPhoneNumber((txtTelp.text.toString())) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("nama")
                txtName.text = result
            } else {
                Toast.makeText(
                    this, "Edit Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
