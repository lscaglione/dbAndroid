import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE minha_tabela (id INTEGER PRIMARY KEY, nome TEXT, idade INTEGER, time TEXT, email TEXT)"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS minha_tabela")
        onCreate(db)
    }

    fun addData(nome: String, idade: Int, time: String, email: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nome", nome)
        values.put("idade", idade)
        values.put("time", time)
        values.put("email", email)

        db.insert("minha_tabela", null, values)
        db.close()
    }

    fun getAllData(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM minha_tabela", null)
    }

    companion object {
        const val DATABASE_NAME = "meu_banco_de_dados"
        const val DATABASE_VERSION = 1
    }
}
