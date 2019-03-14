package com.example.estudoandroid

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLoadImage.setOnClickListener{
            loadImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode === SELECT_PICTURE) {
            if(resultCode == RESULT_OK) {

                if(data?.extras != null) {
                    val bitmap = data.extras?.get("data") as Bitmap
                    ivMain.setImageBitmap(bitmap)
                } else if (data?.data != null) {
                    Picasso.get().load(data.data).into(ivMain)
                }

            }
        }
    }


    private fun loadImage() {
        //Intent sao controladas pelo Android.
        //ACTION_GET_CONTENT abre os docs
        val pickIntent = Intent(Intent.ACTION_GET_CONTENT)

        //nos mais novos nao tem
        pickIntent.type = "image/*"


        val pickGalleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val chooserIntent = Intent.createChooser(pickIntent, "Select or take a image")
        chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                arrayOf(pickGalleryIntent, takePictureIntent))

        //Para evitar erro - ActivityNotFoundException
        if(chooserIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(chooserIntent, SELECT_PICTURE)
        }
    }




    companion object {
        private const val SELECT_PICTURE = 1
    }
}
