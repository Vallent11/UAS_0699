package com.example.gofit.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.gofit.R
import com.example.gofit.data.local.Preferences
import com.example.gofit.databinding.ActivityHomePegawaiBinding
import com.example.gofit.databinding.FragmentChangePasswordBinding
import com.example.gofit.ui.fragment.changepassword.ChangePasswordFragment
import com.example.gofit.ui.fragment.izin.IzinFragment
import com.example.gofit.ui.fragment.jadwal.JadwalFragment
import com.example.gofit.ui.fragment.jadwalinstruktur.JadwalInstrukturFragment
import com.google.android.material.navigation.NavigationView

class HomePegawaiActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomePegawaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, JadwalInstrukturFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment : Fragment? = null
        var title = getString(R.string.app_name)
        when (item.itemId) {
            R.id.nav_home -> {
                fragment = JadwalInstrukturFragment()
                title = getString(R.string.app_name)
            }
            R.id.nav_izin -> {
                fragment = IzinFragment()
                title = getString(R.string.izin)
            }
            R.id.nav_exit -> {
                val intent = Intent(this@HomePegawaiActivity, AuthActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                val preferences = Preferences(this)
                preferences.deleteAll()
                startActivity(intent)
            }
        }
        if (fragment != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
        supportActionBar?.title = title
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}