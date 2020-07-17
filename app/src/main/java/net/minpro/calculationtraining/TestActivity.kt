package net.minpro.calculationtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val bundle = intent.extras
        val numberOfQuestion = bundle?.getInt("numberOfQuestion")
        textRemining.text = numberOfQuestion.toString()

        Button_answerCheck.setOnClickListener {

            answerCheck()
        }

        button_turnBack.setOnClickListener {  }

        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonClear.setOnClickListener(this)

        question()


    }

    private fun question(){
        TODO("Not yet implemented")
    }

    private fun answerCheck() {
        TODO("Not yet implemented")
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
//ちょっとかえたぜ

}