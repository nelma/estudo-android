package com.example.estudoandroid;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;

    private TextView txtNome;
    private TextView txtSobrenome;
    private TextView txtEmail;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String nome = data.getStringExtra("NOME");
        String sobrenome = data.getStringExtra("SOBRENOME");
        String email = data.getStringExtra("EMAIL");

        Toast.makeText(this, "Voltei", Toast.LENGTH_LONG).show();

        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);

        txtNome.setText(nome);
        txtSobrenome.setText(sobrenome);
        txtEmail.setText(email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Inicia uma Activity, enviando uma informação junto
//                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                intent.putExtra("INFO", "MainActivity");
//                startActivity(intent);

                // Inicia uma Activity, esperando uma resposta/resultado
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivityForResult(intent, REQUEST_CODE);


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

}
