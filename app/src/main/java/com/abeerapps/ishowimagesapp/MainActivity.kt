package com.abeerapps.ishowimagesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

const val ACCESS_KEY = "Eu8uR5s4lTHibnAsVFaMdg77XCEWwahDOKIftc4PaGA"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mNavHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val mController = mNavHost.navController
    }
}