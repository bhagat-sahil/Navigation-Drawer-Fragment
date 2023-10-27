package com.example.navdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(

            this,
            drawerLayout,
            R.string.Open,
            R.string.Close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView : NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            it.isChecked = true

            when ( it.itemId ){
                R.id.hpage ->{
                    replaceFragment( HomePage(), it.title.toString() )
                }
                R.id.mpage ->{
                    replaceFragment( MessagePage(), it.title.toString() )
                }
                R.id.spage ->{
                    replaceFragment( SettingPage(), it.title.toString() )
                }
                R.id.login ->{
                    replaceFragment( LoginPage(), it.title.toString() )
                }
            }
            true
        }


    }

    private fun  replaceFragment( fraagment : Fragment , title : String )
    {
        val fragManager = supportFragmentManager
        val fragTrans = fragManager.beginTransaction()
        fragTrans.replace(R.id.frame1,fraagment)
        fragTrans.commit()

        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( toggle.onOptionsItemSelected(item) ){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}