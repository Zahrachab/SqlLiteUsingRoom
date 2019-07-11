package com.example.myappplombiers

import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog;
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_plombier.*
import kotlinx.android.synthetic.main.activity_update_plombier.*





class UpdatePlombierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_plombier)

        val p = intent.getSerializableExtra("plombier") as Plombier

        loadPlombier(p)

        button_update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Toast.makeText(getApplicationContext(), "Mis à jour", Toast.LENGTH_LONG).show();
                updatePlombier(p);
            }
        });


        button_delete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                deletePlombier(p)
            }
        });
    }

    private fun loadPlombier(p: Plombier) {
        editNom.setText(p.nom);
        editNum.setText(p.numero)
        editType.setText(p.type)
    }


    private fun updatePlombier(p: Plombier) {
        val numVal = editNum!!.text.toString().trim { it <= ' ' }
        val nomVal = editNom!!.text.toString().trim { it <= ' ' }
        val typeVal = editType!!.text.toString().trim { it <= ' ' }

        if (numVal.isEmpty()) {
            numero!!.error = "Plombier required"
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

        class UpdatePlombier : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                p.numero = numVal
                p.nom = nomVal
                p.type = typeVal
                DatabaseClient.getInstance(applicationContext).appDatabase
                    .plombierDao()
                    .update(p)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this@UpdatePlombierActivity, MainActivity::class.java))
            }
        }

        val st = UpdatePlombier()
        st.execute()
    }

    private fun deletePlombier(p: Plombier) {
        class DeletePlombier : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                DatabaseClient.getInstance(applicationContext).appDatabase
                    .plombierDao()
                    .delete(p)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this@UpdatePlombierActivity, MainActivity::class.java))
            }
        }

        val dt = DeletePlombier()
        dt.execute()

    }


}
