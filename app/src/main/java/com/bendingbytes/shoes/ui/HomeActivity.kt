package com.bendingbytes.shoes.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bendingbytes.shoes.R
import com.bendingbytes.shoes.ui.fragments.FavouritesFragment
import com.bendingbytes.shoes.ui.fragments.ListFragments
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val favouritesFragment = FavouritesFragment.newInstance()
    private val listFragment = ListFragments.newInstance()
    private var currentFragment: Fragment = listFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("HomeActivity", "Bbytes onCreate")
        //bottom navigation
        initiateAllFragments()
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuItemList -> showFragment(listFragment)
                R.id.menuItemFavorites -> showFragment(favouritesFragment)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            hide(currentFragment)
            show(fragment)
            currentFragment = fragment
            commit()
        }
    }

    private fun initiateAllFragments() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, listFragment)
            add(R.id.fragmentContainer, favouritesFragment).hide(favouritesFragment)
            commit()
        }
    }
}