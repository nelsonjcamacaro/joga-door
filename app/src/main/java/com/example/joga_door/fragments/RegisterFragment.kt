package com.example.joga_door.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
//import com.example.joga_door.LogInFragment
import com.example.joga_door.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var usersCreated = arrayListOf<String>()
    private var passwordsCreated = arrayListOf<String>()
    private var listener: RegisterListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as RegisterListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        binding.btnRegister.setOnClickListener { setUserData()}
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clearPasswords(){
        binding.etRegisterPassword.setText("")
        binding.etRegisterRepeatPassword.setText("")
        binding.etRegisterPassword.requestFocus()
    }

    private fun setUserData(){
        val userName = binding.etRegisterUser.text.toString()
        val newPassword = binding.etRegisterPassword.text.toString()
        val newRepeatPassword = binding.etRegisterRepeatPassword.text.toString()
        if (userName=="" || newPassword == "" || binding.etRegisterEmail.text.toString() == ""){
            Toast.makeText(context,"el usuario, la contraseña y el email no pueden ser vacios, son obligatorios",Toast.LENGTH_LONG)
                .show()
        }else{
            if (!usersCreated.contains(userName) && newPassword == newRepeatPassword && binding.checkBox.isChecked){
                usersCreated.add(userName)
                passwordsCreated.add(newPassword)
                Toast.makeText(context,"usuario creado exitosamente, Ya puedes iniciar sesion!!!",Toast.LENGTH_LONG).show()
                listener!!.returnToLoginScreen(LogInFragment(),usersCreated,passwordsCreated)

            }else if(!usersCreated.contains(userName) && newPassword == newRepeatPassword && !binding.checkBox.isChecked){
                Toast.makeText(context,"Acepte Terminos y condiciones para continuar",Toast.LENGTH_LONG).show()
                binding.checkBox.requestFocus()
            }else if (!usersCreated.contains(userName) && newPassword != newRepeatPassword){
                Toast.makeText(context,"Contraseñas no coinciden",Toast.LENGTH_LONG).show()
                clearPasswords()
            }else if (usersCreated.contains(userName)){
                Toast.makeText(context,"Este nombre de usuario ya existe",Toast.LENGTH_LONG).show()
                binding.etRegisterUser.setText("")
                binding.etRegisterUser.requestFocus()
            }
        }
    }

    interface RegisterListener{
        fun returnToLoginScreen(fragment:Fragment,user:ArrayList<String>,password:ArrayList<String>)
    }
}
