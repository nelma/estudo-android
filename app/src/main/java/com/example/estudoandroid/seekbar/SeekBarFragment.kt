package com.example.estudoandroid.seekbar


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.estudoandroid.R
import kotlinx.android.synthetic.main.fragment_seek_bar.*


//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class SeekBarFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seek_bar, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            val seekBarViewModel = ViewModelProviders
                    .of(it)
                    .get(SeekBarViewModel::class.java)

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if(fromUser) {
                        seekBarViewModel.seekBarValue.value = progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            seekBarViewModel.seekBarValue.observe(this, Observer{progress ->
                seekBar.progress = progress
            })
        }
    }
}
