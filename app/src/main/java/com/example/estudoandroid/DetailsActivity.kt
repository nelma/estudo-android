package com.example.estudoandroid

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent

        if (intent != null) {
            val info = intent.getStringExtra("INFO")
            Toast.makeText(this, "info", Toast.LENGTH_LONG).show()
        }

        btSend?.setOnClickListener {
            if (edNome.text.toString().isEmpty() || edSobrenome.text.toString().isEmpty()
                    || edEmail.text.toString().isEmpty() || edPassword.text.toString().isEmpty()) {
                Toast.makeText(this@DetailsActivity, "Todos os campos são obigatórios", Toast.LENGTH_LONG).show()

                if (edNome.text.toString().isEmpty()) edNome.error = "Digite o nome"

                if (edSobrenome.text.toString().isEmpty()) edSobrenome.error = "Digite o sobrenome"
                if (edEmail.text.toString().isEmpty()) edEmail.error = "Digite o email"
                if (edPassword.text.toString().isEmpty()) edPassword.error = "Digite a senha"
            } else {

                if (intent != null) {
                    intent.putExtra("NOME", edNome.text.toString())
                    intent.putExtra("SOBRENOME", edSobrenome.text.toString())
                    intent.putExtra("EMAIL", edEmail.text.toString())
                    intent.putExtra("SENHA", edPassword.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    this@DetailsActivity.finish()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(NOME, edNome.text.toString())
        outState?.putString(SOBRENOME, edSobrenome.text.toString())
        outState?.putString(EMAIL, edEmail.text.toString())
        outState?.putString(PASSWORD, edPassword.text.toString())

        val usuarioParcelable = UsuarioParcelable(edNome.text.toString(), edSobrenome.text.toString())
        outState?.putParcelable("USUARIO_PARCELABLE", usuarioParcelable)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        val nome = savedInstanceState?.getString(NOME)?: ""
        val sobrenome = savedInstanceState?.getString(SOBRENOME)
        val email = savedInstanceState?.getString(EMAIL)
        val password = savedInstanceState?.getString(PASSWORD)

        edNome.setText(nome)
        edSobrenome.setText(sobrenome)
        edEmail.setText(email)
        edPassword.setText(password)

        val usuarioParcelable = savedInstanceState?.getParcelable<UsuarioParcelable>("USUARIO_PARCELABLE")
        if (usuarioParcelable != null) {
            Log.d(javaClass.name, usuarioParcelable.nome)
            Log.d(javaClass.name, usuarioParcelable.sobrenome)
        }
    }

    companion object {

        internal val NOME = "NOME"
        internal val SOBRENOME = "SOBRENOME"
        internal val EMAIL = "EMAIL"
        internal val PASSWORD = "PASSWORD"
    }
}
