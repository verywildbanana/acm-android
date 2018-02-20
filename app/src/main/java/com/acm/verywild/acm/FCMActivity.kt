package com.acm.verywild.acm

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.acm.verywild.acm.data.entities.Message
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_fcm.fcmMessage
import kotlinx.android.synthetic.main.activity_fcm.fcmScribeToTopic
import kotlinx.android.synthetic.main.activity_fcm.fcmSendMessage
import kotlinx.android.synthetic.main.activity_fcm.fcmTitle

class FCMActivity : AppCompatActivity(), View.OnClickListener {

    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null
    private var dataTitle: String? = null
    private var dataMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcm)

        intent.extras?.let {
            for (key in intent.extras.keySet()) {
                when (key) {
                    "title" -> dataTitle = intent.extras.get(key) as String
                    "message" -> dataMessage = intent.extras.get(key) as String
                }
            }

            showAlertDialog()
        }
//        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance()
        myRef = database?.getReference("messages")
        fcmScribeToTopic.setOnClickListener(this)
        fcmSendMessage.setOnClickListener(this)
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Message")
        builder.setMessage("title: $dataTitle\nmessage: $dataMessage")
        builder.setPositiveButton("OK", null)
        builder.show()
    }

    private fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("notifications")
        Toast.makeText(this, "Subscribed to Topic: Notifications", Toast.LENGTH_SHORT).show()
    }

    private fun sendMessage() {
        myRef?.push()?.setValue(Message(fcmTitle.text.toString(), fcmMessage.text.toString()))
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v) {
            fcmScribeToTopic -> subscribeToTopic()
            fcmSendMessage -> sendMessage()
        }
    }
}