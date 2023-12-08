package com.demo.app

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.demo.app.databinding.ActivityMainBinding
import me.reezy.cosmo.loading.SimpleLoadingView


class MainActivity : AppCompatActivity() {


    private val binding by lazy { ActivityMainBinding.bind(findViewById<ViewGroup>(android.R.id.content).getChildAt(0)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.icons.children.forEach {
            if (it is SimpleLoadingView) {
                it.start()
            }
        }

        binding.dcb1.start()
        binding.dcb2.start()
//        binding.dcb1.setProgress(0f)
//        binding.dcb2.setProgress(1f)

    }


}
