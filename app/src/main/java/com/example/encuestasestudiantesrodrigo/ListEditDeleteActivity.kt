package com.example.encuestasestudiantesrodrigo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestasestudiantesrodrigo.Data.ListStudent
import com.example.encuestasestudiantesrodrigo.Tools.Constants
import com.example.encuestasestudiantesrodrigo.databinding.ActivityDetailBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivityListBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivityListEditDeleteBinding

class ListEditDeleteActivity: AppCompatActivity() {
    private  lateinit var binding: ActivityListEditDeleteBinding
    private val listStudents= ListStudent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListEditDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle((R.string.text_Edit_Delete))

        listStudents.showlistStudents()
        val adapter = ArrayAdapter<String>(this@ListEditDeleteActivity, android.R.layout.simple_list_item_1, listStudents.getStringArray())
        binding.listEdit.adapter = adapter

        binding.listEdit.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val builder = AlertDialog.Builder(this)
            builder.setTitle("¿Que quieres hacer?")



            builder.setPositiveButton("Editar") { dialog, which ->
                /*val selectedItem = adapterView.getItemAtPosition(position)

                Toast.makeText(this@ListEditDeleteActivity, "$position $id $selectedItem", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@ListEditDeleteActivity, EditActivity::class.java).apply {

                    putExtra(Constants.ID, position)
                }*/

                val intent=Intent(this@ListEditDeleteActivity,EditActivity::class.java)
                startActivity(intent)
            }

            builder.setNegativeButton("Eliminar") { dialog, which ->
                val id:Int=intent.getIntExtra(Constants.ID, -1)
                val student = listStudents.getStudents(id)
                listStudents.delete(student.name)
            }


            builder.show()

        }
    }
}