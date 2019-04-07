package com.example.estudoandroid.view

import android.app.Activity
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estudoandroid.R
import com.example.estudoandroid.R.layout.*
import com.example.estudoandroid.entities.Word
import com.example.estudoandroid.listeners.OnItemClickListener
import com.example.estudoandroid.listeners.addOnItemClickListener
import com.example.estudoandroid.viewmodel.WordViewModel

import kotlinx.android.synthetic.main.activity_basic.*
import kotlinx.android.synthetic.main.content_basic.*
import org.jetbrains.anko.*

class BasicActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by lazy {
        ViewModelProviders.of(this).get(WordViewModel::class.java)
    }

    companion object {
        private const val NEW_WORD_REQUEST_CODE: Int = 1
    }

    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))
    private lateinit var deleteIcon: Drawable

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.deleteAll -> {

                alert("Do you wish all items?") {
                    title = "Warning"
                    yesButton {
                        wordViewModel.deleteAll()
                    }
                    noButton {
                        longToast("No")
                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_basic)
        setSupportActionBar(toolbar)


        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete)!!

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
//                alert ("Do you wish this item?") {
//                    title = "Warning"
//                    yesButton {
                        wordViewModel.delete(adapter.getItem(viewHolder.adapterPosition))
                        longToast("Word deleted!")
//                    }
//                    noButton {
//                        longToast("No")
//                    }
//                }.show()
            }

            override fun onChildDraw(c: Canvas,
                                     recyclerView: RecyclerView,
                                     viewHolder: RecyclerView.ViewHolder,
                                     dX: Float,
                                     dY: Float,
                                     actionState: Int,
                                     isCurrentlyActive: Boolean) {

                val itemView = viewHolder.itemView
                val iconMargin = (itemView.height - deleteIcon.intrinsicHeight) / 2

                if(dX > 0) {
                    swipeBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                    deleteIcon.setBounds(itemView.left + iconMargin, itemView.top + iconMargin,
                            itemView.left + iconMargin + deleteIcon.intrinsicWidth,
                            itemView.bottom - iconMargin)

                } else {
                    swipeBackground.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                    deleteIcon.setBounds(itemView.right - iconMargin - deleteIcon.intrinsicWidth,
                            itemView.top + iconMargin,
                            itemView.right - iconMargin,
                            itemView.bottom - iconMargin)
                }

                swipeBackground.draw(c)
                deleteIcon.draw(c)
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        wordViewModel.allWords.observe(this, Observer {
            adapter.items = it
        })


        fab.setOnClickListener {
            val intent = Intent(this@BasicActivity, NewWordActivity::class.java)
            startActivityForResult<NewWordActivity>(NEW_WORD_REQUEST_CODE)
        }


        startActivityForResult<NewWordActivity>(NEW_WORD_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_WORD_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                data?.let {
                    val word = Word(it.getStringExtra(NewWordActivity.WORD_KEY))
                    wordViewModel.insert(word)
                }

            } else {
                Snackbar.make(fab, "Word was empty", Snackbar.LENGTH_LONG)
                        .setAction("Retry", object : View.OnClickListener{
                            override fun onClick(v: View?) {
                                startActivityForResult<NewWordActivity>(NEW_WORD_REQUEST_CODE)
                            }
                        }).show()
            }
        }
    }

}