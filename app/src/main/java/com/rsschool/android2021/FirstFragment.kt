package com.rsschool.android2021

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception

class FirstFragment : Fragment() {

    private lateinit var secondFragmentCommunicate: SecondFragmentCommunicate
    private lateinit var firstFragmentCommunicate: FirstFragmentCommunicate
    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        firstFragmentCommunicate = context as FirstFragmentCommunicate
        secondFragmentCommunicate = context as SecondFragmentCommunicate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val min = view.findViewById<EditText>(R.id.min_value)
        val max = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            try {
                if (max.text.toString().toInt() <= min.text.toString().toInt()) {
                    secondFragmentCommunicate.openFF(previousResult?.text.toString().toInt())
                    Toast.makeText(requireContext(), "Введите корректные данные", Toast.LENGTH_LONG).show()
                }
                firstFragmentCommunicate.openSF(
                    min.text.toString().toInt(),
                    max.text.toString().toInt()
                )
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Введите корректные данные", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}