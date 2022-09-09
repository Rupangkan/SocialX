package com.example.quantumapp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.quantumapp.common.Constants.DOCUMENT_PATH
import com.example.quantumapp.common.Constants.USER_COLLECTION
import com.example.quantumapp.databinding.FragmentSignInBinding
import com.example.quantumapp.viewmodels.AppViewModel
import com.example.quantumapp.views.activities.ListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: DocumentReference
    private lateinit var hashMap: HashMap<String, String>
    private val appViewModel: AppViewModel by activityViewModels()
    private val TAG = "ReposeSignInFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance().collection(USER_COLLECTION)
            .document(DOCUMENT_PATH)
        hashMap = hashMapOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnClickListener {
            signUpToFirebase()
        }

        binding.signInText.setOnClickListener {
            Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun signUpToFirebase() {
        val email = binding.editEmailSignUp.text.toString()
        val name = binding.editNameSignUp.text.toString()
        val phone = binding.editNumberSignUp.text.toString()
        val password = binding.editPassSignUp.text.toString()
        val checkboxSelected = binding.checkBox.isChecked

        appViewModel.validateForm(name, phone, checkboxSelected)
        Log.d(TAG, "signUpToFirebase: $checkboxSelected")

        if(appViewModel.errorMessage.value == null) {
            createNewUser(email, password, name, phone)
        } else {
            Toast.makeText(context, appViewModel.errorMessage.value, Toast.LENGTH_SHORT).show()
        }
    }

    private fun createNewUser(email: String, password: String, name: String, phone: String) {
        binding.progressBar.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    saveDataToFireStore(name, phone, email)
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        binding.progressBar.visibility = View.GONE
    }

    private fun saveDataToFireStore(name: String, phone: String, email: String) {
        hashMap["Name"] = name
        hashMap["Phone"] = phone
        hashMap["Email"] = email
        firestore.set(hashMap)
            .addOnFailureListener {
                Toast.makeText(context, "User Creation Failed!", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            }
            .addOnSuccessListener {
                Toast.makeText(context, "User Created!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null) {
            val intent = Intent(context, ListActivity::class.java)
            startActivity(intent)
        }
    }

}