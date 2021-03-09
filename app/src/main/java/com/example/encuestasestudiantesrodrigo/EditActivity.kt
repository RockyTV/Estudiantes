package com.example.encuestasestudiantesrodrigo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestasestudiantesrodrigo.Data.ListStudent
import com.example.encuestasestudiantesrodrigo.Entity.EntityStudent
import com.example.encuestasestudiantesrodrigo.Tools.Constants
import com.example.encuestasestudiantesrodrigo.databinding.ActivityEditBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar

class EditActivity:AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private var listStudents = ListStudent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle((R.string.text_Edit))

        val id:Int=intent.getIntExtra(Constants.ID, -1)
        if (id!=-1)
        {
            val student = listStudents.getStudents(id)

            binding.edtNameE.setText("${student.name}")
            binding.edtLastNameE.setText("${student.lastname}")
            binding.spinnerGenderE.setPromptId(student.genter)
            if(student.travel==true){
                binding.ckTravel.isChecked
            }
            if(student.sport==true){
                binding.ckSportE.isChecked
            }
            if(student.reading==true){
                binding.ckReadE.isChecked
            }
            if(student.levelDegree.equals("Trunco")){
                binding.TruncoE.isChecked
            }
            if(student.levelDegree.equals("Pasante")){
                binding.TruncoE.isChecked
            }
            if(student.levelDegree.equals("Titulado")){
                binding.TruncoE.isChecked
            }
            if(student.financialAssistance==true){
                binding.switchFinancialAssistanceE.isChecked
            }


            binding.btnOkE.setOnClickListener{
                if(binding.edtNameE.text.isNotEmpty() && binding.edtLastNameE.text.isNotEmpty()) {
                    if(binding.TruncoE.isChecked() || binding.PasanteE.isChecked() || binding.TituladoE.isChecked()) {
                        val genderText:String=binding.spinnerGenderE.selectedItem.toString()
                        if(genderText.equals("Masculino") || genderText.equals("Femenino")) {


                            val student = EntityStudent()
                            student.name = binding.edtNameE.text.toString()
                            student.lastname = binding.edtLastNameE.text.toString()
                            student.genter = binding.spinnerGenderE.selectedItemPosition
                            //val genderText:String=binding.spinnerGender.selectedItem.toString()
                            when (binding.rdDegreeE.checkedRadioButtonId) {
                                binding.TruncoE.id -> {
                                    student.levelDegree = "Trunco"
                                }
                                binding.PasanteE.id -> {
                                    student.levelDegree = "Pasante"
                                }
                                binding.TituladoE.id -> {
                                    student.levelDegree = "Titulado"
                                }
                            }
                            student.sport = binding.ckSportE.isChecked
                            student.reading = binding.ckReadE.isChecked
                            student.travel = binding.ckTravel.isChecked
                            student.financialAssistance = binding.switchFinancialAssistanceE.isChecked
                            val index = listStudents.edit(student.name, student)
                            if (index == true) {
                                val intent= Intent(this@EditActivity,ListEditDeleteActivity::class.java)
                                startActivity(intent)
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
        }
        else
        {
            Toast.makeText(this@EditActivity,"Se genero un problema al intentar cargar esta pagina", Toast.LENGTH_SHORT).show()
            //finish()
            super.onBackPressed()
        }
    }
}