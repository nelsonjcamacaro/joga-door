package com.example.joga_door.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joga_door.datas.PlayersAdapter
import com.example.joga_door.databinding.FragmentListViewBinding
import com.example.joga_door.datas.Players
import com.example.joga_door.datas.PlayersProvider


class ListViewFragment : Fragment() {
    private var _binding : FragmentListViewBinding? = null
    private val binding get() = _binding!!
    private val playersProvider = PlayersProvider.playersList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListViewBinding.inflate(inflater,container,false)
        startRecyclerView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemSelected(player:Players){
        Toast.makeText(context,player.name,Toast.LENGTH_SHORT).show()
    }

    fun startRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = PlayersAdapter(playersProvider){player ->
            onItemSelected(player)}
    }
}