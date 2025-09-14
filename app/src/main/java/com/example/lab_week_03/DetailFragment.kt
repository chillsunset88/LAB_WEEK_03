package com.example.lab_week_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

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
        coffeeDesc = view.findViewById(R.id.coffee_desc)


        coffeeIdToDisplay?.let {
            displayCoffeeData(it)
        }
    }

    // Ubah nama fungsi agar lebih jelas, dan buat private jika hanya digunakan di dalam kelas ini
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

            // Tambahkan else case jika ID tidak dikenali
        }
    }

    // Fungsi publik untuk dipanggil dari luar Fragment
    fun setCoffeeData(id: Int) {
        // Jika view sudah siap, langsung tampilkan data
        if (::coffeeTitle.isInitialized && ::coffeeDesc.isInitialized) {
            displayCoffeeData(id)
        } else {
            // Jika view belum siap, simpan ID untuk ditampilkan nanti di onViewCreated
            coffeeIdToDisplay = id
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String /*, param2: String */) = // Hapus param2 jika tidak digunakan
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    // putString(ARG_PARAM2, param2) // Jika tidak digunakan, bisa dihapus
                }
            }
    }
}
