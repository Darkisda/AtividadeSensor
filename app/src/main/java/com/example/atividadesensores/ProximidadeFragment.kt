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
import com.example.atividadesensores.databinding.FragmentProximidadeBinding

class ProximidadeFragment : Fragment() {
    private lateinit var binding: FragmentProximidadeBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proximidade, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorManager.registerListener(proximityListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)

        return binding.root
    }

    private var proximityListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            Log.i("DISTANCIA", event!!.values[0].toString())

            binding.apply {
                sensorValor.text = event.values[0].toString()

                if(event.values[0] <= 1.5 ) {
                    distanciaValor.text = "Perto"
                } else {
                    distanciaValor.text = "Longe"
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()

        proximitySensor.also {
            proximity -> sensorManager.registerListener(proximityListener, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(proximityListener)
    }

}