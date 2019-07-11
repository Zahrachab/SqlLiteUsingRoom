package com.example.myappplombiers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.AsyncTask
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_plombier.*

class AddPlombierActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.myappplombiers.R.layout.activity_add_plombier)
        button_save.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        val numVal = numero!!.text.toString().trim { it <= ' ' }
        val nomVal = nom!!.text.toString().trim { it <= ' ' }
        val typeVal = type!!.text.toString().trim { it <= ' ' }

        if (numVal.isEmpty()) {
            numero!!.error = "Task required"
            numero!!.requestFocus()
            return
        }

        if (nomVal.isEmpty()) {
            nom!!.error = "Desc required"
            nom!!.requestFocus()
            return
        }

        if (typeVal.isEmpty()) {
            type!!.error = "Finish by required"
            type!!.requestFocus()
            return
        }

        class SavePlombier : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {

                //creating a task
                val plombier = Plombier(numVal, nomVal, typeVal)

                //adding to database
                DatabaseClient.getInstance(applicationContext).appDatabase
                    .plombierDao()
                    .insert(plombier)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
            }
        }

        val st = SavePlombier()
        st.execute()
    }

}