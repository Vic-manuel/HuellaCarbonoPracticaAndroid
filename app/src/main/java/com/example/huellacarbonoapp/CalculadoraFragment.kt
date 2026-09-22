package com.example.huellacarbonoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalculadoraFragmente.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalculadoraFragmente : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculadora, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etCombustible = view.findViewById<EditText>(R.id.etCombustible)

        val etElectricidad = view.findViewById<EditText>(R.id.etElectricidad)

        val btnCalcular = view.findViewById<Button>(R.id.btnCalcular)

        val btnRegresarInicio = view.findViewById<Button>(R.id.btnRegresarInicio)

        btnCalcular.setOnClickListener {


            val combustibleTexto =
                etCombustible.text.toString().trim()

            val electricidadTexto =
                etElectricidad.text.toString().trim()


            if (combustibleTexto.isEmpty()) {

                etCombustible.error =
                    "Ingresa el consumo de combustible"

                etCombustible.requestFocus()

                return@setOnClickListener
            }

            if (electricidadTexto.isEmpty()) {

                etElectricidad.error =
                    "Ingresa el consumo de electricidad"

                etElectricidad.requestFocus()

                return@setOnClickListener
            }


            val combustible =
                combustibleTexto.toDouble()

            val electricidad =
                electricidadTexto.toDouble()


            val emisionesCombustible =
                combustible * 2.31

            val emisionesElectricidad =
                electricidad * 0.40


            val totalCO2 =
                emisionesCombustible + emisionesElectricidad


            val action =
                CalculadoraFragmenteDirections.actionCalculadoraFragmentToResultadoFragment(
                        totalCO2.toFloat()
                    )

            findNavController().navigate(action)

            btnRegresarInicio.setOnClickListener {
                findNavController().navigate(
                    R.id.action_calculadoraFragment_to_inicioFragment
                )
            }

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalculadoraFragmente.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalculadoraFragmente().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}