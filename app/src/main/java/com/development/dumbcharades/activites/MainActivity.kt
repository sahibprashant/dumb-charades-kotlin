package com.development.dumbcharades.activites

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.development.dumbcharades.R
import com.development.dumbcharades.databinding.ActivityMainBinding

@Suppress("UNUSED_VARIABLE")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
    }

}
