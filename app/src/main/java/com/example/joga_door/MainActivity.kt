package com.example.joga_door

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.joga_door.databinding.ActivityMainBinding
import com.example.joga_door.fragments.LogInFragment
import com.example.joga_door.fragments.RegisterFragment

class MainActivity : AppCompatActivity(), LogInFragment.LogInListener, RegisterFragment.RegisterListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(LogInFragment())
    }

    private fun loadFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.constraintMainActivity.id,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun loadNewFragment(fragment: Fragment) {
        loadFragment(fragment)
    }

    override fun returnToLoginScreen(
        fragment: Fragment,
        user: ArrayList<String>,
        password: ArrayList<String>
    ) {
         val logInFragment = LogInFragment.newInstance(user,password)
        loadFragment(logInFragment)
    }

    private fun goToListView(){
        val intent = Intent(this,ListAndDetailsContainer::class.java)
        startActivity(intent)
    }

/*    override fun sendUserAndPassword(user: ArrayList<String>, password: ArrayList<String>) {
        val LogInFragment = LogInFragment.newInstance(user,password)
    }*/

}