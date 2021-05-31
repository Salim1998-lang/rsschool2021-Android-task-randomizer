package com.rsschool.android2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.android2021.FirstFragment.Companion.newInstance

class MainActivity : AppCompatActivity(), FirstFragmentCommunicate, SecondFragmentCommunicate{
    private lateinit var onBackClickInterface: OnBackClickInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFirstFragment(0)
    }

    private fun openFirstFragment(previousNumber: Int) {
        val secondFragment: Fragment = newInstance(previousNumber)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment)
        transaction.commit()
    }

    private fun openSecondFragment(min: Int, max: Int) {
        val secondFragment: Fragment = SecondFragment.newInstance(min, max)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment)
        transaction.commit()
    }

    override fun secondFragment(min: Int, max: Int) {
        openSecondFragment(min, max)
    }

    override fun firstFragment(previousResult: Int) {
        openFirstFragment(previousResult)
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        val list = fm.fragments.toList()
        val backPressedListener: Fragment? = list.find { it is OnBackClickInterface }
        if (backPressedListener is OnBackClickInterface) {
            backPressedListener.onBackClicked()
        } else {
            super.onBackPressed()
        }
    }
}