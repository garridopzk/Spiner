package com.example.spiner;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_THEME = "pref_theme";
    private Spinner spinner;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (!prefs.contains(PREF_THEME)) {
            prefs.edit().putInt(PREF_THEME, R.style.AppTheme).apply();
        }

        int themeId = prefs.getInt(PREF_THEME, R.style.AppTheme);
        setTheme(themeId);

        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.Spinnertemas);
        EditText userEditText = findViewById(R.id.user);
        EditText passwordEditText = findViewById(R.id.Contraseña);
        Button iniciarButton = findViewById(R.id.Iniciar);

        final String[] temas = {
                "Selecciona un tema", "Tema1", "Tema2", "Tema3"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int newThemeId;
                switch (position) {
                    case 0:
                        newThemeId = R.style.AppTheme;
                        break;
                    case 1:
                        newThemeId = R.style.Tema1;
                        break;
                    case 2:
                        newThemeId = R.style.Tema2;
                        break;
                    case 3:
                        newThemeId = R.style.Tema3;
                        break;
                    default:
                        return;
                }

                int currentThemeId = prefs.getInt(PREF_THEME, R.style.AppTheme);
                if (currentThemeId != newThemeId) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt(PREF_THEME, newThemeId);
                    editor.apply();

                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        iniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Toast.makeText(MainActivity.this, "Usuario: " + username + "\nContraseña: " + password, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
