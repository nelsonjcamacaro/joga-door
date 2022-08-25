package com.example.joga_door.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.joga_door.ListAndDetailsContainer
import com.example.joga_door.databinding.FragmentLogInBinding
import com.example.joga_door.fragments.RegisterFragment


private const val USERCREATED= "COM.EXAMPLE.JOGADOOR.USER"
private const val PASSWORDCREATED="COM.EXAMPLE.JOGADOOR.PASSWORD"

class LogInFragment : Fragment() {

    private var _binding : FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private var listener:LogInListener? = null

    private var user1:ArrayList<String>? = null //?: "none") as ArrayList<String>?
    private var  password1:ArrayList<String>? = null //?: "none") as ArrayList<String>?


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as LogInListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user1 = it.getStringArrayList(USERCREATED)
            password1 = it.getStringArrayList(PASSWORDCREATED)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogInBinding.inflate(inflater,container,false)
        binding.tvRegister.setOnClickListener { listener!!.loadNewFragment(RegisterFragment()) }
        binding.btnLogIn.setOnClickListener { logInOk() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface LogInListener{
        fun loadNewFragment(fragment: Fragment)
    }


    companion object{
        @JvmStatic
        fun newInstance(theNewUser:ArrayList<String>,pass:ArrayList<String>) = LogInFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(USERCREATED,theNewUser)
                putStringArrayList(PASSWORDCREATED,pass)
            }
        }
    }

    private fun logInOk(){
        if (user1 == null || password1 == null){
            Toast.makeText(context,"Primero debe registrarse",Toast.LENGTH_SHORT).show()
        } else {
            if (!user1!!.contains(binding.etUser.text.toString())) {
                Toast.makeText(
                    context,
                    "Usuario no existente, por favor registrese si no lo ha hecho",
                    Toast.LENGTH_LONG
                ).show()
                binding.etUser.requestFocus()
            } else if (user1!!.contains(binding.etUser.text.toString()) && !password1!!.contains(
                    binding.etPassword.text.toString()
                )
            ) {
                Toast.makeText(context, "contraseña invalida intente de nuevo", Toast.LENGTH_LONG)
                    .show()

            } else {
                Toast.makeText(context, "Datos correctos", Toast.LENGTH_LONG).show()
                goToListView()
            }
        }
    }

    private fun goToListView(){
        val intent = Intent(this@LogInFragment.requireContext(), ListAndDetailsContainer::class.java)
        startActivity(intent)
    }

}