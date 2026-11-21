package com.example.androcalcul


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre1 = findViewById<EditText>(R.id.etNombre1)
        val etNombre2 = findViewById<EditText>(R.id.etNombre2)

        val btnAddition = findViewById<Button>(R.id.btnAddition)
        val btnSoustraction = findViewById<Button>(R.id.btnSoustraction)
        val btnMultiplication = findViewById<Button>(R.id.btnMultiplication)
        val btnDivision = findViewById<Button>(R.id.btnDivision)

        val tvResultat = findViewById<TextView>(R.id.tvResultat)

        val btnPuissance = findViewById<Button>(R.id.btnPuissance)
        btnPuissance.setOnClickListener {
            effectuerCalcul(etNombre1, etNombre2, tvResultat, "^")
        }
        // Consigne 4 : Brancher un écouteur sur chaque bouton
        btnAddition.setOnClickListener {
            effectuerCalcul(etNombre1, etNombre2, tvResultat, "+")
        }

        btnSoustraction.setOnClickListener {
            effectuerCalcul(etNombre1, etNombre2, tvResultat, "-")
        }

        btnMultiplication.setOnClickListener {
            effectuerCalcul(etNombre1, etNombre2, tvResultat, "*")
        }

        btnDivision.setOnClickListener {
            effectuerCalcul(etNombre1, etNombre2, tvResultat, "/")
        }
    }

    // Consignes 5 et 6 : Lire les valeurs, calculer, gérer le zéro avec Toast, afficher
    private fun effectuerCalcul(
        etNombre1: EditText,
        etNombre2: EditText,
        tvResultat: TextView,
        operation: String
    ) {
        // 1. Récupération des valeurs textuelles
        val texte1 = etNombre1.text.toString().trim()
        val texte2 = etNombre2.text.toString().trim()

        // 2. Vérification que les champs ne sont pas vides
        val n1 = texte1.toDoubleOrNull()
        val n2 = texte2.toDoubleOrNull()

        if (n1 == null || n2 == null) {
            Toast.makeText(this, "Veuillez entrer deux nombres valides", Toast.LENGTH_SHORT).show()
            return
        }

        // 3. Calcul sécurisé
        val resultat: Double = when (operation) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> {
                // SÉCURITÉ CRUCIALE : on intercepte avant de calculer
                if (n2 == 0.0) {
                    Toast.makeText(
                        this,
                        "Erreur : division par zéro impossible !",
                        Toast.LENGTH_LONG
                    ).show()
                    tvResultat.text = "Erreur : division par zéro"
                    return // ON SORT IMMÉDIATEMENT POUR NE PAS PLANTER
                }
                n1 / n2
            }
            "^" -> Math.pow(n1, n2)
            else -> 0.0
        }

        // 4. Affichage du résultat si tout s'est bien passé
        tvResultat.text = "Résultat : $resultat"
    }
}