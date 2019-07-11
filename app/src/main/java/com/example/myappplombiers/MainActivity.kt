package com.example.myappplombiers

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview_plombiers);
        recyclerView!!.setLayoutManager(LinearLayoutManager(this));


        floating_button_add.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@MainActivity, AddPlombierActivity::class.java)
                startActivity(intent)
            }
        })

        getPlombiers()

    }


    private fun getPlombiers() {
        class GetPlombiers : AsyncTask<Void, Void, List<Plombier>>() {

            override fun doInBackground(vararg voids: Void): List<Plombier> {
                return DatabaseClient
                    .getInstance(applicationContext)
                    .appDatabase
                    .plombierDao()
                    .getAll()
            }

            override fun onPostExecute(Plombiers: List<Plombier>) {
                super.onPostExecute(Plombiers)
                val adapter = PlombierAdapter(this@MainActivity, Plombiers)
                recyclerView!!.adapter = adapter
            }
        }

        val gt = GetPlombiers()
        gt.execute()
    }
}
