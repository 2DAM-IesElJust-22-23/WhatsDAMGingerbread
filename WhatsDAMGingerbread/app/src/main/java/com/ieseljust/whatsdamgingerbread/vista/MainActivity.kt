package com.ieseljust.whatsdamgingerbread.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.InetAddresses.isNumericAddress
import com.ieseljust.whatsdamgingerbread.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val nickName = binding.nickNameText.text.toString()
        val intets = Intent(baseContext, MessagesWindow::class.java)
        val ip =  binding.serverAddressText.text.toString()

       if(nickName !=""){
           intent.putExtra("nickName", nickName)
           if (isNumericAddress("ip, "+ ip))
               startActivity(intent)
       }
    }
}