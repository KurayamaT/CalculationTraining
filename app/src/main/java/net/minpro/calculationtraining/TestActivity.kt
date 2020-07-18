package net.minpro.calculationtraining

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*
import net.minpro.calculationtraining.R.layout.activity_test
import java.util.*


@Suppress("UNREACHABLE_CODE")
class TestActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_test)

        val bundle = intent.extras
        val numberOfQuestion = bundle?.getInt("numberOfQuestion")
        textRemining.text = numberOfQuestion.toString()

        Button_answerCheck.setOnClickListener {

            answerCheck()
        }

        button_turnBack.setOnClickListener { }

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


    private fun question() {

        button_turnBack.isEnabled = false

        Button_answerCheck.isEnabled = true
        button9.isEnabled = true
        button8.isEnabled = true
        button7.isEnabled = true
        button6.isEnabled = true
        button5.isEnabled = true
        button4.isEnabled = true
        button3.isEnabled = true
        button2.isEnabled = true
        button1.isEnabled = true
        button0.isEnabled = true
        buttonMinus.isEnabled = true
        buttonClear.isEnabled = true

        val random = Random()
        val intQuestionLeft = random.nextInt(100) + 1
        val intQuestionRight = random.nextInt(100) + 1
        left.text = intQuestionLeft.toString()
        right.text = intQuestionRight.toString()

        when (random.nextInt(2) + 1) {
            1 -> operater.text = "+"
            2 -> operater.text = "-"
        }

        answer.text = ""

        imageView.visibility = View.INVISIBLE


        TODO("Not yet implemented")
    }

    private fun answerCheck() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")

        val button: Button = v as Button

        when(v.id){

            R.id.buttonClear
                    -> answer.text = ""

            R.id.buttonMinus
                    -> if (answer.text.toString() == "")
                        answer.text = "-"
            R.id.button0
                    -> if (answer.text.toString() !="0" && answer.text.toString() != "-")
                        answer.append(button.text)

            else
                -> if (answer.text.toString() == "0")
                    answer.text = button.text
                    else answer.append(button.text)

        }
    }
//ちょっとかえたぜ

}