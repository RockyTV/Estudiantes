package com.example.encuestasestudiantesrodrigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.encuestasestudiantesrodrigo.Data.ListStudent
import com.example.encuestasestudiantesrodrigo.Entity.EntityStudent
import com.example.encuestasestudiantesrodrigo.R.menu
import com.example.encuestasestudiantesrodrigo.Tools.Constants
import com.example.encuestasestudiantesrodrigo.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar


class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private var listStudent = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle((R.string.text_home))

        /*var student=EntityStudent("Rod","Tab", 1,"Prepa", true, true, true )
        listStudent.add(student)
        listStudent.existsName(student.name)
        listStudent.showlistStudents()
        Log.d(Constants.LOG_TAG,)*/

        binding.btnOk.setOnClickListener(){
           if(binding.edtName.text.isNotEmpty() && binding.edtLastName.text.isNotEmpty()) {
               if(binding.Trunco.isChecked() || binding.Pasante.isChecked() || binding.Titulado.isChecked()) {
                   val genderText:String=binding.spinnerGender.selectedItem.toString()
                   if(genderText.equals("Masculino") || genderText.equals("Femenino")) {


                       val student = EntityStudent()
                       student.name = binding.edtName.text.toString()
                       student.lastname = binding.edtLastName.text.toString()
                       student.genter = binding.spinnerGender.selectedItemPosition
                       //val genderText:String=binding.spinnerGender.selectedItem.toString()
                       when (binding.rdDegree.checkedRadioButtonId) {
                           binding.Trunco.id -> {
                               student.levelDegree = "Trunco"
                           }
                           binding.Pasante.id -> {
                               student.levelDegree = "Pasante"
                           }
                           binding.Titulado.id -> {
                               student.levelDegree = "Titulado"
                           }
                       }
                       student.sport = binding.ckSport.isChecked
                       student.reading = binding.ckRead.isChecked
                       student.travel = binding.ckTravel.isChecked
                       student.financialAssistance = binding.switchFinancialAssistance.isChecked
                       val index = listStudent.add(student)
                       if (index >= 0) {
                           Toast.makeText(this@FormActivity, "Estudiante Guardado", Toast.LENGTH_SHORT).show()
                           binding.edtName.setText("")
                           binding.edtLastName.setText("")
                           binding.Trunco.isChecked=false
                           binding.Pasante.isChecked=false
                           binding.Titulado.isChecked=false
                           binding.ckRead.isChecked=false
                           binding.ckSport.isChecked=false
                           binding.ckTravel.isChecked=false
                           binding.switchFinancialAssistance.isChecked=false
                       } else {
                           Snackbar.make(it, "Estudiante no guardado", Snackbar.LENGTH_SHORT).show()
                       }
                   }else{
                       Snackbar.make(it, "Favor de seleccionar un genero", Snackbar.LENGTH_SHORT).show()
                   }
               }else{
                   Snackbar.make(it, "Favor de seleccionarun estado academico", Snackbar.LENGTH_SHORT).show()
               }
           }
            else{
               Snackbar.make(it, "Nombre y Apellidos obligatorios", Snackbar.LENGTH_SHORT).show()
           }

        }
        binding.spinnerGender.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@FormActivity, "Estoy en evento NothingSelected", Toast.LENGTH_SHORT).show()
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedText= parent?.getItemAtPosition(position)
                Toast.makeText(this@FormActivity, "Estoy en evento ItemSelected $position $selectedText", Toast.LENGTH_SHORT).show()

            }
        }
        binding.switchFinancialAssistance.setOnCheckedChangeListener{it, isChecked->
            val checked=if(isChecked) "on" else "off"
            Toast.makeText(this@FormActivity, "Estoy en evento ICheckedChangeListener", Toast.LENGTH_SHORT).show()

        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itmList->{
                val intent = Intent(this@FormActivity,ListActivity::class.java)
                startActivity(intent)
            }
            R.id.itmForm->{
                Toast.makeText(this@FormActivity,"estas en form", Toast.LENGTH_SHORT).show()

            }
            R.id.itmEditarEliminar2->{
               val intent=Intent(this@FormActivity,ListEditDeleteActivity::class.java)
                startActivity(intent)
            }

            R.id.itmExit->{
                finish()

            }
        }


        return super.onOptionsItemSelected(item)
    }

}