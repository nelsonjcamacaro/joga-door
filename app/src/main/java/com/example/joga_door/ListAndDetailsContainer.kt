package com.example.joga_door

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.joga_door.databinding.ActivityListAndDetailsContainerBinding
import com.example.joga_door.fragments.ListViewFragment

class ListAndDetailsContainer : AppCompatActivity() {
    lateinit var binding : ActivityListAndDetailsContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAndDetailsContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.recyclerView.layoutManager =
        loadRecyclerFragment(ListViewFragment())

    }

    private fun loadRecyclerFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.listAndDetailsContanier.id,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}