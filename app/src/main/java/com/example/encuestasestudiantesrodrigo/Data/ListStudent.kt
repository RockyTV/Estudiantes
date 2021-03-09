package com.example.encuestasestudiantesrodrigo.Data

import android.provider.SyncStateContract
import android.util.Log
import com.example.encuestasestudiantesrodrigo.Entity.EntityStudent
import com.example.encuestasestudiantesrodrigo.Tools.Constants

class ListStudent {


    fun add(student: EntityStudent):Int{
        var answer=-1
        if(!existNameFilter((student.name))){
            listStudent.add(student)
            answer=listStudent.size-1
        }
        return answer
    }
    fun delete(name:String):Boolean{
        var answer:Boolean=false
        for((index,item) in  listStudent.withIndex()){
            if(item.name==name){
                listStudent.removeAt(index)
                answer=true
                break
            }
        }
        return answer

    }
    fun edit(name: String, student: EntityStudent):Boolean{
        var answer:Boolean=false
        for((index,item) in  listStudent.withIndex()){
            if(item.name==name){
               listStudent[index].genter = student.genter
                listStudent[index].levelDegree =student.levelDegree
                listStudent[index].genter = student.genter
                listStudent[index].lastname =student.lastname
                listStudent[index].name = student.name
                listStudent[index].financialAssistance =student.financialAssistance
                listStudent[index].reading = student.reading
                listStudent[index].sport =student.sport
                listStudent[index].travel = student.travel

                answer=true
                break
            }
        }
        return answer
    }
    fun existsName(name:String): Boolean {
        var answer: Boolean = false
        for(element in listStudent){
            if(element.name == name){
                answer = true
                break
            }
        }
        return answer
    }
    fun existNameFilter(name:String):Boolean{
        var answer:Boolean=false
        if(listStudent.filter { e -> e.name==name }.count()==1){
            answer=true
        }
        return answer
    }
    fun showlistStudents(){
        Log.d(Constants.LOG_TAG, "${listStudent.size}")
        for((index,item) in  listStudent.withIndex()){
            Log.d(Constants.LOG_TAG, "$index ${listStudent[index].name} ${listStudent[index].genter}")
        }

    }
    fun getStudents(id:Int):EntityStudent{
        return listStudent[id]
    }

    fun getStringArray():Array<String>{
        val answerList= arrayListOf<String>()
        for((index, item) in listStudent.withIndex()){
            if(item.genter.equals(2)) {
                answerList.add(("${item.name} ${item.lastname} ${item.levelDegree} Masculino"))
            }
            if(item.genter.equals(1)) {
                answerList.add(("${item.name} ${item.lastname} ${item.levelDegree} Femenino"))
            }
        }
        return answerList.toTypedArray()
    }
    fun getEntityStudentArray():Array<EntityStudent>{
       return listStudent.toTypedArray()
    }
    companion object{
        private val listStudent= arrayListOf<EntityStudent>()
    }
}