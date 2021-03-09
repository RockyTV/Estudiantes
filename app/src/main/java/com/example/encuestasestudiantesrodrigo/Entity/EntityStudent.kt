package com.example.encuestasestudiantesrodrigo.Entity

data class EntityStudent(var name:String,
                         var lastname:String,
                         var genter:Int,
                         var levelDegree:String,
                         var reading:Boolean,
                         var sport:Boolean,
                         var travel:Boolean,
                        var financialAssistance: Boolean
) {
    constructor():this("", "", 0, "", false,false,false, false)

}