package com.example.sharedpreferance
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private  lateinit var  name :TextInputEditText
 private   var count:Int =0
private lateinit var  message:TextInputEditText
    private  lateinit var  button: Button
    private  lateinit var  checkBox: CheckBox
  private var newCount: Int = count
 private var newName: String? = null
  private var newMessage: String? = null
  private var newCheckbox: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.name_id)
message = findViewById(R.id.message_id)
checkBox = findViewById(R.id.checkbox_id)

        button.setOnClickListener {
            count++
            button.text = "$count"
        }

        checkBox.setOnClickListener {
            checkBox.isChecked = true
        }

    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("CommitPrefEdits")
    fun saveData () {
        val sharedPreferences =
            this.getSharedPreferences("Savedata", Context.MODE_PRIVATE)
        newCount = count
         newName  =name.text.toString()
         newMessage = message.text.toString()
         newCheckbox = checkBox.isChecked

        val editor = sharedPreferences.edit()
        editor.putBoolean("newCheckbox", newCheckbox)
        editor.putInt("newCount", newCount)
        editor.putString("newName", newName)
        editor.putString("newMessage", newMessage)
        Toast.makeText(applicationContext, "Data is saved. !!", Toast.LENGTH_SHORT).show()
        editor.apply()
    }
    private fun getData (){
        val sharedPreferences =
            this.getSharedPreferences("getData", Context.MODE_PRIVATE)
        newName  = sharedPreferences.getString("newName",null)
        newMessage = sharedPreferences.getString("newMessage",null)
        newCheckbox = sharedPreferences.getBoolean("newCheckbox",false)
newCount = sharedPreferences.getInt("newCount",0)
    }
}