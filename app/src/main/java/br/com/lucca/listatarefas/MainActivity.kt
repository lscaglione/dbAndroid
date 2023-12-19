package br.com.lucca.listatarefas

import DatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nomeEditText = findViewById<EditText>(R.id.nome)
        val idadeEditText = findViewById<EditText>(R.id.idade)
        val timeEditText = findViewById<EditText>(R.id.time)
        val emailEditText = findViewById<EditText>(R.id.email)
        val confirmButton = findViewById<Button>(R.id.botaoConfirma)
        val changeButton = findViewById<Button>(R.id.botaoMudaTela)

        confirmButton.setOnClickListener {
            val nome = nomeEditText.text.toString()
            val time = timeEditText.text.toString()
            val email = emailEditText.text.toString()
            val idadeString = idadeEditText.text.toString()

            try {
                val idade = idadeString.toInt()

                val dbHelper = DatabaseHelper(this)
                dbHelper.addData(nome, idade, time, email)

                Toast.makeText(this, "Dados inseridos com sucesso!", Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Por favor, insira uma idade válida.", Toast.LENGTH_SHORT).show()
            }


        }

        changeButton.setOnClickListener {
            val intent = Intent(this, VisualizarDados::class.java)
            startActivity(intent)
        }


    }
}