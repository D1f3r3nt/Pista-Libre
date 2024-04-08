package com.example.pistalibreandroid.ui.navigation

import androidx.navigation.NavHostController

class NavigationController {
    
    companion object {
        
        private var instance: NavHostController? = null
        
        fun init(controller: NavHostController) {
            instance = controller
        }
        
        fun controller(): NavHostController { 
            return instance!!
        }
    }
}