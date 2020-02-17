package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 이전에 입력한 값을 읽어오기
        loadData()

        btnResult.setOnClickListener {
            if (TextUtils.isEmpty(edtWeight.text) ||
                TextUtils.isEmpty(edtHeight.text)) {
                toast("값을 입력해 주세요 please")
                return@setOnClickListener
            }

            // 마지막에 입력한 내용을 저장
            saveData(edtHeight.text.toString().toInt(),
                edtWeight.text.toString().toInt())

            startActivity<ResultActivity>(
                "height" to edtHeight.text.toString(),
                "weight" to edtWeight.text.toString()
            )
        }
    }

    private fun saveData(height: Int, weight: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if (height != 0 && weight != 0) {
            edtHeight.setText(height.toString())
            edtWeight.setText(weight.toString())
        }
    }
}
