package com.smkcoding.initproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {


    companion object {
        val REQUEST_CODE = 100
    }

    private var profileName: String = ""
    private var profileGender: String = ""
    private var profileAge: String = ""
    private var profileEmail: String = ""
    private var profileTelp: String = ""
    private var profileAddress: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getData()

        btnEditProfile.setOnClickListener { navigateToEditProfile() }
        btnAbout.setOnClickListener { navigateToAbout() }
        btnEditCall.setOnClickListener { dialPhoneNumber(txtTelp.text.toString()) }
    }

    private fun getData() {

        val bundle = intent.extras

        profileName = bundle?.getString("nama").toString()
        profileGender = bundle?.getString("gender").toString()
        profileAge = bundle?.getString("age").toString()
        profileEmail = bundle?.getString("email").toString()
        profileTelp = bundle?.getString("telp").toString()
        profileAddress = bundle?.getString("alamat").toString()

        txtName.text = profileName
        txtGender.text = profileGender
        txtAge.text = profileAge
        txtEmail.text = profileEmail
        txtTelp.text = profileTelp
        txtAddress.text = profileAddress
    }

    private fun navigateToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    //  fungsi untuk berpindah ke EditProfileActivity
    private fun navigateToEditProfile() {
        val intent = Intent(this, EditProfileActivity::class.java)

        val bundleProfile = Bundle()
        bundleProfile.putString("profileName", profileName)
        bundleProfile.putString("profileGender", profileGender)
        bundleProfile.putString("profileAge", profileAge)
        bundleProfile.putString("profileEmail", profileEmail)
        bundleProfile.putString("profileTelp", profileTelp)
        bundleProfile.putString("profileAddress", profileAddress)

//        intent.putExtras(bundleProfile)

        startActivityForResult(intent, REQUEST_CODE, bundleProfile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val resultName = data?.getStringExtra("EditedName")
                val resultGender = data?.getStringExtra("EditedGender")
                val resultAge = data?.getStringExtra("editedAge")
                val resultEmail = data?.getStringExtra("editedEmail")
                val resultTelp = data?.getStringExtra("editedTelp")
                val resultAddress = data?.getStringExtra("editedAddress")

                txtName.text = resultName
                txtGender.text = resultGender
                txtAge.text = resultAge
                txtEmail.text = resultEmail
                txtTelp.text = resultTelp
                txtAddress.text = resultAddress
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
