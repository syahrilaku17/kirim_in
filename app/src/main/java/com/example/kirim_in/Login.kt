package com.example.kirim_in

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.kirim_in.Frgament.HomeFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class Login :  AppCompatActivity() {

    //  deklarasi untuk request kode
    private val RC_SIGN_IN = 7
    //  deklarasi untuk sign in client
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    //  deklarasi unutk firebase auth
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        tv_register.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(getString(
            R.string.default_web_client_id
        ))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        btn_google.setOnClickListener {
            SignIn()
        }
    }

    private fun SignIn(){
        val signInClient = mGoogleSignInClient.signInIntent
        startActivityForResult(signInClient, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(
        acct: GoogleSignInAccount
    ){
        Log.d("LOGIN", "firebaseAuth" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken,null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this){
                task ->

            if (task.isSuccessful){
                Log.d("Login", "Sign Is Success")
                val user = mAuth.currentUser
                updateUI(user)
            }else{
                Log.w("Login", "Sign is Error",
                    task.exception)
                Toast.makeText(this, "Sign im Failur", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }
    fun updateUI(user : FirebaseUser?){
        if (user != null){
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Selamat datang ${user.displayName}", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            }catch (e: ApiException){
                Log.w("LOGIN", "Login failed", e)
            }
        }
    }

}