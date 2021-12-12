package com.example.postrequestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import com.example.postrequestpractice.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myRV: RecyclerView
    lateinit var myAdapter: InfoAdapter

    private var persons = arrayListOf<PersonItem>()
    private var apiInterface: APIInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()
        fetchData()
        binding.btnAdd.setOnClickListener {
            addNewPerson()
        }

    }
    private fun setupRV() {
        myRV = binding.rvPerson
        myAdapter = InfoAdapter(persons)
        myRV.adapter = myAdapter
        myRV.layoutManager = LinearLayoutManager(this)
    }
    private fun fetchData() {
        apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.getPerson()?.enqueue(object: Callback<Person>{
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                val data = response.body()!!
                for (i in 0 until data.size) {
                    val name = data[i].name
                    val location = data[i].location
                    val personItem = PersonItem(name, location)
                    persons.add(personItem)
                }
                Log.d("Main","Data fetched correctly!")
                myAdapter.notifyDataSetChanged()
                myRV.scrollToPosition(persons.size - 1)
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Log.d("Main","An error occurred while fetching data")
            }

        })
    }

    private fun addNewPerson() {
        val name = binding.etName.text.toString()
        val location = binding.etLocation.text.toString()

        binding.etName.text.clear()
        binding.etLocation.text.clear()

        addPerson(PersonItem(name, location))

        myAdapter.notifyDataSetChanged()
        myRV.scrollToPosition(persons.size - 1)

    }

    private fun addPerson(newPerson: PersonItem) {
        persons.add(newPerson)
        apiInterface?.addPerson(newPerson)?.enqueue(object: Callback<PersonItem> {
            override fun onResponse(call: Call<PersonItem>, response: Response<PersonItem>) {
                Toast.makeText(applicationContext,"Added successfully",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<PersonItem>, t: Throwable) {
                Log.d("Main","Error: $t")
            }

        })
    }
}