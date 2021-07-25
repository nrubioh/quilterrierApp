 package com.example.quilterrier

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnotherImage.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnotherImage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)


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
        val rootView = inflater.inflate(R.layout.fragment_another_image, container, false) as View

        val nombre = requireArguments().getString("nombre")
        val nombreLayout = rootView.findViewById(R.id.nombreDetalles) as TextView
        nombreLayout.setText(nombre)

        val ubicacion = requireArguments().getString("ubicacion")
        val ubicacionLayout = rootView.findViewById(R.id.ubicacionDetalles) as TextView
        ubicacionLayout.setText(ubicacion)

        val imagenGrande = requireArguments().getInt("imagen")
        val imagen = rootView.findViewById(R.id.fotoGrande) as ImageView
        imagen.setImageResource(imagenGrande)

        return  rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cerrar = view.findViewById(R.id.close) as ImageButton

        cerrar.setOnClickListener { view ->
            Log.d("btnSetup", "Selected")
            val activity=view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().remove(this).commit();

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnotherImage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AnotherImage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}