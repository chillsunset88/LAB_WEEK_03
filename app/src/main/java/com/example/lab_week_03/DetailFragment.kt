package com.example.lab_week_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Button
import com.example.lab_week_03.ListFragment.Companion.COFFEE_ID


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {

    private var param1: String? = null

    private lateinit var coffeeTitle: TextView
    private lateinit var coffeeDesc: TextView

    private var coffeeIdToDisplay: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        coffeeTitle = view.findViewById(R.id.coffee_title)
        coffeeDesc  = view.findViewById(R.id.coffee_desc)

        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListFragment())
                .commit()
        }

        val coffeeId = arguments?.getInt(COFFEE_ID, 0) ?: 0
        displayCoffeeData(coffeeId)
    }



    fun displayCoffeeData(id: Int) {
        when (id) {
            R.id.affogato -> {
                coffeeTitle.text = getString(R.string.affogato_title)
                coffeeDesc.text  = getString(R.string.affogato_desc)
            }
            R.id.americano -> {
                coffeeTitle.text = getString(R.string.americano_title)
                coffeeDesc.text  = getString(R.string.americano_desc)
            }
            R.id.latte -> {
                coffeeTitle.text = getString(R.string.latte_title)
                coffeeDesc.text  = getString(R.string.latte_desc)
            }
            R.id.espresso -> {
                coffeeTitle.text = getString(R.string.espresso_title)
                coffeeDesc.text  = getString(R.string.espresso_desc)
            }
            R.id.mocha -> {
                coffeeTitle.text = getString(R.string.mocha_title)
                coffeeDesc.text  = getString(R.string.mocha_desc)
            }
        }
    }

    fun setCoffeeData(id: Int) {
        if (::coffeeTitle.isInitialized && ::coffeeDesc.isInitialized) {
            displayCoffeeData(id)
        } else {
            coffeeIdToDisplay = id
        }
    }

    companion object {
        private const val COFFEE_ID = "COFFEE_ID"
        fun newInstance(coffeeId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(COFFEE_ID, coffeeId)
                }
            }
    }
}
