package br.com.lucca.listatarefas

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class VisualizarDados : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visualizar_dados)

        val botaoBack = findViewById<Button>(R.id.botaoVoltar)
        botaoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val dbHelper = DatabaseHelper(this)
        val cursor = dbHelper.getAllData()

        try {
            if (cursor.moveToFirst()) {
                val nomeTextView = findViewById<TextView>(R.id.visualizarNome)
                val idadeTextView = findViewById<TextView>(R.id.visualizarIdade)
                val timeTextView = findViewById<TextView>(R.id.visualizarTime)
                val emailTextView = findViewById<TextView>(R.id.visualizarEmail)

                nomeTextView.text = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
                idadeTextView.text = cursor.getInt(cursor.getColumnIndexOrThrow("idade")).toString()
                timeTextView.text = cursor.getString(cursor.getColumnIndexOrThrow("time"))
                emailTextView.text = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.close()
        }


    }

}
