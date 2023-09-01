package com.example.android_applemarket

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.android_applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private var isLike = false

    private val data: Product? by lazy {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.OBJECT_DATA, Product::class.java)
        }
        else {
            intent.getParcelableExtra<Product>(Constants.OBJECT_DATA)
        }
    }

    private val dataPositon: Int by lazy {
        intent.getIntExtra(Constants.INDEX_DATA, 0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.productImageDetailImageView.setImageDrawable(data?.let {
            ResourcesCompat.getDrawable(resources, it.productImage, null)
        })
        binding.nameDetailTextView.text = data?.seller
        binding.addressDetailTextView.text = data?.address
        binding.productTitleDetailTextView.text = data?.productTitle
        binding.productWriteDetailTextView.text = data?.productWrite
        binding.priceDetailTextView.text = getDecimalFormat(data?.price)

        isLike = data?.isLike == true

        binding.likeDetailImageButton.setImageResource(if (isLike == true) {
            R.drawable.ic_detail_heart
        } else {
            R.drawable.ic_main_like
        })

        binding.likeDetailImageButton.setOnClickListener {
            if(!isLike) {
                binding.likeDetailImageButton.setImageResource(R.drawable.ic_detail_heart)
                Snackbar.make(binding.constLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                isLike = true
            }
            else {
                binding.likeDetailImageButton.setImageResource(R.drawable.ic_main_like)
                isLike = false
            }
        }


        binding.backDetailImageButton.setOnClickListener {
            exit()
        }
    }

    fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("dataIndex", dataPositon)
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
    private fun getDecimalFormat(number: Int?): String {
        val deciamlFormat = DecimalFormat("#,###")
        return deciamlFormat.format(number) + "원"
    }

    override fun onBackPressed() {
        exit()
    }
}