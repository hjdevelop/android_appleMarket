package com.example.android_applemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.android_applemarket.databinding.ActivityDetailBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = getIntent()
        val data: Product? = intent.getParcelableExtra("productData")

        binding.productImageDetailImageView.setImageDrawable(data?.let {
            ResourcesCompat.getDrawable(resources, it.productImage, null)
        })
        binding.nameDetailTextView.text = data?.seller
        binding.addressDetailTextView.text = data?.address
        binding.productTitleDetailTextView.text = data?.productTitle
        binding.productWriteDetailTextView.text = data?.productWrite
        binding.priceDetailTextView.text = getDecimalFormat(data?.price)

        binding.backDetailImageButton.setOnClickListener {
            finish()
        }
    }
    private fun getDecimalFormat(number: Int?): String {
        val deciamlFormat = DecimalFormat("#,###")
        return deciamlFormat.format(number) + "원"
    }
}