package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // 전달받은 키와 몸무게
        val height = intent?.getStringExtra("height")?.toInt() ?: 0
        val weight = intent?.getStringExtra("weight")?.toInt() ?: 0

        // BMI 계산
        val bmi = weight/Math.pow(height/100.0,2.0)

        // 결과 표시
        when{
            bmi >= 35 -> tvResult.text = "고도 비만"
            bmi >= 30 -> tvResult.text = "2단계 비만"
            bmi >= 25 -> tvResult.text = "1단계 비만"
            bmi >= 23 -> tvResult.text = "과체중"
            bmi >= 18.5 -> tvResult.text = "정상"
            else -> tvResult.text = "저체중"
        }

        // 이미지 표시
        when{
            bmi >= 23 ->
                imgResult.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >= 18.5 ->
                imgResult.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
            else ->
                imgResult.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }
        toast("$bmi")
    }
}
