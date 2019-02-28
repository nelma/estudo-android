package com.example.estudoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    static final String NOME = "NOME";
    static final String SOBRENOME = "SOBRENOME";
    static final String EMAIL = "EMAIL";
    static final String PASSWORD = "PASSWORD";

    private TextView txtNome;
    private TextView txtSobrenome;
    private TextView txtEmail;
    private TextView txtPassword;
    private Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Intent intent = getIntent();

        if(intent != null) {
            String info = intent.getStringExtra("INFO");
            Toast.makeText(this, "info", Toast.LENGTH_LONG).show();
        }

        txtNome = findViewById(R.id.edNome);
        txtSobrenome = findViewById(R.id.edSobrenome);
        txtEmail = findViewById(R.id.edEmail);
        txtPassword = findViewById(R.id.edPassword);
        btSend = findViewById(R.id.btSend);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(txtNome.getText().toString().isEmpty() || txtSobrenome.getText().toString().isEmpty()
                        || txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(DetailsActivity.this, "Todos os campos são obigatórios", Toast.LENGTH_LONG).show();
                } else {

                    if (intent != null) {
                        intent.putExtra("NOME", txtNome.getText().toString());
                        intent.putExtra("SOBRENOME", txtSobrenome.getText().toString());
                        intent.putExtra("EMAIL", txtEmail.getText().toString());
                        intent.putExtra("SENHA", txtPassword.getText().toString());
                        setResult(RESULT_OK, intent);
                        DetailsActivity.this.finish();
                    }
                }
            }
        });

//        textView = findViewById(R.id.textView);
//        editText = findViewById(R.id.editText);
//        btApplyChanges = findViewById(R.id.btApplyChanges);
//        btKeepValue = findViewById(R.id.btKeepValue);
//        btResetValue = findViewById(R.id.btResetValue);
//
//        btApplyChanges.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = editText.getText().toString();
//                textView.setText((text));
//
//                //Definindp resultado
//                if(intent != null) {
//                    intent.putExtra("RESULTADO", text);
//                    setResult(RESULT_OK, intent);
//                    DetailsActivity.this.finish();
//                }
//            }
//        });
//
//        btKeepValue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                persistText = editText.getText().toString();
//                Toast.makeText(DetailsActivity.this, "Persisted Value: " + persistText, Toast.LENGTH_LONG).show();
//            }
//        });
//
//        btResetValue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                persistText = null;
//                Toast.makeText(DetailsActivity.this, "Value reseted", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(NOME, txtNome.getText().toString());
        outState.putString(SOBRENOME, txtSobrenome.getText().toString());
        outState.putString(EMAIL, txtEmail.getText().toString());
        outState.putString(PASSWORD, txtPassword.getText().toString());

        UsuarioParcelable usuarioParcelable =
                new UsuarioParcelable(txtNome.getText().toString(), txtSobrenome.getText().toString());
        outState.putParcelable("USUARIO_PARCELABLE", usuarioParcelable);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String nome = savedInstanceState.getString(NOME);
        String sobrenome = savedInstanceState.getString(SOBRENOME);
        String email = savedInstanceState.getString(EMAIL);
        String password = savedInstanceState.getString(PASSWORD);


        txtNome.setText(nome);
        txtSobrenome.setText(sobrenome);
        txtEmail.setText(email);
        txtPassword.setText(password);

        UsuarioParcelable usuarioParcelable = savedInstanceState.getParcelable("USUARIO_PARCELABLE");
        if (usuarioParcelable != null) {
            Log.d(getClass().getName(), usuarioParcelable.getNome());
            Log.d(getClass().getName(), usuarioParcelable.getSobrenome());
        }
    }
}
