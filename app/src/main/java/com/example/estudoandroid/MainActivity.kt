package com.example.estudoandroid

import android.content.Intent
import android.os.Bundle

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var txtNome: TextView? = null
    private var txtSobrenome: TextView? = null
    private var txtEmail: TextView? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val nome = data?.getStringExtra("NOME")?: ""
        val sobrenome = data?.getStringExtra("SOBRENOME")?: ""
        val email = data?.getStringExtra("EMAIL")?: ""

        Toast.makeText(this, "Voltei", Toast.LENGTH_LONG).show()

        txtNome = findViewById(R.id.txtNome)
        txtSobrenome = findViewById(R.id.txtSobrenome)
        txtEmail = findViewById(R.id.txtEmail)

        txtNome?.text = nome
        txtSobrenome?.text = sobrenome
        txtEmail?.text = email
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    companion object {

        internal val REQUEST_CODE = 1
    }

}
