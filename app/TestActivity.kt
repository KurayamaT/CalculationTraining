package net.minpro.calculationtraining

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*
import net.minpro.calculationtraining.R.layout.activity_test
import java.text.NumberFormat
import java.util.*
import kotlin.concurrent.schedule


class TestActivity : AppCompatActivity(), View.OnClickListener {
    var numberOfQuestion: Int = 0
    var numberOfRemaining: Int = 0
    var numberOfCorrect: Int = 0
    lateinit var soundPool: SoundPool
    var intSoundId_Correct: Int = 0
    var intSoundId_inCorrect: Int = 0
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_test)

        val bundle = intent.extras!!
        numberOfQuestion = bundle.getInt("numberOfQuestion")
        textViewRemaining.text = numberOfQuestion.toString()

        numberOfRemaining = numberOfQuestion
        numberOfCorrect = 0

        Button_answerCheck.setOnClickListener {
            if (answer.text.toString() != null && answer.text.toString() != "-") {
                answerCheck()
            }

        }

        button_turnBack.setOnClickListener {
            finish()
        }

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


        override fun onResume() {
            super.onResume()

            soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                SoundPool.Builder().setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                    .setMaxStreams(1)
                    .build()
            } else {
                SoundPool(1, AudioManager.STREAM_MUSIC, 0)
            }

            intSoundId_Correct = soundPool.load(this, R.raw.sound_correct, 1)
            intSoundId_inCorrect = soundPool.load(this, R.raw.sound_incorrect, 1)

            timer = Timer()

        }

        override fun onPause() {
            super.onPause()
            soundPool.release()
            timer.cancel()
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

        }

        private fun answerCheck() {

            button_turnBack.isEnabled = false

            Button_answerCheck.isEnabled = false
            button9.isEnabled = false
            button8.isEnabled = false
            button7.isEnabled = false
            button6.isEnabled = false
            button5.isEnabled = false
            button4.isEnabled = false
            button3.isEnabled = false
            button2.isEnabled = false
            button1.isEnabled = false
            button0.isEnabled = false
            buttonMinus.isEnabled = false
            buttonClear.isEnabled = false

            numberOfRemaining -= 1
            textViewRemaining.text = numberOfRemaining.toString()

            imageView.visibility = View.VISIBLE

            val intMyAnswer: Int = answer.text.toString().toInt()
            val intRealAnswer: Int =
                if (operater.text == "+") {
                    left.text.toString().toInt() + right.text.toString().toInt()
                } else {
                    left.text.toString().toInt() - right.text.toString().toInt()
                }

            val i = if (intMyAnswer == intRealAnswer) {
                numberOfCorrect += 1
                textViewCorrext.text = numberOfCorrect.toString()
                imageView.setImageResource(R.drawable.pic_correct)
                soundPool.play(intSoundId_Correct, 1.0f, 1.0f, 0, 0, 1.0f)
            } else {
                imageView.setImageResource(R.drawable.pic_incorrect)
                soundPool.play(intSoundId_inCorrect, 1.0f, 1.0f, 0, 0, 1.0f)

            }


            val intPoint:Int = ((numberOfCorrect.toDouble() / (numberOfQuestion - numberOfRemaining).toDouble()) * 100).toInt()
            textViewPoint.text = intPoint.toString()


            if (numberOfRemaining == 0) {
                button_turnBack.isEnabled = true
                Button_answerCheck.isEnabled = false
                textViewMessage.text = "completed!"
            } else {
                timer.schedule(1000, { runOnUiThread { question() } })
            }
        }

        override fun onClick(v: View?) {

            val button: Button = v as Button

            when (v.id) {

                R.id.buttonClear
                -> answer.text = ""

                R.id.buttonMinus
                -> if (answer.text.toString() == "")
                    answer.text = "-"
                R.id.button0
                -> if (answer.text.toString() != "0" && answer.text.toString() != "-")
                    answer.append(button.text)


                else
                -> if (answer.text.toString() == "0") {
                    answer.text = button.text
                } else {
                    answer.append(button.text)
                    val numberFormat = NumberFormat.getNumberInstance()
                    val parsedNumber = numberFormat.parse(answer.text.toString())
                    val formatNumber = numberFormat.format(parsedNumber)
                    answer.text = formatNumber

                }

            }
        }
    }

