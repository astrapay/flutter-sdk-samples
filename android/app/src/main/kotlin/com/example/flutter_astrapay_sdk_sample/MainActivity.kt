package com.example.flutter_astrapay_sdk_sample

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BasicMessageChannel.MessageHandler
import io.flutter.plugin.common.StringCodec
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterMain
import io.flutter.view.FlutterView

import java.util.*
import android.widget.TextView
import androidx.annotation.NonNull
import android.widget.Toast

import com.astrapay.qrissdk.helper.AstraPayQris
import com.astrapay.qrissdk.helper.AstraPayQrisListener
import com.astrapay.qrissdk.helper.EventType
import com.astrapay.qrissdk.helper.data.AstraPayQrisAuth

class MainActivity: FlutterActivity(), AstraPayQrisListener {
    val CHANNEL = "channel_qris"
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
            if (call.method == "startQris") { 
                //println("---> startQris called")

                AstraPayQris.setup("SDK_TOKEN", "UAT")
                val astraPayAuth = AstraPayQrisAuth(
                    context = this,
                    authorizationToken = "AUTH_TOKEN",
                    listener = this
                )

                AstraPayQris.execute(
                    astraPayQrisAuth = astraPayAuth
                )

            } else{
                result.notImplemented()
            }
        }
    }


    //On Transaction Complete
    override fun onComplete(type: EventType) {
        Toast.makeText(this, "Transaction Complete", Toast.LENGTH_SHORT).show()
    }

    //On Transaction Failed
    override fun onFailed() {
        Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show()
    }

    //On User token forbidden
    override fun onForbidden() {
        Toast.makeText(this, "user access forbidden", Toast.LENGTH_SHORT).show()
    }

    //On User Cancel Transaction
    override fun onCancel() {
        Toast.makeText(this, "User cancel the transaction", Toast.LENGTH_SHORT).show()
    }
}
