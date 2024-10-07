package com.example.examenmovil.framework.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examenmovil.R
import com.example.examenmovil.framework.views.fragments.CharacterListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa el Fragmento de la lista de personajes si aún no ha sido agregado
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CharacterListFragment())
                .commit()
        }
    }
}
