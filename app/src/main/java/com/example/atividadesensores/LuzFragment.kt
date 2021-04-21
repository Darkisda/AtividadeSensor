package com.example.atividadesensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.atividadesensores.databinding.FragmentLuzBinding

class LuzFragment : Fragment() {
    private lateinit var binding: FragmentLuzBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var lightSenor: Sensor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_luz, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSenor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        sensorManager.registerListener(lightListener, lightSenor, SensorManager.SENSOR_DELAY_NORMAL)

        return binding.root
    }

    private var lightListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//            TODO("Not yet implemented")
        }

        override fun onSensorChanged(event: SensorEvent?) {
            binding.apply {
                val light = event!!.values[0]

                lightValue.text = light.toString()

                if (light <= 200) {
                    lightIntesifyValue.text = "Escuro"
                } else if (light > 201 && light <= 1800) {
                    lightIntesifyValue.text = "Normal"
                } else if (light > 1801) {
                    lightIntesifyValue.text = "Muito Claro"
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        lightSenor.also { light ->
            sensorManager.registerListener(
                lightListener,
                light,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(lightListener)
    }

}