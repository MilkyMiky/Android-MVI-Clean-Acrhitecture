package com.example.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testproject.ui.demo.DemoFragment
import com.example.testproject.ui.test.TestFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, TestFragment(), TestFragment::javaClass.name)
            .commit()

    }
}
