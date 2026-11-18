package com.example.expensenote_18

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //สร้างตัวเเปรสำหรับหน้า UL ELemnt
    private lateinit var etAmonut : EditText
    private lateinit var etNote : EditText
    private lateinit var  btnAdd : Button
    private lateinit var  btnclear : Button
    private lateinit var  tvLastItem : TextView
    private lateinit var  tvTotal : TextView

    private var total : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etAmonut = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etNote)
        btnclear = findViewById<Button>(R.id.btnIncome)
        btnAdd = findViewById<Button>(R.id.btnExpense)
        tvTotal = findViewById<TextView>(R.id.tvTotal)
        tvLastItem = findViewById<TextView>(R.id.tvLastItem)

        btnAdd.setOnClickListener {
            //ดึงข้อความ
            val amountText = etAmonut.text.toString().trim()
            val noteText = etNote.text.toString().trim()
            //ตรวจสอบเงื่อนไข
            if (amountText.isEmpty()){
                Toast.makeText(this,"กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val amount = amountText.toDoubleOrNull()
            if (amount == null){
                Toast.makeText(this,"กรุณากรอกจำนวนเป็นตัวเลข", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            total += amount

            //ตรวจสอบว่าช่องคำอธิบายมีการกรอกขัอมูลรึป่าว ถ้าไม่มีให้ใช้คำว่า "ไม่ระบุบ"
            val showNote =if (noteText.isNotEmpty()) noteText else "ไม่ระบุบ"

            tvLastItem.text = "รายการล่าสุด : $showNote - %,.2f".format(amount)
            tvTotal.text = "ยอดคงเหลือ : %,.2f".format(total)

            etAmonut .text.clear()
            etNote.text.clear()
        }

        btnclear.setOnClickListener {
            total = 0.0

            etAmonut.text.clear()
            etNote.text.clear()

            tvLastItem.text = "รายการล่าสุด : - "
            tvTotal.text = "ยอดรวมวันนี้คงเหลือ : 0.00 บาท"
        }
    }
}