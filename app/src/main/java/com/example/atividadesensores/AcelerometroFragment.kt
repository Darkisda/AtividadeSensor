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
import com.example.atividadesensores.databinding.FragmentAcelerometroBinding

class AcelerometroFragment : Fragment() {
    private lateinit var binding: FragmentAcelerometroBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_acelerometro, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)

        return binding.root
    }

    private var accelerometerListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//            TODO("Not yet implemented")
        }

        override fun onSensorChanged(event: SensorEvent?) {

            binding.apply {
                axisXValue.text = event!!.values[0].toString()
                axisYValue.text = event.values[1].toString()
                axisZValue.text = event.values[2].toString()
            }
        }

    }

    override fun onResume() {
        super.onResume()

        accelerometerSensor.also { accelerometer ->
            sensorManager.registerListener(
                accelerometerListener,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(accelerometerListener)
    }

}