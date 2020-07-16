package com.example.clean.presentation.landing

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import com.example.clean.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout, toolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            toolBar.title = destination.label
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            backPress()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_library -> {
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.libraryFragment
                )
            }

            R.id.nav_pdf_reader -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.readerFragment)
            }
        }

        drawer_layout.closeDrawers()
        return true
    }

    private fun backPress() {
        if (findNavController(R.id.nav_host_fragment).currentDestination?.id == R.id.libraryFragment) {
            finish()
            return
        }

        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.nav_host_fragment).navigateUpOrFinish(this)

    private fun NavController.navigateUpOrFinish(activity: AppCompatActivity): Boolean {
        return if (navigateUp()) {
            true
        } else {
            activity.finish()
            true
        }
    }
}