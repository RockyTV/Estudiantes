package com.example.encuestasestudiantesrodrigo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.encuestasestudiantesrodrigo.Data.ListStudent
import com.example.encuestasestudiantesrodrigo.Tools.Constants
import com.example.encuestasestudiantesrodrigo.databinding.ActivityDetailBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivityFormBinding

class DetailActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetailBinding
    private val listStudents= ListStudent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle((R.string.text_detail))

        val id:Int=intent.getIntExtra(Constants.ID, -1)
        if (id!=-1)
        {
            val student = listStudents.getStudents(id)

            binding.txvFullName.text = "${student.name} ${student.lastname}"
            binding.txvGender.text = "${  if (student.genter==1) "Masculino" else if (student.genter==2) "Femenino" else "Genero no seleccionado"} "
            binding.txvDegree.text = "${student.levelDegree}"
            binding.txvFinancialAssistance.text = "${if(student.financialAssistance) "Con beca" else "Sin beca"}"
            binding.txvHobbies.text = "Pasatiempos: ${if(student.reading)"Leer" else ""} ${if(student.travel)"Viajar" else ""} ${if(student.sport)"Deportes" else ""}"

            binding.btnDelete.setOnClickListener{
                if(listStudents.delete(student.name))
                {
                    Toast.makeText(this@DetailActivity,"Elemento eliminado", Toast.LENGTH_SHORT).show()
                    //finish()
                    super.onBackPressed()
                }
                else {
                    Toast.makeText(this@DetailActivity,"Elemento No eliminado", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            Toast.makeText(this@DetailActivity,"Se genero un problema al intentar cargar esta pagina", Toast.LENGTH_SHORT).show()
            //finish()
            super.onBackPressed()
        }
    }
}