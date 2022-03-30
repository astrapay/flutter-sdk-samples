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

                AstraPayQris.setup("XTOKEN", "SIT")
                val astraPayAuth = AstraPayQrisAuth(
                    context = this,
                    authorizationToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJzdWIiOiIwODc3ODU0NTEyODkiLCJyb2xlcyI6WyJMT0dJTiJdLCJpc3MiOiJBc3RyYVBheS1EZXYiLCJ0eXBlIjoiQUNDRVNTIiwidXNlcklkIjo1ODEsImRldmljZUlkIjoiMzFhNzMzMDYtYWU5MS00ZjZiLTliOTgtYjc3YzZiYjM1YWI5IiwidHJhbnNhY3Rpb25JZCI6IiIsInRyYW5zYWN0aW9uVHlwZSI6IiIsIm5iZiI6MTY0MDgzOTQzNiwiZXhwIjoxNjQwODQzMDM2LCJpYXQiOjE2NDA4Mzk0MzYsImp0aSI6IjE5ZWVlMjk4LWVkZWQtNDc0Zi1hMTU5LTdlMmJhYjhlNWMwMiIsImVtYWlsIjpbImphc29ubmF0aGFuYWVsMThAZ21haWwuY29tIl19.bx6B3DTOJvsg4O2TQ18wL_RxVBjzWCR38GEQX5YfywXj-sAKQyRHTxpKjh8GnuZ_uyxmklFjeyxdyN-qNOifzHcyQye82Gzu0-kn6uNL52WbJoA3_1XA6KUwk6lexbl80BJqXeQ8R9-slOm9YXotCl6ekqIJLfjaZF9R7gpwTAHphHrOhY5hXOtAVMDr3GOUWRv-1XdkQK4yXPqkN1_Ohjmw3JSz5CFilmgf5ACdqAteZXEig9gvWBBV12eWgjfnJhlL2Gr4_PaJ9ip8qbM1LNLz2cMG2CaEwhIpNiYwyzqsVv4gfQIn9fKuB7vLGSduhFAtVsK_8LLFKYsngOi2eg",
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

    override fun onProcessing() {
        Toast.makeText(this, "processing", Toast.LENGTH_SHORT).show()
    }

    override fun onShowHistory() {
        Toast.makeText(this, "on show history", Toast.LENGTH_SHORT).show()
    }
}
