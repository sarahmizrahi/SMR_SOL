package com.example.mizrahi_sarahut4p3

sealed class Routes(val route:String) {
    object Pantalla1:Routes("pantalla1")
    object Pantalla2:Routes("pantalla2")
}