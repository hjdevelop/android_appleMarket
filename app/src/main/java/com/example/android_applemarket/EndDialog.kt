package com.example.android_applemarket

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.android_applemarket.databinding.DialogEndBinding
import kotlin.system.exitProcess

class EndDialog(private val context: AppCompatActivity) {

    private lateinit var binding: DialogEndBinding
    private val dlg = Dialog(context)

    fun show() {
        binding = DialogEndBinding.inflate(context.layoutInflater)

        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(binding.root)
        dlg.setCancelable(false)

        binding.okButton.setOnClickListener {
            exitProcess(0)
        }

        binding.cancelButton.setOnClickListener {
            dlg.dismiss()
        }
        dlg.show()
    }
}