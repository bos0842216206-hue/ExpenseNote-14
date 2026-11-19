package com.example.expensenote_14

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
    private lateinit var etAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnClear: Button
    private lateinit var tvLastItem: TextView
    private lateinit var tvTotal: TextView
    private  var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //เชื่อมโยงตัวแปร เข้ากับ ID ในหน้า XML
        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etNote)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnClear = findViewById<Button>(R.id.btnClear)
        tvLastItem = findViewById<TextView>(R.id.tvLastItem)
        tvTotal = findViewById<TextView>(R.id.tvTotal)

        //กำหนดการทำงานเมื่อผู้ใช้ทำการกดปุ่มเพิ่มรายการ
        btnAdd.setOnClickListener {
            //สร้างตัวแปรที่เอาไว้เก็บข้อมูลจากช่อง
            val  amountText = etAmount.text.toString().trim()
            val  notetext = etNote.text.toString().trim()

            if(amountText.isEmpty()){
                Toast.makeText(this, "กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null){
                //แจ้งเตือนผู้ใช้ให้กรอกตัวเลข ไม่ใช่ตัวหนังสือ
                Toast.makeText(this, "กรุณากรอกเฉพาะตัวเลข ในช่องกรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            total += amount

            //ตรวจสอบว่ามีการกรอกคำอธิบายมั้ย ถ้าไม่มี ให้เป็นคำว่า "ไม่ระบุ"
            val showNote = if (notetext.isNotEmpty()) notetext else "ไม่ระบุ"

            tvLastItem.text = "รายการล่าสุด: $showNote - %.2f บาท".format(amount)
            tvTotal.text = "ยอดรวมวันนี้: %.2f บาท".format(total)

            etAmount.text.clear()
            etAmount.text.clear()
        }
        btnClear.setOnClickListener {
            total = 0.0

            etAmount.text.clear()
            etAmount.text.clear()

            tvLastItem.text = "รายการล่าสุด: -"
            tvTotal.text = "ยอดรวมวันนี้: 0.00 บาท"
        }
    }
}
