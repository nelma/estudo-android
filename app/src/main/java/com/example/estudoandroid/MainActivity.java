package com.example.estudoandroid;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText editText = findViewById(R.id.editText);
        editText.setError("Digite dado válido");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editText.setError("Digite algo...");
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(
                R.drawable.ic_java,
                "Android",
                1996,
                "Java Descriptios"
        );

        List<ProgrammingLanguage> programmingLanguages = new ArrayList<>();
        programmingLanguages.add(programmingLanguage);
        programmingLanguages.add(programmingLanguage);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvTitle = v.findViewById(R.id.tvTitle);
                Toast.makeText(MainActivity.this, "Cliquei no card", Toast.LENGTH_SHORT).show();
            }
        };

        RecyclerView.Adapter adapter =
                new ProgrammingLanguageAdapter(programmingLanguages, listener);

        recyclerView.setAdapter(adapter);

    }
}
