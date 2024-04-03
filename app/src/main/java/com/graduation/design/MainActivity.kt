package com.graduation.design

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.graduation.design.databinding.ActivityMainBinding
import com.graduation.design.domian.navigator.FixFragmentNavigator
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //沉浸状态栏
        QMUIStatusBarHelper.translucent(this)
        //黑色状态栏文字
        QMUIStatusBarHelper.setStatusBarLightMode(this)

        initView()

    }

    private fun initView() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //应用自定义Navigator
        //1. 创建自定义navigator
        val navigator =
            FixFragmentNavigator(this, supportFragmentManager, R.id.nav_host_fragment_activity_main)
        //2. 添加navigator
        navController.navigatorProvider.addNavigator(navigator)
        //3. 设置图表
        navController.setGraph(R.navigation.mobile_navigation)

        navView.setupWithNavController(navController)
    }
}