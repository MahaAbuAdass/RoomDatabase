package com.example.roomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.data.User
import com.example.roomdatabase.data.UserViewModel


class AddFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.findViewById<Button>(R.id.btn_add).setOnClickListener {
            insertDataToDatabase()
        }

        return view


    }

    private fun insertDataToDatabase() {
        val firstName = view?.findViewById<EditText>(R.id.first_name)?.text.toString()
        val lastName = view?.findViewById<EditText>(R.id.last_name)?.text.toString()
        val age = view?.findViewById<EditText>(R.id.age)?.text

        Log.v("nameee" , firstName + lastName + age)

        if (age?.let { inputCheck(firstName,lastName, it) } == true) {
            // create user object
            val user = User(0,firstName,lastName, Integer.parseInt(age.toString()))
            // add date to database
            mUserViewModel.addUser(user)

            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(firstName: String, lastName: String , age:Editable) : Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}