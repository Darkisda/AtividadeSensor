package com.example.atividadesensores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.atividadesensores.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.apply {
            buttonProximidade.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_homeFragment_to_proximidadeFragment
                )
            }

            buttonAcelerometro.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_homeFragment_to_acelerometroFragment
                )
            }

            buttonLuz.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_homeFragment_to_luzFragment
                )
            }

            buttonGiroscopico.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_homeFragment_to_giroscopicoFragment
                )
            }
        }

        return binding.root
    }
}