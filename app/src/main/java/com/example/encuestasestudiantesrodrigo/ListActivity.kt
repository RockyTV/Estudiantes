package com.example.encuestasestudiantesrodrigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.encuestasestudiantesrodrigo.Data.ListStudent
import com.example.encuestasestudiantesrodrigo.Entity.EntityStudent
import com.example.encuestasestudiantesrodrigo.Tools.Constants
import com.example.encuestasestudiantesrodrigo.databinding.ActivityDetailBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivityFormBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityListBinding
    private val listStudents= ListStudent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle((R.string.text_list_view))

        listStudents.showlistStudents()

        val adapter = ArrayAdapter<String>(this@ListActivity,android.R.layout.simple_list_item_1,listStudents.getStringArray())
        binding.ltvStudents.adapter = adapter

        binding.ltvStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val selectedItem= adapterView.getItemAtPosition(position)

            Toast.makeText(this@ListActivity,"$position $id $selectedItem", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@ListActivity,DetailActivity::class.java).apply{

                putExtra(Constants.ID,position)
            }
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@ListActivity,android.R.layout.simple_list_item_1,listStudents.getStringArray())
        binding.ltvStudents.adapter = adapter
    }

        /*binding.lvtStudents.adapter=adapter
        binding.lvtStudents.setOnItemClickListener{
            val selectedItem=adapterView.getItemAtPosition(position)
        }

    }
    override fun onRestart(){
        super.onRestart()
        val adapter.ArrayA

    }*/
}