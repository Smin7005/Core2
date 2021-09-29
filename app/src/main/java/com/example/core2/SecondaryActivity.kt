package com.example.core2
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
class SecondaryActivity : AppCompatActivity()
{
    var row:Int = 0
    var col:Int = 0
    var galleryBlock:GalleryBlock? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        Log.i("Core2-Secondary","setOnClick")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_block)
        galleryBlock = intent.getParcelableExtra("galleryBlock")
        row = intent.getIntExtra("ItemRow",0)
        col = intent.getIntExtra("ItemCol",0)
        findViewById<EditText>(R.id.name0).setText(galleryBlock?.name)
        findViewById<EditText>(R.id.location0).setText(galleryBlock?.location)
        findViewById<EditText>(R.id.date0).setText(galleryBlock?.date)
        findViewById<RatingBar>(R.id.rating0).rating = (galleryBlock?.rating)?.toFloat()?:0.0f
        if(row == 0 && col == 0)
        {
            findViewById<ImageView>(R.id.image0).setImageDrawable(getDrawable(R.drawable.image01))
        }
        else if(row == 0 && col == 1)
        {
            findViewById<ImageView>(R.id.image0).setImageDrawable(getDrawable(R.drawable.image02))
        }
        else if(row == 1 && col == 0)
        {
            findViewById<ImageView>(R.id.image0).setImageDrawable(getDrawable(R.drawable.image03))
        }
        else if(row == 1 && col == 1)
        {
            findViewById<ImageView>(R.id.image0).setImageDrawable(getDrawable(R.drawable.image04))
        }
    }
    override fun onBackPressed()
    {
        Log.i("Core2-Secondary","OnBackPressed")
        galleryBlock?.name = findViewById<EditText>(R.id.name0).text.toString()
        galleryBlock?.location = findViewById<EditText>(R.id.location0).text.toString()
        galleryBlock?.date = findViewById<EditText>(R.id.date0).text.toString()
        galleryBlock?.rating = findViewById<RatingBar>(R.id.rating0).rating.toDouble()
        val returnIntent = Intent()
        returnIntent.putExtra("return_galleryBlock",galleryBlock)
        returnIntent.putExtra("return_ItemRow",row)
        returnIntent.putExtra("return_ItemCol",col)
        setResult(RESULT_OK,returnIntent)
        finish()
    }
}