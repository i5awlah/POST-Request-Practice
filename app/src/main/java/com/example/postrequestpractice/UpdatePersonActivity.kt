package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.postrequestpractice.databinding.ActivityUpdatePersonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePersonActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdatePersonBinding
    private var apiInterface: APIInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            updatePerson()
        }
        binding.btnDelete.setOnClickListener {
            deletePerson()
        }
    }

    private fun updatePerson() {
        val pk = binding.etPKUpdate.text.toString().toInt()
        val name = binding.etNameUpdate.text.toString()
        val location = binding.etLocationUpdate.text.toString()

        val updatePerson = PersonItem(pk,name, location)

        apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.updatePerson(pk, updatePerson)?.enqueue(object: Callback<PersonItem>{
            override fun onResponse(call: Call<PersonItem>, response: Response<PersonItem>) {
                Toast.makeText(applicationContext,"Updated successfully", Toast.LENGTH_LONG).show()
                backToMainActivity()
            }

            override fun onFailure(call: Call<PersonItem>, t: Throwable) {
                Log.d("Main","Error: $t")
            }

        })
    }

    private fun deletePerson() {
        val pk = binding.etPKUpdate.text.toString().toInt()

        apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deletePerson(pk)?.enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(applicationContext,"Deleted successfully", Toast.LENGTH_LONG).show()
                backToMainActivity()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("Main","Error: $t")
            }

        })
    }

    private fun backToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}