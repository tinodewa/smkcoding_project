package com.smkcoding.initproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_main.*

class EditProfileActivity : AppCompatActivity() {

    private var userName: String = ""
    private var userGender: String = ""
    private var userAge: String = ""
    private var userEmail: String = ""
    private var userTelp: String = ""
    private var userAddress: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        getData()

//      memberikan action click ke tombol simpan
        btnEditSave.setOnClickListener { saveData() }
    }

    private fun getData() {

//      menerima data yang dikirimkan dari ProfileActivity.kt
        val intentBundle = intent.extras

        userName = intentBundle?.getString("profileName").toString()
        userGender = intentBundle?.getString("profileGender").toString()
        userAge = intentBundle?.getString("profileAge").toString()
        userEmail = intentBundle?.getString("profileEmail").toString()
        userTelp = intentBundle?.getString("profileTelp").toString()
        userAddress = intentBundle?.getString("profileAddress").toString()

//      set edittext dengan data yang dikirimkan tadi
        editProfilName.setText(userName)
        editspinnerGender.setSelection(userGender.length)
        editProfilAge.setText(userAge)
        editProfilEmail.setText(userEmail)
        editProfilTelp.setText(userTelp)
        editProfileAddress.setText(userAddress)

    }

    private fun saveData() {
//        val namaEdit = editProfilName.text.toString()
        if (!userName.isEmpty()) {
//            jika editText namaEdit tidak kosong, maka kirimkan value nya ke ProfileActivity, dan beri tanda RESULT_OK
            val result = Intent()

            result.putExtra("editedName", editProfilName.toString())
            result.putExtra("editedGender", editspinnerGender.toString())
            result.putExtra("editedAge", editProfilAge.toString())
            result.putExtra("editedEmail", editProfilEmail.toString())
            result.putExtra("editedTelp", editProfilTelp.toString())
            result.putExtra("editedAddress", editProfileAddress.toString())
            setResult(Activity.RESULT_OK, result)
        } else {
//            jika editText NamaEdit kosong, maka kirimkan tanda RESULT_CANCELED
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }

}
