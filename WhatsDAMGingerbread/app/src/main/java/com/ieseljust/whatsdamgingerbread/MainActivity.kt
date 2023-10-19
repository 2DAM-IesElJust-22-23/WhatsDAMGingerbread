package com.ieseljust.whatsdamgingerbread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.InetAddresses.isNumericAddress
import com.ieseljust.whatsdamgingerbread.databinding.ActivityMainBinding
import com.ieseljust.whatsdamgingerbread.databinding.ActivityMessagesWindowBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /**
         * Guardar el texto introducido en el nickname y serverAddress
         * Comprobamos que el nickname y que la IP son válidas
         **/

        val intent = Intent(baseContext, MessagesWidow::class.java)
        var nickName = binding.nickNameText.text.toString()
        var ip = binding.serverAddressText.text.toString()
        if (nickName != "") {
            intent.putExtra("nickname", nickName)
            if (isNumericAddress(ip)) {
                intent.putExtra("Ip", ip)
                startActivity(intent)
            }
        }
    }
}