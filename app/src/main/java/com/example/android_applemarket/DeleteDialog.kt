package com.example.android_applemarket

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import com.example.android_applemarket.databinding.DialogDeleteBinding

class DeleteDialog(private val context: AppCompatActivity, val items: MutableList<Product>, val position: Int, val adapter: MyAdapter) {

    private lateinit var binding: DialogDeleteBinding
    private val dlg = Dialog(context)

    fun show() {
        binding = DialogDeleteBinding.inflate(context.layoutInflater)

        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setContentView(binding.root)
        dlg.setCancelable(false)

        binding.okButton.setOnClickListener {
            items.removeAt(position)
            adapter.notifyDataSetChanged()
            dlg.dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dlg.dismiss()
        }
        dlg.show()
    }
}