package com.example.core2
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    var galleryBlock: ArrayList<GalleryBlock> = ArrayList<GalleryBlock>();
    var col:Int = 0
    var row:Int = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        Log.i("Core2-Main","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null) // Save the data from last time changing.
        {
            galleryBlock = ArrayList<GalleryBlock>(savedInstanceState.getParcelableArrayList("galleryBlock"))
        }
        populateGalley() // Populate the default value.
        updatedGallery() // Change the value of each block.
        bindOnClickListener() // Bind the click listener on each image.
    }
    override fun onSaveInstanceState(outState: Bundle)
    {
        Log.i("Core2-Main","onSaveInstanceState")
        super.onSaveInstanceState(outState)
        with(outState)
        {
            putParcelableArrayList("galleryBlock", galleryBlock)
        }
    }
    fun populateGalley() // Populate the default value.
    {
        Log.i("Core2-Main","populateGallery")
        // Default value of first image
        galleryBlock.add(
            GalleryBlock(
                name = "St.Michael's Cathedral,Qingdao",
                location = "Qingdao",
                date = "09/26/2021",
                rating = 4.5
            )
        )
        // Default value of second image
        galleryBlock.add(
            GalleryBlock(
                name = "May Fourth Square",
                location = "Qingdao",
                date = "09/26/2021",
                rating = 4.5
            )
        )
        // Default value of third image
        galleryBlock.add(
            GalleryBlock(
                name = "Mount Lao",
                location = "Qingdao",
                date = "09/26/2021",
                rating = 4.5
            )
        )
        // Default value of fourth image
        galleryBlock.add(
            GalleryBlock(
                name = "The Eight Great Passes",
                location = "Qingdao",
                date = "09/26/2021",
                rating = 4.5
            )
        )
    }
    fun updatedGallery() // Change the name and rating in the main activity.
    {
        Log.i("Core2-Main","updatedGallery")
        // Name and rating for first image
        var name1:TextView = findViewById(R.id.name1)
        name1.setText(
            galleryBlock[0].name
        )
        var rating1:TextView = findViewById(R.id.grade1)
        rating1.setText(
            galleryBlock[0].rating.toString()
        )
        // Name and rating for second image
        var name2:TextView = findViewById(R.id.name2)
        name2.setText(
            galleryBlock[1].name
        )
        var rating2:TextView = findViewById(R.id.grade2)
        rating2.setText(
            galleryBlock[1].rating.toString()
        )
        // Name and rating for third image
        var name3:TextView = findViewById(R.id.name3)
        name3.setText(
            galleryBlock[2].name
        )
        var rating3:TextView = findViewById(R.id.grade3)
        rating3.setText(
            galleryBlock[2].rating.toString()
        )
        // Name and rating for fourth image
        var name4:TextView = findViewById(R.id.name4)
        name4.setText(
            galleryBlock[3].name
        )
        var rating4:TextView = findViewById(R.id.grade4)
        rating4.setText(
            galleryBlock[3].rating.toString()
        )
        findViewById<TextView>(R.id.name1).text=galleryBlock[0].name
        findViewById<ImageView>(R.id.image1).setImageDrawable(getDrawable(R.drawable.image01))
        findViewById<ImageView>(R.id.image2).setImageDrawable(getDrawable(R.drawable.image02))
        findViewById<ImageView>(R.id.image3).setImageDrawable(getDrawable(R.drawable.image03))
        findViewById<ImageView>(R.id.image4).setImageDrawable(getDrawable(R.drawable.image04))
    }
    fun bindOnClickListener()
    {
        Log.i("Core2-Main","bindOnClickListener")
        // Bind the click listener on first image.
        var image1:ImageView = findViewById(R.id.image1)
        setOnClick(image1,0,0)
        // Bind the click listener on second image.
        var image2:ImageView = findViewById(R.id.image2)
        setOnClick(image2,0,1)
        // Bind the click listener on third image.
        var image3:ImageView = findViewById(R.id.image3)
        setOnClick(image3,1,0)
        // Bind the click listener on fourth image.
        var image4:ImageView = findViewById(R.id.image4)
        setOnClick(image4,1,1)
    }
    fun setOnClick(view:View,row:Int = 0,col:Int = 0)
    {
        view.setOnClickListener()
        {
            Log.i("Core2-Main","setOnClick")
            val intent = Intent(this,SecondaryActivity::class.java).apply{
                putExtra("galleryBlock",galleryBlock[row * 2 + col])
                putExtra("ItemRow",row)
                putExtra("ItemCol",col)
            }
            startActivityForResult(intent,1)
        }
    }
    override fun onActivityResult(requestCode:Int,resultCode:Int,data:Intent?)
    {
        super.onActivityResult(requestCode,resultCode,data)
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                val row:Int? = data?.getIntExtra("return_ItemRow",0)
                val col:Int? = data?.getIntExtra("return_ItemCol",0)
                Toast.makeText(this, "item(row: $row, col: $col) was updated", Toast.LENGTH_LONG).show()
                if(row != null)
                {
                    if(col != null)
                    {
                        galleryBlock[row * 2 + col] = data.getParcelableExtra("return_galleryBlock")!!
                    }
                }
                updatedGallery()
            }
        }
    }
}