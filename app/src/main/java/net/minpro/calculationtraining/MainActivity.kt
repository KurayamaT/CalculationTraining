package net.minpro.calculationtraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val arrayAdapter = ArrayAdapter.createFromResource(this, R.array.number_of_question, android.R.layout.simple_spinner_item)

        spinner.adapter= arrayAdapter

        button.setOnClickListener {
            val numberOfQuestion = spinner.selectedItem.toString().toInt()

            val intent = Intent(this@MainActivity, TestActivity::class.java)
            intent.putExtra("numberOfQuestion", numberOfQuestion)


        }
    }
}